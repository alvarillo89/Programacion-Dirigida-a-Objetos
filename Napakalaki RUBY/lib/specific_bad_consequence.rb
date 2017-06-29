# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
class SpecificBadConsequence < BadConsequence
  def initialize(t,l,v,h)
    super(t,l)
    @specific_visible_treasures = v
    @specific_hidden_treasures = h
  end
  
  def getSpecificVisibleTreasures()
    @specific_visible_treasures
  end
  
  def getSpecificHiddenTreasures()
    @specific_hidden_treasures
  end
  
  def isEmpty()
    if(@specific_visible_treasures.empty? && @specific_hidden_treasures.empty?)
      return true
    end
    
    return false
  end
  
  def substractVisibleTreasure(t)
    if (!(@specific_visible_treasures.empty?))
        @specific_visible_treasures.delete(t.getType())
    end
  end
  
  def substractHiddenTreasure(t)
    if (!(@specific_hidden_treasures.empty?))
        @specific_hidden_treasures.delete(t.getType())
    end
  end
  
  def adjustToFitTreasureLists(v, h)
    vt = Array.new
    vh = Array.new
    
    v.each do |t|
        if(@specific_visible_treasures.index(t.getType()) != nil)
          vt << t.getType()
        end
    end
      
    h.each do |t|
      if(@specific_hidden_treasures.index(t.getType()) != nil)
        vh << t.getType()
      end
    end
    
    bc = SpecificBadConsequence.new(getText(), getLevels(), vt, vh)
    
    return bc
  end
  
  def to_s
    return "\n " + getText() + "\n Pierdes " + getLevels().to_s + " niveles \n Specific visible treasures: #{@specific_visible_treasures} \n Specific hidden treasures: #{@specific_hidden_treasures}\n"
  end
end
end