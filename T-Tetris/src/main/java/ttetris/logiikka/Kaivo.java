package ttetris.logiikka;

import java.util.ArrayList;
import java.util.List;
import ttetris.tetriminot.Pala;
import ttetris.tetriminot.Tetrimino;

/**
 * Luo tetrispelissä käytetyn pelikentän ja hallinnoi sen toimintaa.
 */
public class Kaivo {

    private int leveys;
    private int korkeus;
    private Pala[][] ruudukko;
    private Tetrimino tetrimino;
    private boolean pitaakotyhjentaa;
    private List<Integer> tyhjennetytRivit;
    private boolean lukittuu;

    /**
     * Luo tetrispelissä käytettävän pelialueen parametrien mukaisesti. Oikea
     * luotu korkeus on annettu korkeus + 1 koska tetrispelissä käytetty katto
     * luodaan mukana.
     *
     * @param leveys pelikentän leveys.
     * @param korkeus pelikentän korkeus laskematta mukaan kattoa.
     */
    public Kaivo(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus + 1;
        ruudukko = new Pala[leveys][this.korkeus];
        asetaKatto();
        this.pitaakotyhjentaa = false;
        this.tyhjennetytRivit = new ArrayList();
        this.lukittuu = false;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public boolean getPitaakoTyhjentaa() {
        return this.pitaakotyhjentaa;
    }

    private void asetaKatto() {
        for (int x = 0; x < this.leveys; x++) {
            Pala pala = new Pala(x, 0);
            pala.setMerkki("   ");
            this.ruudukko[x][0] = pala;
        }
    }

    public Pala[][] getRuudukko() {
        return ruudukko;
    }
/**
 * Palauttaa että voiko pala liikkua alemmaksi.
 * @return Aloittaako lukkiutumisen.
 */
    public boolean lukittuuko() {
        this.tetrimino.alas();
        if (!this.voikoTetriminoOllaTassa()) {
            this.tetrimino.ylos();
            return true;
        }
        this.tetrimino.ylos();
        return false;
    }

    /**
     * Tulostaa merkkeinä miltä pelikenttä tällä hetkellä näyttää.
     */
    public void tulostaKaivo() {
        //käytetään testauksessa
        for (int y = 0; y < this.korkeus; y++) {
            for (int x = 0; x < this.leveys; x++) {
                if (this.ruudukko[x][y] == null) {
                    System.out.print("[.]");
                } else {
                    System.out.print(this.ruudukko[x][y].toString());
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setTetrimino(Tetrimino tetrimino) {
        this.tetrimino = tetrimino;
    }

    public Tetrimino getTetrimino() {
        return tetrimino;
    }

    /**
     * Palauttaa true jos peli päättyy eli tetrimino osuu toiseen tullessaan
     * alas. Tätä voi estää kääntämällä palan enne kuin se tulee alas, jos se on
     * mahdollista
     *
     * @return False jos peli päättyy koska Tetrimino osuu toiseen tullessaan
     * alas tai jos pelillä ei ole Tetrimino oliota.
     */
    public boolean uusiTetriminoKaivoon() {
        if (this.tetrimino == null) {
            return false;
        }
        int aloituskohta = this.leveys / 2 - this.tetrimino.aloitusPaikanKeskittaja();
        for (Pala pala : this.tetrimino.getPalat()) {
            pala.setX(pala.getX() + aloituskohta);
            pala.setY(pala.getY() + 1);
        }
        if (voikoTetriminoOllaTassa()) {
            tetriminoRuudukkoon();
            return false;
        }
        tetriminoRuudukkoon();
        return true;
    }

    private void tetriminoRuudukkoon() {
        for (Pala pala : this.tetrimino.getPalat()) {
            this.ruudukko[pala.getX()][pala.getY()] = pala;
        }
    }

    private void tetriminoPoisRuudukosta() {
        for (Pala pala : this.tetrimino.getPalat()) {
            if (pala.getY() > 0) {
                this.ruudukko[pala.getX()][pala.getY()] = null;
            } else {
                Pala katto = new Pala(pala.getX(), pala.getY());
                katto.setMerkki("   ");
                this.ruudukko[pala.getX()][pala.getY()] = katto;
            }
        }
    }

    /**
     * Liikuttaa viimeksi annettua Tetrimino-oliota kaivossa askeleen alas. Jos
     * ei mahdollista, niin asettaa nykyisen Tetrimino-olion arvoksi null, eli
     * lukittaa Tetriminon Kaivoon. Kysyy myös metodilta tyhjentyykoKaivo() että
     * onko palan lukituksen jälkeen käynyt niin että jokin kaivon riveistä on
     * täytetty. Jos niin on, tyhjentaakoKaivo() asettaa boolean atribuutin
     * pitaatyhjentaa arvoksi true.
     */
    public void tetriminoAlas() {
        this.tetrimino.alas();
        if (voikoTetriminoOllaTassa()) {
            this.tetrimino.ylos();
            tetriminoPoisRuudukosta();
            this.tetrimino.alas();
            this.tetriminoRuudukkoon();
        } else {
            tyhjentyykoKaivo();
            this.tetrimino = null;
        }
    }
    
    private void tyhjentyykoKaivo() {
        for (int y = 0; y < this.korkeus; y++) {
            int taytetyt = 0;
            for (int x = 0; x < this.leveys; x++) {
                if (this.ruudukko[x][y] != null && !this.ruudukko[x][y].toString().equals("   ")) {
                    taytetyt++;
                }
            }
            if (taytetyt == this.leveys) {
                this.pitaakotyhjentaa = true;
                return;
            }
        }

    }

    /**
     * Kääntää viimeksi annettua Tetrimino-oliota vastapaivaan jos pelikentällä
     * mahdollista.
     */
    public void tetriminoVastapaivaan() {
        this.tetrimino.kaannaVastapaivaan();
        if (voikoTetriminoOllaTassa()) {
            this.tetrimino.kaannaMyotapaivaan();
            tetriminoPoisRuudukosta();
            this.tetrimino.kaannaVastapaivaan();
            tetriminoRuudukkoon();
        } else {
            if (!onnistuukoVastapaivaanOikeampana()) {
                onnistuukoVastapaivaanVasempana();
            }
        }
    }

    /**
     * Kääntää viimeksi annettua Tetrimino-oliota myötäpäivään jos pelikentällä
     * mahdollista.
     */
    public void tetriminoMyotapaivaan() {
        this.tetrimino.kaannaMyotapaivaan();
        if (voikoTetriminoOllaTassa()) {
            this.tetrimino.kaannaVastapaivaan();
            tetriminoPoisRuudukosta();
            this.tetrimino.kaannaMyotapaivaan();
            tetriminoRuudukkoon();
        } else {
            if (!onnistuukoMyotapaivaanOikeampana()) {
                onnistuukoMyotapaivaanVasempana();
            }
        }
    }

    /**
     * Liikuttaa viimeksi luotua Tetrimino-oliota oikealle jos pelikentällä
     * mahdollista.
     */
    public void tetriminoOikealle() {
        this.tetrimino.oikealle();
        if (this.voikoTetriminoOllaTassa()) {
            this.tetrimino.vasemmalle();
            tetriminoPoisRuudukosta();
            this.tetrimino.oikealle();
            tetriminoRuudukkoon();
        } else {
            this.tetrimino.vasemmalle();
        }
    }

    /**
     * Liikuttaa viimeksi luotua Tetrimino-oliota oikealle jos pelikentällä
     * mahdollista.
     */
    public void tetriminoVasemmalle() {
        this.tetrimino.vasemmalle();
        if (this.voikoTetriminoOllaTassa()) {
            this.tetrimino.oikealle();
            tetriminoPoisRuudukosta();
            this.tetrimino.vasemmalle();
            tetriminoRuudukkoon();
        } else {
            this.tetrimino.oikealle();
        }
    }

    private boolean voikoTetriminoOllaTassa() {
        for (Pala pala : this.tetrimino.getPalat()) {
            if (pala.getX() < 0 | pala.getX() > this.leveys - 1
                    | pala.getY() < 0 | pala.getY() > this.korkeus - 1) {
                return false;
            }
            if (this.ruudukko[pala.getX()][pala.getY()] != null) {
                if (!this.ruudukko[pala.getX()][pala.getY()].toString().equals("   ")
                        & !this.tetrimino.getPalat().contains(this.ruudukko[pala.getX()][pala.getY()])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean onnistuukoVasempana() {
        this.tetrimino.vasemmalle();
        if (voikoTetriminoOllaTassa()) {
            this.tetrimino.oikealle();
            return true;
        }
        this.tetrimino.oikealle();
        return false;
    }

    private boolean onnistuukoOikeampana() {
        this.tetrimino.oikealle();
        if (voikoTetriminoOllaTassa()) {
            this.tetrimino.vasemmalle();
            return true;
        }
        this.tetrimino.vasemmalle();
        return false;
    }

    private boolean onnistuukoVastapaivaanOikeampana() {
        if (onnistuukoOikeampana()) {
            this.tetrimino.kaannaMyotapaivaan();
            tetriminoPoisRuudukosta();
            this.tetrimino.oikealle();
            this.tetrimino.kaannaVastapaivaan();
            tetriminoRuudukkoon();
            return true;
        }
        return false;
    }

    private void onnistuukoVastapaivaanVasempana() {
        if (onnistuukoVasempana()) {
            this.tetrimino.kaannaMyotapaivaan();
            tetriminoPoisRuudukosta();
            this.tetrimino.vasemmalle();
            this.tetrimino.kaannaVastapaivaan();
            tetriminoRuudukkoon();
        } else {
            this.tetrimino.kaannaMyotapaivaan();
        }
    }

    private boolean onnistuukoMyotapaivaanOikeampana() {
        if (onnistuukoOikeampana()) {
            this.tetrimino.kaannaVastapaivaan();
            tetriminoPoisRuudukosta();
            this.tetrimino.oikealle();
            this.tetrimino.kaannaMyotapaivaan();
            tetriminoRuudukkoon();
            return true;
        }
        return false;
    }

    private void onnistuukoMyotapaivaanVasempana() {
        if (onnistuukoVasempana()) {
            this.tetrimino.kaannaVastapaivaan();
            tetriminoPoisRuudukosta();
            this.tetrimino.vasemmalle();
            this.tetrimino.kaannaMyotapaivaan();
            tetriminoRuudukkoon();
        } else {
            this.tetrimino.kaannaVastapaivaan();
        }
    }

    /**
     * Tyhjentaa taydet rivit, mutta ei tiputa palasia.
     *
     * @return Monta rivia tyhjennettiin.
     */
    public int tyhjennaTaydetRivit() {
        for (int y = 0; y < this.korkeus; y++) {
            int taytetty = 0;
            for (int x = 0; x < this.leveys; x++) {
                if (this.ruudukko[x][y] != null && !this.ruudukko[x][y].toString().equals("   ")) {
                    taytetty++;
                }
            }
            if (taytetty == this.leveys) {
                tyhjennaRivi(y);
                this.tyhjennetytRivit.add(y);
            }
        }
        this.pitaakotyhjentaa = false;
        return this.tyhjennetytRivit.size();
    }

    private void tyhjennaRivi(int y) {
        for (int x = 0; x < this.leveys; x++) {
            this.ruudukko[x][y] = null;
        }
    }

    /**
     * Tiputtaa paloja jotka ovat tyhjennettyjen rivien yläpuolella.
     */
    public void tiputaPalojaTyhjennetyilleRiveille() {
        for (Integer i : this.tyhjennetytRivit) {
            for (int y = i - 1; y > 0; y--) {
                for (int x = 0; x < this.leveys; x++) {
                    if (this.ruudukko[x][y] != null && !this.ruudukko[x][y].toString().equals("   ")) {
                        Pala pala = this.ruudukko[x][y];
                        this.ruudukko[x][y + 1] = pala;
                        this.ruudukko[x][y] = null;
                    }
                }
            }
        }
        this.tyhjennetytRivit.clear();
    }

}
