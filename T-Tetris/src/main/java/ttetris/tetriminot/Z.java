package ttetris.tetriminot;

public class Z extends Tetrimino {

    int asento;
    int[][] kaantuminen;

    public Z() {
        super();
        luoPalat();
        luoKaantumiset();
        this.asento = 0;
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

    @Override
    protected void luoKaantumiset() {
        int[][] kaantuminen = {{2, -1}, {0, -1}};
        this.kaantuminen = kaantuminen;
    }

    @Override
    public void kaannaMyotapaivaan() {
        if (this.asento == 0) {
            super.getPalat().get(0).setX(super.getPalat().get(0).getX()
                    + this.kaantuminen[0][0]);
            super.getPalat().get(0).setY(super.getPalat().get(0).getY()
                    + this.kaantuminen[0][1]);
            super.getPalat().get(3).setX(super.getPalat().get(3).getX()
                    + this.kaantuminen[1][0]);
            super.getPalat().get(3).setY(super.getPalat().get(3).getY()
                    + this.kaantuminen[1][1]);
            this.asento++;
        } else {
            kaannaVastapaivaan();
        }
    }

    @Override
    public void kaannaVastapaivaan() {
        if (this.asento != 0) {
            super.getPalat().get(0).setX(super.getPalat().get(0).getX()
                    - this.kaantuminen[0][0]);
            super.getPalat().get(0).setY(super.getPalat().get(0).getY()
                    - this.kaantuminen[0][1]);
            super.getPalat().get(3).setX(super.getPalat().get(3).getX()
                    - this.kaantuminen[1][0]);
            super.getPalat().get(3).setY(super.getPalat().get(3).getY()
                    - this.kaantuminen[1][1]);
            this.asento = 0;
        } else {
            kaannaMyotapaivaan();
        }
    }

}
