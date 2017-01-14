package ttetris.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LevellaskuriTest {

    Levellaskuri level;

    @Before
    public void setUp() {
        level = new Levellaskuri();
    }

    @Test
    public void levelKasvaaOikein1() {
        this.level.kasvataLevelia();
        this.level.kasvataLevelia();
        assertEquals(2, this.level.getLevel());
    }

    @Test
    public void levelKasvaaOikein2() {
        for (int i = 0; i < 120; i++) {
            this.level.kasvataLevelia();
        }
        assertEquals(99, this.level.getLevel());
    }
    
    @Test
    public void tyhjennettaessaKasvattaaYliMaximin() {
        levelKasvaaOikein2();
        this.level.kasvataLeveliaTyhjennetyilla(1);
        assertEquals(100, this.level.getLevel());
    }
    
    public void pelinLapaisyToimiiOikein() {
        for (int i = 0; i < 912; i++) {
            this.level.kasvataLeveliaTyhjennetyilla(i);
        }
        for (int i = 0; i < 100; i++) {
            this.level.kasvataLevelia();
        }
        assertEquals(998, this.level.getLevel());
        this.level.kasvataLeveliaTyhjennetyilla(2);
        this.level.kasvataLevelia();
        assertEquals(999, this.level.getLevel());
    }
}
