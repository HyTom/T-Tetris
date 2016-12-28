package ttetris;

import ttetris.logiikka.Tetrispeli;

public class Main {

    public static void main(String[] args) {
        //Tällä hetkellä peli tiputtaa satunnaisesti valittuja tetris-paloja
        //alas ruudukossa ja kun palikka tullessaan ei voi liikkua,
        //peli päättyy.
        Tetrispeli peli = new Tetrispeli();
        peli.aloita();
    }

}
