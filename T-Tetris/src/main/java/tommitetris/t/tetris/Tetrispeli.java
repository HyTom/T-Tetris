package tommitetris.t.tetris;

class Tetrispeli {

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
    
    public void aloita() {
        this.kaivo.annaTetrimino(this.tetrimino);
        this.kaivo.tetriminoKaivoon();
        this.kaivo.tulostaKaivo();
        this.kaivo.tetriminoAlas();
        this.kaivo.tulostaKaivo();
    }

}
