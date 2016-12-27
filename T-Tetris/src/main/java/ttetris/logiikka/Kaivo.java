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

    public void tetriminoKaivoon() {
        int aloituskohta = this.leveys / 2 - this.tetrimino.aloitusPaikanKeskittaja();
        for (Pala pala : this.tetrimino.getPalat()) {
            pala.setX(pala.getX() + aloituskohta);
        }
        tetriminoRuudukkoon();
    }

    private void tetriminoRuudukkoon() {
        for (Pala pala : this.tetrimino.getPalat()) {
            this.ruudukko[pala.getX()][pala.getY()] = pala;
        }
    }

    public void tetriminoAlas() {
        tetriminoPoisRuudukosta();
        tetriminoAlempanaRuudukkoon();
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

}
