
package ttetris.tetriminot;

public class S extends Tetrimino {

    public S() {
        super();
        luoPalat();
    }

    @Override
    protected void luoPalat() {
        for (int i = 1; i < 3; i++) {
            Pala pala = new Pala(i, 0);
            pala.setMerkki("[S]");
            super.getPalat().add(pala);
        }
        for (int i = 0; i < 2; i++) {
            Pala pala = new Pala(i, 1);
            pala.setMerkki("[S]");
            super.getPalat().add(pala);
        }
    }
    
}
