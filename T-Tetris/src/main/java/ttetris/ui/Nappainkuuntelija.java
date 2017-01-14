package ttetris.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import ttetris.logiikka.Kaivo;
import ttetris.logiikka.Pelilaskuri;
import ttetris.logiikka.Tetrispeli;

public class Nappainkuuntelija implements KeyListener {

    private Kaivo kaivo;
    private Piirtaja piirtaja;
    private boolean pelipaattyy;
    private boolean voikoOhjata;
    private List<Nappaimet> komennot;
  
    private Pelilaskuri lukitusmittari;
    private Tetrispeli peli;

    public Nappainkuuntelija() {
        this.voikoOhjata = true;
        this.komennot = new ArrayList();
    }

    public void setVoikoOhjata(boolean voikoOhjata) {
        this.voikoOhjata = voikoOhjata;
    }

    public void setTetrispeli(Tetrispeli peli) {
        this.peli = peli;
    }

    public void setTetrispeliintarvittavat(Kaivo kaivo, Pelilaskuri lukitusmittari) {
        this.kaivo = kaivo;
        this.pelipaattyy = false;
        this.lukitusmittari = lukitusmittari;
    }

    public void setPiirtaja(Piirtaja piirtaja) {
        this.piirtaja = piirtaja;
    }

    public void setPelipaattyy(boolean pelipaattyy) {
        this.pelipaattyy = pelipaattyy;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.voikoOhjata & this.kaivo.getTetrimino() != null & !this.pelipaattyy) {
            if (e.getKeyCode() == KeyEvent.VK_A | e.getKeyCode() == KeyEvent.VK_LEFT) {
                this.kaivo.tetriminoVasemmalle();
            }
            if (e.getKeyCode() == KeyEvent.VK_D | e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.kaivo.tetriminoOikealle();
            }
            if (e.getKeyCode() == KeyEvent.VK_S | e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (this.peli.getLukitusmittari().getOnkoaktiivinen() & (this.kaivo.lukittuuko())) {
                    this.peli.aloitaLukitusAika();
                } else if (this.kaivo.lukittuuko()) {
                    this.peli.getLukitusmittari().setOnkoaktiivinen(true);
                } else {
                    this.peli.getLukitusmittari().setOnkoaktiivinen(false);
                    this.peli.getTippuukoTetrimino().aikaAlkuTilaan(false);
                    this.kaivo.tetriminoAlas();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_M | e.getKeyCode() == KeyEvent.VK_Z) {
                this.kaivo.tetriminoVastapaivaan();
            }
            if (e.getKeyCode() == KeyEvent.VK_COMMA | e.getKeyCode() == KeyEvent.VK_X) {
                this.kaivo.tetriminoMyotapaivaan();
            }
            if (e.getKeyCode() == KeyEvent.VK_PERIOD | e.getKeyCode() == KeyEvent.VK_C) {
                this.kaivo.tetriminoVastapaivaan();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
