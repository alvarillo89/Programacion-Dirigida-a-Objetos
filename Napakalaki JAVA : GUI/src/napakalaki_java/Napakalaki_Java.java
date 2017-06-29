/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki_java;
import GUI.*;
import GUI.Dice;
import java.util.ArrayList;

/**
 *
 * @author jumacasni
 */
public class Napakalaki_Java {


    
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList();
        Napakalaki game = Napakalaki.getInstance();
        NapakalakiView napakalakiView = new NapakalakiView();
        Dice.createInstance(napakalakiView);
        
        PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView, true);
        names = namesCapture.getNames();
        game.initGame(names);
        
        napakalakiView.setNapakalaki(game);
        napakalakiView.setVisible(true);
    }
   
}
