package ttetris.tetriminot;

public class Z extends Tetrimino {

    public Z() {
        super();
        luoPalat();
    }

    @Override
    protected void luoPalat() {
        for (int i = 0; i < 2; i++) {
            Pala pala = new Pala(i, 0);
            pala.setMerkki("[Z]");
            super.getPalat().add(pala);
        }
        for (int i = 1; i < 3; i++) {
            Pala pala = new Pala(i, 1);
            pala.setMerkki("[Z]");
            super.getPalat().add(pala);
        }
    }
    
    

}
