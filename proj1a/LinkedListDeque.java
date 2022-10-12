public class LinkedListDeque<T> {

    private class Node { // node class
        private T item;
        private Node prev;
        private Node next;

        private Node(T Item, Node Prev, Node Next) { // constructor for node class
            item = Item;
            prev = Prev;
            next = Next;
        }
    }

    private Node sentinel;
    private Node front, end;
    private int size;

    public LinkedListDeque() { // constructor for deque class
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        front = sentinel;
        end = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node new_node = new Node(item, end, front);
        front.prev = new_node;
        front = new_node;
        size++;
    }

    public void addLast(T item) {
        Node new_node = new Node(item, end, front);
        end.next = new_node;
        end = new_node;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node ptr = front;
        do {
            if (ptr != sentinel) {
                System.out.print(ptr.item + " ");
            }
            ptr = ptr.next;
        } while (ptr != front);
        System.out.println();
    }

    public T removeFirst() {
        if(front == sentinel)
        {
            front = front.next;
        }
        T val = front.item;
        Node old_prev = front.prev;
        front = front.next;
        front.prev = old_prev;
        size--;
        return val;
    }

    public T removeLast() {
        if(end == sentinel)
        {
            end = end.prev;
        }
        T val = end.item;
        Node old_next = end.next;
        end = end.prev;
        end.next = old_next;
        size--;
        return val;
    }

    public T get(int index) {
        // TODO if index < 0 ?
        if (index < size / 2) {
            int cnt = 0;
            Node ptr = front;
            while (cnt != index) {
                ptr = ptr.next;
                if (ptr != sentinel) {
                    cnt++;
                }
            }
            return ptr.item;
        } else if (index < size) {
            int cnt = 1;
            index = size - index;
            Node ptr = end;
            while (cnt != index) {
                ptr = ptr.prev;
                if (ptr != sentinel) {
                    cnt++;
                }
            }
            return ptr.item;
        } else {
            return null;
        }
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(front, index);
    }

    private T getRecursiveHelper(Node ptr, int index) {
        // TODO if index < 0 ?
        if (index == 0) {
            return ptr.item;
        }
        if(ptr == sentinel)
        {
            return getRecursiveHelper(ptr.next, index);
        }
        return getRecursiveHelper(ptr.next, index - 1);
    }

}