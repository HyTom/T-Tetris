package ttetris.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import ttetris.logiikka.Tetrispeli;

public class PelinAloittaja implements ActionListener {

    private JFrame frame;
    private Timer timer;
    private int ajastus;
    private Tetrispeli peli;
    private int ikkunankokox;
    private int ikkunankokoy;

    PelinAloittaja(JFrame frame, Tetrispeli peli) {
        this.frame = frame;
        this.ajastus = 16;
        this.peli = peli;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.getContentPane().removeAll();
        Piirtaja piirtaja = new Piirtaja();
        this.frame.getContentPane().add(piirtaja);
        this.frame.addKeyListener(this.peli.getNappainkuuntelija());
        this.frame.setVisible(true);
        this.peli.getNappainkuuntelija().setTetrispeli(this.peli);
        this.frame.requestFocusInWindow();
        //this.frame.setFocusable(true);
        this.frame.pack();
        this.timer = new Timer(this.ajastus, this.peli);
        this.peli.setTimer(this.timer);
        this.peli.setPiirtaja(piirtaja);
        piirtaja.annaIkkunanKoko(this.ikkunankokox, this.ikkunankokoy);
        this.timer.start();
    }
/**
 * Antaa Kayttoliityma luokan määrittämän ikkunan koon PelinAloittajalle.
 * @param x leveys
 * @param y korkeus
 */
    public void annaIkkunanKoko(int x, int y) {
        this.ikkunankokox = x;
        this.ikkunankokoy = y;
    }
}
