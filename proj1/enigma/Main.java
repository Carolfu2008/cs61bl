// This is a SUGGESTED skeleton file.  Throw it away if you want.
package enigma;

import java.io.*;

/** Enigma simulator.
 *  @author
 */
public final class Main {

    // WARNING: Not all methods that have code in them are complete!

    /** Process a sequence of encryptions and decryptions, as
     *  specified in the input from the standard input.  Print the
     *  results on the standard output. Exits normally if there are
     *  no errors in the input; otherwise with code 1. */
    public static void main(String[] args) {
        Machine M;
        BufferedReader input = null;
        try {
            input = new BufferedReader(
                    new InputStreamReader(new FileInputStream(args[0])));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file found.");
        }

        String outputFilename;
        if (args.length >= 2) {
            outputFilename = args[1];
        } else {
            outputFilename = "output.txt";
        }

        //buildRotors();
        Rotor[] rotors = new Rotor[5];
        rotors[4]  = new Rotor();
        rotors[3]  = new Rotor();
        rotors[2]  = new Rotor();
        rotors[1] = new FixedRotor();
        rotors[0] = new Reflector();

        M = null;

        int x = 0;
        try {
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                }
                if (isConfigurationLine(line)) {
                    x++;
                    M = new Machine();
                    M.replaceRotors(rotors);
                    configure(M, line);
                } else {
                    if (x == 0) throw new EnigmaException();
                    writeMessageLine(M.convert(standardize(line)),
                                     outputFilename);
                }

            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
    }

    /** Return true iff LINE is an Enigma configuration line. */
    private static boolean isConfigurationLine(String line) {
        if (line.length() == 0) return false;
        else if (line.charAt(0) == '*') { return true; }
        return false; // FIXME
    }
    public static int change(String p) {
        if (p.equals("I")) {
            return 0;
        } else if (p.equals("II")) {
            return 1;
        } else if (p.equals("III")) {
            return 2;
        } else if (p.equals("IV")) {
            return 3;
        } else if (p.equals("V")) {
            return 4;
        } else if (p.equals("VI")) {
            return 5;
        } else if (p.equals("VII")) {
            return 6;
        } else if (p.equals("VIII")) {
            return 7;
        } else {
            System.out.println("Wrong");
            throw new EnigmaException();
        }
    }
    /** Configure M according to the specification given on CONFIG,
     *  which must have the format specified in the assignment. */
    private static void configure(Machine M, String config) {
        String[] splitStr = config.split(" ");
        if (splitStr.length != 7) {
            throw new EnigmaException();
        }
        //for(int i =0;i < 7;i++) System.out.println(splitStr[i]);
        if (!splitStr[1].equals("B") && !splitStr[2].equals("C")) {
            throw new EnigmaException();
        }
        if(!splitStr[2].equals("BETA") && !splitStr[2].equals("GAMMA")) {
            throw new EnigmaException();
        }
        M.rotors[0].name = (splitStr[1].charAt(0)  - 'B' + 10);
        //System.out.println(M.rotors[0].name);
        if (splitStr[2].charAt(0) == 'B') {
            M.rotors[1].name = 8;
        } else M.rotors[1].name = 9;
        //System.out.println(M.rotors[1].name);
        M.rotors[2].name = change(splitStr[3]);
        M.rotors[3].name = change(splitStr[4]);
        M.rotors[4].name = change(splitStr[5]);
        //System.out.println(M.rotors[2].name);
        //System.out.println(M.rotors[3].name);
        //System.out.println(M.rotors[4].name);
        M.setRotors(splitStr[6]);

        // FIXME
    }

    /** Return the result of converting LINE to all upper case,
     *  removing all blanks and tabs.  It is an error if LINE contains
     *  characters other than letters and blanks. */
    private static String standardize(String line) {
        line = line.toUpperCase();
        line = line.trim();
        line = line.replaceAll(" ", "");
        return line; // FIXME
    }

    /** Write MSG in groups of five to out file (except that the last
     *  group may have fewer letters). */
    private static void writeMessageLine(String msg, String filename) {
        try {
            FileWriter writer = new FileWriter(filename, true);
            String outputString = ""; 
            for (int i = 0; i < msg.length(); i += 5) {
                outputString += msg.substring(i, Math.min(i + 5, msg.length()));
                if (i + 5 < msg.length()) {
                    outputString += " ";
                }
            }
            writer.write(outputString + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException when writing: " + e);
        }
    }

    /** Create all the necessary rotors. */
    private static void buildRotors() {
        // FIXME
    }

}

