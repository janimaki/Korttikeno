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
public class Kenoarvonta2Test {

    Kenoarvonta arvonta;

    public Kenoarvonta2Test() {
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
        arvonta.arvotutNumerot.add(1);
        arvonta.arvotutNumerot.add(2);
        arvonta.arvotutNumerot.add(10);
        arvonta.arvotutNumerot.add(15);
        arvonta.arvotutNumerot.add(16);
        arvonta.arvotutNumerot.add(4);
        arvonta.arvotutNumerot.add(31);
        arvonta.arvotutNumerot.add(32);
        arvonta.arvotutNumerot.add(33);
        arvonta.arvotutNumerot.add(34);
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void MontakoOsumaaToimii() {
        arvonta.pelaaja.valitseKortti(1);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(6);
        arvonta.pelaaja.valitseKortti(5);
        assertEquals(2, arvonta.montakoOsumaa());
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.pelaaja.valitseKortti(1);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(6);
        assertEquals(2, arvonta.montakoOsumaa());
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(6);
        assertEquals(1, arvonta.montakoOsumaa());

    }

    @Test
    public void montaValittuaNumeroaToimii() {
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(6);
        assertEquals(3, arvonta.montaValittuaNumeroa());
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(4);
        arvonta.pelaaja.valitseKortti(8);
        arvonta.pelaaja.valitseKortti(10);
        assertEquals(4, arvonta.montaValittuaNumeroa());
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.pelaaja.valitseKortti(6);
        assertEquals(1, arvonta.montaValittuaNumeroa());
        arvonta.pelaaja.tyhjennaRivi();
        assertEquals(0, arvonta.montaValittuaNumeroa());

    }

    public void onkoVoittoaToimii() {
        arvonta.pelaaja.valitseKortti(1);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(6);
        arvonta.pelaaja.valitseKortti(5);
        boolean tosi = arvonta.onkoVoittoa();
        assertEquals(true, tosi);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.pelaaja.valitseKortti(1);
        arvonta.pelaaja.valitseKortti(2);
        tosi = arvonta.onkoVoittoa();
        assertEquals(true, tosi);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(5);
        tosi = arvonta.onkoVoittoa();
        assertEquals(false, tosi);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.pelaaja.valitseKortti(7);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(6);
        arvonta.pelaaja.valitseKortti(5);
        tosi = arvonta.onkoVoittoa();
        assertEquals(false, tosi);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(5);
        arvonta.pelaaja.valitseKortti(7);
        tosi = arvonta.onkoVoittoa();
        assertEquals(false, tosi);
        arvonta.pelaaja.valitseKortti(1);
        tosi = arvonta.onkoVoittoa();
        assertEquals(false, tosi);
        arvonta.pelaaja.valitseKortti(2);
        tosi = arvonta.onkoVoittoa();
        assertEquals(true, tosi);
        arvonta.pelaaja.tyhjennaRivi();
        tosi = arvonta.onkoVoittoa();
        assertEquals(false,tosi);
        

    }

    @Test
    public void voitonmaksuYhdellaKortilla() {
        arvonta.setSaldo(10);
        arvonta.setPanos(1);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.suoritaVoitonmaksu();
        assertEquals(15.0, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(0.5);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.suoritaVoitonmaksu();
        assertEquals(15.0, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(0.6);
        arvonta.pelaaja.valitseKortti(1);
        arvonta.suoritaVoitonmaksu();
        assertEquals(18, arvonta.pelaaja.getSaldo(), 0.0001);
    }

    @Test
    public void voitonmaksuKahdellaKortilla() {
        arvonta.setSaldo(6);
        arvonta.setPanos(1);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(1);
        arvonta.suoritaVoitonmaksu();
        assertEquals(14, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(0.5);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.suoritaVoitonmaksu();
        assertEquals(15, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(0.6);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(5);
        arvonta.suoritaVoitonmaksu();
        assertEquals(15, arvonta.pelaaja.getSaldo(), 0.0001);
    }

    @Test
    public void voitonmaksuKolmellaKortilla() {
        arvonta.setSaldo(6);
        arvonta.setPanos(1);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(1);
        arvonta.pelaaja.valitseKortti(4);
        arvonta.suoritaVoitonmaksu();
        assertEquals(24, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(0.4);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(1);
        arvonta.suoritaVoitonmaksu();
        assertEquals(26, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(1);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(5);
        arvonta.pelaaja.valitseKortti(1);
        arvonta.suoritaVoitonmaksu();
        assertEquals(27, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(0.6);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(5);
        arvonta.pelaaja.valitseKortti(1);
        arvonta.suoritaVoitonmaksu();
        assertEquals(27.6, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.setPanos(0.6);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(5);
        arvonta.pelaaja.valitseKortti(7);
        arvonta.suoritaVoitonmaksu();
        assertEquals(27.6, arvonta.pelaaja.getSaldo(), 0.0001);
    }

    @Test
    public void voitonmaksuNeljallaKortilla() {
        arvonta.setSaldo(6);
        arvonta.setPanos(1);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(1);
        arvonta.pelaaja.valitseKortti(4);
        arvonta.pelaaja.valitseKortti(10);
        arvonta.suoritaVoitonmaksu();
        assertEquals(26, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(0.6);
        arvonta.pelaaja.valitseKortti(4);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(1);
        arvonta.pelaaja.valitseKortti(5);
        arvonta.suoritaVoitonmaksu();
        assertEquals(32, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(1);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(5);
        arvonta.pelaaja.valitseKortti(1);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.suoritaVoitonmaksu();
        assertEquals(37, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(0.5);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(5);
        arvonta.pelaaja.valitseKortti(7);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.suoritaVoitonmaksu();
        assertEquals(37, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(1);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(5);
        arvonta.pelaaja.valitseKortti(7);
        arvonta.pelaaja.valitseKortti(8);
        arvonta.suoritaVoitonmaksu();
        assertEquals(37, arvonta.pelaaja.getSaldo(), 0.0001);
    }

    @Test
    public void voitonmaksuViidellaKortilla() {
        arvonta.setSaldo(6);
        arvonta.setPanos(0.2);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(1);
        arvonta.pelaaja.valitseKortti(4);
        arvonta.pelaaja.valitseKortti(10);
        arvonta.pelaaja.valitseKortti(15);
        arvonta.suoritaVoitonmaksu();
        assertEquals(16, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(1);
        arvonta.pelaaja.valitseKortti(4);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(1);
        arvonta.pelaaja.valitseKortti(10);
        arvonta.pelaaja.valitseKortti(7);
        arvonta.suoritaVoitonmaksu();
        assertEquals(46, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(0.2);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(5);
        arvonta.pelaaja.valitseKortti(1);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(4);
        arvonta.suoritaVoitonmaksu();
        assertEquals(48, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(1);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(5);
        arvonta.pelaaja.valitseKortti(7);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(1);
        arvonta.suoritaVoitonmaksu();
        assertEquals(50, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(1);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(5);
        arvonta.pelaaja.valitseKortti(7);
        arvonta.pelaaja.valitseKortti(2);
        arvonta.pelaaja.valitseKortti(8);
        arvonta.suoritaVoitonmaksu();
        assertEquals(50, arvonta.pelaaja.getSaldo(), 0.0001);
        arvonta.pelaaja.tyhjennaRivi();
        arvonta.setPanos(1);
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(5);
        arvonta.pelaaja.valitseKortti(7);
        arvonta.pelaaja.valitseKortti(9);
        arvonta.pelaaja.valitseKortti(8);
        arvonta.suoritaVoitonmaksu();
        assertEquals(50, arvonta.pelaaja.getSaldo(), 0.0001);
    }

    @Test
    public void valitutJaArvotutNumerotTyhjennetaan() {
        arvonta.pelaaja.valitseKortti(3);
        arvonta.pelaaja.valitseKortti(5);
        arvonta.pelaaja.valitseKortti(7);
        arvonta.pelaaja.valitseKortti(9);
        arvonta.pelaaja.valitseKortti(8);
        arvonta.valmistaUuttaPelia();
        assertEquals(0,arvonta.arvotutNumerot.size());
        assertEquals(0,arvonta.pelaaja.montakoValittuaKorttia());        
    }    
    
}
