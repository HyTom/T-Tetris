package ttetris.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ttetris.tetriminot.I;
import ttetris.tetriminot.O;
import ttetris.tetriminot.Pala;

public class KaivoTest {

    Kaivo kaivo;

    @Before
    public void setUp() {
        kaivo = new Kaivo(10, 20);
    }

    @Test
    public void syntyessaRuudukko() {
        Pala[][] ruudukko = new Pala[10][20];
        Assert.assertArrayEquals(ruudukko, kaivo.getRuudukko());
    }

    @Test
    public void tetriminoMeneeOikeinRuudukkoon() {
        I i = new I();
        kaivo.setTetrimino(i);
        kaivo.tetriminoKaivoon();
        assertEquals("[3,0][4,0][5,0][6,0]", kaivo.getTetrimino().toString());
    }

    @Test
    public void tetriminoTippuuAskeleenAlas() {
        I i = new I();
        kaivo.setTetrimino(i);
        kaivo.tetriminoKaivoon();
        kaivo.tetriminoAlas();
        assertEquals("[3,1][4,1][5,1][6,1]", kaivo.getTetrimino().toString());
    }

    @Test
    public void tetriminoIOnRuudukossa() {
        I i = new I();
        kaivo.setTetrimino(i);
        kaivo.tetriminoKaivoon();
        Pala[][] ruudukko = kaivo.getRuudukko();
        assertEquals(null, ruudukko[2][0]);
        assertEquals("[I]", ruudukko[3][0].toString());
        assertEquals("[I]", ruudukko[4][0].toString());
        assertEquals("[I]", ruudukko[5][0].toString());
        assertEquals("[I]", ruudukko[6][0].toString());
    }

    @Test
    public void tetriminoIOnRuudukossaTiputtuaanAskeleenAlas() {
        I i = new I();
        kaivo.setTetrimino(i);
        kaivo.tetriminoKaivoon();
        kaivo.tetriminoAlas();
        Pala[][] ruudukko = kaivo.getRuudukko();
        assertEquals(null, ruudukko[3][0]);
        assertEquals("[I]", ruudukko[3][1].toString());
        assertEquals("[I]", ruudukko[4][1].toString());
        assertEquals("[I]", ruudukko[5][1].toString());
        assertEquals("[I]", ruudukko[6][1].toString());
    }

    public void tiputaKaivoonO() {
        O o = new O();
        kaivo.setTetrimino(o);
        kaivo.tetriminoKaivoon();
    }

    @Test
    public void tetriminoLukittuuOikeinLattiaan() {
        tiputaKaivoonO();
        while (kaivo.getTetrimino() != null) {
            kaivo.tetriminoAlas();
        }
        Pala[][] ruudukko = kaivo.getRuudukko();
        assertEquals("[O]", ruudukko[4][19].toString());
        assertEquals("[O]", ruudukko[5][19].toString());
    }

    @Test
    public void tetriminoLukittuuOikeinToiseenPalaan() {
        tiputaKaivoonO();
        while (kaivo.getTetrimino() != null) {
            kaivo.tetriminoAlas();
        }
        tiputaKaivoonO();
        while (kaivo.getTetrimino() != null) {
            kaivo.tetriminoAlas();
        }
        Pala[][] ruudukko = kaivo.getRuudukko();
        assertEquals("[O]", ruudukko[4][18].toString());
        assertEquals("[O]", ruudukko[5][18].toString());
        assertEquals("[O]", ruudukko[4][17].toString());
        assertEquals("[O]", ruudukko[5][17].toString());
    }

    @Test
    public void peliPaattyyOkein() {
        for (int i = 0; i < 10; i++) {
            tiputaKaivoonO();
            while (kaivo.getTetrimino() != null) {
                kaivo.tetriminoAlas();
            }
        }
        I i = new I();
        kaivo.setTetrimino(i);
        assertEquals(true, kaivo.tetriminoKaivoon());
    }
}
