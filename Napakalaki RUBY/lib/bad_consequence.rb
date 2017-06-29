#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "napakalaki.rb"

module NapakalakiGame
  class BadConsequence
  
  @@MAX_TREASURES = 10
  
 def initialize(aText, someLevels)
    @text = aText
    @levels = someLevels
 end  
  
  def self.getMaxTreasures()
    @@MAX_TREASURES
  end
  
  def getText()
    @text
  end
  
  def getLevels()
    @levels
  end
  
end
end

