#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require "singleton"

module NapakalakiGame
  class Dice
  
  include Singleton
    
  def nextNumber()
    num_aleat = rand(1..6)
    return num_aleat
  end

  end
end

