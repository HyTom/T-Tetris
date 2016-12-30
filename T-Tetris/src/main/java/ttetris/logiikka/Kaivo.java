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

    public boolean tetriminoKaivoon() {
        //Palauttaa true jos peli päättyy eli
        //tetrimino osuu toiseen tullessaan alas
        //paitsi että peli ei loppuu ainoastaan sillon kun pala
        //menee päällekkäin toisen kanssa, nyt riittää kosketus alhaalla.
        //Korjataan jossain kohtaa.
        int aloituskohta = this.leveys / 2 - this.tetrimino.aloitusPaikanKeskittaja();
        for (Pala pala : this.tetrimino.getPalat()) {
            pala.setX(pala.getX() + aloituskohta);
            pala.setY(pala.getY() + 1);
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

    public void tetriminoVastapaivaan() {
        String voiko = voiKaantuaVastapaivaan();
        if (!voiko.equals("ei")) {
            tetriminoPoisRuudukosta();
            if (voiko.equals("vasemmalle")) {
                this.tetrimino.vasemmalle();
                voiko = "voi";
            }
            if (voiko.equals("oikealle")) {
                this.tetrimino.oikealle();
                voiko = "voi";
            }
            if (voiko.equals("voi")) {
                this.tetrimino.kaannaMyotapaivaan();
                tetriminoRuudukkoon();
            }
        }
    }

    public void tetriminoMyotapaivaan() {
        String voiko = voiKaantuaMyotapaivaan();
        if (!voiko.equals("ei")) {
            tetriminoPoisRuudukosta();
            this.tetrimino.kaannaMyotapaivaan();
            if (voiko.equals("vasemmalle")) {
                this.tetrimino.vasemmalle();
                voiko = "voi";
            }
            if (voiko.equals("oikealle")) {
                this.tetrimino.oikealle();
                voiko = "voi";
            }
            if (voiko.equals("voi")) {
                tetriminoRuudukkoon();
            }
        }
    }

    public void tetriminoOikealle() {
        boolean voiliikkua = false;
        this.tetrimino.oikealle();

        if (this.voikoTetriminoOllaTassa()) {
            voiliikkua = true;
        }
        this.tetrimino.vasemmalle();
        if (voiliikkua) {
            tetriminoPoisRuudukosta();
            this.tetrimino.oikealle();
            tetriminoRuudukkoon();
        }
    }

    public void tetriminoVasemmalle() {
        boolean voiliikkua = false;
        this.tetrimino.vasemmalle();

        if (this.voikoTetriminoOllaTassa()) {
            voiliikkua = true;
        }
        this.tetrimino.oikealle();
        if (voiliikkua) {
            tetriminoPoisRuudukosta();
            this.tetrimino.vasemmalle();
            tetriminoRuudukkoon();
        }
    }

    private String voiKaantuaMyotapaivaan() {
        this.tetrimino.kaannaMyotapaivaan();
        if (voikoTetriminoOllaTassa()) {
            System.out.println("Tetrimino voi olla tassa!");
            this.tetrimino.kaannaVastapaivaan();
            return "voi";
        }
        System.out.println("Ei voi, katsotaan voiko muualla...");
        String novoiko = voiKaantuaMuualla();
        this.tetrimino.kaannaVastapaivaan();
        if (novoiko.equals("ei")) {
            System.out.println("ei");
            return "ei";
        }
        return novoiko;
    }

    private String voiKaantuaVastapaivaan() {
        this.tetrimino.kaannaVastapaivaan();
        if (voikoTetriminoOllaTassa()) {
            this.tetrimino.kaannaMyotapaivaan();
            return "voi";
        }
        String novoiko = voiKaantuaMuualla();
        if (novoiko.equals("ei")) {
            this.tetrimino.kaannaMyotapaivaan();
            return "ei";
        }

        this.tetrimino.kaannaMyotapaivaan();
        return novoiko;
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

    private String voiKaantuaMuualla() {
        if (this.tetrimino.getClass() == I.class) {
            return "ei";
        }
        if (onnistuukoOikeampana()) {
            return "oikealle";
        }
        if (onnistuukoVasempana()) {
            return "vasemmalle";
        }
        return "ei";
    }

    private boolean onnistuukoVasempana() {
        for (Pala pala : this.tetrimino.getPalat()) {
            if (pala.getX() - 1 > this.leveys - 1 | pala.getX() - 1 < 0) {
                return false;
            }
            if (this.ruudukko[pala.getX() - 1][pala.getY()] != null) {
                if (!this.ruudukko[pala.getX() - 1][pala.getY()].toString().equals("   ")
                        && !this.tetrimino.getPalat().contains(this.ruudukko[pala.getX() - 1][pala.getY()])) {
                    System.out.println(pala.getX() + "," + pala.getY());
                    return false;
                }
            }
        }
        return true;
    }

    private boolean onnistuukoOikeampana() {
        for (Pala pala : this.tetrimino.getPalat()) {
            if (pala.getX() + 1 > this.leveys - 1 | pala.getX() + 1 < 0) {
                return false;
            }
            if (this.ruudukko[pala.getX() + 1][pala.getY()] != null) {
                if (!this.ruudukko[pala.getX() + 1][pala.getY()].toString().equals("   ")
                        && !this.tetrimino.getPalat().contains(this.ruudukko[pala.getX() + 1][pala.getY()])) {
                    System.out.println(pala.getX() + "," + pala.getY());
                    return false;
                }
            }
        }
        return true;
    }

}
