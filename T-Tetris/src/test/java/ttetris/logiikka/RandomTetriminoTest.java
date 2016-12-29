
package ttetris.logiikka;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RandomTetriminoTest {
    
    RandomTetrimino random;
    
    @Before
    public void setUp() {
        random = new RandomTetrimino();
    }
    
    @Test
    public void antaaOikeinTetriminonI() {
        assertEquals("[0,0][1,0][2,0][3,0]",random.annaI().toString());
    }
    
    @Test
    public void antaaOikeinTetriminonJ() {
        assertEquals("[0,0][1,0][2,0][2,1]",random.annaJ().toString());
    }
    
    @Test
    public void antaaOikeinTetriminonL() {
        assertEquals("[0,0][1,0][2,0][0,1]",random.annaL().toString());
    }
    
    @Test
    public void antaaOikeinTetriminonO() {
        assertEquals("[0,0][1,0][0,1][1,1]",random.annaO().toString());
    }
    
    @Test
    public void antaaOikeinTetriminonS() {
        assertEquals("[1,0][2,0][0,1][1,1]",random.annaS().toString());
    }
    
    @Test
    public void antaaOikeinTetriminonT() {
        assertEquals("[0,0][1,0][2,0][1,1]",random.annaT().toString());
    }
    
    @Test
    public void antaaOikeinTetriminonZ() {
        assertEquals("[0,0][1,0][1,1][2,1]",random.annaZ().toString());
    }
    
}
