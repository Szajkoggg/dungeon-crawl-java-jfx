package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.objects.Item;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private final List<Item> inventory;
    public Player(Cell cell) {
        super(cell);
        this.inventory = new ArrayList<>();
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        pickUpItem();
    }

    private void pickUpItem() {
        if (cell.getItem() != null) {
            addItem(cell.getItem());
            cell.setItem(null);
        }
    }
    private void addItem(Item item) {
        inventory.add(item);
    }

    public List<Item> getInventory () {
        return inventory;
    }
}
