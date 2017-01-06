package ttetris.ui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import ttetris.logiikka.Tetrispeli;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private PelinAloittaja pelinAloittaja;

    public Kayttoliittyma(Tetrispeli peli) {
        this.frame = new JFrame("TTetris");
        this.pelinAloittaja = new PelinAloittaja(this.frame, peli);
    }

    @Override
    public void run() {
        this.frame.setPreferredSize(new Dimension(700, 500));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(this.frame.getContentPane());

        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        JLabel teksti = new JLabel("TTetris!");
        JButton jbutton = new JButton("Aloita");
        jbutton.addActionListener(this.pelinAloittaja);
        container.add(teksti);
        container.add(jbutton);
    }
}
