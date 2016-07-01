// This is a SUGGESTED skeleton file.  Throw it away if you don't use it.
package enigma;

/** Class that represents a complete enigma machine.
 *  @author
 */
class Machine {
    Rotor[] rotors = new Rotor[5];
    // This needs other methods or constructors.
    Machine() { }
    /** Set my rotors to (from left to right) ROTORS.  Initially, the rotor
     *  settings are all 'A'. */
    void replaceRotors(Rotor[] e) {
        // FIXME
        rotors[4] = e[4];
        rotors[3] = e[3];
        rotors[2] = e[2];
        rotors[1] = e[1];
        rotors[0] = e[0];
    }

    /** Set my rotors according to SETTING, which must be a string of four
     *  upper-case letters. The first letter refers to the leftmost
     *  rotor setting.  */
    void setRotors(String setting) {
        // FIXME
        rotors[4].set(setting.charAt(3) - 'A');
        rotors[3].set(setting.charAt(2) - 'A');
        rotors[2].set(setting.charAt(1) - 'A');
        rotors[1].set(setting.charAt(0) - 'A');
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        String out = "";
        for (int i = 0; i < msg.length(); i++) {
            int p = msg.charAt(i) - 'A';
            if (rotors[3].atNotch()) {
                rotors[2].advance();
                if (!rotors[4].atNotch()) rotors[3].advance();
            }
            if (rotors[4].atNotch()) {
                rotors[3].advance();
            }
            rotors[4].advance();
            p = rotors[4].convertForward(p);
            p = rotors[3].convertForward(p);
            p = rotors[2].convertForward(p);
            p = rotors[1].convertForward(p);
            p = rotors[0].convertForward(p);
            p = rotors[1].convertBackward(p);
            p = rotors[2].convertBackward(p);
            p = rotors[3].convertBackward(p);
            p = rotors[4].convertBackward(p);
            char x = (char) (p + 'A');
            out += x;

        }
        return out;
        // FIXME
    }
}
