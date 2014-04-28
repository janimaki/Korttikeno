/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package korttikeno.Sovelluslogiikka;

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

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        arpoja = new Arpoja();
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

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
