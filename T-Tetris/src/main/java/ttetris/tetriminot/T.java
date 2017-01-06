package ttetris.tetriminot;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class T extends Tetrimino {

    private int asento;
    private List<int[][]> kaantumiset;

    public T() {
        super();
        luoPalat();
        luoKaantumiset();
        this.asento = 0;
    }

    @Override
    protected void luoPalat() {
        for (int i = 0; i < 3; i++) {
            Pala pala = new Pala(i, 0);
            pala.setMerkki("[T]");
            pala.setVari(this.getVari());
            super.getPalat().add(pala);
        }
        Pala pala = new Pala(1, 1);
        pala.setMerkki("[T]");
        pala.setVari(this.getVari());
        super.getPalat().add(pala);
    }

    @Override
    protected void luoKaantumiset() {
        ArrayList<int[][]> tkaantumiset = new ArrayList();
        int[][] kaantuminen0 = {{1, -1}, {0, 0}};
        int[][] kaantuminen1 = {{-1, 2}, {0, 1}};
        int[][] kaantuminen2 = {{0, -1}, {-1, -2}};
        int[][] kaantuminen3 = {{0, 0}, {1, 1}};
        tkaantumiset.add(kaantuminen0);
        tkaantumiset.add(kaantuminen1);
        tkaantumiset.add(kaantuminen2);
        tkaantumiset.add(kaantuminen3);
        this.kaantumiset = tkaantumiset;
    }

    @Override
    public void kaannaVastapaivaan() {
        int[][] kaantuminen = this.kaantumiset.get(this.asento);
        super.getPalat().get(0).setX(super.getPalat().get(0).getX()
                + kaantuminen[0][0]);
        super.getPalat().get(0).setY(super.getPalat().get(0).getY()
                + kaantuminen[0][1]);
        super.getPalat().get(2).setX(super.getPalat().get(2).getX()
                + kaantuminen[1][0]);
        super.getPalat().get(2).setY(super.getPalat().get(2).getY()
                + kaantuminen[1][1]);
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
        int[][] kaantuminen = this.kaantumiset.get(this.asento);
        super.getPalat().get(0).setX(super.getPalat().get(0).getX()
                - kaantuminen[0][0]);
        super.getPalat().get(0).setY(super.getPalat().get(0).getY()
                - kaantuminen[0][1]);
        super.getPalat().get(2).setX(super.getPalat().get(2).getX()
                - kaantuminen[1][0]);
        super.getPalat().get(2).setY(super.getPalat().get(2).getY()
                - kaantuminen[1][1]);
    }

    @Override
    public Color getVari() {
        return new Color(66, 244, 235);
    }

}
