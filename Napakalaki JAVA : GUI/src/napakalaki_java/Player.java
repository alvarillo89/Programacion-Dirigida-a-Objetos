/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki_java;
import java.util.*;
import GUI.Dice;

/**
 *
 * @author jumacasni
 */
public class Player {
    static final int MAXLEVEL = 10;
    private String name;
    private int level;
    private boolean dead = true;
    private boolean canISteal = true;
    protected Player enemy;
    private ArrayList<Treasure> visibleTreasures;
    private ArrayList<Treasure> hiddenTreasures;
    private BadConsequence pendingBadConsequence;
    
    public Player(String name){
        this.name = name;
        level = 1;
        enemy = null;
        visibleTreasures = new ArrayList();
        hiddenTreasures = new ArrayList();
        pendingBadConsequence = null;
    }
    
    public Player(Player p){
        name = p.name;
        level = p.level;
        enemy = p.enemy;
        visibleTreasures = p.visibleTreasures;
        hiddenTreasures = p.hiddenTreasures;
        pendingBadConsequence = p.pendingBadConsequence;
    }
    
    public String getName(){
        return name;
    }
    
    private void bringToLife(){
        dead = false;
    }
    
    public int getCombatLevel(){
        int nivel = level;
        for(Treasure tv : visibleTreasures)
            nivel = nivel + tv.getBonus();
        
        return nivel;
    }
            
    private void incrementLevels(int l){
        level = level + l;
    }
    
    private void decrementLevels(int l){
        if(level - l > 0)
            level = level - l;
        else
            level = 1;
    }
    
    private void setPendingBadConsequence(BadConsequence b){
        pendingBadConsequence = b;
    }
    
    private void applyPrize(Monster m){
        int nLevels = m.getLevelsGained();
        this.incrementLevels(nLevels);
        int nTreasures = m.getTreasuresGained();
        
        if(nTreasures > 0){
            CardDealer dealer = CardDealer.getInstance();
            Treasure treasure;
            
            for(int i = 0; i < nTreasures; ++i){
              treasure = dealer.nextTreasure();
              hiddenTreasures.add(treasure);
            }
        }       
    }
    
    private void applyBadConsequence(Monster m){
        BadConsequence badConsequence = m.getBadConsequence();
        int nLevels = badConsequence.getLevels();
        this.decrementLevels(nLevels);
        BadConsequence pendingBad = badConsequence.adjustToFitTreasureLists(visibleTreasures, hiddenTreasures);
        this.setPendingBadConsequence(pendingBad);
    }
    
    private boolean canMakeTreasureVisible(Treasure tesoro){
        boolean sePuede = true;
        

        if(tesoro.getType() == TreasureKind.ONEHAND){
            if(howManyVisibleTreasures(tesoro.getType()) > 1 ||
               howManyVisibleTreasures(TreasureKind.BOTHHANDS) > 0)
                sePuede = false;
        }
        else if(tesoro.getType() == TreasureKind.BOTHHANDS){
            if(howManyVisibleTreasures(tesoro.getType()) > 0 ||
               howManyVisibleTreasures(TreasureKind.ONEHAND) > 0)
                sePuede = false;
        }
        else{
            if(howManyVisibleTreasures(tesoro.getType()) > 0)
                sePuede = false;
        }        
        
        return sePuede;   
    }
    
    private int howManyVisibleTreasures(TreasureKind tKind){
        int nVisibleTreasures = 0;
        for(Treasure t : visibleTreasures)
            if (tKind == t.getType())
                nVisibleTreasures = nVisibleTreasures + 1;
        
        return nVisibleTreasures;
    }
    
    private void dieIfNoTreasures(){
        if(visibleTreasures.isEmpty() && hiddenTreasures.isEmpty())
            dead = true;
    }
    
    public boolean isDead(){
        return dead;
    }
    
    public ArrayList<Treasure> getHiddenTreasures(){
        return hiddenTreasures;
    }
    
    public ArrayList<Treasure> getVisibleTreasures(){
        return visibleTreasures;
    }
    
