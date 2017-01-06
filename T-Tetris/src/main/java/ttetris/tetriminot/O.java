package ttetris.tetriminot;

import java.awt.Color;

public class O extends Tetrimino {

    public O() {
        super();
        luoPalat();
    }

    @Override
    public int aloitusPaikanKeskittaja() {
        return 1;
    }

    @Override
    protected void luoPalat() {
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 2; x++) {
                Pala pala = new Pala(x, y);
                pala.setMerkki("[O]");
                pala.setVari(this.getVari());
                super.getPalat().add(pala);
            }
        }
    }

    @Override
    public Color getVari() {
        return Color.YELLOW;
    }
    
    

}
