import edu.princeton.cs.algs4.BST;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    public class BSTNode {
        public K key;
        public V value;
        public BSTNode left;
        public BSTNode right;
        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap() {
        this.size = 0;
        this.root = null;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper(root, key, value);
    }
    private BSTNode putHelper(BSTNode node, K key, V value) {
        if(node == null) {
            size++;
            return new BSTNode(key, value);
        }
        int cmp = key.compareTo(node.key);
        if(cmp == 0) {
            node.value = value;
        } else if(cmp < 0) {
            node.left = putHelper(node.left, key, value);
        } else {
            node.right = putHelper(node.right, key, value);
        }
        return node;
    }

    @Override
    public V get(K key) {
        return getHelper(root, key);
    }
    private V getHelper(BSTNode node, K key) {
        if(node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp == 0) {
            return node.value;
        } else if(cmp < 0) {
            return getHelper(node.left, key);
        } else {
            return getHelper(node.right, key);
        }
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }
    private boolean containsKeyHelper(BSTNode node, K key) {
        if(node == null) {
            return false;
        }
        int cmp = key.compareTo(node.key);
        if(cmp == 0) {
            return true;
        } else if(cmp < 0) {
            return containsKeyHelper(node.left, key);
        } else {
            return containsKeyHelper(node.right, key);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public java.util.Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