    public CombatResult combat(Monster m){
        CombatResult combatResult;
        int myLevel = getCombatLevel();
        int monsterLevel = getOponentLevel(m);
        
        if(!canISteal()){
            Dice dice = Dice.getInstance();
            int number = dice.nextNumber();
            if(number < 3){
                int enemyLevel = enemy.getCombatLevel();
                monsterLevel += enemyLevel;
            }
        }
        
        if(myLevel > monsterLevel) {
            applyPrize(m);
            if(level >= MAXLEVEL)
                combatResult = CombatResult.WINGAME;
            else
                combatResult = CombatResult.WIN;
        }
        else{
            applyBadConsequence(m);
            if(shouldConvert())
                combatResult = CombatResult.LOSEANDCONVERT;
            else
                combatResult = CombatResult.LOSE;
        }
            
        return combatResult;
    }
    
    public void makeTreasureVisible(Treasure t){
        boolean canI = canMakeTreasureVisible(t);
        
        if(canI){
            visibleTreasures.add(t);
            hiddenTreasures.remove(t);
        }
    }
    
    public void discardVisibleTreasure(Treasure t){
        visibleTreasures.remove(t);
        if(pendingBadConsequence != null && !pendingBadConsequence.isEmpty())
            pendingBadConsequence.substractVisibleTreasure(t);
        
       dieIfNoTreasures();
    }
    
    public void discardHiddenTreasure(Treasure t){
        hiddenTreasures.remove(t);
        if(pendingBadConsequence != null && !pendingBadConsequence.isEmpty())
            pendingBadConsequence.substractHiddenTreasure(t);
        
        dieIfNoTreasures();       
    }
    
    public boolean validState(){
        
        if(pendingBadConsequence != null){
            if(pendingBadConsequence.isEmpty() && hiddenTreasures.size() <= 4)
                return true;
        }
        else if(hiddenTreasures.size() <= 4)
            return true;
        
        return false;
        
    }
    
    public BadConsequence getPending(){
        return pendingBadConsequence;
    }
            
    
    public void initTreasures(){
        CardDealer dealer = CardDealer.getInstance();
        Dice dice = Dice.getInstance();
        this.bringToLife();
        Treasure treasure = dealer.nextTreasure();
        hiddenTreasures.add(treasure);
        int number = dice.nextNumber();
        if(number > 1){
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
        if(number == 6){
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
    }
    
    public int getLevels(){
        return level;
    }
    
    public Player getEnemy(){
        return enemy;
    }
    
    public Treasure stealTreasure(){
        Treasure treasure = null;
        boolean canI = this.canISteal();
        if(canI){
            boolean canYou = enemy.canYouGiveMeATreasure();
            if(canYou){
                treasure = enemy.giveMeATreasure();
                hiddenTreasures.add(treasure);
                this.haveStolen();
            }
        }
        return treasure;
    }
    
    public void SetEnemy(Player enemy){
        this.enemy = enemy;
    }
    
    protected Treasure giveMeATreasure(){
        int numeroAleatorio;
        numeroAleatorio = (int) (Math.random()*(hiddenTreasures.size()));
        
        Treasure t = hiddenTreasures.get(numeroAleatorio);
        hiddenTreasures.remove(t);
        
        return t;
    }
    
    public boolean canISteal(){
        return canISteal;
    }
    
    protected boolean canYouGiveMeATreasure(){
        if(hiddenTreasures.size() > 0)
            return true;
        
        return false;
    }
    
    private void haveStolen(){
        canISteal = false;
    }
    
    public void discardAllTreasures(){
        ArrayList<Treasure> visibleT = new ArrayList(visibleTreasures);
        ArrayList<Treasure> hiddenT = new ArrayList(hiddenTreasures);
    
        for(Treasure t : visibleT)
            this.discardVisibleTreasure(t);
        
        for(Treasure t : hiddenT)
            this.discardHiddenTreasure(t);
        
    }   
    
    protected int getOponentLevel(Monster m){
        return m.getCombatLevel();
    }
    
    protected boolean shouldConvert(){
        Dice dice = Dice.getInstance();
        int number = dice.nextNumber();
        if(number == 6)
            return true;
        
        return false;
    }
    
    public String toString(){
        String s = "";
        s = s + name + " (Nivel: " + level + " Combat Level: " + getCombatLevel() + " Enemigo: " + enemy.name + ")";
        if(pendingBadConsequence != null)
            s = s + "\nPendingBadConsequence = " + pendingBadConsequence.toString();
            
        return s;
    }
    
    //Consultores
    protected int getLevel(){
        return level;
    }
    
    protected BadConsequence getPendingBad(){
        return pendingBadConsequence;
    }
    
}
