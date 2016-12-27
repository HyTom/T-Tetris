package ttetris.logiikka;

import ttetris.tetriminot.Tetrimino;

public class Tetrispeli {

    private Kaivo kaivo;
    private RandomTetrimino randomoija;
    private Tetrimino tetrimino;

    public Tetrispeli() {
        this.kaivo = new Kaivo(10, 20);
        this.randomoija = new RandomTetrimino();
        this.tetrimino = this.randomoija.annaRandomTetrimino();
    }

    public Kaivo getKaivo() {
        return kaivo;
    }

    public Tetrimino getTetrimino() {
        return tetrimino;
    }

    public void setTetrimino(Tetrimino tetrimino) {
        this.tetrimino = tetrimino;
    }
    
    

    public RandomTetrimino getRandomoija() {
        return randomoija;
    }
    
    
    public void aloita() {
        //tällä hetkellä vain testaa että tetrimino tippuu alas
        this.kaivo.setTetrimino(this.tetrimino);
        this.kaivo.tetriminoKaivoon();
        this.kaivo.tulostaKaivo();
        this.kaivo.tetriminoAlas();
        this.kaivo.tulostaKaivo();
    }

}
