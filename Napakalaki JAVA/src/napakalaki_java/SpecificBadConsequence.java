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
public class SpecificBadConsequence extends BadConsequence {
    protected ArrayList<TreasureKind>specificHiddenTreasures;
    protected ArrayList<TreasureKind>specificVisibleTreasures;
       
    
    public SpecificBadConsequence(String text, int levels, ArrayList<TreasureKind> tVisible, ArrayList<TreasureKind> tHidden){
        super(text, levels);
        specificVisibleTreasures = tVisible;
        specificHiddenTreasures = tHidden;
    }    

    public ArrayList<TreasureKind> getSpecificVisibleTreasures(){
            return specificVisibleTreasures;
    }
    
    public ArrayList<TreasureKind> getSpecificHiddenTreasures(){
            return specificHiddenTreasures;
    } 
    
    @Override
    public boolean isEmpty(){
        return (specificHiddenTreasures.isEmpty() && specificVisibleTreasures.isEmpty());
    }
    
    @Override
    public void substractVisibleTreasure(Treasure t){
        if(!specificVisibleTreasures.isEmpty())
            specificVisibleTreasures.remove(t.getType());
    }
    
    @Override
    public void substractHiddenTreasure(Treasure t){
        if(!specificHiddenTreasures.isEmpty())
            specificHiddenTreasures.remove(t.getType());
    }
    
    @Override
    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){
        BadConsequence nuevaBadConsequence;
        ArrayList<TreasureKind> nuevoSpecificVisible = new ArrayList();
        ArrayList<TreasureKind> nuevoSpecificHidden = new ArrayList();
        
        for(Treasure t : v){
            if(specificVisibleTreasures.indexOf(t.getType()) != -1)
                nuevoSpecificVisible.add(t.getType());
        }

        for(Treasure t : h){
            if(specificHiddenTreasures.indexOf(t.getType()) != -1)
                nuevoSpecificHidden.add(t.getType());
        }

        nuevaBadConsequence = new SpecificBadConsequence(text, levels, nuevoSpecificVisible, nuevoSpecificHidden);
    
        return nuevaBadConsequence;
    } 
    
    @Override
    public String toString(){
        
       String VTreasures = "";
       String HTreasures = "";
       
       for(TreasureKind s : specificVisibleTreasures){
               VTreasures += s.toString();
        }
       
       for(TreasureKind d : specificHiddenTreasures){
                HTreasures += d.toString();
       }      
    
        return text + " \nNiveles que pierdes = " + Integer.toString(levels) +
               "\nSpecific visible treasures = " + VTreasures + 
               "\nSpecific hidden treasures = " + HTreasures;
    }


} 
