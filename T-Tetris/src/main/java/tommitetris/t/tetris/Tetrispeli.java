package tommitetris.t.tetris;

class Tetrispeli {

    private Kaivo kaivo;
    private RandomPala randomoija;
    private Pala pala;

    public Tetrispeli() {
        this.kaivo = new Kaivo(10, 20);
        this.randomoija = new RandomPala();
        this.pala = this.randomoija.annaRandomPala();
    }

    public Kaivo getKaivo() {
        return kaivo;
    }
    
    public void aloita() {
        this.kaivo.annaPala(this.pala);
        this.kaivo.palaKaivoon();
        this.kaivo.tulostaKaivo();
        this.kaivo.palaAlas();
        this.kaivo.tulostaKaivo();
    }

}
