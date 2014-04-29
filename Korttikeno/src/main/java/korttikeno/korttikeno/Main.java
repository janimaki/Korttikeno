package korttikeno.korttikeno;

import javax.swing.SwingUtilities;
import main.java.korttikeno.Kayttoliittyma.Kayttoliittyma;

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
