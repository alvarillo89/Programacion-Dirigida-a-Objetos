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
public class CardDealer {
    
    private ArrayList<Monster> unusedMonster;
    private ArrayList<Monster> usedMonster;
    private ArrayList<Treasure> unusedTreasures;    
    private ArrayList<Treasure> usedTreasures;   
    private ArrayList<Cultist> unusedCultist;
    private static CardDealer instance = null;
    
    private CardDealer() {
    }
    
    private void initTreasureCardDeck(){
        unusedTreasures = new ArrayList();
        usedTreasures = new ArrayList();
        
        unusedTreasures.add(new Treasure("¡Sí mi amo!", 4, TreasureKind.HELMET));
        
        unusedTreasures.add(new Treasure("Botas de investigación", 3, TreasureKind.SHOES));
      
        unusedTreasures.add(new Treasure("Capucha de Cthulhu", 3, TreasureKind.HELMET));
        
        unusedTreasures.add(new Treasure("A prueba de babas", 2, TreasureKind.ARMOR));
        
        unusedTreasures.add(new Treasure("Botas de lluvia ácida", 1, TreasureKind.BOTHHANDS));
        
        unusedTreasures.add(new Treasure("Casco minero", 2, TreasureKind.HELMET));
        
        unusedTreasures.add(new Treasure("Ametralladora ACME", 4, TreasureKind.BOTHHANDS));
        
        unusedTreasures.add(new Treasure("Camiseta de la ETSIIT", 1, TreasureKind.ARMOR));
        
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario", 3, TreasureKind.ONEHAND));
        
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano", 2, TreasureKind.ONEHAND));
        
        unusedTreasures.add(new Treasure("Fez alópodo", 3, TreasureKind.HELMET));
        
        unusedTreasures.add(new Treasure("Hacha prehistórica", 2, TreasureKind.ONEHAND));
        
        unusedTreasures.add(new Treasure("El aparato de Pr. Tesla", 4, TreasureKind.ARMOR));
        
        unusedTreasures.add(new Treasure("Gaita", 4, TreasureKind.BOTHHANDS));
        
        unusedTreasures.add(new Treasure("Insecticida", 2, TreasureKind.ONEHAND));
        
        unusedTreasures.add(new Treasure("Escopeta de 3 cañones", 4, TreasureKind.BOTHHANDS));
        
        unusedTreasures.add(new Treasure("Garabato místico", 2, TreasureKind.ONEHAND));
        
        unusedTreasures.add(new Treasure("La rebeca metálica", 2, TreasureKind.ARMOR));
        
        unusedTreasures.add(new Treasure("Lanzallamas", 4, TreasureKind.BOTHHANDS));
        
        unusedTreasures.add(new Treasure("Necro-comicón", 1, TreasureKind.ONEHAND));
        
        unusedTreasures.add(new Treasure("Necronomicón", 5, TreasureKind.BOTHHANDS));
        
        unusedTreasures.add(new Treasure("Linterna a 2 manos", 3, TreasureKind.BOTHHANDS));
        
        unusedTreasures.add(new Treasure("Negro-gnomicón", 2, TreasureKind.ONEHAND));
        
        unusedTreasures.add(new Treasure("Necrotelecom", 2, TreasureKind.HELMET));
        
        unusedTreasures.add(new Treasure("Mazo de los antiguos", 3, TreasureKind.ONEHAND));
        
        unusedTreasures.add(new Treasure("Necro-playboycón", 3, TreasureKind.ONEHAND));
        
        unusedTreasures.add(new Treasure("Porra preternatural", 2, TreasureKind.ONEHAND));
        
        unusedTreasures.add(new Treasure("Shogulador", 1, TreasureKind.BOTHHANDS));
        
        unusedTreasures.add(new Treasure("Varita de atizamiento", 3, TreasureKind.ONEHAND));
        
        unusedTreasures.add(new Treasure("Tentáculo de pega", 2, TreasureKind.HELMET));
        
        unusedTreasures.add(new Treasure("Zapato deja-amigos", 1, TreasureKind.SHOES));
    }
    
