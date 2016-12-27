package ttetris;

import ttetris.logiikka.Tetrispeli;

public class Main {

    public static void main(String[] args) {
        //Tällä hetkellä pelissä on vain yksi tetris pala jonka voi
        //lisätä pelikentälle ja liikuttaa alaspäin. Useita samanlaisia voi
        //myös lisätä pelikentälle ja liikuttaa alas.
        Tetrispeli peli = new Tetrispeli();
        peli.aloita();
    }

}
