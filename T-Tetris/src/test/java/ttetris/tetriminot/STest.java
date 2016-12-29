package ttetris.tetriminot;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class STest {

    S s;

    @Before
    public void setUp() {
        s = new S();
    }

    @Test
    public void luodessaISisältääNeljäPalaa() {
        assertEquals(4, s.getPalat().size());
    }
    
    @Test
    public void merkitOikein() {
        for (Pala pala : s.getPalat()) {
            assertEquals("[S]", pala.toString());
        }
    }
}
