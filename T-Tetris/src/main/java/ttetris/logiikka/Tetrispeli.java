package ttetris.logiikka;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import ttetris.tetriminot.Tetrimino;
import ttetris.ui.Nappainkuuntelija;
import ttetris.ui.Piirtaja;

/**
 * Luokka luo ja hallinnoi muita tetrispelissä käytettäviä luokkia sekä
 * hallinnoi pelilooppia.
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

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean jatketaanko = suoritaLukitusJaTyhjennys();
        if (jatketaanko) {
            if (this.kaivo.getTetrimino() == null) {
                generoiUusiTetrimino();
            }
            if (!this.pelipaattyy) {
                liikkuukoTetriminoAlas();
                this.piirtaja.repaint();
            } else {
                lopetaPeli();
            }
            this.piirtaja.repaint();
        }
    }

    private boolean suoritaLukitusJaTyhjennys() {
        if (this.lukitusaika.getOnkoaktiivinen()
                | this.kaivo.getPitaakoTyhjentaa()
                | (this.ruudunTyhjennys.getOnkoaktiivinen())) {
            if (this.kaivo.getPitaakoTyhjentaa()) {
                this.ruudunTyhjennys.setOnkoaktiivinen(true);
            }
            if (this.ruudunTyhjennys.getOnkoaktiivinen()) {
                kaivoaPitaaTyhjentaa();
            }
            suoritaLukitusAika();
            return false;
        }
        return true;
    }

    private void kaivoaPitaaTyhjentaa() {
        if (!this.ruudunTyhjennys.onkoAikaaKulunut()) {
            int montarivia = this.kaivo.tyhjennaTaydetRivit();
            this.pisteet += montarivia * 2;
            if (montarivia > 3) {
                this.pisteet += 2;
            }
            this.level.kasvataLeveliaTyhjennetyilla(montarivia);
        }
        this.piirtaja.repaint();
        this.ruudunTyhjennys.vahennaAikaaYhdella();
        if (this.ruudunTyhjennys.getAika() <= 30) {
            this.kaivo.tiputaPalojaTyhjennetyilleRiveille();
        }
        if (this.ruudunTyhjennys.onkoAikaKulunutLoppuun()) {
            this.ruudunTyhjennys.setOnkoaktiivinen(false);
            this.ruudunTyhjennys.aikaAlkuTilaan(false);
        }
    }

    /**
     * Asettaa kaikki atribuutit valmiiksi odottamaan uuden palasen tulemista.
     * Tarkistaa myös, päättyykö peli ja asettaa this.pelipaattyy booleanin
     * vastauksen mukaiseksi.
     */
    public void generoiUusiTetrimino() {
        this.level.kasvataLevelia();
        annaPiirtajalleLevelJaPisteet();
        this.kaivo.setTetrimino(this.tetrimino);
        this.tetrimino = this.randomoija.annaRandomTetrimino();
        this.piirtaja.setTetrimino(this.tetrimino);
        this.pelipaattyy = this.kaivo.uusiTetriminoKaivoon();
    }

    private void liikkuukoTetriminoAlas() {
        tippuukoTetrimino.vahennaAikaa(this.painovoima.annaPainovoima(this.level.getLevel()));
        if (this.kaivo.lukittuuko()) {
            this.lukitusmittari.setOnkoaktiivinen(true);
        }
        if (tippuukoTetrimino.onkoAikaKulunutLoppuun()) {
            tippuukoTetrimino.aikaAlkuTilaan(true);
            this.kaivo.tetriminoAlas();
            this.lukitusmittari.aikaAlkuTilaan(false);
            this.lukitusmittari.setOnkoaktiivinen(false);
        }
        if (this.lukitusmittari.getOnkoaktiivinen()) {
            vahennaLukitusMittaria();

        }
    }

    public void vahennaLukitusMittaria() {
        if (this.kaivo.lukittuuko()) {
            this.lukitusmittari.vahennaAikaaYhdella();
        }
        if (this.lukitusmittari.onkoAikaKulunutLoppuun()) {
            tippuukoTetrimino.aikaAlkuTilaan(false);
            this.lukitusmittari.aikaAlkuTilaan(false);
            this.lukitusmittari.setOnkoaktiivinen(false);
            aloitaLukitusAika();
        }
    }

    /**
     * Asettaa lukitusajan arvoksi true, jolloin lukitusaika suoritetaan
     * seuraavan kerran kun suoritaLukitusAika() metodia kutsutaan.
     */
    public void aloitaLukitusAika() {
        this.nappainkuuntelija.setVoikoOhjata(false);
        this.lukitusmittari.aikaAlkuTilaan(false);
        this.lukitusaika.setOnkoaktiivinen(true);
        this.kaivo.tyhjentyykoKaivo();
    }

    /**
     * Suorittaa lukituksen, jolloin ei voi tehdä mitää muuta kuin odottaa
     * seuraavaa Tetriminoa.
     */
    public void suoritaLukitusAika() {
        if (this.lukitusaika.getOnkoaktiivinen()) {
            this.piirtaja.repaint();
            this.lukitusaika.vahennaAikaaYhdella();
            if (this.lukitusaika.getAika() > 15) {
                this.piirtaja.setLukitusPiirretaan(true);
            } else {
                this.piirtaja.setLukitusPiirretaan(false);
            }
            if (this.lukitusaika.onkoAikaKulunutLoppuun()) {
                this.lukitusaika.aikaAlkuTilaan(false);
                this.lukitusaika.setOnkoaktiivinen(false);
                this.nappainkuuntelija.setVoikoOhjata(true);
                this.kaivo.setTetrimino(null);
            }
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

    /**
     * Kertoo onko tetrispeli päättynyt.
     *
     * @return onko päättynyt
     */
    public boolean paattyikoPeli() {
        return pelipaattyy;
    }

    /**
     * Aloittaa pelin pyörittämisen tulosteena. Käyetään vain jos halutaan
     * testata peliä käsin. Säästetty museointi syistä.
     */
    public void aloita() {
        peliAlkaa();
    }

    /**
     * Käytetään testaamisessa jos halutaan katsoa peliä merkkijono esityksenä.
     * Tämä metodi on ruma ja tarpeeton, mutta säästetty museointi syistä.
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
}
