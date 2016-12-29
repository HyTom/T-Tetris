package ttetris.tetriminot;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class JTest {

    J j;

    @Before
    public void setUp() {
        j = new J();
    }

    @Test
    public void luodessaISisältääNeljäPalaa() {
        assertEquals(4, j.getPalat().size());
    }
    
    @Test
    public void merkitOikein() {
        for (Pala pala : j.getPalat()) {
            assertEquals("[J]", pala.toString());
        }
    }
}
