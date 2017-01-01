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
        peliAlkaa();
    }

    private void peliAlkaa() {
        boolean pelipaattyy = false;
        int level = -1;
        while (!pelipaattyy) {
            if (this.kaivo.getTetrimino() == null) {
                this.kaivo.setTetrimino(this.tetrimino);
                this.tetrimino = this.randomoija.annaRandomTetrimino();
                pelipaattyy = this.kaivo.uusiTetriminoKaivoon();
                level++;
                System.out.println("LEVEL : " + level);
            }

            this.kaivo.tulostaKaivo();

            if (!pelipaattyy) {
                int luku1 = this.randomoija.annaRandomLukuValilta(2);
                if (luku1 == 0) {
                    this.kaivo.tetriminoMyotapaivaan();
                } else {
                    this.kaivo.tetriminoVastapaivaan();
                }
                int luku2 = this.randomoija.annaRandomLukuValilta(2);
                if (luku2 == 0) {
                    this.kaivo.tetriminoOikealle();
                } else {
                    this.kaivo.tetriminoVasemmalle();
                }
                this.kaivo.tulostaKaivo();
                this.kaivo.tetriminoAlas();
            }
        }
        System.out.println("Peli on paattynyt!");
    }

}
