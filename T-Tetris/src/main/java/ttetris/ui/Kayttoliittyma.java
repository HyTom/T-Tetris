package ttetris.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import ttetris.logiikka.Tetrispeli;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private PelinAloittaja pelinAloittaja;
    private Nappainkuuntelija nappaimet;
    private Tetrispeli peli;

    public Kayttoliittyma(Tetrispeli peli) {
        this.frame = new JFrame("TTetris");
        this.nappaimet = new Nappainkuuntelija();
        peli.setNappainkuuntelija(this.nappaimet);
        this.pelinAloittaja = new PelinAloittaja(this.frame, peli);
        this.peli = peli;
    }

    @Override
    public void run() {
        luo();
        while (true) {
            while (!this.peli.paattyikoPeli()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            luoUusiPeli();
        }
    }

    private void luoKomponentit(Container container) {
        JLabel teksti = new JLabel("TTetris!");
        JButton jbutton = new JButton("Aloita");
        jbutton.addActionListener(this.pelinAloittaja);
        container.add(teksti);
        container.add(jbutton);
    }

    private void luo() {
        this.frame.setPreferredSize(new Dimension(800, 600));
        this.pelinAloittaja.annaIkkunanKoko(800, 600);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(this.frame.getContentPane());

        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void luoUusiPeli() {
        try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
                }
        Tetrispeli peli = new Tetrispeli();
        this.peli = peli;
        this.pelinAloittaja = new PelinAloittaja(this.frame, peli);
        this.pelinAloittaja.annaIkkunanKoko(800, 600);
        this.nappaimet = new Nappainkuuntelija();
        peli.setNappainkuuntelija(nappaimet);
        this.frame.getContentPane().removeAll();
        luoKomponentit(this.frame.getContentPane());
        
        this.frame.pack();
        this.frame.setVisible(true);
    }
}
