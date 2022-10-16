package byog.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    static public void addHexagon(TETile[][] world, int n, int xpos, int ypos, TETile t) {
        if (n < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        int total = n + (n - 1) * 2;
        for (int row = 0; row < n; row++) {
            int xOffset = total / 2 - n / 2 - row;
            for (int col = 0; col < n + 2 * row; col++) {
                world[xpos + xOffset + col][ypos + row] = t;
                world[xpos + xOffset + col][ypos + n * 2 - row - 1] = t;
            }
        }
    }

    static public void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(60, 30);
        TETile[][] world = new TETile[60][30];
        for (int x = 0; x < 60; x += 1) {
            for (int y = 0; y < 30; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        addHexagon(world, 5, 4, 8, Tileset.GRASS);
        ter.renderFrame(world);
    }
}
