/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki_java;

import java.util.ArrayList;

/**
 *
 * @author alvaro89
 */
public class NumericBadConsequence extends BadConsequence {
    protected int nVisibleTreasures;
    protected int nHiddenTreasures;
    
    public NumericBadConsequence(String text, int levels, int nVisible, int nHidden){
        super(text,levels);
        nVisibleTreasures = nVisible;
        nHiddenTreasures = nHidden;
    }
    
    @Override
    public boolean isEmpty(){
        return (nVisibleTreasures == 0 && nHiddenTreasures == 0);
    }
    
    public int getVisibleTreasures(){
        return nVisibleTreasures;
    }
    
    public int getHiddenTreasures(){
        return nHiddenTreasures;
    }
    
    @Override
    public void substractVisibleTreasure(Treasure t){
        if(nVisibleTreasures != 0)
            nVisibleTreasures--; 
    }
    
    @Override
    public void substractHiddenTreasure(Treasure t){
        if(nHiddenTreasures != 0)
            nHiddenTreasures--;
    }
    
    @Override    
    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){
        BadConsequence nuevaBadConsequence;
        int nuevoNVisible, nuevoNHidden;

        if(nVisibleTreasures <= v.size())
            nuevoNVisible = nVisibleTreasures;
        else
            nuevoNVisible = v.size();

        if(nHiddenTreasures <= h.size())
            nuevoNHidden = nHiddenTreasures;
        else
            nuevoNHidden = h.size();

        nuevaBadConsequence = new NumericBadConsequence(text,levels,nuevoNVisible, nuevoNHidden);

        
        return nuevaBadConsequence;
    }
    
    @Override
    public String toString(){
    
        return text + " \nNiveles que pierdes = " + Integer.toString(levels) +
               "\nNumero visible treasures = " + Integer.toString(nVisibleTreasures) +
               "\nNumero hidden treasures = " + Integer.toString(nHiddenTreasures);
    }
    
}
