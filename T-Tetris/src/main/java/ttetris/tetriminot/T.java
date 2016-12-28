package ttetris.tetriminot;

public class T extends Tetrimino {

    public T() {
        super();
        luoPalat();
    }

    @Override
    protected void luoPalat() {
        for (int i = 0; i < 3; i++) {
            Pala pala = new Pala(i, 0);
            pala.setMerkki("[T]");
            super.getPalat().add(pala);
        }
        Pala pala = new Pala(1, 1);
        pala.setMerkki("[T]");
        super.getPalat().add(pala);
    }

}
