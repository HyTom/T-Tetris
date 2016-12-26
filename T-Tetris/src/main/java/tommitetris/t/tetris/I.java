package tommitetris.t.tetris;

public class I extends Tetrimino {

    public I() {
        super();
        luoPalat();
    }

    @Override
    public void luoPalat() {
        for (int i = 0; i < 4; i++) {
            Pala laatikko = new Pala(i, 0);
            laatikko.setMerkki("[I]");
            super.getPalat().add(laatikko);
        }
    }

    
    

}
