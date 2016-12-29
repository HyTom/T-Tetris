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
        peliAlkaa();
    }

    private void peliAlkaa() {
        boolean pelipaattyy = false;
        int level = -1;
        while (!pelipaattyy) {
            if (this.kaivo.getTetrimino() == null) {
                this.kaivo.setTetrimino(this.tetrimino);
                this.tetrimino = this.randomoija.annaRandomTetrimino();
                pelipaattyy = this.kaivo.tetriminoKaivoon();
                level++;
                System.out.println("LEVEL : " + level);
            }
            this.kaivo.tetriminoVastapaivaan();
            this.kaivo.tetriminoVasemmalle();

            this.kaivo.tulostaKaivo();

            if (!pelipaattyy) {
                this.kaivo.tetriminoAlas();
            }
        }
        System.out.println("Peli on paattynyt!");
    }

}
