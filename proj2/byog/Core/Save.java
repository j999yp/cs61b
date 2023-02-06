package byog.Core;

import java.io.Serializable;;

public class Save implements Serializable {
    int x, y, seed;

    public Save(int seed, int x, int y) {
        this.seed = seed;
        this.x = x;
        this.y = y;
    }
}
