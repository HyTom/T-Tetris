package ttetris.logiikka;

import java.util.HashMap;
import java.util.Map;

/**
 * Luo ja hallinnoi tetriksessä käytettyä painovoimaa, joka määrää kuinka monta
 * palaa tetris-pala tippuu per ruutu. Painovoima laskee ja nousee sen mukaan,
 * mikä on pelissä saavutettu Level. Painovoimat on kovakoodattu pelin sääntöjen
 * mukaisiksi.
 */
public class Painovoima {

    private Map<Integer, Integer> painovoimat;

    public Painovoima() {
        luoPainovoimat();
    }

    private void luoPainovoimat() {
        this.painovoimat = new HashMap();
        painovoimat.put(0, 4);
        painovoimat.put(30, 6);
        painovoimat.put(35, 8);
        painovoimat.put(40, 10);
        painovoimat.put(50, 12);
        painovoimat.put(60, 16);
        painovoimat.put(70, 32);
        painovoimat.put(80, 48);
        painovoimat.put(90, 64);
        painovoimat.put(100, 80);
        painovoimat.put(120, 96);
        painovoimat.put(140, 112);
        painovoimat.put(160, 128);
        painovoimat.put(170, 144);
        painovoimat.put(200, 4);
        painovoimat.put(220, 32);
        painovoimat.put(230, 64);
        painovoimat.put(233, 96);
        painovoimat.put(236, 128);
        painovoimat.put(239, 160);
        painovoimat.put(243, 192);
        painovoimat.put(247, 224);
        painovoimat.put(251, 256);
        painovoimat.put(300, 512);
        painovoimat.put(330, 768);
        painovoimat.put(360, 1024);
        painovoimat.put(400, 1280);
        painovoimat.put(420, 1024);
        painovoimat.put(450, 768);
        painovoimat.put(500, 5120);
    }

    /**
    *Palauttaa Tetrispelin leveliä vastaavan painovoiman.
    */
    public int annaPainovoima(int level) {
        int palautettava = 0;
        int avain = 0;
        for (Integer key : this.painovoimat.keySet()) {
            if (level >= key & avain <= key) {
                avain = key;
                palautettava = this.painovoimat.get(key);
            }
        }
        return palautettava;
    }

}
