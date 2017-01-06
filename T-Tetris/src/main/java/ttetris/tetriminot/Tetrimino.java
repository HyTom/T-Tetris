package ttetris.tetriminot;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Sisältää listan Pala-olioita ja rakentaa niistä Tetris-palikoita.
 */
public abstract class Tetrimino {

    private List<Pala> palat;

    public Tetrimino() {
        this.palat = new ArrayList();
    }

    protected void luoKaantumiset() {
    }

    protected void luoPalat() {
    }

    public List<Pala> getPalat() {
        return this.palat;
    }

    /**
     * Tetrinimon ensimmäinen laatikko aloittaa kaivossa aina kaksi palaa
     * vasemmalle, paitsi tetrimino o.
     *
     * @return Arvon siitä kuinka monta palaa vasemmalle Tetrispelin
     * pelialueella, kaivossa, tämä Tetrimino aloittaa.
     */
    public int aloitusPaikanKeskittaja() {
        return 2;
    }

    /**
     *
     * @return Kaikkien Tetriminon pala-olioiden x ja y arvot muodossa [x,y]...
     */
    @Override
    public String toString() {
        String tostring = "";
        for (Pala pala : palat) {
            tostring += "[" + pala.getX() + "," + pala.getY() + "]";
        }
        return tostring;
    }

    /**
     * Kääntää Tetriminoa TGM-rotaation mukaisesti vastapaivaan muuttaen
     * Pala-olioiden x ja y arvoja.
     */
    public void kaannaVastapaivaan() {
    }

    /**
     * Kääntää Tetriminoa TGM-rotaation mukaisesti myotapaivaan muuttaen
     * Pala-olioiden x ja y arvoja.
     */
    public void kaannaMyotapaivaan() {
    }

    /**
     * Lisää kaikkien omistettujen Pala-olioiden x-arvoa yhdellä.
     */
    public void oikealle() {
        for (Pala pala : this.getPalat()) {
            pala.setX(pala.getX() + 1);
        }
    }

    /**
     * Vähentää kaikkien omistettujen Pala-olioiden x-arvoa yhdellä.
     */
    public void vasemmalle() {
        for (Pala pala : this.getPalat()) {
            pala.setX(pala.getX() - 1);
        }
    }

    /**
     * Lisää kaikkien omistettujen Pala-olioiden y-arvoa yhdellä.
     */
    public void alas() {
        for (Pala pala : this.getPalat()) {
            pala.setY(pala.getY() + 1);
        }
    }

    /**
     * Vähentää kaikkien omistettujen Pala-olioiden x-arvoa yhdellä.
     */
    public void ylos() {
        for (Pala pala : this.getPalat()) {
            pala.setY(pala.getY() - 1);
        }
    }
    
    public Color getVari() {
        return Color.WHITE;
    }
}
