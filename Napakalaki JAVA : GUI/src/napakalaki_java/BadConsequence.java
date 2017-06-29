/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki_java;

import java.util.ArrayList;

/**
 *
 * @author jumacasni
 */
public abstract class BadConsequence {
    static int MAXTREASURES = 10; 
    protected String text;
    protected int levels;
    
    public BadConsequence(String text, int levels){
        this.text = text;
        this.levels = levels;
    }
       
    public abstract boolean isEmpty();
    
    public int getLevels(){
        return levels;
    }
        
    public abstract void substractVisibleTreasure(Treasure t);
    
    public abstract void substractHiddenTreasure(Treasure t);
    
    public abstract BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h);  
            
    public abstract String toString();
}

