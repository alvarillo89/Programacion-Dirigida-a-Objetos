#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "napakalaki.rb"

module NapakalakiGame
  class Player
  @@MAXLEVEL = 10
  
  def initialize(n,l,e,ht,vt,pbc)
    @name = n
    @level = l
    @dead = true
    @can_i_steal = true
    @enemy = e
    @hidden_treasures = ht
    @visible_treasures = vt 
    @pending_bad_consequence = pbc
  end
  
  def copyPlayer(p)
    @name = p.getName()
    @level = p.getLevels()
    @dead = p.isDead()
    @can_i_steal = p.canISteal()
    @enemy = p.getEnemy()
    @hidden_treasures = p.getHiddenTreasures() 
    @visible_treasures = p.getVisibleTreasures()
    @pending_bad_consequence = p.getPendingBadConsequence()
  end
  
  def self.newPlayer(n)
      new(n,1,nil,Array.new,Array.new,nil)
  end
  
  def self.getMaxLevel()
    return @@MAXLEVEL
  end
  
  private
   
    def bringToLife()
        @dead = false
    end

    def incrementLevels(l)
      @level = @level + l
    end

    def decrementLevels(l)
      if((@level-l)> 0)
        @level = @level - l
      else
        @level = 1
      end
    end

    def setPendingBadConsequence(b)
      @pending_bad_consequence = b
        
    end

    def applyPrize(m)
        nLevels = m.getLevelsGained()
        incrementLevels(nLevels)
        nTreasures = m.getTreasuresGained()
        
        if(nTreasures > 0)
          dealer = CardDealer.instance
          
          for i in 1..nTreasures
            treasure = dealer.nextTreasure()
            @hidden_treasures << treasure
          end
        end
    end

    def applyBadConsequence(m)
        badConsequence = m.getBadConsequence()
        nLevels = badConsequence.getLevels()
        decrementLevels(nLevels)
        pendingBad = badConsequence.adjustToFitTreasureLists(@visible_treasures, @hidden_treasures)
        setPendingBadConsequence(pendingBad)
    
    end

    def canMakeTreasureVisible(t)
      sePuede = true
      
      if(t.getType() == TreasureKind::ONEHAND)
        if(howManyVisibleTreasures(t.getType()) > 1 || howManyVisibleTreasures(TreasureKind::BOTHHANDS) > 0)
          sePuede = false
        end
      elsif (t.getType() == TreasureKind::BOTHHANDS)
        if(howManyVisibleTreasures(t.getType()) > 0 || howManyVisibleTreasures(TreasureKind::ONEHAND) > 0)
          sePuede = false
        end
      elsif (howManyVisibleTreasures(t.getType()) > 0)
        sePuede = false
      end
      
      return sePuede
    end

    def howManyVisibleTreasures(tKind)
      nVisibleTreasures = 0
      
      @visible_treasures.each do |t|
        if(t.getType() == tKind)
          nVisibleTreasures = nVisibleTreasures + 1
        end
      end
      
      return nVisibleTreasures
    end

    def dieIfNoTreasures()
      if(@visible_treasures.empty? && @hidden_treasures.empty?)
        @dead = true
      end
    end

    def haveStolen()
      @can_i_steal = false
    end
  
  protected
      
    def getOponentLevel(m)
      m.getCombatLevel()
    end
    
    def shouldConvert();
      convert = false
      
      dice = Dice.instance
      number = dice.nextNumber()
      
      if(number == 6)
        convert = true
      end
      
      return convert
    end
    
    def getCombatLevel()
      nivel_total = @level
      @visible_treasures.each {|v| 
        nivel_total = nivel_total + v.getBonus()
      }    
   
      return nivel_total
    end
    
  public
    
    def getEnemy()
      @enemy
    end
    
    
    def canYouGiveMeATreasure()
      if(@hidden_treasures.size > 0)
        return true
      end
        
      return false
    end
    
    def giveMeATreasure()
      numeroAleatorio = rand(0..(@hidden_treasures.size))
      t = @hidden_treasures[numeroAleatorio]
      @hidden_treasures.delete(t)
      return t
    end
    
    def getName()
      @name
    end
    
    def isDead()
      @dead
    end
    
    def getHiddenTreasures()
      @hidden_treasures
    end
    
    def getLevels()
      @level
    end
    
    def canISteal()
      @can_i_steal
    end
    
    def getVisibleTreasures()
      @visible_treasures
    end
    
    def getPendingBadConsequence()
      @pending_bad_consequence
    end

    def combat(m)
       myLevel = getCombatLevel()
       monsterLevel = getOponentLevel(m)
       
      if(!canISteal())
         dice = Dice.instance
         number = dice.nextNumber()
         
        if(number < 3)
          enemyLevel = @enemy.getCombatLevel()
          monsterLevel = monsterLevel + enemyLevel
        end
         
      end
     
      if (myLevel > monsterLevel)
        applyPrize(m)
        if(@level >= @@MAXLEVEL)
          combatResult = CombatResult::WINGAME
        else
          combatResult = CombatResult::WIN
        end
        
      else
        applyBadConsequence(m)
        combatResult = CombatResult::LOSE
        if(shouldConvert())
          combatResult = CombatResult::LOSEANDCONVERT
        end
      end
      
      return combatResult  
    end

    def makeTreasureVisible(t)
        canI = canMakeTreasureVisible(t)
        if(canI)
          @visible_treasures << t
          @hidden_treasures.delete(t)
        end
    end

    def discardVisibleTreasure(t)
        @visible_treasures.delete(t)
        if(@pending_bad_consequence != nil && !(@pending_bad_consequence.isEmpty()))
          @pending_bad_consequence.substractVisibleTreasure(t)
        end
        
        dieIfNoTreasures()
    end

    def discardHiddenTreasure(t)
        @hidden_treasures.delete(t)
        if(@pending_bad_consequence != nil && !(@pending_bad_consequence.isEmpty()))
          @pending_bad_consequence.substractHiddenTreasure(t)
        end
        
        dieIfNoTreasures()
    end

    def validState()
      if(@pending_bad_consequence != nil)
        if(@pending_bad_consequence.isEmpty() && @hidden_treasures.size <= 4)
          return true
        end
      elsif (@hidden_treasures.size <= 4)
        return true
      end
        
      return false 
    end

    def initTreasures()
      dealer = CardDealer.instance
      dice = Dice.instance
      bringToLife()
      treasure = dealer.nextTreasure()
      @hidden_treasures << treasure
      number = dice.nextNumber()
      
      if (number > 1)
        treasure = dealer.nextTreasure()
        @hidden_treasures << treasure
      end
      
      if(number == 6)
        treasure = dealer.nextTreasure()
        @hidden_treasures << treasure
      end
    end

    def stealTreasure()
        treasure = nil
        canI = canISteal()
        if(canI)
          canYou = @enemy.canYouGiveMeATreasure()
          if(canYou)
            treasure = @enemy.giveMeATreasure()
            @hidden_treasures << treasure
            haveStolen()
          end
        end
        
        return treasure
    end

    def setEnemy(enemy)
      @enemy = enemy
    end

    def discardAllTreasures()
      vt = Array.new(@visible_treasures)        
      
      vt.each do |t|
        discardVisibleTreasure(t)
      end
      
      ht = Array.new(@hidden_treasures)
      
      ht.each do |t|
        discardHiddenTreasure(t)
      end
      
    end
    
    def to_s
      s = "#{@name} (Nivel: #{@level} Combat Level: " + getCombatLevel().to_s + " Enemigo: " + @enemy.getName() + ")"
      if(@pending_bad_consequence != nil)
        s = s + "\n Pending Bad Consequence = " + @pending_bad_consequence.to_s
      end
      
      return s
    end
end
end


