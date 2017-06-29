# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "napakalaki.rb"

module NapakalakiGame
class CultistPlayer < Player
  @@totalCultistPlayer = 0
  
  def initialize(p, c)
    super(p.getName(),1,nil,Array.new,Array.new,nil)
    copyPlayer(p)
    @myCultistCard = c
    @@totalCultistPlayer = @@totalCultistPlayer + 1
  end
  
  def self.getTotalCultistPlayers()
    @@totalCultistPlayer
  end
  
  private
  def giveMeATreasure()
    numeroAleatorio = rand(0..(Player.getVisibleTreasures().size))
    t = Player.getVisibleTreasures()[numeroAleatorio]
    Player.discardVisibleTreasure(t)
    return t
  end
  
  def canYouGiveMeATreasure()
    if(Player.getVisibleTreasures().size > 0)
      return true
    end
        
    return false
  end
  
  protected
    
  def getCombatLevel()
    level = super
    level = level + 0.7*level
    level = level + @myCultistCard.getGainedLevels()*@@totalCultistPlayer
    level = level.to_i
    
    return level
  end
  
  def getOponentLevel(m)
    m.getCombatLevelAgainstCultistPlayer()
  end
  
  def shouldConvert()
    return false
  end
end
end
