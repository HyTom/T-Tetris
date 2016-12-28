package ttetris.tetriminot;

public class J extends Tetrimino {

    public J() {
        super();
        luoPalat();
    }

    @Override
    protected void luoPalat() {
        for (int i = 0; i < 3; i++) {
            Pala pala = new Pala(i, 0);
            pala.setMerkki("[J]");
            super.getPalat().add(pala);
        }
        Pala pala = new Pala(2, 1);
        pala.setMerkki("[J]");
        super.getPalat().add(pala);
    }

}
