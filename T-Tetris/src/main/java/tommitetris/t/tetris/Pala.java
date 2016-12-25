package tommitetris.t.tetris;

import java.util.ArrayList;
import java.util.List;

public abstract class Pala {

    private List<Laatikko> laatikot;

    public Pala() {
        this.laatikot = new ArrayList();
    }

    public void luoLaatikot() {
    }

    public List<Laatikko> getLaatikot() {
        return this.laatikot;
    }

    public int aloitusPaikanKeskittaja() {
        //Palan ensimmäinen laatikko aloittaa kaivossa yleensä joko yhden 
        // tai kaksi palaa kaivon keskikohdasta, mutta olkoon oletuksen
        // 2 ja poikkeuksen tullessa voi pala Overrideta tämän metodin.
        return 2;
    }

}
