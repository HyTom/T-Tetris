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
        this.frame.setVisible(true);
        this.frame.pack();
        this.timer = new Timer(this.ajastus, this.peli);
        this.peli.setTimer(this.timer);
        this.peli.setPiirtaja(piirtaja);
        this.timer.start();
        if (!this.timer.isRunning()) {
            System.out.println("LOPPU SATTAYAJKNHILNHNDKHYUKSNGIKSGEUIHMDIO");
        }
    }
}
