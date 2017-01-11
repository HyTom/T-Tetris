package ttetris.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import ttetris.logiikka.Kaivo;

public class Nappainkuuntelija implements KeyListener {

    private Kaivo kaivo;
    private Piirtaja piirtaja;
    private boolean pelipaattyy;
    private boolean voikoOhjata;
    private List<Nappaimet> komennot;

    public Nappainkuuntelija() {
        this.voikoOhjata = true;
        this.komennot = new ArrayList();
    }

    public void setVoikoOhjata(boolean voikoOhjata) {
        this.voikoOhjata = voikoOhjata;
    }

    public void setKaivo(Kaivo kaivo) {
        this.kaivo = kaivo;
        this.pelipaattyy = false;
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
                this.piirtaja.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_D | e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.kaivo.tetriminoOikealle();
                this.piirtaja.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_S | e.getKeyCode() == KeyEvent.VK_DOWN) {
                this.kaivo.tetriminoAlas();
                this.piirtaja.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_M | e.getKeyCode() == KeyEvent.VK_Z) {
                this.kaivo.tetriminoVastapaivaan();
                this.piirtaja.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_COMMA | e.getKeyCode() == KeyEvent.VK_X) {
                this.kaivo.tetriminoMyotapaivaan();
                this.piirtaja.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_PERIOD | e.getKeyCode() == KeyEvent.VK_C) {
                this.kaivo.tetriminoVastapaivaan();
                this.piirtaja.repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
