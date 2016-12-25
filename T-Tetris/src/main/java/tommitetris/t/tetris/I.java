package tommitetris.t.tetris;

public class I extends Pala {

    public I() {
        super();
        luoLaatikot();
    }

    @Override
    public void luoLaatikot() {
        for (int i = 0; i < 4; i++) {
            Laatikko laatikko = new Laatikko(i, 0);
            laatikko.setMerkki("[I]");
            super.getLaatikot().add(laatikko);
        }
    }

    
    

}
