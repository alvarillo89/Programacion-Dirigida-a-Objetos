/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki_java;

/**
 *
 * @author jumacasni
 */
public class Monster {
    private String name;
    private int combatLevel;
    private Prize prize;
    private BadConsequence badConsequence;
    private int levelChangeAgainstCultistPlayer;
    
    public Monster(String name, int level, BadConsequence bc, Prize prize){
        levelChangeAgainstCultistPlayer = 0;
        this.name = name;
        combatLevel = level;
        this.prize = prize;
        badConsequence = bc;
    }
    
    public Monster(String n, int l, BadConsequence badConsequence, Prize p, int lC){
        name = n;
        combatLevel = l;
        this.badConsequence = badConsequence;
        prize = p;
        levelChangeAgainstCultistPlayer = lC;
    }
    
    public String getName(){
        return name;
    }
    
    public int getCombatLevel(){
        return combatLevel;
    }
    
    public int getCombatLevelAgainstCultistPlayer(){
        return (combatLevel + levelChangeAgainstCultistPlayer);
    }
    
    public Prize getPrize(){
        return prize;
    }
    
    public BadConsequence getBadConsequence(){
        return badConsequence;
    }
    
    public int getLevelsGained(){
        return prize.getLevel();
    }
    
    public int getTreasuresGained(){
        return prize.getTreasures();
    }
    
    public String toString(){
        String s = "";
        s = s + name + "\nCombat Level = " + combatLevel + "\nBonus Sectarios = " + levelChangeAgainstCultistPlayer + "\nMal Rollo = "  + badConsequence.toString()
            + "\nBuen Rollo = " + prize.toString();
        return s;
    }
}

