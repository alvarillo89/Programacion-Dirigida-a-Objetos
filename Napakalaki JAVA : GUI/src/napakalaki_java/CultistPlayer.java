/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki_java;

/**
 *
 * @author alvarillo89
 */
public class CultistPlayer extends Player{
    private static int totalCultistPlayers = 0;
    private Cultist myCultistCard;
    
    public CultistPlayer(Player p, Cultist c){
        super(p);
        myCultistCard = c;
        totalCultistPlayers++;
    }
    
    @Override
    public int getCombatLevel(){
        int CombatLevel = super.getCombatLevel();
        double combatLv = CombatLevel * 0.7;
        CombatLevel = (int)combatLv;
        CombatLevel += myCultistCard.getGainedLevels();
        CombatLevel *= totalCultistPlayers;
        return CombatLevel;
    }
    
    @Override
    protected int getOponentLevel(Monster m){
        return m.getCombatLevelAgainstCultistPlayer();
    }
    
    @Override
    protected boolean shouldConvert(){
        return false;
    }
    
    @Override
    protected Treasure giveMeATreasure(){
        int numeroAleatorio;
        numeroAleatorio = (int) (Math.random()*(getVisibleTreasures().size()));
        Treasure t = getVisibleTreasures().get(numeroAleatorio);
        getVisibleTreasures().remove(t);
        
        return t;
    }
    
    @Override    
    protected boolean canYouGiveMeATreasure(){
        if(getVisibleTreasures().size() > 0)
            return true;
        
        return false;
    }
    
    public static int getTotalCultistPlayers(){
        return totalCultistPlayers;
    }
    
    public Cultist getCultistCard(){
        return myCultistCard;
    }
    
    @Override
    public String toString(){
        String s = "";
        s = s + getName() + " (Nivel: " + getLevel() + " Combat Level: " + getCombatLevel() + " Enemigo: " + enemy.getName() + "\nCultistCard = " + myCultistCard.toString() + ")";
        if(getPendingBad() != null)
            s = s + "\nPendingBadConsequence = " + getPendingBad().toString();
            
        return s;
    }
}
