import javax.xml.soap.Node;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *  A simple mapping from string names to string values backed by an array.
 *  Supports only A-Z for the first character of the key name. Values can be
 *  any valid string.
 *
 *  @author You
 */
public class HashMap<K,V> implements Map61BL<K,V> {
    LinkedList<Entry>[] array;
    double loadFactor;
    int size;
    /**
     * Create a new hash map with default parameters.
     */
    HashMap() {
        array = new LinkedList[16];
        loadFactor = 0.75;
    }

    /**
     * Create a new hash map with an array of size INITIALCAPACITY.
     */
    HashMap(int initialCapacity) {
        array = new LinkedList[initialCapacity];
        loadFactor = 0.75;
    }

    /**
     * Create a new hash map with INITIALCAPACITY and LOADFACTOR.
     */
    HashMap(int initialCapacity, float loadFactor) {
        array = new LinkedList[initialCapacity];
        this.loadFactor = loadFactor;
    }

    /**
     * Return the capacity of this hash table's internal array.
     */
    int capacity() {
        return array.length;
    }

    @Override
    public void clear() {
        array = new LinkedList[16];
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int index = (key.hashCode()& 0x7FFFFFFF) % array.length;
        if (array[index] == null)
            return false;
        for (int i = 0; i < array[index].size(); i++){
            if (key.equals(array[index].get(i)._key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = (key.hashCode()& 0x7FFFFFFF) % array.length;
        for (int i = 0; i < array[index].size(); i++){
            if (key.equals(array[index].get(i)._key)) {
                return array[index].get(i)._value;
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)){
            remove(key);
        }
        size++;
        if ((double)(size)/(double)(array.length) > loadFactor) {
            LinkedList[] temp = new LinkedList[array.length * 2];
            for (int i = 0;i < array.length;i++){
                if (array[i] == null)
                    continue;
                for (int j = 0; j < array[i].size();j++){
                    int index = (array[i].get(j)._key.hashCode() & 0x7FFFFFFF)% temp.length;
                    if (temp[index] == null) {
                        temp[index] = new LinkedList();
                        temp[index].add(new Entry(array[i].get(j)._key,array[i].get(j)._value));
                    }else{
                        temp[index].add(new Entry(array[i].get(j)._key,array[i].get(j)._value));
                    }
                }
            }
            array = temp;
        }
        int index = (key.hashCode()& 0x7FFFFFFF) % array.length;
        if (array[index] == null) {
            array[index] = new LinkedList();
            array[index].addFirst(new Entry(key,value));
        }else{
            array[index].add(new Entry(key,value));
        }
    }

    @Override
    public V remove(K key) {
        int index = (key.hashCode()& 0x7FFFFFFF) % array.length;
        size--;
        for (int i = 0; i < array[index].size(); i++){
            if (key.equals(array[index].get(i)._key)) {
                V rtn = array[index].get(i)._value;
                array[index].remove(i);
                return rtn;
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key, V value) {
        int index = (key.hashCode()& 0x7FFFFFFF) % array.length;
        size--;
        for (int i = 0; i < array[index].size(); i++){
            if (key.equals(array[index].get(i)._key)) {
                array[index].remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }
    private class HashMapIterator implements Iterator {
        int p = 0;
        int q = 0;
        int k = 0;
        @Override
        public boolean hasNext() {
            if (k >= size)
                return false;
            else
                return true;
        }

        @Override
        public Object next() {
            while (p < array.length){
                if (array[p] != null && !array[p].isEmpty()){
                    if (q < array[p].size()){
                        k++;
                        return array[p].get(q++)._key;
                    }else{
                        p++;
                        q = 0;
                    }
                }else {
                    p++;
                    q = 0;
                }
            }
            return null;
        }
    }

    /** A wrapper class for holding each (KEY, VALUE) pair. */
    private  class Entry {

        /** The key used for lookup. */
        private K _key;
        /** The associated value. */
        private V _value;

        /** Create a new (KEY, VALUE) pair. */
        public Entry(K key, V value) {
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
            return (//other instanceof Entry &&
                    _key.equals(((Entry) other)._key) &&
                    _value.equals(((Entry) other)._value));
        }

    }
}