    private void initMonsterCardDeck(){
        
        unusedMonster = new ArrayList();
        usedMonster = new ArrayList();
        
        
       // 3 Byakhees de bonanza  
       BadConsequence badConsequence = new SpecificBadConsequence("Pierdes tu armadura visible y otra oculta",
       0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
       Prize prize = new Prize(2,1);
       unusedMonster.add(new Monster("3 Byakhees de bonanza",8,badConsequence,prize));
       
       // Tenochtitlan
       badConsequence = new SpecificBadConsequence("Embobados con el lindo primigenio te descartas de tu casco visible", 0, 
       new ArrayList(Arrays.asList(TreasureKind.HELMET)), new ArrayList());
       prize = new Prize(1,1);
       unusedMonster.add(new Monster("Tenochtitlan",2,badConsequence,prize));
       
       // El sopor de Dunwich
       badConsequence = new SpecificBadConsequence("El primordial bostezo contagioso. Pierdes el calzado visible", 0,
       new ArrayList(Arrays.asList(TreasureKind.SHOES)), new ArrayList());
       prize = new Prize(1,1);
       unusedMonster.add(new Monster("El sopor de Dunwich",2,badConsequence,prize));
       
       // Demonios de Magaluf
       badConsequence = new SpecificBadConsequence("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta",
       0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList(Arrays.asList(TreasureKind.ONEHAND))); 
       prize = new Prize(4,1);
       unusedMonster.add(new Monster("Demonios de Magaluf",2,badConsequence,prize));
        
       // El gorrón en el umbral
       badConsequence = new NumericBadConsequence("Pierdes todos tus tesoros visibles", 0, BadConsequence.MAXTREASURES, 0);
       prize = new Prize(3,1);
       unusedMonster.add(new Monster("El gorrón en el umbral",13,badConsequence,prize));
       
       //H.P.Munchcraft
       badConsequence = new SpecificBadConsequence("Pierdes la armadura visible", 0,
       new ArrayList(Arrays.asList(TreasureKind.ARMOR)),new ArrayList());
       prize = new Prize(2,1);
       unusedMonster.add(new Monster("H.P.Munchcraft",6,badConsequence,prize));
       
       // Necrófago
       badConsequence = new SpecificBadConsequence("Sientes bichos bajo la ropa. Descarta la armadura visible", 0,
       new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
       prize = new Prize(1,1);
       unusedMonster.add(new Monster("Necrófago",13,badConsequence,prize));
       
       //El rey de rosado
       badConsequence = new NumericBadConsequence("Pierdes 5 niveles y 3 tesoros visibles.",5,3,0);
       prize = new Prize(3,2);
       unusedMonster.add(new Monster("El rey de rosado",11,badConsequence,prize));
       
       // Flecher
       badConsequence = new NumericBadConsequence("Toses los pulmones y pierdes 2 niveles.",2,0,0);
       prize = new Prize(1,1);
       unusedMonster.add(new Monster("Flecher",2,badConsequence,prize));
       
       // Los hondos
       badConsequence = new DeathBadConsequence("Estos monstruos resultan bastante superficiales y te aburren mortalmente. Estas muerto");
       prize = new Prize(2,1);
       unusedMonster.add(new Monster("Los hondos",8,badConsequence,prize));
       
       //Semillas Cthulhu
       badConsequence = new NumericBadConsequence("Pierdes 2 niveles y 2 tesoros ocultos.",2,0,2);
       prize = new Prize(2,1);
       unusedMonster.add(new Monster("Semillas Cthulhu",4,badConsequence,prize));       
       
       // Dameargo
       badConsequence = new SpecificBadConsequence("Te intentas escaquear. Pierdes una mano visible",0,
       new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList());
       prize = new Prize(2,1);
       unusedMonster.add(new Monster("Dameargo",1,badConsequence,prize));  
       
       //Pollipólipo volante
       badConsequence = new NumericBadConsequence("Da mucho asquito. Pierdes 3 niveles",3,0,0);
       prize = new Prize(2,1);
       unusedMonster.add(new Monster("Pollipólipo volante",3,badConsequence,prize)); 
       
       //Yskhtihyssg-Goth
       badConsequence = new DeathBadConsequence("No le hace gracia que pronuncien mal su nombre. Estás muerto.");
       prize = new Prize(3,1);
       unusedMonster.add(new Monster("Yskhtihyssg-Goth",14,badConsequence,prize));
       
       //Familia feliz
       badConsequence = new DeathBadConsequence("La familia te atrapa. Estás muerto.");
       prize = new Prize(3,1);
       unusedMonster.add(new Monster("Familia feliz",1,badConsequence,prize));
       
       //Roboggoth
       badConsequence = new SpecificBadConsequence("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible",2,
       new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)), new ArrayList());
       prize = new Prize(2,1);
       unusedMonster.add(new Monster("Roboggoth",8,badConsequence,prize));
       
       //El espía sordo
       badConsequence = new SpecificBadConsequence("Te asusta en la noche. Pierdes un casco visible",0,
       new ArrayList(Arrays.asList(TreasureKind.HELMET)), new ArrayList());
       prize = new Prize(1,1);
       unusedMonster.add(new Monster("El espía sordo",5,badConsequence,prize));  
       
       //Tongue
       badConsequence = new NumericBadConsequence("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles.",2,5,0);
       prize = new Prize(2,1);
       unusedMonster.add(new Monster("Tongue",19,badConsequence,prize));
       
       //Bicéfalo
       badConsequence = new SpecificBadConsequence("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos.",3,
       new ArrayList(Arrays.asList(TreasureKind.ONEHAND, TreasureKind.BOTHHANDS)), new ArrayList());
       prize = new Prize(2,1);
       unusedMonster.add(new Monster("Bicéfalo",21,badConsequence,prize));
       
       //Monstruos Sectarios:
       //El mal indecible impronunciable
       badConsequence = new SpecificBadConsequence("Pierdes 1 mano visible",0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList());
       prize = new Prize(3,1);
       unusedMonster.add(new Monster("El mal indecible impronunciable", 10, badConsequence, prize, -2));
       
       // Testigos oculares
       badConsequence = new NumericBadConsequence("Pierdes tus tesoros visibles. Jajaja.", 0, BadConsequence.MAXTREASURES, 0);
       prize = new Prize(2,1);
       unusedMonster.add(new Monster("Testigos oculares", 6, badConsequence, prize, 2));
       
       //El gran cthulhu
       badConsequence = new DeathBadConsequence("Hoy no es tu día de suerte. Mueres.");
       prize = new Prize(2,5);
       unusedMonster.add(new Monster("El gran cthulhu", 20, badConsequence, prize, 4));
       
       //Serpiente Político
       badConsequence = new NumericBadConsequence("Su gobierno te recorta 2 niveles.", 2, 0, 0);
       prize = new Prize(2, 1);
       unusedMonster.add(new Monster("Serpiente Político", 8, badConsequence, prize, -2));
       
       //Felpuggoth
       badConsequence = new SpecificBadConsequence("Pierdes tu casco y tu armadura visible. Pierdes tus manos ocultas", 
       0, new ArrayList(Arrays.asList(TreasureKind.HELMET, TreasureKind.ARMOR)), new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS, TreasureKind.BOTHHANDS, TreasureKind.BOTHHANDS, TreasureKind.BOTHHANDS)));
       prize = new Prize(1,1);
       unusedMonster.add(new Monster("Felpuggoth", 2, badConsequence, prize, 5));
       
       //Shoggoth
       badConsequence = new NumericBadConsequence("Pierdes 2 niveles.", 2, 0, 0);
       prize = new Prize(4,2);
       unusedMonster.add(new Monster("Shoggoth", 16, badConsequence, prize, -4));
       
       //Lolitagooth
       badConsequence = new NumericBadConsequence("Pintalabios negro. Pierdes 2 niveles", 2, 0, 0);
       prize = new Prize(1,1);
       unusedMonster.add(new Monster("Lolitagooth", 2, badConsequence, prize, 3));

    }    
    
    private void initCultistCardDeck(){
        unusedCultist = new ArrayList();
        unusedCultist.add(new Cultist("Sectario", 1));
        unusedCultist.add(new Cultist("Sectario", 2));
        unusedCultist.add(new Cultist("Sectario", 1));
        unusedCultist.add(new Cultist("Sectario", 2));
        unusedCultist.add(new Cultist("Sectario", 1));
        unusedCultist.add(new Cultist("Sectario", 1)); 
    }
    
    public static CardDealer getInstance(){
        if (instance == null)
            instance = new CardDealer();
        
        return instance;
    }
    private void shuffleTreasures(){
        Collections.shuffle(unusedTreasures);
    }
    
    private void shuffleMonsters(){
        Collections.shuffle(unusedMonster);
    }
    
    private void shuffleCultist(){
        Collections.shuffle(unusedCultist);
    }
    
    public Treasure nextTreasure() {
        if (unusedTreasures.isEmpty()){
            unusedTreasures = usedTreasures;
            shuffleTreasures();
            usedTreasures.clear();
        }
        
        Treasure t = unusedTreasures.get(0);
        unusedTreasures.remove(t);
        
        return t;
    }
    
    public Monster nextMonster() {
        if (unusedMonster.isEmpty()){
            unusedMonster = usedMonster;
            shuffleMonsters();
            usedMonster.clear();
        }
        
        Monster m = unusedMonster.get(0);
        unusedMonster.remove(m);
        
        return m;
    }
    
    public Cultist nextCultist(){
        Cultist c = unusedCultist.get(0);
        unusedCultist.remove(c);
        
        return c;
    } 
    
    public void giveTreasureBack(Treasure t){
        usedTreasures.add(t);
    }
    
    public void giveMonsterBack(Monster m){
        usedMonster.add(m);
    }
    
    public void initCards() {
        initTreasureCardDeck();
        shuffleTreasures();
        initMonsterCardDeck();
        shuffleMonsters();
        initCultistCardDeck();
        shuffleCultist();
    }
 
}
