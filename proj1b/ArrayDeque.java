public class ArrayDeque<T> implements Deque<T> {
    private int total = 8;
    private int free = 8;
    private int head = 0;
    private int tail = 1;

    private T[] list;

    public ArrayDeque() { // constructor
        list = (T[]) new Object[8];
    }

    private void extend() {
        T[] newList = (T[]) new Object[total * 2];
        int ptr = tail;
        int i = 0;
        while (ptr != head) {
            newList[i++] = list[ptr++];
            ptr = ptr % total;
        }
        newList[i] = list[head];
        list = newList;
        free = total;
        total = total * 2;
        head = total - 1;
        tail = i + 1;
    }

    private void free() {
        T[] newList = (T[]) new Object[total / 2];
        int ptr = (head + 1) % total;
        int i = 0;
        while (ptr != tail) {
            newList[i++] = list[ptr++];
            ptr = ptr % total;
        }

        list = newList;
        free = free - total / 2;
        total = total / 2;
        head = total - 1;
        tail = i;
    }

    @Override
    public void addFirst(T item) {
        if (free == 0) {
            extend();
        }
        list[head] = item;
        head = (head - 1 + total) % total;
        free--;
    }

    @Override
    public void addLast(T item) {
        if (free == 0) {
            extend();
        }
        list[tail] = item;
        tail = (tail + 1) % total;
        free--;
    }

    @Override
    public boolean isEmpty() {
        return free == total;
    }

    @Override
    public int size() {
        return total - free;
    }

    @Override
    public void printDeque() {
        int i = (head + 1) % total;
        while (i != tail) {
            System.out.print(list[i] + " ");
            i++;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (free == total) {
            return null;
        }
        head = (head + 1) % total;
        T val = list[head];
        free++;
        if ((total >= 16) && (free > total * 3 / 4)) {
            free();
        }
        return val;
    }

    @Override
    public T removeLast() {
        if (free == total) {
            return null;
        }
        tail = (tail - 1 + total) % total;
        T val = list[tail];
        free++;
        if ((total >= 16) && (free > total * 3 / 4)) {
            free();
        }
        return val;
    }

    @Override
    public T get(int index) {
        return list[(head + 1 + index) % total];
    }

}
