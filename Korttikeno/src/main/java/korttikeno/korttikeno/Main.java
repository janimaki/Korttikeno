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
        
        Korttikeno keno = new Korttikeno();
//        
//        keno.Pelaa();
//                           
        Kayttoliittyma kayttol = new Kayttoliittyma(keno);
        SwingUtilities.invokeLater(kayttol);
        keno.Pelaa();
        
    }
}
