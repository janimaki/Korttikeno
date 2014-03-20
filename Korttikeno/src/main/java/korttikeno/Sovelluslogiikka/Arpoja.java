/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Sovelluslogiikka;

import java.util.Random;

/**
 *
 * @author Jani
 */
public class Arpoja {

    Random arpoja = new Random();

    public Arpoja() {
    }

    public int arvoLuvut() {
        return arpoja.nextInt(52) + 1;
    }

    public int arvoKortti() {
        return arpoja.nextInt(13) + 1;
    }

    public int arvoMaa() {
        return arpoja.nextInt(4);
    }
}
