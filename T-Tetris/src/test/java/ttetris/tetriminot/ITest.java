package ttetris.tetriminot;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ttetris.tetriminot.I;
import ttetris.tetriminot.Pala;

public class ITest {

    I i;

    @Before
    public void setUp() {
        i = new I();
    }

    @Test
    public void luodessaISisältääNeljäPalaa() {
        assertEquals(4, i.getPalat().size());
    }
    
    @Test
    public void merkitOikein() {
        for (Pala pala : i.getPalat()) {
            assertEquals("[I]", pala.toString());
        }
    }
}
