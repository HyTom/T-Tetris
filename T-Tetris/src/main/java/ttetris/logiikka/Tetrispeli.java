package ttetris.logiikka;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
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
    private Levellaskuri level;
    private Painovoima painovoima;
    private Pelilaskuri tippuukoTetrimino;
    private Pelilaskuri ruudunTyhjennys;
    private Pelilaskuri lukitusmittari;
    private Pelilaskuri lukitusaika;
    private Nappainkuuntelija nappainkuuntelija;
    private Timer timer;
    private int pisteet;
    private boolean pelipaattyy;

    public Tetrispeli() {
        this.kaivo = new Kaivo(10, 20);
        this.randomoija = new RandomTetrimino();
        this.tetrimino = this.randomoija.annaRandomTetrimino();
        this.level = new Levellaskuri();
        this.ruudunTyhjennys = new Pelilaskuri(60);
        this.tippuukoTetrimino = new Pelilaskuri(256);
        this.lukitusmittari = new Pelilaskuri(31);
        this.lukitusaika = new Pelilaskuri(30);
        this.painovoima = new Painovoima();
        this.pisteet = 0;
        this.pelipaattyy = false;
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
        return this.nappainkuuntelija;
    }

    public Pelilaskuri getLukitusmittari() {
        return lukitusmittari;
    }

    public Pelilaskuri getLukitusaika() {
        return lukitusaika;
    }

    public Pelilaskuri getTippuukoTetrimino() {
        return tippuukoTetrimino;
    }

    public void setNappainkuuntelija(Nappainkuuntelija nappaimet) {
        this.nappainkuuntelija = nappaimet;
        nappaimet.setTetrispeliintarvittavat(this.kaivo, this.lukitusmittari);
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setPiirtaja(Piirtaja piirtaja) {
        this.piirtaja = piirtaja;
        this.piirtaja.setKaivo(this.kaivo);
        this.piirtaja.setTetrimino(this.tetrimino);
        this.piirtaja.setLevelLaskuri(this.level);
        this.piirtaja.setLukitusmittari(this.lukitusmittari);
        annaPiirtajalleLevelJaPisteet();
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
        if (this.lukitusaika.getOnkoaktiivinen()) {
            suoritaLukitusAika();
        } else {
            if (this.kaivo.getPitaakoTyhjentaa()) {
                this.ruudunTyhjennys.setOnkoaktiivinen(true);
            }
            if (this.ruudunTyhjennys.getOnkoaktiivinen()) {
                kaivoaPitaaTyhjentaa();
            } else {
                if (this.kaivo.getTetrimino() == null) {
                    pelipaattyy = generoiUusiTetrimino();
                }
                this.piirtaja.repaint();
                if (!this.pelipaattyy) {
                    liikkuukoTetriminoAlas();
                    this.piirtaja.repaint();
                } else {
                    lopetaPeli();
                }
            }
        }

    }

    private void kaivoaPitaaTyhjentaa() {
        if (!this.ruudunTyhjennys.onkoAikaaKulunut()) {
            int montarivia = this.kaivo.tyhjennaTaydetRivit();
            this.pisteet = this.pisteet + montarivia;
            this.level.kasvataLeveliaTyhjennetyilla(montarivia);
        }
        this.piirtaja.repaint();
        this.ruudunTyhjennys.vahennaAikaaYhdellä();
        if (this.ruudunTyhjennys.getAika() <= 30) {
            this.kaivo.tiputaPalojaTyhjennetyilleRiveille();
        }
        if (this.ruudunTyhjennys.onkoAikaKulunutLoppuun()) {
            this.ruudunTyhjennys.setOnkoaktiivinen(false);
            this.ruudunTyhjennys.aikaAlkuTilaan(false);
        }
    }

    private boolean generoiUusiTetrimino() {
        this.level.kasvataLevelia();
        annaPiirtajalleLevelJaPisteet();
        this.kaivo.setTetrimino(this.tetrimino);
        this.tetrimino = this.randomoija.annaRandomTetrimino();
        this.piirtaja.setTetrimino(this.tetrimino);
        return this.kaivo.uusiTetriminoKaivoon();
    }

    private void liikkuukoTetriminoAlas() {
        tippuukoTetrimino.vahennaAikaa(this.painovoima.annaPainovoima(this.level.getLevel()));
        if (tippuukoTetrimino.onkoAikaKulunutLoppuun()) {
            tippuukoTetrimino.aikaAlkuTilaan(true);
            if (this.kaivo.lukittuuko()) {
                this.lukitusmittari.setOnkoaktiivinen(true);
            } else {
                this.kaivo.tetriminoAlas();
                this.lukitusmittari.aikaAlkuTilaan(false);
            }
        }
        if (this.lukitusmittari.getOnkoaktiivinen()) {
            vahennaLukitusMittaria();

        }
    }

    public void vahennaLukitusMittaria() {
        if (this.kaivo.lukittuuko()) {
            this.lukitusmittari.vahennaAikaaYhdellä();
        }
        if (this.lukitusmittari.onkoAikaKulunutLoppuun()) {
            tippuukoTetrimino.aikaAlkuTilaan(false);
            this.lukitusmittari.aikaAlkuTilaan(false);
            this.lukitusmittari.setOnkoaktiivinen(false);
            aloitaLukitusAika();
        }
    }

    public void aloitaLukitusAika() {
        this.nappainkuuntelija.setVoikoOhjata(false);
        this.lukitusmittari.aikaAlkuTilaan(false);
        this.lukitusaika.setOnkoaktiivinen(true);
    }

    public void suoritaLukitusAika() {
        this.piirtaja.repaint();
        this.lukitusaika.vahennaAikaaYhdellä();
        if (this.lukitusaika.getAika() > 10) {
            this.piirtaja.setLukitusPiirretaan(true);
        } else {
            this.piirtaja.setLukitusPiirretaan(false);
        }
        if (this.lukitusaika.onkoAikaKulunutLoppuun()) {
            this.lukitusaika.aikaAlkuTilaan(false);
            this.lukitusaika.setOnkoaktiivinen(false);
            this.nappainkuuntelija.setVoikoOhjata(true);
            this.kaivo.tetriminoAlas();
        }
    }

    private void annaPiirtajalleLevelJaPisteet() {
        this.piirtaja.setPisteet(this.pisteet);
    }

    private void lopetaPeli() {
        this.nappainkuuntelija.setPelipaattyy(true);
        this.piirtaja.repaint();
        this.timer.stop();
        setPeliPaattyy(true);
    }

    private void setPeliPaattyy(boolean paattyyko) {
        this.pelipaattyy = paattyyko;
    }

    public boolean paattyikoPeli() {
        return pelipaattyy;
    }
}
