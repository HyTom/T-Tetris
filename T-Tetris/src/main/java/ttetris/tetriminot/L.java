package ttetris.tetriminot;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class L extends Tetrimino {

    private int asento;
    private List<int[][]> kaantumiset;

    public L() {
        super();
        luoPalat();
        luoKaantumiset();
        this.asento = 0;
    }

    @Override
    protected void luoPalat() {
        for (int i = 0; i < 3; i++) {
            Pala pala = new Pala(i, 0);
            pala.setMerkki("[L]");
            pala.setVari(this.getVari());
            super.getPalat().add(pala);
        }
        Pala pala = new Pala(0, 1);
        pala.setMerkki("[L]");
        pala.setVari(this.getVari());
        super.getPalat().add(pala);
    }

    @Override
    protected void luoKaantumiset() {
        ArrayList<int[][]> ikaantumiset = new ArrayList();
        int[][] kaantumiset0 = {{1, 1}, {0, 0}, {-1, -1}, {2, 0}};
        int[][] kaantumiset1 = {{1, 0}, {0, 1}, {-1, 2}, {0, -1}};
        int[][] kaantumiset2 = {{-1, -2}, {0, -1}, {1, 0}, {-2, -1}};
        int[][] kaantumiset3 = {{-1, 1}, {0, 0}, {1, -1}, {0, 2}};
        ikaantumiset.add(kaantumiset0);
        ikaantumiset.add(kaantumiset1);
        ikaantumiset.add(kaantumiset2);
        ikaantumiset.add(kaantumiset3);
        this.kaantumiset = ikaantumiset;
    }

    @Override
    public void kaannaVastapaivaan() {
        for (int mones = 0; mones < 4; mones++) {
            Pala pala = super.getPalat().get(mones);
            pala.setX(pala.getX() + this.kaantumiset.get(this.asento)[mones][0]);
            pala.setY(pala.getY() + this.kaantumiset.get(this.asento)[mones][1]);
        }
        this.asento++;
        if (this.asento > 3) {
            this.asento = 0;
        }
    }

    @Override
    public void kaannaMyotapaivaan() {
        this.asento--;
        if (this.asento < 0) {
            this.asento = 3;
        }
        for (int mones = 0; mones < 4; mones++) {
            Pala pala = super.getPalat().get(mones);
            pala.setX(pala.getX() - this.kaantumiset.get(this.asento)[mones][0]);
            pala.setY(pala.getY() - this.kaantumiset.get(this.asento)[mones][1]);
        }
    }

    @Override
    public Color getVari() {
        return new Color(244, 173, 66);
    }

}
