package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class testGame {

    static public void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(Game.WIDTH, Game.HEIGHT);
        Game game = new Game(1,ter);
        TETile[][] world = game.playWithInputString("abcNab2abb3412S");
        ter.renderFrame(world);
    }
}
