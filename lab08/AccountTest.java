import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ENVY on 7/1/2016.
 */
public class AccountTest {
    @org.junit.Test
    public void testInit() throws Exception{
        Account c = new Account(1000);
        assertTrue(c.getBalance() == 1000);
    }

    @Test
    public void testInvalidArgs() throws Exception{
        Account c = new Account(1000);
        c.deposit(-1000);
        assertTrue(c.getBalance() == 1000);
        c.withdraw(-1000);
        assertTrue(c.getBalance() == 1000);
    }

    @Test
    public void testOverdraft() throws Exception{
        Account c = new Account(1000);
        c.withdraw(2000);
        assertTrue(c.getBalance() == 1000);
    }

    @Test
    public void testDeposit() throws Exception {
        Account c = new Account(1000);
        c.deposit(1000);
        assertTrue(c.getBalance() == 2000);
    }

    @Test
    public void withdraw() throws Exception {
        Account c = new Account(1000);
        c.withdraw(500);
        assertTrue(c.getBalance() == 500);
    }


}