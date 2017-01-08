package ttetris.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ttetris.tetriminot.I;
import ttetris.tetriminot.O;
import ttetris.tetriminot.Pala;
import ttetris.tetriminot.T;

public class KaivoTest {

    Kaivo kaivo;

    @Before
    public void setUp() {
        kaivo = new Kaivo(10, 20);
    }

    @Test
    public void syntyessaRuudukko() {
        Pala[][] ruudukko = new Pala[10][21];
        assertEquals(ruudukko.length, kaivo.getRuudukko().length);
        assertEquals(ruudukko[0].length, kaivo.getRuudukko()[0].length);
    }

    @Test
    public void tetriminoMeneeOikeinRuudukkoon() {
        I i = new I();
        kaivo.setTetrimino(i);
        kaivo.uusiTetriminoKaivoon();
        assertEquals("[3,1][4,1][5,1][6,1]", kaivo.getTetrimino().toString());
    }

    @Test
    public void tetriminoTippuuAskeleenAlas() {
        I i = new I();
        kaivo.setTetrimino(i);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoAlas();
        assertEquals("[3,2][4,2][5,2][6,2]", kaivo.getTetrimino().toString());
    }

    @Test
    public void tetriminoIOnRuudukossa() {
        I i = new I();
        kaivo.setTetrimino(i);
        kaivo.uusiTetriminoKaivoon();
        Pala[][] ruudukko = kaivo.getRuudukko();
        assertEquals("   ", ruudukko[2][0].toString());
        assertEquals(null, ruudukko[3][2]);
        assertEquals("[I]", ruudukko[3][1].toString());
        assertEquals("[I]", ruudukko[4][1].toString());
        assertEquals("[I]", ruudukko[5][1].toString());
        assertEquals("[I]", ruudukko[6][1].toString());
    }

    @Test
    public void tetriminoIOnRuudukossaTiputtuaanAskeleenAlas() {
        I i = new I();
        kaivo.setTetrimino(i);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoAlas();
        Pala[][] ruudukko = kaivo.getRuudukko();
        assertEquals("   ", ruudukko[3][0].toString());
        assertEquals(null, ruudukko[3][1]);
        assertEquals("[I]", ruudukko[3][2].toString());
        assertEquals("[I]", ruudukko[4][2].toString());
        assertEquals("[I]", ruudukko[5][2].toString());
        assertEquals("[I]", ruudukko[6][2].toString());
    }

    public void tiputaKaivoonO() {
        O o = new O();
        kaivo.setTetrimino(o);
        kaivo.uusiTetriminoKaivoon();
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
        assertEquals(true, kaivo.uusiTetriminoKaivoon());
    }

    @Test
    public void antaaFalseJosEiPalaa() {
        assertEquals(false, kaivo.uusiTetriminoKaivoon());
    }

    @Test
    public void poistaaRuudukostaOikein() {
        I i = new I();
        kaivo.setTetrimino(i);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoAlas();
        assertEquals(kaivo.getRuudukko()[3][1], null);
        assertEquals(kaivo.getRuudukko()[4][1], null);
        assertEquals(kaivo.getRuudukko()[5][1], null);
        assertEquals(kaivo.getRuudukko()[6][1], null);
    }

    @Test
    public void eiPoistaKattoa() {
        I i = new I();
        kaivo.setTetrimino(i);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoMyotapaivaan();
        assertEquals(kaivo.getRuudukko()[5][0].toString(), "[I]");
        kaivo.tetriminoAlas();
        assertEquals(kaivo.getRuudukko()[5][0].toString(), "   ");
    }

    @Test
    public void myotapaivaanKaantaminenToimii() {
        T t = new T();
        kaivo.setTetrimino(t);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoMyotapaivaan();
        assertEquals(kaivo.getRuudukko()[3][1].toString(), "[T]");
        assertEquals(kaivo.getRuudukko()[4][1].toString(), "[T]");
        assertEquals(kaivo.getRuudukko()[4][0].toString(), "[T]");
        assertEquals(kaivo.getRuudukko()[4][2].toString(), "[T]");
    }

    @Test
    public void vastapaivaanKaantaminenToimii() {
        T t = new T();
        kaivo.setTetrimino(t);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoVastapaivaan();
        assertEquals(kaivo.getRuudukko()[5][1].toString(), "[T]");
        assertEquals(kaivo.getRuudukko()[4][1].toString(), "[T]");
        assertEquals(kaivo.getRuudukko()[4][0].toString(), "[T]");
        assertEquals(kaivo.getRuudukko()[4][2].toString(), "[T]");
    }

    @Test
    public void tetriminoLiikkuuVasemmalle() {
        O o = new O();
        kaivo.setTetrimino(o);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoVasemmalle();
        kaivo.tetriminoVasemmalle();
        assertEquals(kaivo.getRuudukko()[2][1].toString(), "[O]");
        assertEquals(kaivo.getRuudukko()[2][2].toString(), "[O]");
        assertEquals(kaivo.getRuudukko()[3][1].toString(), "[O]");
        assertEquals(kaivo.getRuudukko()[3][2].toString(), "[O]");
    }

    @Test
    public void tetriminoLiikkuuOikealle() {
        O o = new O();
        kaivo.setTetrimino(o);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoOikealle();
        kaivo.tetriminoOikealle();
        assertEquals(kaivo.getRuudukko()[6][1].toString(), "[O]");
        assertEquals(kaivo.getRuudukko()[6][2].toString(), "[O]");
        assertEquals(kaivo.getRuudukko()[7][1].toString(), "[O]");
        assertEquals(kaivo.getRuudukko()[7][2].toString(), "[O]");
    }

