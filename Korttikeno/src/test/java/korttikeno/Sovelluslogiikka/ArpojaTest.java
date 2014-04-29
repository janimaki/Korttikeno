
package main.java.korttikeno.Sovelluslogiikka;

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
public class ArpojaTest {

    Arpoja arpoja;

    public ArpojaTest() {
    }

    @Before
    public void setUp() {
        arpoja = new Arpoja();
    }

    @Test
    public void ArvoLuvutValillaYksViiskaks() {
        for (int i = 0; i < 10000; i++) {
            boolean tosi = false;
            int luku = arpoja.arvoLuvut();
            if (luku >= 1 && luku <= 52) {
                tosi = true;
            }
            assertEquals(true, tosi);
        }
    }

}
