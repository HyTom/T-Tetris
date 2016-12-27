
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
    
}
