package ttetris.tetriminot;

public class L extends Tetrimino {

    public L() {
        super();
        luoPalat();
    }

    @Override
    protected void luoPalat() {
        for (int i = 0; i < 3; i++) {
            Pala pala = new Pala(i, 0);
            pala.setMerkki("[L]");
            super.getPalat().add(pala);
        }
        Pala pala = new Pala(0, 1);
        pala.setMerkki("[L]");
        super.getPalat().add(pala);
    }

}
