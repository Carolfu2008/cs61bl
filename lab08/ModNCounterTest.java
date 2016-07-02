import static org.junit.Assert.*;

/**
 * Created by ENVY on 7/1/2016.
 */
public class ModNCounterTest {
    @org.junit.Test
    public void testConstructor(){
        ModNCounter c = new ModNCounter(4);
        assertTrue(c.getN() == 4);
    }
    @org.junit.Test
    public void testIncrement() throws Exception {
        ModNCounter c = new ModNCounter(2);
        c.increment();
        assertTrue(c.value() == 1);
        c.increment();
        assertTrue(c.value() == 0);
    }

    @org.junit.Test
    public void testReset() throws Exception {
        ModNCounter c = new ModNCounter();
        c.increment();
        c.reset();
        assertTrue(c.value() == 0);
    }


}