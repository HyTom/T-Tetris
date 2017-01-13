package ttetris.logiikka;

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

    public RandomTetrimino() {
        this.random = new Random();
    }

    /**
     * Antaa satunnaisesti luodun Tetrimino olion.
     */
    public Tetrimino annaRandomTetrimino() {
        //Pelin ei ole tarkoitus antaa paloja täysin randomisti, niinkuin nyt.
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

    public int annaRandomLukuValilta(int i) {
        return random.nextInt(i);
    }
}
