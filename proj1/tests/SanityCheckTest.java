package tests;

import enigma.EnigmaException;
import enigma.Main;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.UnsupportedOperationException;
import org.junit.rules.ExpectedException;
import org.junit.runner.JUnitCore;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
public class SanityCheckTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void trivialTest() {
        String[] args = {"tests/correct/trivial/trivial.inp", "output.txt",
                         "tests/correct/trivial/trivial.out"};
        deleteFile(args[1]);
        Main.main(args);
        checkIsFile(args[0]);
        checkIsFile(args[1]);
        checkFilesEqual(args[1], args[2]);
        deleteFile(args[1]);

        args = new String[]{"tests/correct/trivial/trivial_encrypted.inp", "output.txt",
                            "tests/correct/trivial/trivial_encrypted.out"};
        Main.main(args);
        checkIsFile(args[0]);
        checkIsFile(args[1]);
        checkFilesEqual(args[1], args[2]);
        deleteFile(args[1]);
    }

    @Test
    public void largeTest() {
        String[] args = {"tests/correct/large/large.inp", "output.txt",
                         "tests/correct/large/large.out"};
        deleteFile(args[1]);
        Main.main(args);
        checkIsFile(args[0]);
        checkIsFile(args[1]);
        checkFilesEqual(args[1], args[2]);
        deleteFile(args[1]);
 
        args = new String[]{"tests/correct/large/large_encrypted.inp", "output.txt",
                            "tests/correct/large/large_encrypted.out"};
        Main.main(args);
        checkIsFile(args[0]);
        checkIsFile(args[1]);
        checkFilesEqual(args[1], args[2]);
        deleteFile(args[1]);
    }

    @Test
    public void errorTest() {
        String[] args = new String[]{"tests/error/trivialerr.inp", "output.txt"};
        expectedEx.expect(EnigmaException.class);
        Main.main(args);
        deleteFile(args[1]);
    }

    public void checkIsFile(String filename) {
        File f = new File(filename);
        assertTrue(f.exists());
        assertTrue(f.isFile());
    }

    public void deleteFile(String filename) {
        File f = new File(filename);
        if (f.exists()) {
            try {
                f.delete();
            } catch (SecurityException e) {
                System.out.println("Encountered exception while deleting: " + e);
            }
        }
    }

    public void checkFilesEqual(String file1, String file2) {
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        try {
            br1 = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
            br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
        } catch (FileNotFoundException e) {
            assertTrue("FileNotFoundException: one of input files does not exist.", false);
        }

        try {
            int read1 = br1.read();
            int read2 = br2.read();
            while (true) {
                assertEquals(read1, read2);
                read1 = br1.read();
                read2 = br2.read();

                if (read1 == -1) {
                    return;
                }
            }
        } catch (IOException e) {
            /* If reached, something has gone wrong. */
            assertTrue("Exception while reading files.", false);
        }
    }

    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        runner.run(SanityCheckTest.class);
    }
}
