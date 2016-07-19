import javax.xml.soap.Node;
import java.lang.reflect.Array;
import java.util.LinkedList;

/**
 *  A simple mapping from string names to string values backed by an array.
 *  Supports only A-Z for the first character of the key name. Values can be
 *  any valid string.
 *
 *  @author You
 */
public class SimpleNameMap {
    LinkedList<Entry>[] array = new LinkedList[26];
    /** A wrapper class for holding each (KEY, VALUE) pair. */
    private static class Entry {

        /** The key used for lookup. */
        private String _key;
        /** The associated value. */
        private String _value;

        /** Create a new (KEY, VALUE) pair. */
        public Entry(String key, String value) {
            _key = key;
            _value = value;
        }

        /** Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry other) {
            return _key.equals(other._key);
        }

        /** Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry &&
                    _key.equals(((Entry) other)._key) &&
                    _value.equals(((Entry) other)._value));
        }

    }

    /** Returns true if the given KEY is a valid name that starts with A-Z. */
    private static boolean isValidName(String key) {
        return 'A' <= key.charAt(0) && key.charAt(0) <= 'Z';
    }

    /**
     * Returns true if the map contains the KEY.
     */
    boolean containsKey(String key) {
        int index = key.hashCode();
        for (int i = 0; i < array[index].size(); i++){
            if (key.equals(array[index].get(i)._key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value for the specified KEY.
     */
    String get(String key) {
        int index = key.hashCode();
        for (int i = 0; i < array[index].size(); i++){
            if (key.equals(array[index].get(i))) {
                return array[index].get(i)._value;
            }
        }
        return null;
    }

    /**
     * Put a (KEY, VALUE) pair into this map.
     */
    void put(String key, String value) {
        int index = key.hashCode();
        if (array[index] == null) {
            array[index] = new LinkedList();
            array[index].add(new Entry(key,value));
        }else{
            array[index].add(new Entry(key,value));
        }
    }

    /**
     * Remove a single entry, KEY, from this table and returns true if successful.
     */
    boolean remove(String key) {
        int index = key.hashCode();
        for (int i = 0; i < array[index].size(); i++){
            if (key.equals(array[index].get(i))) {
                array[index].remove(i);
                return true;
            }
        }
        return false;
    }

}
