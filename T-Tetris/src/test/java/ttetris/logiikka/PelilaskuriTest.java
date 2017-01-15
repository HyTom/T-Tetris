package ttetris.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelilaskuriTest {

    Pelilaskuri laskuri;

    @Before
    public void setUp() {
        laskuri = new Pelilaskuri(10);
    }

    @Test
    public void aikaVahenee() {
        laskuri.vahennaAikaaYhdella();
        assertEquals(9, laskuri.getAika());
    }

    @Test
    public void aikaVaheneeMonella() {
        laskuri.vahennaAikaa(5);
        assertEquals(5, laskuri.getAika());
    }

    @Test
    public void aikaOnVahentunut() {
        laskuri.vahennaAikaaYhdella();
        assertEquals(true, laskuri.onkoAikaaKulunut());
    }

    @Test
    public void aikaVaheneeLoppuun() {
        assertEquals(false, laskuri.onkoAikaKulunutLoppuun());
        laskuri.vahennaAikaa(50);
        assertEquals(true, laskuri.onkoAikaKulunutLoppuun());
    }

    @Test
    public void ajanVoiPalauttaa() {
        laskuri.vahennaAikaa(50);
        laskuri.aikaAlkuTilaan(false);
        assertEquals(false, laskuri.onkoAikaaKulunut());
    }

    @Test
    public void ajanVoiPalauttaaYlitetynAjanKanssa() {
        laskuri.vahennaAikaa(15);
        laskuri.aikaAlkuTilaan(true);
        assertEquals(5, laskuri.getAika());
    }

    @Test
    public void aloitusAikaOikein() {
        assertEquals(10, laskuri.getAloitusaika());
    }
    
    @Test
    public void ajanVoiAsettaaAktiiviseksi() {
        assertEquals(false,laskuri.getOnkoaktiivinen());
        laskuri.setOnkoaktiivinen(true);
        assertEquals(true,laskuri.getOnkoaktiivinen());
    }
}
