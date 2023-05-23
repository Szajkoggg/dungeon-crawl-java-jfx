package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.objects.Item;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory;
    public Player(Cell cell) {
        super(cell);
        this.inventory = new ArrayList<>();
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = this.getCell().getNeighbor(dx, dy);
        if (nextCell.isPassable()) {
            this.getCell().setActor(null);
            nextCell.setActor(this);
            this.setCell(nextCell);
            if (this.getCell().getItem() != null) {
                this.addItem(this.getCell().getItem());
                this.getCell().setItem(null);
            }
        }
    }
    public void addItem(Item item) {
        inventory.add(item);
    }
}
