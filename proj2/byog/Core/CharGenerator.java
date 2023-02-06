package byog.Core;

import edu.princeton.cs.introcs.StdDraw;

public class CharGenerator {
    boolean playWithInputString = false;
    boolean peeked = false;
    char buffer;
    String s;
    int index = 0;

    public CharGenerator(String s) {
        playWithInputString = true;
        this.s = s;
    }

    public CharGenerator() {
    }

    public char next() {
        if (playWithInputString)
            if (has_next())
                return s.charAt(index++);
            else
                return Character.MIN_VALUE;
        else {
            if (peeked) {
                peeked = false;
                return buffer;
            } else {
                while (!StdDraw.hasNextKeyTyped())
                    ;
                return StdDraw.nextKeyTyped();
            }
        }
    }

    public char peek() {
        if (playWithInputString)
            if (has_next())
                return s.charAt(index);
            else
                return Character.MIN_VALUE;
        else
            while (!StdDraw.hasNextKeyTyped())
                ;
        peeked = true;
        buffer = StdDraw.nextKeyTyped();
        return buffer;
    }

    public boolean has_next() {
        if (playWithInputString)
            return index < s.length();
        return true;
    }
}
