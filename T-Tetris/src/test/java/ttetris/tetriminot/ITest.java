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

    @Test
    public void kaantyyOikeinMyotapaivaan() {
        i.kaannaMyotapaivaan();
        assertEquals(2, i.getPalat().get(0).getX());
        assertEquals(-1, i.getPalat().get(0).getY());

        assertEquals(2, i.getPalat().get(1).getX());
        assertEquals(1, i.getPalat().get(1).getY());

        assertEquals(2, i.getPalat().get(2).getX());
        assertEquals(0, i.getPalat().get(2).getY());

        assertEquals(2, i.getPalat().get(3).getX());
        assertEquals(2, i.getPalat().get(3).getY());
    }
}
