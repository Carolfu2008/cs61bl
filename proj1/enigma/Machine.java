// This is a SUGGESTED skeleton file.  Throw it away if you don't use it.
package enigma;

/** Class that represents a complete enigma machine.
 *  @author
 */
class Machine{
    Rotor[] rotors= new Rotor[5];
    // This needs other methods or constructors.
    Machine(){

    }
    /** Set my rotors to (from left to right) ROTORS.  Initially, the rotor
     *  settings are all 'A'. */
    void replaceRotors(Rotor[] rotors) {
        // FIXME

    }

    /** Set my rotors according to SETTING, which must be a string of four
     *  upper-case letters. The first letter refers to the leftmost
     *  rotor setting.  */
    void setRotors(String setting) {
        // FIXME
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        String out = null;
        msg = msg.toUpperCase();
        for(int i = 0;i < msg.length();i++){
            if(msg.charAt(i) != ' ') {
                int p = msg.charAt(i) - 'A';
                p = rotors[4].convertForward(p);
                p = rotors[3].convertForward(p);
                p = rotors[2].convertForward(p);
                p = rotors[1].convertForward(p);
                p = rotors[0].convertForward(p);
                p = rotors[1].convertBackward(p);
                p = rotors[2].convertBackward(p);
                p = rotors[3].convertBackward(p);
                p = rotors[4].convertBackward(p);
                char x = (char)(p+'A');
                out += x;
            }
            else out += ' ';
        }
        return out;
        // FIXME
    }
}
