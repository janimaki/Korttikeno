/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Sovelluslogiikka;

import korttikeno.Sovelluslogiikka.Kenoarvonta;
import korttikeno.Pelaaja.Pelaaja;
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
public class KenoarvontaTest {

    Kenoarvonta kenoarvonta;
//    Pelaaja pelaaja;

    public KenoarvontaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kenoarvonta = new Kenoarvonta();
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void arvontaaEiSuoritetaIlmanSaldoa() {
        kenoarvonta.pelaaja.setSaldo(0);
        kenoarvonta.suoritaArvonta();
        assertEquals(0, kenoarvonta.arvotutNumerot.size());
    }

    

    @Test
    public void arvotaanOikeaMaaraLukuja() {
        kenoarvonta.pelaaja.setSaldo(10);
        kenoarvonta.suoritaArvonta();
        assertEquals(10, kenoarvonta.arvotutNumerot.size());
    }
}
