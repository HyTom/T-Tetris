package tommitetris.t.tetris;

public class Kaivo {

    private int leveys;
    private int korkeus;
    private Laatikko[][] ruudukko;
    private Pala pala;

    public Kaivo(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        ruudukko = new Laatikko[leveys][korkeus];
    }

    public Laatikko[][] getRuudukko() {
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

    public void annaPala(Pala pala) {
        this.pala = pala;
    }

    public void palaKaivoon() {
        int aloituskohta = this.leveys / 2 - this.pala.aloitusPaikanKeskittaja();
        for (Laatikko laatikko : this.pala.getLaatikot()) {
            laatikko.setX(laatikko.getX() + aloituskohta);
            System.out.println(laatikko.getX() + "," + laatikko.getY());
        }
        palaRuudukkoon();
    }

    private void palaRuudukkoon() {
        for (Laatikko laatikko : this.pala.getLaatikot()) {
            this.ruudukko[laatikko.getX()][laatikko.getY()] = laatikko;
        }
    }

    public void palaAlas() {
        palaPoisRuudukosta();
        palaAlempanaRuudukkoon();
    }

    private void palaPoisRuudukosta() {
        for (Laatikko laatikko : this.pala.getLaatikot()) {
            this.ruudukko[laatikko.getX()][laatikko.getY()] = null;
        }
    }

    private void palaAlempanaRuudukkoon() {
        for (Laatikko laatikko : this.pala.getLaatikot()) {
            laatikko.setY(laatikko.getY() + 1);
        }
        palaRuudukkoon();
    }

}
