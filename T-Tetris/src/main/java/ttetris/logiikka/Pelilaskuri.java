package ttetris.logiikka;

/**
 * Koska Tetrispeli laskee yhden ruudun tapahtumat 60 kertaa sekunnissa, voi
 * pelin sisäisiä ajastettuja tapahtumia laskea ruuduissa. Nämä tapahtumat on
 * tämän luokan vastuulla. Pelilaskurille voi myös määrittää onko laskuri 
 * aktiivinen tai ei jos haluaa käyttää laskuria tällä tavalla ilman ylimääräisiä
 * boolean olioita. Pelilaskuri on luodessa epäaktiivinen.
 */
public class Pelilaskuri {

    private int aika;
    private int aloitusaika;
    private boolean onkoaktiivinen;

    /**
     * Laskurille annetaan kuinka monta ruutua tapahtuma kestää.
     *
     * @param aika ruutujen määrä
     */
    public Pelilaskuri(int aika) {
        this.aika = aika;
        this.aloitusaika = aika;
        this.onkoaktiivinen = false;
    }

    /**
     * Vahentaa aikaa annetulla maaralla.
     *
     * @param aika vahennattava maara
     */
    public void vahennaAikaa(int aika) {
        this.aika -= aika;
    }
    
    /**
     * Vahentaa asetettua aikaa yhdellä.
     */
    public void vahennaAikaaYhdellä() {
        this.aika--;
    }

    /**
     * Palauttaa onko aika kulunut loppuun;
     */
    public boolean onkoAikaKulunutLoppuun() {
        if (this.aika <= 0) {
            return true;
        }
        return false;
    }

    /**
     * Palauttaa laskurin ajan takaisin alkuun. Jos annetaan parametriksi true,
     * vähennetään mahdollisesti ylitetty aika palautetusta ajasta.
     *
     * @param b Halutaanko vahentaa ylitetty aika aloitusajasta.
     */
    public void aikaAlkuTilaan(boolean b) {
        int ylitettyaika = this.aika;
        this.aika = this.aloitusaika;
        if (b) {
            this.aika += ylitettyaika;
        }
    }

    /**
     * @return Kertoo true jos aika on eri kuin alussa annettu.
     */
    public boolean onkoAikaaKulunut() {
        if (this.aika != this.aloitusaika) {
            return true;
        }
        return false;
    }

    public int getAika() {
        return aika;
    }

    public boolean getOnkoaktiivinen() {
        return onkoaktiivinen;
    }

    public void setOnkoaktiivinen(boolean onkoaktiivinen) {
        this.onkoaktiivinen = onkoaktiivinen;
    }

    public int getAloitusaika() {
        return aloitusaika;
    }
    
    
    
}
