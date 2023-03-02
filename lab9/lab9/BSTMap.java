package lab9;

import java.util.*;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }

        private boolean is_leaf() {
            return this.left == null && this.right == null;
        }
    }

    public Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        Node current = root;
        while(current !=null && !current.key.equals(key))
        {
            // key < current
            if(key.compareTo(current.key) < 0)
                current = current.left;
            else
                current = current.right;
        }
        if(current == null)
            return null;
        return current.value;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        Node current = root;
        Node parent = null;
        boolean is_left = false;
        while(current != null)
        {
            int val = key.compareTo(current.key);
            parent = current;
            if(val < 0)
            {
                current = current.left;
                is_left = true;
            }
            else if(val > 0)
            {
                current = current.right;
                is_left = false;
            }
            else
            {
                current.value = value;
                break;
            }
        }
        if(parent == null)
        {
            root = new Node(key,value);
            size++;
        }
        else if(current == null)
        {
            if(is_left)
                parent.left = new Node(key, value);
            else
                parent.right = new Node(key, value);
            size++;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        preorder(set, root);
        return set;
    }

    public void preorder(Collection s, Node n) {
        if(n == null)
            return;
        s.add(n.key);
        if(n.left != null)
            preorder(s, n.left);
        if(n.right != null)
            preorder(s, n.right);
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
            Node n = remove(key,null,root,false,false);
            if(n != null)
                return n.value;
            return null;
        }

    private Node remove(K key,V value, Node node, boolean value_check, boolean subtree_needed) {
        Node current = node;
        boolean is_left = false;
        Node parent = null;
        while(current !=null && !current.key.equals(key))
        {
            parent = current;
            if(key.compareTo(current.key) < 0)
            {
                is_left = true;
                current = current.left;
            }
            else
            {
                is_left = false;
                current = current.right;
            }
        }
        if(current == null || (value_check && current.value != value))
            return null;
        // removal
        Node removed = new Node(current.key, current.value);
        try {
            if(current.is_leaf())
            {
                if(is_left)
                    parent.left = null;
                else
                    parent.right = null;
            }
            else if(current.right == null)
                if(is_left)
                    parent.left = current.left;
                else
                    parent.right = current.left;
            else if(current.left == null)
                if(is_left)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            else
            {
                Node min = min(current.right);
                current.key = min.key;
                current.value = min.value;
                current.right = remove(min.key,null,current.right,false,true);
            }
        }
        catch (Exception e) {
            if(parent == null)
                return null;
        }
        if(!subtree_needed)
            return removed;
        return node;
    }

    private Node min(Node node)
    {
        while(node.left != null)
            node = node.left;
        return node;
    }


    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        Node n = remove(key,value,root,true,false);
        if(n != null)
            return n.value;
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        // preorder
        List<K> lst = new ArrayList<K>();
        preorder(lst, root);
        return lst.listIterator();
    }
}
