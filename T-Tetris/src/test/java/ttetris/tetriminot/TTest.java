package ttetris.tetriminot;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TTest {

    T t;

    @Before
    public void setUp() {
        t = new T();
    }

    @Test
    public void luodessaISisältääNeljäPalaa() {
        assertEquals(4, t.getPalat().size());
    }
    
    @Test
    public void merkitOikein() {
        for (Pala pala : t.getPalat()) {
            assertEquals("[T]", pala.toString());
        }
    }
}


