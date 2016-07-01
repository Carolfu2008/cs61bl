// This is a SUGGESTED skeleton file.  Throw it away if you don't use it.
package enigma;
/** Class that represents a rotor in the enigma machine.
 *  @author Hong-Shuo Chen
 */
class Rotor extends  PermutationData {
    // This needs other methods, fields, and constructors.
    Rotor() { }

    /** Size of alphabet used for plaintext and ciphertext. */
    static final int ALPHABET_SIZE = 26;

    /** Assuming that P is an integer in the range 0..25, returns the
     *  corresponding upper-case letter in the range A..Z. */
    static char toLetter(int p) {
        return (char) ('A' + p);  // FIXME
    }

    /** Assuming that C is an upper-case letter in the range A-Z, return the
     *  corresponding index in the range 0..25. Inverse of toLetter. */
    static int toIndex(char c) {
        return c - 'A';  // FIXME
    }

    /** Returns true iff this rotor has a ratchet and can advance. */
    boolean advances() {
        return true;
    }

    /** Returns true iff this rotor has a left-to-right inverse. */
    boolean hasInverse() {
        return true;
    }

    /** Return my current rotational setting as an integer between 0
     *  and 25 (corresponding to letters 'A' to 'Z').  */
    int getSetting() {
        return _setting;
    }

    /** Set getSetting() to POSN.  */
    void set(int posn) {
        assert 0 <= posn && posn < ALPHABET_SIZE;
        _setting = posn;
    }

    /** Return the conversion of P (an integer in the range 0..25)
     *  according to my permutation. */
    int convertForward(int p) {
        //System.out.println(p);
        if (p >= 26 || p < 0) throw new EnigmaException();
        if (name < 10) p += getSetting();
        p = p % 26;
        //System.out.println(p);
        p = toIndex(ROTOR_SPECS[name][1].charAt(p));
        //System.out.println(p);
        if (name < 10) p = p - getSetting();
        p = p % 26;
        if (p < 0) p += 26;
      //System.out.println(p);
        return p; // FIXME
    }

    /** Return the conversion of E (an integer in the range 0..25)
     *  according to the inverse of my permutation. */
    int convertBackward(int e) {
        if(e >= 26 || e < 0) throw new EnigmaException();
        if (name < 10) e += getSetting();
        e = e % 26;
        //System.out.println(e);
        e = toIndex(ROTOR_SPECS[name][2].charAt(e));
        //System.out.println(e);
        if (name < 10) e -= getSetting();
        e = e % 26;
        if (e < 0) e += 26;
        //System.out.println(e);
        return e; // FIXME
    }

    /** Returns true iff I am positioned to allow the rotor to my left
     *  to advance. */
    boolean atNotch() {
        if (_setting == toIndex(ROTOR_SPECS[name][3].charAt(0))) {
            return true;
        }
        if (name == 5 || name == 6 || name == 7) {
            if (_setting == toIndex(ROTOR_SPECS[name][3].charAt(1))) {
                return true;
            } else  return false;
        }
        return false;
    }

    /** Advance me one position. */
    void advance() {
        _setting++;
        if (_setting == 26) _setting = 0;
    }

    /** My current setting (index 0..25, with 0 indicating that 'A'
     *  is showing). */
    private int _setting;
    protected int name;


}
