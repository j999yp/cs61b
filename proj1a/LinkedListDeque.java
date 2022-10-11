public class LinkedListDeque<Type>{

    private class Node{ //node class
        private Type item;
        private Node prev;
        private Node next;

        private Node(Type Item, Node Prev, Node Next) { //constructor for node class
            item=Item;
            prev=Prev;
            next=Next;
        }   
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){ //constructor for deque class
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(Type item){
        Node new_node = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = new_node;
        size++;
    }

    public void addLast(Type item){
        Node new_node = new Node(item, sentinel, sentinel.next);
        sentinel.next = new_node;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node ptr = sentinel.next;
        while(ptr != sentinel){
            System.out.print(ptr.item+" ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    public Type removeFirst(){
        Type val = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return val;
    }

    public Type removeLast(){
        Type val = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size--;
        return val;
    }

    public Type get(int index){
        //TODO if index < 0 ?
        if(index < size/2){
            int cnt = 0;
            Node ptr = sentinel.next;
            while(cnt != index){
                ptr = ptr.next;
                cnt++;
            }
            return ptr.item;
        }
        else if(index < size){
            int cnt = 1;
            index = size - index;
            Node ptr = sentinel.prev;
            while(cnt != index){
                ptr = ptr.prev;
                cnt++;
            }
            return ptr.item;
        }
        else{
            return null;
        }
    }

    public Type getRecursive(int index){
        return getRecursiveHelper(sentinel.next, index);
    }

    private Type getRecursiveHelper(Node ptr, int index){
        //TODO if index < 0 ?
        if(index == 0){
            return ptr.item;
        }
        return getRecursiveHelper(ptr.next, index-1);
    }

}