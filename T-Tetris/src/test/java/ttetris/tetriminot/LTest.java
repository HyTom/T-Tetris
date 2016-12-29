
package ttetris.tetriminot;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LTest {
    
    L l;

    @Before
    public void setUp() {
        l = new L();
    }

    @Test
    public void luodessaISisältääNeljäPalaa() {
        assertEquals(4, l.getPalat().size());
    }
    
    @Test
    public void merkitOikein() {
        for (Pala pala : l.getPalat()) {
            assertEquals("[L]", pala.toString());
        }
    }
}
