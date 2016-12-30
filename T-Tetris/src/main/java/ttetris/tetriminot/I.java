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
        for (int i = 0; i < 4; i++) {
            Pala pala = new Pala(i, 0);
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
        this.asento--;
        if (this.asento <= 0) {
            super.getPalat().get(0).setX(super.getPalat().get(0).getX() - kaantuminen[0][0]);
            super.getPalat().get(0).setY(super.getPalat().get(0).getY() - kaantuminen[0][1]);
            super.getPalat().get(0).setY(super.getPalat().get(2).getY() - kaantuminen[1][0]);
            super.getPalat().get(0).setY(super.getPalat().get(2).getY() - kaantuminen[1][1]);
            super.getPalat().get(0).setY(super.getPalat().get(3).getY() - kaantuminen[2][0]);
            super.getPalat().get(0).setY(super.getPalat().get(3).getY() - kaantuminen[2][1]);
        } else {
            this.asento = 0;
            kaannaVastapaivaan();
        }
    }

    @Override
    public void kaannaVastapaivaan() {
        this.asento++;
        if (this.asento <= 1) {
            super.getPalat().get(0).setX(super.getPalat().get(0).getX() + kaantuminen[0][0]);
            super.getPalat().get(0).setY(super.getPalat().get(0).getY() + kaantuminen[0][1]);
            super.getPalat().get(0).setY(super.getPalat().get(2).getY() + kaantuminen[1][0]);
            super.getPalat().get(0).setY(super.getPalat().get(2).getY() + kaantuminen[1][1]);
            super.getPalat().get(0).setY(super.getPalat().get(3).getY() + kaantuminen[2][0]);
            super.getPalat().get(0).setY(super.getPalat().get(3).getY() + kaantuminen[2][1]);
        } else {
            this.asento = 1;
            kaannaMyotapaivaan();
        }

    }

}
