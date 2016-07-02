import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ENVY on 7/1/2016.
 */
public class MeasurementTest {
    @org.junit.Test
    public void getFeet() throws Exception {

    }

    @Test
    public void getInches() throws Exception {

    }

    @Test
    public void plus() throws Exception {
        Measurement a = new Measurement(3,2);
        Measurement b = new Measurement(2,10);
        System.out.println(a.plus(b));


    }

    @Test
    public void minus() throws Exception {

    }

    @Test
    public void multiple() throws Exception {

    }


}