
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
public class KorttiTest {
    Kortti kortti;
    public KorttiTest() {
    }
    
    @Test
    public void toStringOikein(){
        kortti = new Kortti(1);
        assertEquals("kortti_1", kortti.toString());

    }
}
