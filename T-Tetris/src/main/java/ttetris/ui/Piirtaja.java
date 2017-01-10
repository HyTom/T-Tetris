package ttetris.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import ttetris.logiikka.Kaivo;
import ttetris.tetriminot.Pala;

public class Piirtaja extends JPanel {

    private Kaivo kaivo;
    private int koko;

    public Piirtaja() {
        super.setBackground(Color.WHITE);
        this.koko = 20;
    }

    public void setKaivo(Kaivo kaivo) {
        this.kaivo = kaivo;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        piirraSeinat(g);
        piirraKaivo(g);
    }

    private void piirraSeinat(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        //Katto
        g.fillRect(0, 0, (kaivo.getLeveys() + 2) * koko, koko);
        //vasen seinä
        g.fillRect(0, 0, koko, (kaivo.getKorkeus() + 1) * koko);
        //oikea seinä
        g.fillRect((kaivo.getLeveys() + 1) * koko, 0, koko, (kaivo.getKorkeus() + 1) * koko);
        //lattia
        g.fillRect(0, kaivo.getKorkeus() * koko, (kaivo.getLeveys() + 2) * koko, koko);
    }

    private void piirraKaivo(Graphics g) {
        for (int y = 1; y < this.kaivo.getKorkeus(); y++) {
            for (int x = 0; x < this.kaivo.getLeveys(); x++) {
                Pala pala = this.kaivo.getRuudukko()[x][y];
                if (pala != null) {
                    g.setColor(pala.getVari());
                    g.fill3DRect((x + 1) * koko,
                            y * koko, koko, koko, true);
                    
                }
            }
        }
    }

}
