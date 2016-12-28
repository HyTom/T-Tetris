package ttetris.logiikka;

import ttetris.tetriminot.Pala;
import ttetris.tetriminot.Tetrimino;

public class Kaivo {

    private int leveys;
    private int korkeus;
    private Pala[][] ruudukko;
    private Tetrimino tetrimino;

    public Kaivo(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        ruudukko = new Pala[leveys][korkeus];
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

    public boolean tetriminoKaivoon() {
        int aloituskohta = this.leveys / 2 - this.tetrimino.aloitusPaikanKeskittaja();
        for (Pala pala : this.tetrimino.getPalat()) {
            pala.setX(pala.getX() + aloituskohta);
        }
        tetriminoRuudukkoon();
        if (tetriminoLukittuu()) {
            return true;
        }
        return false;
    }

    private void tetriminoRuudukkoon() {
        for (Pala pala : this.tetrimino.getPalat()) {
            this.ruudukko[pala.getX()][pala.getY()] = pala;
        }
    }

    public void tetriminoAlas() {
        if (!tetriminoLukittuu()) {
            tetriminoPoisRuudukosta();
            tetriminoAlempanaRuudukkoon();
        } else {
            this.tetrimino = null;
        }
    }

    private void tetriminoPoisRuudukosta() {
        for (Pala pala : this.tetrimino.getPalat()) {
            this.ruudukko[pala.getX()][pala.getY()] = null;
        }
    }

    private void tetriminoAlempanaRuudukkoon() {
        for (Pala pala : this.tetrimino.getPalat()) {
            pala.setY(pala.getY() + 1);
        }
        tetriminoRuudukkoon();
    }

    private boolean tetriminoLukittuu() {
        for (Pala pala : this.tetrimino.getPalat()) {
            if (this.ruudukko[0].length - 1 == pala.getY()) {
                System.out.println("Lukittui lattiaan");
                return true;
            }
            if (pala.getY() + 1 <= this.ruudukko[0].length - 1) {
                if (this.ruudukko[pala.getX()][pala.getY() + 1] != null
                        && !this.tetrimino.getPalat().contains(this.ruudukko[pala.getX()][pala.getY() + 1])) {
                    System.out.println("Lukittui palan paalle");
                    return true;
                }
            }

        }
        System.out.println("Ei lukittunut");
        return false;
    }

}
