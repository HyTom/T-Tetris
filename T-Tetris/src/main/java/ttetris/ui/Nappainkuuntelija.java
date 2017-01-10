package ttetris.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import ttetris.logiikka.Kaivo;

public class Nappainkuuntelija implements KeyListener {

    private Kaivo kaivo;
    private Piirtaja piirtaja;
    private boolean pelipaattyy;

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
        if (this.kaivo.getTetrimino() != null && !this.pelipaattyy) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                this.kaivo.tetriminoVasemmalle();
                this.piirtaja.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                this.kaivo.tetriminoOikealle();
                this.piirtaja.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                this.kaivo.tetriminoAlas();
                this.piirtaja.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_M) {
                this.kaivo.tetriminoMyotapaivaan();
                this.piirtaja.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_COMMA) {
                this.kaivo.tetriminoVastapaivaan();
                this.piirtaja.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_PERIOD) {
                this.kaivo.tetriminoMyotapaivaan();
                this.piirtaja.repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
