package ttetris.logiikka;


import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import ttetris.logiikka.Kaivo;
import ttetris.logiikka.RandomTetrimino;
import ttetris.logiikka.Tetrispeli;

public class TetrispeliTest {

    Tetrispeli peli;

    @Before
    public void setUp() {
        peli = new Tetrispeli();
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

}
