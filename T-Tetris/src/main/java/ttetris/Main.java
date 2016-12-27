package ttetris;

import ttetris.logiikka.Tetrispeli;

public class Main {

    public static void main(String[] args) {
        //Tällä hetkellä peli tulostaa pelikentän johon se
        //on lisännyt tetris-palan. Sen jälkeen se tulostaa uuden ruudukon
        //jossa pala on tippunut askeleen alas.
        Tetrispeli peli = new Tetrispeli();
        peli.aloita();
    }

}
