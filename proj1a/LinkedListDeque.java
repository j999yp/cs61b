public class LinkedListDeque<T> {

    private class Node { // node class
        private T item;
        private Node prev;
        private Node next;

        private Node(T iitem, Node pprev, Node nnext) { // constructor for node class
            item = iitem;
            prev = pprev;
            next = nnext;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() { // constructor for deque class
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next = newNode;
        newNode.next.prev = newNode;
        size++;
    }

    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = newNode;
        newNode.prev.next = newNode;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        T val = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        if (size < 0) {
            size = 0;
        }
        return val;
    }

    public T removeLast() {
        T val = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        if (size < 0) {
            size = 0;
        }
        return val;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node ptr = sentinel.next;
        while (index != 0) {
            ptr = ptr.next;
            index--;
        }
        return ptr.item;
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node ptr, int index) {
        // TODO if index < 0 ?
        if (index == 0) {
            return ptr.item;
        }
        return getRecursiveHelper(ptr.next, index - 1);
    }

}
