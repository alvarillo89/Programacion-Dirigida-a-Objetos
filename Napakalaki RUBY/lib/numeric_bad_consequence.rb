# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
class NumericBadConsequence < BadConsequence
  def initialize(t, l, nv, nh)
    super(t, l)
    @n_visible_treasures = nv
    @n_hidden_treasures = nh
  end
  
  def getVisibleTreasures()
    @n_visible_treasures
  end
  
  def getHiddenTreasures()
    @n_hidden_treasures
  end
  
  def isEmpty()
    if(@n_visible_treasures == 0 && @n_hidden_treasures == 0)
      return true
    end
    
    return false
  end
  
  def substractVisibleTreasure(t)
    if(@n_visible_treasures != 0)
        @n_visible_treasures = @n_visible_treasures - 1;
    end
  end
  
  def substractHiddenTreasure(t)
    if(@n_hidden_treasures != 0)
        @n_hidden_treasures = @n_hidden_treasures - 1;
    end
  end
  
  def adjustToFitTreasureLists(v, h)
    if(@n_visible_treasures <= v.size)
        nuevoNVisible = @n_visible_treasures
    else
        nuevoNVisible = v.size
    end
      
    if(@n_hidden_treasures <= h.size)
        nuevoNHidden = @n_hidden_treasures
    else
        nuevoNHidden = h.size
    end
    
    bc = NumericBadConsequence.new(getText(), getLevels(), nuevoNVisible, nuevoNHidden)
    
    return bc
  end
  
  def to_s
    return "\n " + getText() + "\n Pierdes " + getLevels().to_s + " niveles\n Tesoros visibles perdidos: #{@n_visible_treasures}\n Tesoros escondidos perdidos: #{@n_hidden_treasures}\n"
  end
end
end