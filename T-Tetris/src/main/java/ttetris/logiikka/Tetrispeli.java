package ttetris.logiikka;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import ttetris.tetriminot.Tetrimino;
import ttetris.ui.Piirtaja;

/**
 * Luokka luo ja hallinnoi muita tetrispelissä käytettäviä luokkia.
 */
public class Tetrispeli implements ActionListener {

    private Kaivo kaivo;
    private RandomTetrimino randomoija;
    private Tetrimino tetrimino;
    private Timer timer;
    private Piirtaja piirtaja;
    private int level;
    private int tippuuko;

    public Tetrispeli() {
        this.kaivo = new Kaivo(10, 20);
        this.randomoija = new RandomTetrimino();
        this.tetrimino = this.randomoija.annaRandomTetrimino();
        this.level = 0;
        this.tippuuko = 0;
    }

    /**
     * Palauttaa pelissä käytetyn Kaivo-olion eli pelikentän.
     *
     * @return Pelissä käytetyn Kaivo-olion.
     */
    public Kaivo getKaivo() {
        return kaivo;
    }

    /**
     * Palauttaa Tetrispelin RandomTetrimino luokan viimeksi luoman
     * Tetrimino-olion.
     *
     * @return Tetrimino-olio.
     */
    public Tetrimino getTetrimino() {
        return tetrimino;
    }

    public void setTetrimino(Tetrimino tetrimino) {
        this.tetrimino = tetrimino;
    }

    public RandomTetrimino getRandomoija() {
        return randomoija;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setPiirtaja(Piirtaja piirtaja) {
        this.piirtaja = piirtaja;
        this.piirtaja.setKaivo(this.kaivo);
    }

    /**
     * TÄLLÄ HETKELLÄ Aloittaa pelin looppaamisen.
     */
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
                    System.out.println("Myotapaivaan");
                    this.kaivo.tetriminoMyotapaivaan();
                } else {
                    System.out.println("Vastapaivaan");
                    this.kaivo.tetriminoVastapaivaan();
                }
                int luku2 = this.randomoija.annaRandomLukuValilta(2);
                if (luku2 == 0) {
                    System.out.println("Oikealle");
                    this.kaivo.tetriminoOikealle();
                } else {
                    System.out.println("Vasemmalle");
                    this.kaivo.tetriminoVasemmalle();
                }
                this.kaivo.tulostaKaivo();

                this.kaivo.tetriminoAlas();
            }
        }
        System.out.println("Peli on paattynyt!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean pelipaattyy = false;
        if (this.kaivo.getTetrimino() == null) {
            this.level++;
            this.kaivo.setTetrimino(this.tetrimino);
            this.tetrimino = this.randomoija.annaRandomTetrimino();
            pelipaattyy = this.kaivo.uusiTetriminoKaivoon();
        }

        this.kaivo.tulostaKaivo();
        this.piirtaja.repaint();

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
            liikkuukoTetriminoAlas();
            this.piirtaja.repaint();
        } else {
            this.timer.stop();
        }
    }

    private void liikkuukoTetriminoAlas() {
        tippuuko += annaPainovoima();
        System.out.println("tippuuko: " + tippuuko);
        if (tippuuko >= 256) {
            tippuuko = 0;
            this.kaivo.tetriminoAlas();
        }
    }

    private int annaPainovoima() {
        if (this.level < 30) {
            return 4;
        } else {
            return 6;
        }
    }
}
