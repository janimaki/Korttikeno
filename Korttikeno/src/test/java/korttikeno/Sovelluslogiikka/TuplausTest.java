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
public class TuplausTest {
    Tuplaus tuplaus;
    
    public TuplausTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tuplaus = new Tuplaus();
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void kortinVertailuKunnossa() {
         tuplaus.kortti = new Kortti(4,2);         
         assertEquals(0,tuplaus.korttiPieniVaiSuuri());
         tuplaus.kortti = new Kortti(6,2); 
         assertEquals(0,tuplaus.korttiPieniVaiSuuri());
         tuplaus.kortti = new Kortti(11,3); 
         assertEquals(3,tuplaus.korttiPieniVaiSuuri());
         tuplaus.kortti = new Kortti(7,2); 
         assertEquals(1,tuplaus.korttiPieniVaiSuuri());
         tuplaus.kortti = new Kortti(7,3); 
         assertEquals(2,tuplaus.korttiPieniVaiSuuri());
     }
}
