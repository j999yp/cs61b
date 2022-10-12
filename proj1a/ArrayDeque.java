public class ArrayDeque<Type> {
    private int total = 8;
    private int free = 8;
    private int head = 0;
    private int tail = 1;

    private Type[] list;
    public ArrayDeque() { // constructor
        list = (Type[]) new Object[8];
    }

    private void Extend(){
        Type[] newList = (Type[]) new Object[total * 2];
        int ptr = tail;
        int i = 0;
        while(ptr != head){
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

    private void Free(){
        Type[] newList = (Type[]) new Object[total / 2];
        int ptr = (head + 1) % total;
        int i = 0;
        while(ptr != tail){
            newList[i++] = list[ptr++];
            ptr = ptr % total;
        }
        
        list = newList;
        free = free - total / 2;
        total = total / 2;
        head = total - 1;
        tail = i;
    }

    public void addFirst(Type item){
        if(free == 0){
            Extend();
        }
        list[head] = item;
        head = (head - 1 + total) % total;
        free--;
    }

    public void addLast(Type item){
        if(free == 0){
            Extend();
        }
        list[tail] = item;
        tail = (tail + 1) % total;
        free--;
    }

    public boolean isEmpty(){
        return free == 0;
    }

    public int size(){
        return total - free;
    }

    public void printDeque(){
        int i = (head + 1)% total;
        while(i != tail){
            System.out.print(list[i]+" ");
        }
        System.out.println();
    }

    public Type removeFirst(){
        if(free == total){
            return null;
        }
        head = (head + 1) % total;
        Type val = list[head];
        free++;
        if((total >= 16) && (free > total * 3 / 4)){
            Free();
        }
        return val;
    }

    public Type removeLast(){
        if(free == total){
            return null;
        }
        tail = (tail - 1) % total;
        Type val = list[tail];
        free++;
        if((total >= 16) && (free > total * 3 / 4)){
            Free();
        }
        return val;
    }

    public Type get(int index){
        return list[(head+1+index)%total];
    }

}
