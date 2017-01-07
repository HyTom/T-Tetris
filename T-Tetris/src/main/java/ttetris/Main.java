package ttetris;

import ttetris.logiikka.Tetrispeli;
import ttetris.ui.Kayttoliittyma;

public class Main {

    public static void main(String[] args) {
        Tetrispeli peli = new Tetrispeli();
        Kayttoliittyma kayttis = new Kayttoliittyma(peli);
        kayttis.run();
    }
}
