package ttetris.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PainovoimaTest {

    Painovoima painovoima;

    @Before
    public void setUp() {
        painovoima = new Painovoima();
    }

    @Test
    public void painovoimaAlussaOikein() {
        assertEquals(4, this.painovoima.annaPainovoima(0));
    }

    @Test
    public void painovoimia30JaAntaaKaikki() {
        int uusipainovoima = 0;
        int painovoimienMaara = 0;
        Levellaskuri level = new Levellaskuri();
        for (int i = 0; i < 1000; i++) {
            level.kasvataLeveliaTyhjennetyilla(1);
            if (uusipainovoima != this.painovoima.annaPainovoima(level.getLevel())) {
                uusipainovoima = this.painovoima.annaPainovoima(level.getLevel());
                painovoimienMaara++;
            }
        }
        assertEquals(30, painovoimienMaara);
    }
}
