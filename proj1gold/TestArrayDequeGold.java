import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void myTest() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> anser = new ArrayDequeSolution<>();
        String message = "";
        int num;

        while (true) {
            int rand = StdRandom.uniform(1, 5);
            if (anser.size() == 0) {
                rand += 2;
            }
            switch (rand) {
                case (1):
                    message += "removeFirst()\n";
                    assertEquals(message,  anser.removeFirst(),student.removeFirst());
                    break;
                case (2):
                    message+="removeLast()\n";
                    assertEquals(message,anser.removeLast(),student.removeLast());
                    break;
                case(3):
                    num = StdRandom.uniform(1000);
                    message+="addFirst("+num+")\n";
                    student.addFirst(num);
                    anser.addFirst(num);
                    break;
                case(4):
                    num = StdRandom.uniform(1000);
                    message+="addLast("+num+")\n";
                    student.addLast(num);
                    anser.addLast(num);
                    break;
            }
        }
    }
}
