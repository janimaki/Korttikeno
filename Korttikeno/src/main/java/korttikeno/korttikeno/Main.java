package korttikeno.korttikeno;

import javax.swing.SwingUtilities;
import korttikeno.Kayttoliittyma.Kayttoliittyma;
import korttikeno.Pelaaja.Pelaaja;
import korttikeno.Sovelluslogiikka.Kenoarvonta;

/**
 * Pääluokka, joka käynnistää ohjelman.
 *
 * @author Jani
 */
public class Main {

    public static void main(String[] args) {

        Kayttoliittyma kayttol = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttol);

    }
}
