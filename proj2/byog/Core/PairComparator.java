package byog.Core;

import java.util.*;

class PairComparator implements Comparator<Pair<Pair, Integer>> {
    public int compare(Pair<Pair, Integer> p1, Pair<Pair, Integer> p2) {
        return p1.itemB - p2.itemB;
        // if (p1.itemB < p2.itemB)
        //     return -1;
        // else if (p1.itemB > p2.itemB)
        //     return 1;
        // return 0;
    }
}