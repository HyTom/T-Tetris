package ttetris.logiikka;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import ttetris.logiikka.Kaivo;
import ttetris.logiikka.RandomTetrimino;
import ttetris.logiikka.Tetrispeli;
import ttetris.tetriminot.O;
import ttetris.tetriminot.Tetrimino;
import ttetris.ui.Nappainkuuntelija;
import ttetris.ui.Piirtaja;

public class TetrispeliTest {

    Tetrispeli peli;

    @Before
    public void setUp() {
        peli = new Tetrispeli();
        peli.setPiirtaja(new Piirtaja());
        peli.setNappainkuuntelija(new Nappainkuuntelija());
    }

    @Test
    public void luodessaKaivo() {
        assertEquals(Kaivo.class, peli.getKaivo().getClass());
    }

    @Test
    public void luodessaRandomoija() {
        assertEquals(RandomTetrimino.class, peli.getRandomoija().getClass());
    }

    @Test
    public void testaaTetriminonTippumistaKaivossa() {
        //Myöhemmin ei voida olettaa että randomoija antaa I:n
        RandomTetrimino random = new RandomTetrimino();
        peli.setTetrimino(random.annaI());
        peli.getKaivo().setTetrimino(peli.getTetrimino());
        peli.getKaivo().uusiTetriminoKaivoon();
        assertEquals("[3,1][4,1][5,1][6,1]", peli.getTetrimino().toString());
        peli.getKaivo().tetriminoAlas();
        assertEquals("[3,2][4,2][5,2][6,2]", peli.getTetrimino().toString());
    }

    @Test
    public void kaivoSaaPelinAlussaTetriminon() {
        Tetrimino palikka = peli.getTetrimino();
        peli.aloita();
        peli.getKaivo().getTetrimino().equals(palikka);
    }

    @Test
    public void peliLopetusToimii1() {
        peli.getKaivo().setTetrimino(new O());
        peli.getKaivo().uusiTetriminoKaivoon();
        peli.generoiUusiTetrimino();

        assertEquals(true, peli.paattyikoPeli());
    }

    @Test
    public void peliLopetusToimii2() {
        peli.generoiUusiTetrimino();

        assertEquals(false, peli.paattyikoPeli());
    }

    @Test
    public void lukitusAikaToimii() {
        O o = new O();
        peli.getKaivo().setTetrimino(o);
        assertEquals(o, this.peli.getKaivo().getTetrimino());
        peli.suoritaLukitusAika();
        assertEquals(o, this.peli.getKaivo().getTetrimino());
        peli.aloitaLukitusAika();
        for (int i = 0; i < 30; i++) {
            peli.suoritaLukitusAika();
        }
        assertEquals(null, this.peli.getKaivo().getTetrimino());
    }

}
