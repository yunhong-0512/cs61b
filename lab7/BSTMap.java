import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {

    private int size;
    /** Keys and values are stored in a node of Binary Search Tree. */
    private BST node;

    public BSTMap() {
        size = 0;
        node = null;
    }

    /** Represents one node in the BST that stores the key-value pairs
     *  in the dictionary. */
    public class BST {
        /** Stores the key of the key-value pair of this node in the tree. */
        private K key;
        /** Stores the value of the key-value pair of this node in the tree. */
        private V value;
        /** Stores the left node. */
        private BST left;
        /** Stores the right node. */
        private BST right;

        BST(K k, V v, BST left, BST right) {
            this.key = k;
            this.value = v;
            this.left = left;
            this.right = right;
        }

        BST(K k, V v) {
            this.key = k;
            this.value = v;
        }

        /** Return true if the BST contains the specific key. */
        public V find(K key) {
            if (this == null) {
                return null;
            }
            if (key.compareTo(this.key) == 0) {
                return this.value;
            } else if (key.compareTo(this.key) > 0) {
                if (this.right == null) {
                    return null;
                }
                return this.right.find(key);
            } else {
                if (this.left == null) {
                    return null;
                }
                return this.left.find(key);
            }
        }

        public BST insert(BST n, K key, V value) {
            if (n == null) {
                size += 1;
                return new BST(key, value);
            }
            if (n.find(key) != null) {
                return n;
            }
            if(key.compareTo(n.key) > 0) {
                n.right = insert(n.right, key, value);
            } else {
                n.left = insert(n.left, key, value);
            }
            return n;
        }

        public void printInOder() {
            if (this != null) {
                this.left.printInOder();
                System.out.println("<" + this.key.toString() + ", " + this.value.toString() + ">");
                this.right.printInOder();
            }
        }

    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        size = 0;
        node = null;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        if (this.node == null) {
            return false;
        }
        if (this.node.find(key) == null) {
            return false;
        }
        return true;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (this.node == null) {
            return null;
        }
        return this.node.find(key);
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value){
        if (this.node == null) {
            this.node = new BST(key, value);
            size = 1;
            return;
        }
        this.node.insert(this.node, key, value);
    }

    /** Prints out the Map in order of increasing Key. */
    public void printInOrder() {
        this.node.printInOder();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
        //return new BSTMapIter();
    }

    /** An iterator that iterates over the keys of the dictionary.
    private class BSTMapIter implements Iterator<K> {

        private BST cur;

        public BSTMapIter() {
            cur = node;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public K next() {
            if (this != null) {
                return cur.left.
            }
            K ret = cur.key;
            cur = cur.next;
            return ret;
        }
    }
    */



    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /** Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /** Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
