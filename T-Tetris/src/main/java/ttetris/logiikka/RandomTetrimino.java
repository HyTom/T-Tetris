package ttetris.logiikka;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ttetris.tetriminot.I;
import ttetris.tetriminot.J;
import ttetris.tetriminot.L;
import ttetris.tetriminot.O;
import ttetris.tetriminot.S;
import ttetris.tetriminot.T;
import ttetris.tetriminot.Tetrimino;
import ttetris.tetriminot.Z;

/**
 * Antaa satunnaisesti luotuja Tetrimino olioita.
 */
public class RandomTetrimino {

    Random random;
    List<Tetrimino> historia;
    boolean onkorajoituksia;

    /**
     * Tetrispelin tetris-palojen randomoija. Noudattaa samoja sääntöjä kuin
     * ensimmäinen Tetris Grand Master.
     */
    public RandomTetrimino() {
        this.random = new Random();
        historia = new ArrayList();
        valmisteleHistoria();
        onkorajoituksia = true;
    }

    /**
     * Valitsee randomisti tetriminon. Jos tetrimino löytyy listalta jota luokka
     * pitää, se yrittää max 4 kertaa kunnes sopiva pala löytyy. Sen jälkeen se
     * lisää palan historiaan ja poistaa vanhimman. Ensimmäisen palan
     * antamisella on rajoituksia, se ei voi olla koskaan S, Z tai O.
     * @return Valittu Tetrimino
     */
    public Tetrimino annaRandomTetrimino() {
        Tetrimino palautettava;
        int yrityksia = 0;
        while (true) {
            yrityksia++;
            palautettava = valitseRandomTetrimino();
            if (onkorajoituksia) {
                palautettava = alkuRajoitukset(palautettava);
            }
            if (tarkistetaanHistoria(palautettava)) {
                paivitaHistoria(palautettava);
                onkorajoituksia = false;
                return palautettava;
            } else if (yrityksia >= 4) {
                paivitaHistoria(palautettava);
                onkorajoituksia = false;
                return palautettava;
            }
        }
    }

    private boolean tarkistetaanHistoria(Tetrimino palautettava) {
        for (Tetrimino tetrimino : historia) {
            if (tetrimino.getClass().equals(palautettava.getClass())) {
                return false;
            }
        }
        return true;
    }

    private void paivitaHistoria(Tetrimino palautettava) {
        this.historia.remove(0);
        this.historia.add(palautettava);
    }

    private Tetrimino alkuRajoitukset(Tetrimino palautettava) {
        while (true) {
            if (palautettava.getClass().equals(annaS().getClass())
                    | palautettava.getClass().equals(annaZ().getClass())
                    | palautettava.getClass().equals(annaO().getClass())) {
                palautettava = valitseRandomTetrimino();
            } else {
                break;
            }
        }
        return palautettava;
    }

    private Tetrimino valitseRandomTetrimino() {
        int luku = this.random.nextInt(7);
        switch (luku) {
            case 0:
                return annaI();
            case 1:
                return annaJ();
            case 2:
                return annaL();
            case 3:
                return annaS();
            case 4:
                return annaZ();
            case 5:
                return annaT();
            default:
                return annaO();
        }
    }

    public I annaI() {
        I i = new I();
        return i;
    }

    public J annaJ() {
        J j = new J();
        return j;
    }

    public L annaL() {
        L l = new L();
        return l;
    }

    public S annaS() {
        S s = new S();
        return s;
    }

    public Z annaZ() {
        Z z = new Z();
        return z;
    }

    public T annaT() {
        T t = new T();
        return t;
    }

    public O annaO() {
        O o = new O();
        return o;
    }

    /**
     * Käytä Random luokan nextInt() metodia.
     * @param i pois suljettu luku
     * @return random luku joka alle i
     */
    public int annaRandomLukuValilta(int i) {
        return random.nextInt(i);
    }

    private void valmisteleHistoria() {
        for (int i = 0; i < 4; i++) {
            this.historia.add(new Z());
        }
    }
}
