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
public class PanosTest {

    Panos panos;

    public PanosTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        panos = new Panos(0.6);
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void panosAsettuu() {
        panos.setPanos(0.2);
        assertEquals(0.2, panos.getPanos(), 0.00001);
        panos.setPanos(0.6);
        assertEquals(0.6, panos.getPanos(), 0.00001);
        panos.setPanos(1.0);
        assertEquals(1.0, panos.getPanos(), 0.00001);
    }
}
