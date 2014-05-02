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

    public KenoarvontaTest() {
    }

    @Before
    public void setUp() {
        arvonta = new Kenoarvonta();
        arvonta.setSaldo(5);

    }

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
        assertEquals(0, arvonta.pelaaja.getSaldo(), 0.00001);
    }

    @Test
    public void kasvataPanostaToimii() {
        arvonta.setPanos(0.2);
        assertEquals(0.2, arvonta.getPanos(), 0.00001);
        arvonta.kasvataPanosta();
        assertEquals(0.4, arvonta.getPanos(), 0.00001);
        arvonta.kasvataPanosta();
        assertEquals(0.6, arvonta.getPanos(), 0.00001);
        arvonta.kasvataPanosta();
        assertEquals(0.8, arvonta.getPanos(), 0.00001);
        arvonta.kasvataPanosta();
        assertEquals(1.0, arvonta.getPanos(), 0.00001);
        arvonta.kasvataPanosta();
        assertEquals(0.2, arvonta.getPanos(), 0.00001);
    }

    @Test
    public void getSaldoToimii() {
        arvonta.setSaldo(1.5);
        assertEquals(1.5, arvonta.getSaldo(), 0.00001);
    }

    @Test
    public void arvontaPaattynytEiErehdy() {
        arvonta.arvotutNumerot.add(33);
        arvonta.arvotutNumerot.add(34);
        assertEquals(false, arvonta.onkoArvontaPaattynyt());
        arvonta.valmistaUuttaPelia();
        assertEquals(true, arvonta.onkoArvontaPaattynyt());
    }

    @Test
    public void tuplausEiLahoa() {
        arvonta.setPanos(1.0);
        arvonta.pelaaja.setTuplaus(1);
        for (int i = 0; i < 10000; i++) {
            arvonta.tuplaaVoitto();
            if (arvonta.tupla.korttiPieniVaiSuuri() == 1) {
                assertEquals(2, arvonta.getPanos(), 0.0001);
            } else if (arvonta.tupla.korttiPieniVaiSuuri() == 1) {
                assertEquals(1, arvonta.getPanos(), 0.0001);
            } else {
                assertEquals(0, arvonta.getPanos(), 0.0001);
            }
            arvonta.setPanos(1);
        }
        arvonta.pelaaja.setTuplaus(3);
        for (int i = 0; i < 10000; i++) {
            arvonta.tuplaaVoitto();
            if (arvonta.tupla.korttiPieniVaiSuuri() == 3) {
                assertEquals(2, arvonta.getPanos(), 0.0001);
            } else if (arvonta.tupla.korttiPieniVaiSuuri() == 1) {
                assertEquals(1, arvonta.getPanos(), 0.0001);
            } else {
                assertEquals(0, arvonta.getPanos(), 0.0001);
            }
            arvonta.setPanos(1);
        }
    }
    
    
}
