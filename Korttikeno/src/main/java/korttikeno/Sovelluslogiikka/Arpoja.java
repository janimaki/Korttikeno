/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Sovelluslogiikka;

import java.util.Random;

/**
 * Luokka, jota käytetään arpomaan korttien indeksejä normaalissa arvonnassa ja
 * tuplauksessa
 *
 * @author Jani
 */
public class Arpoja {

    Random arpoja = new Random();

    public Arpoja() {
    }

    /**
     * Metodi, joka arpoo luvun 1-52 väliltä
     * 
     * @return kokonaisluku väliltä 1-52
     */
    public int arvoLuvut() {
        return arpoja.nextInt(52) + 1;
    }

    /**
     * Metodi, joka arpoo luvun 1-13 väliltä
     * 
     * @return kokonaisluku väliltä 1-13
     */
    public int arvoKortti() {
        return arpoja.nextInt(13) + 1;
    }

    /**
     * Metodi, joka arpoo luvun 0-3 väliltä
     * 
     * @return kokonaisluku väliltä 0-3
     */
    public int arvoMaa() {
        return arpoja.nextInt(4);
    }
}
