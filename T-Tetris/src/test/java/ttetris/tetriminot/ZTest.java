
package ttetris.tetriminot;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ZTest {
    
    Z z;

    @Before
    public void setUp() {
        z = new Z();
    }

    @Test
    public void luodessaISisältääNeljäPalaa() {
        assertEquals(4, z.getPalat().size());
    }
    
    @Test
    public void merkitOikein() {
        for (Pala pala : z.getPalat()) {
            assertEquals("[Z]", pala.toString());
        }
    }
}
