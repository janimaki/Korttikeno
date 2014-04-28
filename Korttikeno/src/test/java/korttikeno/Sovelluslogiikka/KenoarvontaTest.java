/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Sovelluslogiikka;

import java.util.Random;
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

    Kenoarvonta arvonta;
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
        arvonta = new Kenoarvonta();
        arvonta.setSaldo(5);

    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void arvontaaEiSuoritetaIlmanSaldoa() {
        arvonta.setSaldo(0);
        arvonta.setPanos(0.2);
        arvonta.suoritaArvonta();
        assertEquals(0, arvonta.arvotutNumerot.size());
        assertEquals(0, arvonta.pelaaja.getSaldo(), 0.00001);
    }

    @Test
    public void arvotaanOikeaMaaraLukuja() {
        arvonta.setPanos(1.0);
        arvonta.suoritaArvonta();
        assertEquals(10, arvonta.arvotutNumerot.size());
    }


    @Test
    public void arvontaaEiSuoritetaJosSaldoEiRiita() {
        arvonta.setSaldo(0.8);
        arvonta.setPanos(1.0);
        arvonta.suoritaArvonta();
        assertEquals(0, arvonta.arvotutNumerot.size());
        assertEquals(0.8, arvonta.pelaaja.getSaldo(), 0.00001);

    }

    @Test
    public void panosVahentyySaldosta() {
        arvonta.pelaaja.setSaldo(1);
        arvonta.setPanos(0.6);
        arvonta.suoritaArvonta();
        assertEquals(0.4, arvonta.pelaaja.getSaldo(), 0.00001);
        arvonta.pelaaja.setSaldo(1);
        arvonta.setPanos(1);
        arvonta.suoritaArvonta();
        assertEquals(0,arvonta.pelaaja.getSaldo(),0.00001);
    }
    
}
