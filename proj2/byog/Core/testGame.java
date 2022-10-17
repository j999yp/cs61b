package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class testGame {

    static public void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(Game.WIDTH, Game.HEIGHT);
        Game game = new Game(1);
        TETile[][] world = new TETile[Game.WIDTH][Game.HEIGHT];
        for (int x = 0; x < Game.WIDTH; x++) {
            for (int y = 0; y < Game.HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        game.generate_rooms(world);
        ter.renderFrame(world);
    }
}
