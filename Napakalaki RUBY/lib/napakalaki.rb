#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require "singleton"
require_relative "monster.rb"
require_relative "card_dealer.rb"
require_relative "player.rb"
require_relative "bad_consequence.rb"
require_relative "combat_result.rb"
require_relative "dice.rb"
require_relative "treasure.rb"
require_relative "treasure_kind.rb"
require_relative "prize.rb"
require_relative "cultist.rb"
require_relative "cultist_player.rb"
require_relative "specific_bad_consequence.rb"
require_relative "numeric_bad_consequence.rb"
require_relative "death_bad_consequence.rb"

module NapakalakiGame
  
  class Napakalaki
  
  include Singleton
  
  private
    
    def initPlayers(names)
      
      @players = Array.new
      
      names.each do |nombre|
        @players << Player.newPlayer(nombre)
      end
    end
    
    def nextPlayer()
      
      if(@currentPlayer == nil)
        numeroAleatorio = rand(0..(@players.size-1))
        indice = numeroAleatorio
        
      elsif (@currentPlayer == @players[@players.size-1])
        indice = 0
      
      else
        indice = @players.index(@currentPlayer) + 1;
      end
    
      nextPlayer = @players[indice]
      @currentPlayer = nextPlayer
      
      return nextPlayer
    end
    
    def nextTurnAllowed()
      allow = false
      
      if(@currentPlayer == nil)
        allow = true
      elsif (@currentPlayer.validState())
        allow = true
      end
      
      return allow
    end
    
    def setEnemies()
      
      @players.each do |p|
        numeroAleatorio = rand(0..(@players.size - 1))
        enemy = @players[numeroAleatorio]
        while(enemy == p)
          numeroAleatorio = rand(0..(@players.size - 1))
          enemy = @players[numeroAleatorio]
        end
        
        p.setEnemy(enemy)
      end
      
    end
    
  public
    
    def getCurrentPlayer()
      @currentPlayer
    end
    
    def getCurrentMonster()
      @currentMonster
    end
    
    def developCombat()
      combatResult = @currentPlayer.combat(@currentMonster)
      @dealer.giveMonsterBack(@currentMonster)
      
      if combatResult == CombatResult::LOSEANDCONVERT
        cultist = @dealer.nextCultist()
        
        cultistPlayer = CultistPlayer.new(@currentPlayer, cultist)
        
        @players.each do |p|
          if(p.getEnemy() == @currentPlayer)
            p.setEnemy(cultistPlayer)
          end
        end
        
        indice = @players.index(@currentPlayer);
        @players.delete_at(indice)
        @players.insert(indice, cultistPlayer)
        
        @currentPlayer = cultistPlayer
      end
      
      return combatResult
    end
    
    def discardVisibleTreasures(treasures)
      treasures.each do |treasure|
        @currentPlayer.discardVisibleTreasure(treasure)
        @dealer.giveTreasureBack(treasure)
      end
    end
    
    def discardHiddenTreasures(treasures)
      treasures.each do |treasure|
        @currentPlayer.discardHiddenTreasure(treasure)
        @dealer.giveTreasureBack(treasure)
      end
    end
    
    def makeTreasuresVisible(treasures)
      treasures.each do |t|
        @currentPlayer.makeTreasureVisible(t)
      end
    end
    
    def initGame(players)
      @dealer = CardDealer.instance
      initPlayers(players)
      setEnemies()
      @dealer.initCards()
      nextTurn()
    end
    
    def nextTurn()
      stateOK = nextTurnAllowed()
      
      if(stateOK)
        @currentMonster = @dealer.nextMonster()
        @currentPlayer = nextPlayer()
        
        dead = @currentPlayer.isDead()
        
        if(dead)
          @currentPlayer.initTreasures()
        end
      end
      
      return stateOK
    end
    
    def endOfGame(result)
      terminado = false
      
      if (result == CombatResult::WINGAME)
        terminado = true
      end
      
      return terminado
    end

end
end


