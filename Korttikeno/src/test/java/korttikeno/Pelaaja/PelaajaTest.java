/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Pelaaja;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jani
 */
public class PelaajaTest {

    Pelaaja pelaaja;

    public PelaajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pelaaja = new Pelaaja(10);
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void eiVoiValitaSamojaLukuja() {
        pelaaja.valitseKortti(4);
        pelaaja.valitseKortti(4);
        pelaaja.valitseKortti(20);
        pelaaja.valitseKortti(20);
        pelaaja.valitseKortti(15);
        assertEquals(3, pelaaja.montakoValittuaKorttia());
    }

    @Test
    public void pelaajaSetSaldoToimii() {
        pelaaja.setSaldo(18);
        assertEquals(18, pelaaja.getSaldo(), 0.0001);
    }

    @Test
    public void muutaSaldoaToimii() {
        pelaaja.muutaSaldoa(-2);
        assertEquals(8, pelaaja.getSaldo(), 0.0001);
        pelaaja.muutaSaldoa(12);
        assertEquals(20, pelaaja.getSaldo(), 0.0001);
        pelaaja.muutaSaldoa(-32.4);
        assertEquals(20, pelaaja.getSaldo(), 0.0001);
        pelaaja.muutaSaldoa(-20);
        assertEquals(0, pelaaja.getSaldo(), 0.0001);
    }


    @Test
    public void tyhjennaRiviJaMontakoValittuaNumeroaToimii() {
        pelaaja.valitseKortti(16);
        pelaaja.valitseKortti(2);
        pelaaja.valitseKortti(17);
        pelaaja.valitseKortti(9);
        assertEquals(4, pelaaja.montakoValittuaKorttia());
        pelaaja.tyhjennaRivi();
        pelaaja.valitseKortti(17);
        pelaaja.valitseKortti(9);
        assertEquals(2, pelaaja.montakoValittuaKorttia());
        pelaaja.tyhjennaRivi();
        assertEquals(0, pelaaja.montakoValittuaKorttia());
    }
    
    @Test
    public void getJaSetTuplausToimii(){
        pelaaja.setTuplaus(1);
        assertEquals(1, pelaaja.getTuplaus());
        pelaaja.setTuplaus(3);
        assertEquals(3, pelaaja.getTuplaus());
    }
}
