package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static final Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static final Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;

        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(24, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("sword", new Tile(0, 29));
        tileMap.put("key", new Tile(13, 28));
        tileMap.put("closed", new Tile(5, 10));
        tileMap.put("opened", new Tile(9, 9));
        tileMap.put("ghost", new Tile(26, 6));
        tileMap.put("bat", new Tile(26, 8));
        tileMap.put("phial", new Tile(17, 25));
        tileMap.put("shelob", new Tile(30, 5));
        tileMap.put("golum", new Tile(25, 2));
        tileMap.put("cave", new Tile(4, 13));
        tileMap.put("wizard", new Tile(24, 1));
        tileMap.put("forest", new Tile(3, 1));
        tileMap.put("hobbithouse", new Tile(19, 10));
        tileMap.put("grass", new Tile(5, 0));
        tileMap.put("rivendell", new Tile(5, 19));
        tileMap.put("deadforest", new Tile(6, 2));
        tileMap.put("marshrock", new Tile(1, 0));
        tileMap.put("bones", new Tile(0, 15));
        tileMap.put("web", new Tile(2, 15));
        tileMap.put("apple", new Tile(15, 29));
        tileMap.put("mt_doom", new Tile(18, 6));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