    @Test
    public void eisiirraVasemmalleJosEivoi() {
        O o = new O();
        kaivo.setTetrimino(o);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoVasemmalle();
        kaivo.tetriminoVasemmalle();
        kaivo.tetriminoVasemmalle();
        T t = new T();
        kaivo.setTetrimino(t);
        kaivo.uusiTetriminoKaivoon();
        assertEquals(kaivo.getRuudukko()[4][2].toString(), "[T]");
        kaivo.tetriminoVasemmalle();
        assertEquals(kaivo.getRuudukko()[4][2].toString(), "[T]");
    }

    @Test
    public void eisiirraOikealleJosEivoi() {
        O o = new O();
        kaivo.setTetrimino(o);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoOikealle();
        kaivo.tetriminoOikealle();
        T t = new T();
        kaivo.setTetrimino(t);
        kaivo.uusiTetriminoKaivoon();
        assertEquals(kaivo.getRuudukko()[4][2].toString(), "[T]");
        kaivo.tetriminoOikealle();
        assertEquals(kaivo.getRuudukko()[4][2].toString(), "[T]");
    }

    @Test
    public void palaTyontaaitsensaSeinastaKaantuessaanVasemmalla() {
        T t = new T();
        kaivo.setTetrimino(t);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoVastapaivaan();
        for (int i = 0; i < 4; i++) {
            kaivo.tetriminoVasemmalle();
        }
        kaivo.tetriminoMyotapaivaan();
        assertEquals(kaivo.getRuudukko()[0][1].toString(), "[T]");
        assertEquals(kaivo.getRuudukko()[1][1].toString(), "[T]");
        assertEquals(kaivo.getRuudukko()[2][1].toString(), "[T]");
        assertEquals(kaivo.getRuudukko()[1][2].toString(), "[T]");
    }

    @Test
    public void palaTyontaaitsensaSeinastaKaantuessaanOikealla() {
        T t = new T();
        kaivo.setTetrimino(t);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoMyotapaivaan();
        for (int i = 0; i < 5; i++) {
            kaivo.tetriminoOikealle();
        }
        kaivo.tetriminoVastapaivaan();
        assertEquals(kaivo.getRuudukko()[7][1].toString(), "[T]");
        assertEquals(kaivo.getRuudukko()[8][1].toString(), "[T]");
        assertEquals(kaivo.getRuudukko()[9][1].toString(), "[T]");
        assertEquals(kaivo.getRuudukko()[8][2].toString(), "[T]");
    }

    @Test
    public void tunnistaaTaydenRivin() {
        I i1 = new I();
        I i2 = new I();
        O o = new O();
        kaivo.setTetrimino(i1);
        while (kaivo.getTetrimino() != null) {
            kaivo.tetriminoVasemmalle();
            kaivo.tetriminoAlas();
        }
        kaivo.setTetrimino(i2);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoOikealle();
        while (kaivo.getTetrimino() != null) {
            kaivo.tetriminoAlas();
        }
        kaivo.setTetrimino(o);
        while (kaivo.getTetrimino() != null) {
            kaivo.tetriminoOikealle();
            kaivo.tetriminoAlas();
        }
        assertEquals(true, kaivo.getPitaakoTyhjentaa());
    }

    @Test
    public void osaaPoistaaTaydenRivin() {
        I i1 = new I();
        I i2 = new I();
        O o = new O();
        kaivo.setTetrimino(i1);
        while (kaivo.getTetrimino() != null) {
            kaivo.tetriminoVasemmalle();
            kaivo.tetriminoAlas();
        }
        kaivo.setTetrimino(i2);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoOikealle();
        while (kaivo.getTetrimino() != null) {
            kaivo.tetriminoAlas();
        }
        kaivo.setTetrimino(o);
        while (kaivo.getTetrimino() != null) {
            kaivo.tetriminoOikealle();
            kaivo.tetriminoAlas();
        }
        kaivo.tyhjennaTaydetRivit();
        kaivo.tulostaKaivo();
        for (int x = 0; x < kaivo.getLeveys(); x++) {
            assertEquals(null, kaivo.getRuudukko()[x][20]);
        }
    }

    @Test
    public void osaaTiputtaaPalasiaRivinVerranRivinPoistonJalkeen() {
        I i1 = new I();
        I i2 = new I();
        O o = new O();
        kaivo.setTetrimino(i1);
        while (kaivo.getTetrimino() != null) {
            kaivo.tetriminoVasemmalle();
            kaivo.tetriminoAlas();
        }
        kaivo.setTetrimino(i2);
        kaivo.uusiTetriminoKaivoon();
        kaivo.tetriminoOikealle();
        while (kaivo.getTetrimino() != null) {
            kaivo.tetriminoAlas();
        }
        kaivo.setTetrimino(o);
        while (kaivo.getTetrimino() != null) {
            kaivo.tetriminoOikealle();
            kaivo.tetriminoAlas();
        }
        kaivo.tyhjennaTaydetRivit();
        kaivo.tiputaPalojaTyhjennetyilleRiveille();
        assertEquals("[O]", kaivo.getRuudukko()[8][20].toString());
        assertEquals("[O]", kaivo.getRuudukko()[9][20].toString());
    }
}
