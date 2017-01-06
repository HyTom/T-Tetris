package ttetris;

import ttetris.logiikka.Tetrispeli;
import ttetris.ui.Kayttoliittyma;

public class Main {

    public static void main(String[] args) {
        Kayttoliittyma kayttis = new Kayttoliittyma(new Tetrispeli());
        kayttis.run();
    }
}
