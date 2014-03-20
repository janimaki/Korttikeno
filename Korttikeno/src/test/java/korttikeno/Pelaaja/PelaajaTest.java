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
        pelaaja = new Pelaaja("Testaaja", 10);
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void valittujaNumeroitaEiTuleLiikaa() {
        pelaaja.valitseNumero(4);
        pelaaja.valitseNumero(30);
        pelaaja.valitseNumero(12);
        pelaaja.valitseNumero(16);
        pelaaja.valitseNumero(2);
        assertEquals(4, pelaaja.montakoValittuaNumeroa());
    }
    
    @Test
    public void eiVoiValitaSamojaLukuja(){
        pelaaja.valitseNumero(4);
        pelaaja.valitseNumero(4);
        pelaaja.valitseNumero(20);
        pelaaja.valitseNumero(20);
        pelaaja.valitseNumero(15);
        assertEquals(3, pelaaja.montakoValittuaNumeroa());
    }
    
    public void pelaajaSetSaldoToimii(){
        pelaaja.setSaldo(18);
        assertEquals(18,pelaaja.getSaldo());
    }
    
    public void muutaSaldoaToimii(){
        pelaaja.muutaSaldoa(-2);
        assertEquals(8,pelaaja.getSaldo());
        pelaaja.muutaSaldoa(12);
        assertEquals(20, pelaaja.getSaldo());
    }
}
