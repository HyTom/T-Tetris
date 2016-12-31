package ttetris.tetriminot;

import java.util.ArrayList;
import java.util.List;

public abstract class Tetrimino {

    private List<Pala> palat;

    public Tetrimino() {
        this.palat = new ArrayList();
    }

    protected void luoKaantumiset() {
    }

    protected void luoPalat() {
    }

    public List<Pala> getPalat() {
        return this.palat;
    }

    public int aloitusPaikanKeskittaja() {
        //Tetrinimon ensimmäinen laatikko aloittaa kaivossa aina
        //kaksi palaa vasemmalle, paitsi tetrimino o.
        //O voi korvata tämän metodin ykkösellä.
        return 2;
    }

    @Override
    public String toString() {
        String tostring = "";
        for (Pala pala : palat) {
            tostring += "[" + pala.getX() + "," + pala.getY() + "]";
        }
        return tostring;
    }

    public void kaannaVastapaivaan() {
    }

    public void kaannaMyotapaivaan() {
    }

    public void oikealle() {
        for (Pala pala : this.getPalat()) {
            pala.setX(pala.getX() + 1);
        }
    }

    public void vasemmalle() {
        for (Pala pala : this.getPalat()) {
            pala.setX(pala.getX() - 1);
        }
    }
}
