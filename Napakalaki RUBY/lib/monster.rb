#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  class Monster
  
  def initialize(n, l, bc, p, lc = 0)
    @name = n
    @combatLevel = l
    @badConsequence = bc
    @prize = p
    @levelChangeAgainstCultistPlayer = lc
  end
  
  def getName()
    @name
  end
  
  def getCombatLevel()
    @combatLevel
  end
  
  def getBadConsequence()
    @badConsequence
  end
  
  def getPrize()
    @prize
  end
  
  def getLevelsGained()
    @prize.getLevel()
  end
  
  def getTreasuresGained()
    @prize.getTreasures()
  end
  
  def getCombatLevelAgainstCultistPlayer()
    @combatLevel + @levelChangeAgainstCultistPlayer
  end
  
  def to_s
    return "Nombre: #{@name} \n Nivel: #{@combatLevel} \n BadConsequence: #{@badConsequence.to_s}\n Prize: #{@prize.to_s}\n Bonus sectarios: #{@levelChangeAgainstCultistPlayer}\n"
  end
  
end
end


