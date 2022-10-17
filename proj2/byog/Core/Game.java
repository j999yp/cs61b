package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main
     * menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will
     * be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The
     * game should
     * behave exactly as if the user typed these characters into the game after
     * playing
     * playWithKeyboard. If the string ends in ":q", the same world should be
     * returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should
     * return the same
     * world. However, the behavior is slightly different. After playing with
     * "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string
     * "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the
     * saved game.
     * 
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }

    // proj2 phase1

    private Random random;

    public Game(int seed) {
        random = new Random(seed);
    }

    private static final int room_width_limit = 5;
    private static final int room_height_limit = 4;
    private static final int room_num_limit = 15;

    public void generate_rooms(TETile world[][]) {
        int room_cnt = 0;
        while (room_cnt < room_num_limit) {
            int width, height;
            do {
                width = random.nextInt(room_width_limit) + 1;
            } while (width < 3);
            do {
                height = random.nextInt(room_height_limit) + 1;
            } while (height < 2);

            int xPos, yPos;
            do {
                xPos = random.nextInt(WIDTH);
            } while (xPos + width > WIDTH - 1 || xPos < 1);
            do {
                yPos = random.nextInt(HEIGHT);
            } while (yPos + height > HEIGHT - 1 || yPos < 1);

            if (check_overlap(world, xPos, yPos, width, height)) {
                generate_room(world, xPos, yPos, width, height);
                room_cnt++;
            }
        }
        // ter.renderFrame(world);
    }

    private boolean check_overlap(TETile world[][], int xPos, int yPos, int width, int height) {
        for (int i = xPos - 1; i <= xPos + width; i++) {
            for (int j = yPos - 1; j <= yPos + height; j++) {
                if (world[i][j] == Tileset.FLOOR) {
                    return false;
                }
            }
        }
        return true;
    }

    private void generate_room(TETile world[][], int xPos, int yPos, int width, int height) {
        for (int i = xPos; i < xPos + width; i++) {
            for (int j = yPos; j < yPos + height; j++) {
                world[i][j] = Tileset.FLOOR;
            }
        }
    }

}
