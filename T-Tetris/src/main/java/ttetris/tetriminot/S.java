package ttetris.tetriminot;

public class S extends Tetrimino {

    int asento;
    int[][] kaantuminen;

    public S() {
        super();
        luoPalat();
        luoKaantumiset();
        this.asento = 0;
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

    @Override
    protected void luoKaantumiset() {
        int[][] kaantuminen = {{-2, -1}, {0, -1}};
        this.kaantuminen = kaantuminen;
    }

    @Override
    public void kaannaMyotapaivaan() {
        if (this.asento == 0) {
            super.getPalat().get(1).setX(super.getPalat().get(1).getX()
                    + this.kaantuminen[0][0]);
            super.getPalat().get(1).setY(super.getPalat().get(1).getY()
                    + this.kaantuminen[0][1]);
            super.getPalat().get(2).setX(super.getPalat().get(2).getX()
                    + this.kaantuminen[1][0]);
            super.getPalat().get(2).setY(super.getPalat().get(2).getY()
                    + this.kaantuminen[1][1]);
            this.asento++;
        } else {
            kaannaVastapaivaan();
        }
    }

    @Override
    public void kaannaVastapaivaan() {
        if (this.asento != 0) {
            super.getPalat().get(1).setX(super.getPalat().get(1).getX()
                    - this.kaantuminen[0][0]);
            super.getPalat().get(1).setY(super.getPalat().get(1).getY()
                    - this.kaantuminen[0][1]);
            super.getPalat().get(2).setX(super.getPalat().get(2).getX()
                    - this.kaantuminen[1][0]);
            super.getPalat().get(2).setY(super.getPalat().get(2).getY()
                    - this.kaantuminen[1][1]);
            this.asento = 0;
        } else {
            kaannaMyotapaivaan();
        }
    }
}
