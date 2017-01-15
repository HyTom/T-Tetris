package ttetris.logiikka;

public class Levellaskuri {

    private int level;
    private int max;

    /**
     * Levellaskuri laskee Tetriksen leveleitä. Levellaskuri ei kasva yli arvon
     * 99 jos ei käytetä metodia kasvataLeveliaTyhjennetyilla, koska yli levelin
     * 99 pääsemiseen vaaditaan vähintään yksi rikottu pala. Tällöin maximia 
     * kasvatetaan seuraavaan sadannes lukuun. Sama toistuu jokaisen 
     * 100 arvon kohdalla kunnes päästään tasolle 900, jolloin maximiin pääsemiseen
     * vaaditaan levelissä 998 palan rikkominen ja max aro on 999 eikä maximiarvo 
     * kasva koska Tetrispeli on läpäisty.
     */
    public Levellaskuri() {
        this.level = 0;
        this.max = 100;
    }

    /**
     * Kasvattaa Levellaskuria halutulla arvolla. Jos yli maximin, kasvattaa
     * maximia.
     *
     * @param i Luku jolla kasvatetaan.
     */
    public void kasvataLeveliaTyhjennetyilla(int i) {
        this.level += i;
        if (this.level >= this.max) {
            this.max += 100;
            if (this.max > 900) {
                this.max = 999;
            }
        }
    }

    /**
     * Kasvattaa Levellaskurin arvoa yhdellä, mutta ei yli nykyisen maximin.
     */
    public void kasvataLevelia() {
        this.level++;
        if (this.level == this.max | this.level == 998) {
            this.level--;
        }
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return this.level + " / " + this.max;
    }
}
