package korttikeno.korttikeno;

import javax.swing.SwingUtilities;
import korttikeno.Kayttoliittyma.Kayttoliittyma;
import korttikeno.Pelaaja.Pelaaja;
import korttikeno.Sovelluslogiikka.Kenoarvonta;

/**
 * Hello world!
 *
 */
public class Main {

    public static void main(String[] args) {

//        Korttikeno keno = new Korttikeno();
//        keno.pelaaGraafinen();
//                           
        Kayttoliittyma kayttol = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttol);

//        keno.Pelaa();
    }
}
