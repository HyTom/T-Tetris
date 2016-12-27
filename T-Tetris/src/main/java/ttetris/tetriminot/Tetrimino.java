package ttetris.tetriminot;

import java.util.ArrayList;
import java.util.List;

public abstract class Tetrimino {

    private List<Pala> palat;

    public Tetrimino() {
        this.palat = new ArrayList();
    }

    protected void luoPalat() {
    }

    public List<Pala> getPalat() {
        return this.palat;
    }

    public int aloitusPaikanKeskittaja() {
        //Tetrinimon ensimmäinen laatikko aloittaa kaivossa yleensä joko yhden 
        // tai kaksi palaa kaivon keskikohdasta vasemmalle, mutta olkoon oletuksen
        // 2 ja poikkeuksen tullessa voi tetrimino Overrideta tämän metodin.
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
    
    

}
