package ttetris.tetriminot;

public class I extends Tetrimino {

    public I() {
        super();
        luoPalat();
    }

    @Override
    protected void luoPalat() {
        for (int i = 0; i < 4; i++) {
            Pala pala = new Pala(i, 0);
            pala.setMerkki("[I]");
            super.getPalat().add(pala);
        }
    }

    
    

}
