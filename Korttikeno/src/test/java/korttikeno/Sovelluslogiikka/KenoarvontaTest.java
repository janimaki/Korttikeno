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
        kenoarvonta.uusiPelaaja("testaaja", 5);
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void arvontaaEiSuoritetaIlmanSaldoa() {
        kenoarvonta.asetaSaldo(0);
        kenoarvonta.asetaPanos(0.2);       
        kenoarvonta.suoritaArvonta();
        assertEquals(0, kenoarvonta.arvotutNumerot.size());
        assertEquals(0, kenoarvonta.pelaaja.getSaldo(), 0.00001);
    }

    

    @Test
    public void arvotaanOikeaMaaraLukuja() {
        kenoarvonta.asetaPanos(1.0);
        kenoarvonta.suoritaArvonta();
        assertEquals(10, kenoarvonta.arvotutNumerot.size());
    }
    
    @Test
    public void uusiPelaajaToimii(){
        kenoarvonta.uusiPelaaja("pelle", 10);
        assertEquals("pelle, 10.0", kenoarvonta.pelaaja.toString());
        
    }
    
    @Test
    public void arvontaaEiSuoritetaJosSaldoEiRiita() {
        kenoarvonta.asetaSaldo(0.8);
        kenoarvonta.asetaPanos(1.0);
        kenoarvonta.suoritaArvonta();
        assertEquals(0, kenoarvonta.arvotutNumerot.size());
        assertEquals(0.8, kenoarvonta.pelaaja.getSaldo(), 0.00001);
        
    }
    
    @Test
    public void panosOnOikeallaValilla(){
        
        kenoarvonta.asetaPanos(0.1999);
        assertEquals(0.2, kenoarvonta.panos.getPanos(), 0.00001);
        kenoarvonta.asetaPanos(1.0001);
        assertEquals(1.0, kenoarvonta.panos.getPanos(), 0.00001);
        kenoarvonta.asetaPanos(0);
        assertEquals(0.2, kenoarvonta.panos.getPanos(), 0.00001);
        kenoarvonta.asetaPanos(1);
        assertEquals(1.0, kenoarvonta.panos.getPanos(), 0.00001);
        kenoarvonta.asetaPanos(0.2);
        assertEquals(0.2, kenoarvonta.panos.getPanos(), 0.00001);
        kenoarvonta.asetaPanos(kenoarvonta.pelaaja.saldo);
        assertEquals(1, kenoarvonta.panos.getPanos(), 0.00001);
        kenoarvonta.asetaPanos(-1);
        assertEquals(0.2, kenoarvonta.panos.getPanos(), 0.00001);
    }
    
    
    
}
