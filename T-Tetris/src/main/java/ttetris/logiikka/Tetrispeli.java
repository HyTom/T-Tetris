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
    private int level;
    private int tippuuko;
    private int ruudunTyhjennys;
    private Nappainkuuntelija nappainkuuntelija;
    private Timer timer;
    private int lukitus;
    private boolean peliOdottaaTyhjennysta;
    private Map<Integer, Integer> painovoimat;
    private int pisteet;

    public Tetrispeli() {
        this.kaivo = new Kaivo(10, 20);
        this.randomoija = new RandomTetrimino();
        this.tetrimino = this.randomoija.annaRandomTetrimino();
        this.level = 0;
        this.tippuuko = 0;
        this.lukitus = 30;
        this.peliOdottaaTyhjennysta = false;
        this.ruudunTyhjennys = 60;
        this.luoPainovoimat();
        this.pisteet = 0;
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

    public void setNappainkuuntelija(Nappainkuuntelija nappaimet) {
        this.nappainkuuntelija = nappaimet;
        nappaimet.setKaivo(this.kaivo);
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setPiirtaja(Piirtaja piirtaja) {
        this.piirtaja = piirtaja;
        this.piirtaja.setKaivo(this.kaivo);
        this.piirtaja.setTetrimino(this.tetrimino);
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
        boolean pelipaattyy = false;
        if (this.kaivo.getPitaakoTyhjentaa()) {
            this.peliOdottaaTyhjennysta = true;
        }
        if (!this.peliOdottaaTyhjennysta) {
            if (this.kaivo.getTetrimino() == null) {
                pelipaattyy = generoiUusiTetrimino();
            }
            this.piirtaja.repaint();
            if (!pelipaattyy) {
                liikkuukoTetriminoAlas();
                this.piirtaja.repaint();
            } else {
                this.nappainkuuntelija.setPelipaattyy(true);
                this.timer.stop();
            }
        } else {
            kaivoaPitaaTyhjentaa();
        }
    }

    private void kaivoaPitaaTyhjentaa() {
        if (this.ruudunTyhjennys == 60) {
            int montarivia = this.kaivo.tyhjennaTaydetRivit();
            this.pisteet = this.pisteet + montarivia;
            if (montarivia > 1) {
                this.level = this.level + montarivia;
            }
        }
        this.piirtaja.repaint();
        this.ruudunTyhjennys--;
        if (this.ruudunTyhjennys <= 30) {
            this.kaivo.tiputaPalojaTyhjennetyilleRiveille();
        }
        if (this.ruudunTyhjennys <= 0) {
            this.peliOdottaaTyhjennysta = false;
            this.ruudunTyhjennys = 60;
        }
    }

    private boolean generoiUusiTetrimino() {
        this.level++;
        annaPiirtajalleLevelJaPisteet();
        this.kaivo.setTetrimino(this.tetrimino);
        this.tetrimino = this.randomoija.annaRandomTetrimino();
        this.piirtaja.setTetrimino(this.tetrimino);
        return this.kaivo.uusiTetriminoKaivoon();
    }

    private void liikkuukoTetriminoAlas() {
        tippuuko += annaPainovoima();
        if (this.lukitus == 31) {
            if (tippuuko >= 256) {
                tippuuko = 0;
                if (!this.kaivo.lukittuuko()) {
                    this.kaivo.tetriminoAlas();
                } else {
                    this.lukitus--;
                    this.piirtaja.setLukitus(this.lukitus);
                }
            }
        } else {
            this.lukitus--;
            this.piirtaja.setLukitus(this.lukitus);
            if (this.lukitus <= 0) {
                tippuuko = 0;
                this.kaivo.tetriminoAlas();
                this.piirtaja.setLukitus(31);
                this.lukitus = 31;
            }
        }
    }

    private int annaPainovoima() {
        int leveli = 4;
        for (Integer integer : this.painovoimat.keySet()) {
            if (this.level >= integer) {
                leveli = this.painovoimat.get(integer);
            }
        }
        return leveli;
    }

    private void annaPiirtajalleLevelJaPisteet() {
        this.piirtaja.setLevel(this.level);
        this.piirtaja.setPisteet(this.pisteet);
    }

    private void luoPainovoimat() {
        this.painovoimat = new HashMap();
        painovoimat.put(0, 4);
        painovoimat.put(30, 6);
        painovoimat.put(35, 8);
        painovoimat.put(40, 10);
        painovoimat.put(50, 12);
        painovoimat.put(60, 16);
        painovoimat.put(70, 32);
        painovoimat.put(80, 48);
        painovoimat.put(90, 64);
        painovoimat.put(100, 80);
        painovoimat.put(120, 96);
        painovoimat.put(140, 112);
        painovoimat.put(160, 128);
        painovoimat.put(170, 144);
        painovoimat.put(200, 4);
        painovoimat.put(220, 32);
        painovoimat.put(230, 64);
        painovoimat.put(233, 96);
        painovoimat.put(236, 128);
        painovoimat.put(239, 160);
        painovoimat.put(243, 192);
        painovoimat.put(247, 224);
        painovoimat.put(251, 256);
        painovoimat.put(300, 512);
        painovoimat.put(330, 768);
        painovoimat.put(360, 1024);
        painovoimat.put(400, 1280);
        painovoimat.put(420, 1024);
        painovoimat.put(450, 768);
        painovoimat.put(500, 5120);

    }
}
