package byog.Core;

public class Pair<attrbA,attrbB> {
    public attrbA itemA;
    public attrbB itemB;

    public Pair(attrbA i, attrbB j) {
        itemA = i;
        itemB = j;
    }
    public boolean equals(Pair x)
    {
        return this.itemA == x.itemA && this.itemB == x.itemB;
    }
}
