package tommitetris.t.tetris;

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

    public void annaTetrimino(Tetrimino tetrimino) {
        this.tetrimino = tetrimino;
    }

    public void tetriminoKaivoon() {
        int aloituskohta = this.leveys / 2 - this.tetrimino.aloitusPaikanKeskittaja();
        for (Pala laatikko : this.tetrimino.getPalat()) {
            laatikko.setX(laatikko.getX() + aloituskohta);
            System.out.println(laatikko.getX() + "," + laatikko.getY());
        }
        tetriminoRuudukkoon();
    }

    private void tetriminoRuudukkoon() {
        for (Pala laatikko : this.tetrimino.getPalat()) {
            this.ruudukko[laatikko.getX()][laatikko.getY()] = laatikko;
        }
    }

    public void tetriminoAlas() {
        tetriminoPoisRuudukosta();
        tetriminoAlempanaRuudukkoon();
    }

    private void tetriminoPoisRuudukosta() {
        for (Pala laatikko : this.tetrimino.getPalat()) {
            this.ruudukko[laatikko.getX()][laatikko.getY()] = null;
        }
    }

    private void tetriminoAlempanaRuudukkoon() {
        for (Pala laatikko : this.tetrimino.getPalat()) {
            laatikko.setY(laatikko.getY() + 1);
        }
        tetriminoRuudukkoon();
    }

}
