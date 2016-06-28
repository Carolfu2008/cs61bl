package enigma;

/** Class that represents a rotor that has no ratchet and does not advance.
 *  @author
 */
class FixedRotor extends Rotor {

    // This needs other methods or constructors.
    FixedRotor(){}
    FixedRotor(int name,int _setting){
        this.name = name;
        this.set(_setting);
    }

    @Override
    boolean advances() {
        return false;
    }

    @Override
    boolean atNotch() {
        return false;
    }

    /** Fixed rotors do not advance. */
    @Override
    void advance() {
    }

}
