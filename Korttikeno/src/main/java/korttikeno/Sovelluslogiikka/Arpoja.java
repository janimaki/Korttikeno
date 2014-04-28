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
}
