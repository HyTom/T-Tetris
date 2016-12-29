package ttetris.tetriminot;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OTest {

    O o;

    @Before
    public void setUp() {
        o = new O();
    }

    @Test
    public void luodessaISisältääNeljäPalaa() {
        assertEquals(4, o.getPalat().size());
    }

    @Test
    public void merkitOikein() {
        for (Pala pala : o.getPalat()) {
            assertEquals("[O]", pala.toString());
        }
    }

    @Test
    public void aloitusPaikanKeskittäjäOkein() {
        assertEquals(1, o.aloitusPaikanKeskittaja());
    }
}
