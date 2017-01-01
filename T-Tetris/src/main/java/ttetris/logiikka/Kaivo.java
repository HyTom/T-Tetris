package ttetris.logiikka;

import ttetris.tetriminot.I;
import ttetris.tetriminot.Pala;
import ttetris.tetriminot.Tetrimino;

public class Kaivo {

    private int leveys;
    private int korkeus;
    private Pala[][] ruudukko;
    private Tetrimino tetrimino;

    public Kaivo(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus + 1;
        ruudukko = new Pala[leveys][this.korkeus];
        asetaKatto();
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

    public boolean uusiTetriminoKaivoon() {
        //Palauttaa true jos peli päättyy eli
        //tetrimino osuu toiseen tullessaan alas.
        //Tätä voi estää kääntämällä palan enne kuin se tulee alas,
        //jos se on mahdollista
        if (this.tetrimino == null) {
            System.out.println("Kaivolla ei ole tetriminoa!");
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
            System.out.println(pala.getX() + "," + pala.getY());
            if (pala.getY() > 0) {
                this.ruudukko[pala.getX()][pala.getY()] = null;
            } else {
                Pala katto = new Pala(pala.getX(), pala.getY());
                katto.setMerkki("   ");
                System.out.println(pala.getX() + "," + pala.getY());
                this.ruudukko[pala.getX()][pala.getY()] = katto;
            }
        }
    }

    public void tetriminoAlas() {
        this.tetrimino.alas();
        if (voikoTetriminoOllaTassa()) {
            this.tetrimino.ylos();
            tetriminoPoisRuudukosta();
            this.tetrimino.alas();
            this.tetriminoRuudukkoon();
        } else {
            this.tetrimino = null;
        }
    }

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
        System.out.println("Voi olla tassa");
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

}
