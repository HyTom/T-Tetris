package ttetris.logiikka;

import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import ttetris.tetriminot.Tetrimino;
import ttetris.ui.Nappainkuuntelija;
import ttetris.ui.Piirtaja;

/**
 * Luokka luo ja hallinnoi muita tetrispelissä käytettäviä luokkia.
 */
public class Tetrispeli implements ActionListener {

    private Kaivo kaivo;
    private RandomTetrimino randomoija;
    private Tetrimino tetrimino;
    private Piirtaja piirtaja;
    private int level;
    private int tippuuko;
    private Nappainkuuntelija nappaimet;
    private Timer timer;

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

    public Nappainkuuntelija getNappainkuuntelija() {
        return this.nappaimet;
    }

    public void setNappainkuuntelija(Nappainkuuntelija nappaimet) {
        this.nappaimet = nappaimet;
        nappaimet.setKaivo(this.kaivo);
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

    /**
     * Käytetään testaamisessa jos halutaan katsoa peliä merkkijono esityksenä.
     */
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
        if (this.kaivo.getPitaakoTyhjentaa()) {
            int montarivia = this.kaivo.tyhjennaTaydetRivit();
            this.piirtaja.repaint();
            nukuPuolisekuntia();
            this.kaivo.tiputaPalojaTyhjennetyilleRiveille();
            this.piirtaja.repaint();
            nukuPuolisekuntia();
        }
        if (this.kaivo.getTetrimino() == null) {
            this.level++;
            this.kaivo.setTetrimino(this.tetrimino);
            this.tetrimino = this.randomoija.annaRandomTetrimino();
            pelipaattyy = this.kaivo.uusiTetriminoKaivoon();
        }

        this.piirtaja.repaint();

        if (!pelipaattyy) {
            liikkuukoTetriminoAlas();
            this.piirtaja.repaint();
        } else {
            this.timer.stop();
        }
    }

    private void liikkuukoTetriminoAlas() {
        tippuuko += annaPainovoima();
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

    private void nukuPuolisekuntia() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tetrispeli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
