/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki_java;
import java.util.*;

/**
 *
 * @author jumacasni
 */
public class Napakalaki {
    
    private static Napakalaki instance = null;
    private Monster currentMonster;
    private CardDealer dealer;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Napakalaki() {
    }
    
    private void initPlayers(String[] names){
        players = new ArrayList();
        
        for(String nombre : names)
            players.add(new Player(nombre));
    }
    
    private Player nextPlayer(){
        
        Player nextPlayer;
        int numeroAleatorio;
        int indice;
        
        if(currentPlayer == null){
            numeroAleatorio = (int) (Math.random()*(players.size()));
            indice = numeroAleatorio;
        }
        
        else{
            if(currentPlayer == players.get(players.size()-1))
                indice = 0;
            
            else
                indice = players.indexOf(currentPlayer) + 1;
            
        }
        
        nextPlayer = players.get(indice);
        currentPlayer = nextPlayer;
        
        return nextPlayer;
    }
    
    private boolean nextTurnAllowed(){
        boolean allow = false;
        
        if(currentPlayer == null)
            allow = true;
        else if(currentPlayer.validState())
            allow = true;
        
        return allow;    
    }
    
    private void setEnemies(){
        int numeroAleatorio;
        Player enemy;
        
        for(Player p : players){
            numeroAleatorio = (int) (Math.random()*(players.size()));
            enemy = players.get(numeroAleatorio);
                while(enemy == p){
                numeroAleatorio = (int) (Math.random()*(players.size()));
                enemy = players.get(numeroAleatorio);
            }
            
            p.SetEnemy(enemy);
        }
        
    }
    
    public static Napakalaki getInstance() {
        if (instance == null)
                instance = new Napakalaki();
        
        return instance;
    }
    
    public CombatResult developCombat(){
        CombatResult combatResult = currentPlayer.combat(currentMonster);
        dealer.giveMonsterBack(currentMonster);
        
        if(combatResult == CombatResult.LOSEANDCONVERT){
            Cultist card = dealer.nextCultist();
            CultistPlayer sectario = new CultistPlayer(currentPlayer, card);
            
            // Reemplazar atributo enemy:
            for(Player p : players)
                if(p.getEnemy() == currentPlayer)
                    p.SetEnemy(sectario);
            
            // Reemplazar de la lista de Players:
            int pos = players.indexOf(currentPlayer);
            players.set(pos,sectario);
            
            // Reemplazar CurrentPlayer::
            currentPlayer = sectario;
        }
        return combatResult;
    }
    
    public void discardVisibleTreasures(ArrayList<Treasure> treasures){
        for(Treasure treasure : treasures){
            currentPlayer.discardVisibleTreasure(treasure);
            dealer.giveTreasureBack(treasure);        
        }
        
    }
    
    public void discardHiddenTreasures(ArrayList<Treasure> treasures){
        for(Treasure treasure : treasures){
            currentPlayer.discardHiddenTreasure(treasure);
            dealer.giveTreasureBack(treasure);        
        }
        
    }
    
    public void makeTreasuresVisible (ArrayList<Treasure> treasures){
        for(Treasure t : treasures){
            currentPlayer.makeTreasureVisible(t);
        }
    }
    
    public void initGame (ArrayList<String> players){
        initPlayers(players.toArray(new String[players.size()]));
        setEnemies();
        dealer = CardDealer.getInstance();
        dealer.initCards();
        nextTurn();
    }
    
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    
    public Monster getCurrentMonster(){
        return currentMonster;
    }
    
    public boolean nextTurn(){
        boolean stateOK = nextTurnAllowed();
        
        if(stateOK){
            currentMonster = dealer.nextMonster();
            currentPlayer = nextPlayer();
            boolean dead = currentPlayer.isDead();
            
            if(dead){
                currentPlayer.initTreasures();
            }
        }
        
        return stateOK;
    }
    
    public boolean endOfGame(CombatResult result){
        boolean end = false;
        
        if(result == CombatResult.WINGAME)
            end = true;
        
        return end;
    }
}
