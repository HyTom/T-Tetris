package tommitetris.t.tetris;

import java.util.ArrayList;
import java.util.List;

public abstract class Tetrimino {

    private List<Pala> palat;

    public Tetrimino() {
        this.palat = new ArrayList();
    }

    public void luoPalat() {
    }

    public List<Pala> getPalat() {
        return this.palat;
    }

    public int aloitusPaikanKeskittaja() {
        //Tetrinimon ensimmäinen laatikko aloittaa kaivossa yleensä joko yhden 
        // tai kaksi palaa kaivon keskikohdasta, mutta olkoon oletuksen
        // 2 ja poikkeuksen tullessa voi pala Overrideta tämän metodin.
        return 2;
    }

}
