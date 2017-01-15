package ttetris.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import ttetris.logiikka.Kaivo;
import ttetris.logiikka.Levellaskuri;
import ttetris.logiikka.Pelilaskuri;
import ttetris.tetriminot.Pala;
import ttetris.tetriminot.Tetrimino;

public class Piirtaja extends JPanel {

    private Kaivo kaivo;
    private int koko;
    private int ikkunanleveys;
    private int ikkunankorkeus;
    private int kaivonsijaintix;
    private int kaivonsijaintiy;
    private Tetrimino tetrimino;
    private Pelilaskuri lukitusmittari;
    private int pisteet;
    private Levellaskuri level;
    private boolean lukitus;

    public Piirtaja() {
        super.setBackground(Color.BLACK);
        this.koko = 20;
        this.pisteet = 0;
        this.lukitus = false;
    }

    public void setKaivo(Kaivo kaivo) {
        this.kaivo = kaivo;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        piirraSeinat(g);
        piirraKaivo(g);
        piirraSeuraavaPala(g);
        PiirraLevelJaPisteet(g);
        piirraLukitusmittari(g);
        if (this.lukitus) {
            piirraLukkiutuminen(g);
        }
    }

    private void piirraSeinat(Graphics g) {
        g.setColor(new Color(192, 202, 206));
        //Katto
        g.fill3DRect(this.kaivonsijaintix, this.kaivonsijaintiy,
                (kaivo.getLeveys() + 2) * koko, koko, true);
        //vasen seinä
        g.fill3DRect(this.kaivonsijaintix, this.kaivonsijaintiy,
                koko, (kaivo.getKorkeus() + 1) * koko, true);
        //oikea seinä
        g.fill3DRect(this.kaivonsijaintix + (kaivo.getLeveys() + 1) * koko, this.kaivonsijaintiy,
                koko, (kaivo.getKorkeus() + 1) * koko, true);
        //lattia
        g.fill3DRect(this.kaivonsijaintix + 0, kaivo.getKorkeus() * koko + this.kaivonsijaintiy,
                (kaivo.getLeveys() + 2) * koko, koko, true);
    }

    private void piirraKaivo(Graphics g) {
        for (int y = 1; y < this.kaivo.getKorkeus(); y++) {
            for (int x = 0; x < this.kaivo.getLeveys(); x++) {
                Pala pala = this.kaivo.getRuudukko()[x][y];
                if (pala != null) {
                    g.setColor(pala.getVari());
                    g.fill3DRect((x + 1) * koko + this.kaivonsijaintix,
                            y * koko + this.kaivonsijaintiy, koko, koko, true);
                }
            }
        }
    }

    private void laskeKaivonsijainti(int x, int y) {
        this.kaivonsijaintix = x / 2 - (this.kaivo.getLeveys() + 2) * koko / 2;
        this.kaivonsijaintiy = (y - this.kaivo.getKorkeus() * koko) / 2;
    }

    void annaIkkunanKoko(int x, int y) {
        this.ikkunanleveys = x;
        this.ikkunankorkeus = y;
        laskeKaivonsijainti(ikkunanleveys, ikkunankorkeus);
    }

    private void piirraSeuraavaPala(Graphics g) {
        g.setColor(this.tetrimino.getVari());
        for (Pala pala : this.tetrimino.getPalat()) {
            g.fill3DRect(this.kaivonsijaintix + koko * (this.kaivo.getLeveys() + 2) / 2
                    + pala.getX() * koko - this.tetrimino.aloitusPaikanKeskittaja() * koko,
                    this.kaivonsijaintiy - koko * 3 + pala.getY() * koko,
                    koko, koko, true);
        }
    }

    public void setTetrimino(Tetrimino tetrimino) {
        this.tetrimino = tetrimino;
    }

    private void PiirraLevelJaPisteet(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("LEVEL :" + this.level.toString(),
                this.kaivonsijaintix + this.kaivo.getLeveys() * koko + 3 * koko,
                this.kaivonsijaintiy + this.kaivo.getKorkeus() * koko / 2);
        g.drawString("POINTS :" + this.pisteet,
                this.kaivonsijaintix + this.kaivo.getLeveys() * koko + 3 * koko,
                this.kaivonsijaintiy + this.kaivo.getKorkeus() * koko / 2 + 2 * koko);
    }

    public void setLukitusmittari(Pelilaskuri lukitusmittari) {
        this.lukitusmittari = lukitusmittari;
    }

    private void piirraLukitusmittari(Graphics g) {
        if (this.lukitusmittari.onkoAikaaKulunut()) {
            g.drawLine(this.kaivonsijaintix,
                    this.kaivonsijaintiy + (this.kaivo.getKorkeus() + 2) * koko,
                    this.kaivonsijaintix + (this.kaivo.getLeveys() + 2) * koko
                    / 30 * this.lukitusmittari.getAika(),
                    this.kaivonsijaintiy + (this.kaivo.getKorkeus() + 2) * koko);
        }
    }

    private void piirraLukkiutuminen(Graphics g) {
        g.setColor(Color.WHITE);
        for (Pala pala : this.kaivo.getTetrimino().getPalat()) {
            g.fill3DRect((pala.getX() + 1) * koko + this.kaivonsijaintix,
                    pala.getY() * koko + this.kaivonsijaintiy, koko, koko, true);
        }
    }

    public void setPisteet(int pisteet) {
        this.pisteet = pisteet;
    }

    public void setLevelLaskuri(Levellaskuri level) {
        this.level = level;
    }

    public void setLukitusPiirretaan(boolean b) {
        this.lukitus = b;
    }
}
