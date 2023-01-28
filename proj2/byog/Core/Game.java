package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.*;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    int union_set[];
    List<Pair> rooms = new ArrayList<Pair>();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 32;
    public static final int HEIGHT = 32;

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

    public Game(int seed, TERenderer tter) {
        random = new Random(seed);
        ter = tter;
    }

    public void generate_maze(TETile world[][]) {
        for (int x = 0; x < Game.WIDTH; x++) {
            for (int y = 0; y < Game.HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        int room_width = WIDTH/5;
        int room_height = HEIGHT/5;
        int room_num = random.nextInt(11)+10;
        generate_rooms(world, room_width, room_height, room_num);
        generate_hallways(world, room_num);
        generate_walls(world);
        generate_door(world);
    }

    private void generate_rooms(TETile world[][], int width_lim, int height_lim, int room_num) {
        // initialize
        union_set = new int[WIDTH * HEIGHT];
        for (int i = 0; i < WIDTH * HEIGHT; i++) {
            union_set[i] = -1;
        }
        int max_retry = 5;
        // generate rooms
        for (int room_cnt = 0; room_cnt < room_num; room_cnt++) {
            if (max_retry == 0)
                break;
            int room_width = random.nextInt(width_lim - 1) + 2;
            int room_height = random.nextInt(height_lim - 1) + 2;
            int x_pos = random.nextInt(WIDTH - room_width * 2) + room_width;
            int y_pos = random.nextInt(HEIGHT - room_height * 2) + room_height;
            int pos = y_pos * WIDTH + x_pos;
            if (check_overlap(union_set, x_pos, y_pos, room_width, room_height)) {
                room_cnt--;
                max_retry--;
                continue;
            }
            for (int i = x_pos; i < x_pos + room_width; i++) {
                for (int j = y_pos; j < y_pos + room_height; j++) {
                    if (i == x_pos && j == y_pos) {
                        union_set[j * WIDTH + i] = -(room_width * room_height);
                        Pair room = new Pair<Integer, Integer>(i, j);
                        rooms.add(room);
                    } else
                        union_set[j * WIDTH + i] = pos;
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }
    }

    private void generate_hallways(TETile world[][], int room_num) {
        int size = WIDTH * HEIGHT;
        int weight_map[][] = new int[WIDTH][HEIGHT];
        Pair[][] path = new Pair[WIDTH][HEIGHT];
        int[][] dist = new int[WIDTH][HEIGHT];
        boolean visited[][] = new boolean[WIDTH][HEIGHT];
        Comparator PairComparator = new PairComparator();
        PriorityQueue<Pair<Pair, Integer>> pQueue = new PriorityQueue<Pair<Pair, Integer>>(room_num, PairComparator);

        while (rooms.size() > 1) {
            // initialize
            for (int i = 0; i < WIDTH; i++)
                for (int j = 0; j < HEIGHT; j++) {
                    weight_map[i][j] = random.nextInt(size);
                    path[i][j] = null;
                    dist[i][j] = Integer.MAX_VALUE;
                    visited[i][j] = false;
                }

            int from = random.nextInt(rooms.size());
            int to = from;
            while (from == to) {
                to = random.nextInt(rooms.size());
            }
            Pair<Integer, Integer> current_pos = rooms.get(from);
            dist[current_pos.itemA][current_pos.itemB] = 0;
            pQueue.add(new Pair(current_pos, weight_map[current_pos.itemA][current_pos.itemB]));
            while (!pQueue.isEmpty()) {
                // djikstra
                int weight = pQueue.peek().itemB;
                current_pos = pQueue.poll().itemA;
                visited[current_pos.itemA][current_pos.itemB] = true;
                int total_weight = dist[current_pos.itemA][current_pos.itemB] + weight;
                enqueue(pQueue, current_pos, weight_map, visited);

                int x = current_pos.itemA - 1;
                int y = current_pos.itemB;
                if (!visited[x][y] && !(x == 0 || x == WIDTH - 1 || y == 0 || y == HEIGHT - 1))
                    if (total_weight < dist[x][y]) {
                        dist[x][y] = total_weight;
                        path[x][y] = current_pos;
                    }
                x = current_pos.itemA + 1;
                if (!visited[x][y] && !(x == 0 || x == WIDTH - 1 || y == 0 || y == HEIGHT - 1))
                    if (total_weight < dist[x][y]) {
                        dist[x][y] = total_weight;
                        path[x][y] = current_pos;
                    }
                x = current_pos.itemA;
                y = current_pos.itemB - 1;
                if (!visited[x][y] && !(x == 0 || x == WIDTH - 1 || y == 0 || y == HEIGHT - 1))
                    if (total_weight < dist[x][y]) {
                        dist[x][y] = total_weight;
                        path[x][y] = current_pos;
                    }
                y = current_pos.itemB + 1;
                if (!visited[x][y] && !(x == 0 || x == WIDTH - 1 || y == 0 || y == HEIGHT - 1))
                    if (total_weight < dist[x][y]) {
                        dist[x][y] = total_weight;
                        path[x][y] = current_pos;
                    }
            }
            current_pos = rooms.get(to);
            while (!current_pos.equals(rooms.get(from))) {
                world[current_pos.itemA][current_pos.itemB] = Tileset.FLOOR;
                current_pos = path[current_pos.itemA][current_pos.itemB];
            }
            rooms.remove(to);
        }
    }

    private void enqueue(PriorityQueue pQueue, Pair<Integer, Integer> middle, int[][] weight_map, boolean visited[][]) {
        int x = middle.itemA - 1;
        int y = middle.itemB;
        if (!(x == 0 || x == WIDTH - 1 || y == 0 || y == HEIGHT - 1) && !visited[x][y])
            pQueue.add(new Pair(new Pair(x, y), weight_map[x][y]));
        x = middle.itemA + 1;
        if (!(x == 0 || x == WIDTH - 1 || y == 0 || y == HEIGHT - 1) && !visited[x][y])
            pQueue.add(new Pair(new Pair(x, y), weight_map[x][y]));
        x = middle.itemA;
        y = middle.itemB - 1;
        if (!(x == 0 || x == WIDTH - 1 || y == 0 || y == HEIGHT - 1) && !visited[x][y])
            pQueue.add(new Pair(new Pair(x, y), weight_map[x][y]));
        y = middle.itemB + 1;
        if (!(x == 0 || x == WIDTH - 1 || y == 0 || y == HEIGHT - 1) && !visited[x][y])
            pQueue.add(new Pair(new Pair(x, y), weight_map[x][y]));
    }

    private void generate_walls(TETile world[][]) {
        for (int i = 0; i < WIDTH; i++)
            for (int j = 0; j < HEIGHT; j++)
                if (check_wall(world, i, j))
                    world[i][j] = Tileset.WALL;
    }

    private boolean check_wall(TETile world[][], int x, int y) {
        if ((x != 0 && y != 0 && world[x - 1][y - 1] == Tileset.FLOOR) ||
                (x != 0 && world[x - 1][y] == Tileset.FLOOR) ||
                (x != 0 && y != HEIGHT - 1 && world[x - 1][y + 1] == Tileset.FLOOR) ||
                (y != 0 && world[x][y - 1] == Tileset.FLOOR) ||
                (y != HEIGHT - 1 && world[x][y + 1] == Tileset.FLOOR) ||
                (x != WIDTH - 1 && y != 0 && world[x + 1][y - 1] == Tileset.FLOOR) ||
                (x != WIDTH - 1 && world[x + 1][y] == Tileset.FLOOR) ||
                (x != WIDTH - 1 && y != HEIGHT - 1 && world[x + 1][y + 1] == Tileset.FLOOR))
            if (world[x][y] == Tileset.NOTHING)
                return true;
        return false;
    }

    private void generate_door(TETile world[][]) {
        for (int i = 1; i < WIDTH - 1; i++)
            for (int j = 1; j < HEIGHT - 1; j++)
                if (world[i][j] == Tileset.WALL &&
                        (world[i][j - 1] == Tileset.WALL && world[i][j + 1] == Tileset.WALL) &&
                        (world[i + 1][j] == Tileset.FLOOR && world[i - 1][j] == Tileset.NOTHING)) {
                    world[i][j] = Tileset.LOCKED_DOOR;
                    return;
                }
    }

    private boolean check_overlap(int maze[], int x_pos, int y_pos, int width, int height) {
        int left_up = y_pos * WIDTH + x_pos;
        int current_pos = left_up - 1;
        for (int col = 0; col < width; col++) {
            if (maze[++current_pos] > 0 && maze[current_pos] != left_up)
                return true;
        }
        int right_up = current_pos;
        current_pos = left_up;
        for (int row = 1; row < height; row++) {
            current_pos += WIDTH;
            if (maze[current_pos] > 0 && maze[current_pos] != left_up)
                return true;
        }
        for (int col = 1; col < width; col++) {
            if (maze[++current_pos] > 0 && maze[current_pos] != left_up)
                return true;
        }
        current_pos = right_up;
        for (int row = 1; row < height - 1; row++) {
            current_pos += WIDTH;
            if (maze[current_pos] > 0 && maze[current_pos] != left_up)
                return true;
        }
        return false;
    }
}
