package ttetris.tetriminot;

import java.util.ArrayList;

public class I extends Tetrimino {

    int asento;
    int[][] kaantuminen;

    public I() {
        super();
        luoPalat();
        luoKaantumiset();
        this.asento = 0;
    }

    @Override
    protected void luoPalat() {
        for (int x = 0; x < 4; x++) {
            Pala pala = new Pala(x, 0);
            pala.setMerkki("[I]");
            super.getPalat().add(pala);
        }
    }

    @Override
    protected void luoKaantumiset() {
        int[][] kaantuminen = {{2, -1}, {1, 1}, {-1, 2}};
        this.kaantuminen = kaantuminen;
    }

    @Override
    public void kaannaMyotapaivaan() {
        if (this.asento == 1) {
            super.getPalat().get(0).setX(super.getPalat().get(0).getX() - kaantuminen[0][0]);
            super.getPalat().get(0).setY(super.getPalat().get(0).getY() - kaantuminen[0][1]);
            super.getPalat().get(1).setX(super.getPalat().get(1).getX() - kaantuminen[1][0]);
            super.getPalat().get(1).setY(super.getPalat().get(1).getY() - kaantuminen[1][1]);
            super.getPalat().get(3).setX(super.getPalat().get(3).getX() - kaantuminen[2][0]);
            super.getPalat().get(3).setY(super.getPalat().get(3).getY() - kaantuminen[2][1]);
            this.asento = 0;
        } else {
            kaannaVastapaivaan();
        }
    }

    @Override
    public void kaannaVastapaivaan() {
        if (this.asento == 0) {
            super.getPalat().get(0).setX(super.getPalat().get(0).getX() + kaantuminen[0][0]);
            super.getPalat().get(0).setY(super.getPalat().get(0).getY() + kaantuminen[0][1]);
            super.getPalat().get(1).setX(super.getPalat().get(1).getX() + kaantuminen[1][0]);
            super.getPalat().get(1).setY(super.getPalat().get(1).getY() + kaantuminen[1][1]);
            super.getPalat().get(3).setX(super.getPalat().get(3).getX() + kaantuminen[2][0]);
            super.getPalat().get(3).setY(super.getPalat().get(3).getY() + kaantuminen[2][1]);
            this.asento = 1;
        } else {
            kaannaMyotapaivaan();
        }

    }

}
