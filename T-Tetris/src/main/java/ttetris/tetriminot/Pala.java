package ttetris.tetriminot;

import java.awt.Color;

/**
 * Olio jolla on atribuutteina x ja y Integer arvot sekä String merkki arvo.
 */
public class Pala {

    private int x;
    private int y;
    private String merkki;
    private Color vari;

    public Pala(int x, int y) {
        this.x = x;
        this.y = y;
        this.merkki = "[]";
        this.vari = Color.WHITE;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMerkki(String merkki) {
        //Korvataan myöhemmin värillä
        this.merkki = merkki;
    }

    @Override
    public String toString() {
        return merkki;
    }

    public void setVari(Color vari) {
        this.vari = vari;
    }

    public Color getVari() {
        return this.vari;
    }
}
