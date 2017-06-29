#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module NapakalakiGame
  class Prize
  
  def initialize(t, l)
    @treasures = t
    @levels = l
  end
  
  def getLevel()
    @levels
  end
  
  def getTreasures()
    @treasures
  end
  
  def to_s
    return "\n Tesoros: #{@treasures} \n Niveles ganados: #{@levels}\n"
  end
end
end


