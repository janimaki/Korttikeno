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
        arvonta.uusiPelaaja("testaaja", 5);

    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void arvontaaEiSuoritetaIlmanSaldoa() {
        arvonta.asetaSaldo(0);
        arvonta.asetaPanos(0.2);
        arvonta.suoritaArvonta();
        assertEquals(0, arvonta.arvotutNumerot.size());
        assertEquals(0, arvonta.pelaaja.getSaldo(), 0.00001);
    }

    @Test
    public void arvotaanOikeaMaaraLukuja() {
        arvonta.asetaPanos(1.0);
        arvonta.suoritaArvonta();
        assertEquals(10, arvonta.arvotutNumerot.size());
    }

    @Test
    public void uusiPelaajaToimii() {
        arvonta.uusiPelaaja("pelle", 10);
        assertEquals("pelle, 10.0", arvonta.pelaaja.toString());

    }

    @Test
    public void arvontaaEiSuoritetaJosSaldoEiRiita() {
        arvonta.asetaSaldo(0.8);
        arvonta.asetaPanos(1.0);
        arvonta.suoritaArvonta();
        assertEquals(0, arvonta.arvotutNumerot.size());
        assertEquals(0.8, arvonta.pelaaja.getSaldo(), 0.00001);

    }

    @Test
    public void panosOnOikeallaValilla() {

        arvonta.asetaPanos(0.1999);
        assertEquals(0.2, arvonta.panos.getPanos(), 0.00001);
        arvonta.asetaPanos(1.0001);
        assertEquals(1.0, arvonta.panos.getPanos(), 0.00001);
        arvonta.asetaPanos(0);
        assertEquals(0.2, arvonta.panos.getPanos(), 0.00001);
        arvonta.asetaPanos(1.0);
        assertEquals(1.0, arvonta.panos.getPanos(), 0.00001);
        arvonta.asetaPanos(0.2);
        assertEquals(0.2, arvonta.panos.getPanos(), 0.00001);
        arvonta.asetaPanos(arvonta.pelaaja.saldo);
        assertEquals(1, arvonta.panos.getPanos(), 0.00001);
        arvonta.asetaPanos(-1);
        assertEquals(0.2, arvonta.panos.getPanos(), 0.00001);
    }

    @Test
    public void panosVahentyySaldosta() {
        arvonta.pelaaja.setSaldo(1);
        arvonta.asetaPanos(0.6);
        arvonta.suoritaArvonta();
        assertEquals(0.4, arvonta.pelaaja.getSaldo(), 0.00001);
        arvonta.pelaaja.setSaldo(1);
        arvonta.asetaPanos(1);
        arvonta.suoritaArvonta();
        assertEquals(0,arvonta.pelaaja.getSaldo(),0.00001);
    }
    
}
