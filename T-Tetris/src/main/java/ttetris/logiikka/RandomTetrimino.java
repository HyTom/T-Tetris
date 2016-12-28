package ttetris.logiikka;

import ttetris.tetriminot.I;
import ttetris.tetriminot.J;
import ttetris.tetriminot.L;
import ttetris.tetriminot.O;
import ttetris.tetriminot.S;
import ttetris.tetriminot.T;
import ttetris.tetriminot.Tetrimino;
import ttetris.tetriminot.Z;

public class RandomTetrimino {

    public RandomTetrimino() {
    }
    
    public Tetrimino annaRandomTetrimino() {
        //ei anna vielä randomtetriminoa
        return annaO();
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
}
