package hashmap;

import java.util.Collection;
import java.lang.Math;


/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size;
    private double loadFactor;

    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
     }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) { 
        this.buckets = new Collection[initialCapacity];
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new java.util.ArrayList<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public void put(K key, V value) {
        resize();
        int index = Math.floorMod(key.hashCode(), buckets.length);
        if(buckets[index] == null) {
            buckets[index] = createBucket();
        }
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                node.value = value;
            }
        }
        buckets[index].add(new Node(key, value));
        size++;
    }

    @Override
    public V get(K key) {
        int  index = Math.floorMod(key.hashCode(), buckets.length);
        if(buckets[index] == null) {
            return null;
            }
        for(Node node : buckets[index]) {
            if(node.key.equals(key)) {
                return node.value;
            }
        }
        return null;        
    }

    @Override
    public boolean containsKey(K key) {
        int index = Math.floorMod(key.hashCode(), buckets.length);
        if(buckets[index] == null) {
            return false;
        }
        for(Node node : buckets[index]) {
            if(node.key.equals(key)) {
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
    public void clear() {
        buckets = new Collection[buckets.length];
        size = 0;
    }

    @Override
    public java.util.Set<K> keySet() {
        java.util.Set<K> set = new java.util.HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                for (Node node : buckets[i]) {
                    set.add(node.key);
                }
            }
        }
        return set;
    }
    
    @Override
    public V remove(K key) {
        int index = Math.floorMod(key.hashCode(), buckets.length);
        if(buckets[index] == null) {
            return null;
        }
        for(Node node : buckets[index]) {
            if(node.key.equals(key)) {
                V value = node.value;
                buckets[index].remove(node);
                size--;
                return value;
            }
        }
        return null;
    }

    public void resize() {
        if((double)size / buckets.length > loadFactor) {
            Collection<Node>[] oldBuckets = buckets;
            buckets = new Collection[oldBuckets.length * 2];
            size = 0;
            for (Collection<Node> bucket : oldBuckets) {
                if (bucket != null) {
                    for (Node node : bucket) {
                        put(node.key, node.value);
                    }
                }
            }
        }
    }

    @Override
    public java.util.Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
