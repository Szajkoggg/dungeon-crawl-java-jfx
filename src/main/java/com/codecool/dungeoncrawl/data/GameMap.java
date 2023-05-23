package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Player;

import java.util.HashSet;
import java.util.Set;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

    private Set<Actor> monsters;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        this.monsters = new HashSet<>();
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void addMonster(Actor monster) {
        monsters.add(monster);
    }

    public Set<Actor> getMonsters(){
        return monsters;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
