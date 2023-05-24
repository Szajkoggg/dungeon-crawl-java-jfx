package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
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
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType().equals(CellType.CLOSED)) {
            openDoor(nextCell);
        }
        if (nextCell.getActor() instanceof Monster) {
            Monster monster = (Monster) nextCell.getActor();
            attackMonster(monster);
        }
        super.move(dx, dy);
        pickUpItem();
    }

    private void pickUpItem() {
        if (cell.getItem() != null) {
            addItem(cell.getItem());
            cell.setItem(null);
        }
    }

    private void openDoor(Cell nextCell) {
        if (nextCell.getType().equals(CellType.CLOSED) && inventory
                .stream()
                .anyMatch(e -> e.getTileName().equals("key"))) {
            nextCell.setType(CellType.OPENED);
        }
    }

    private void attackMonster(Monster monster) {
        monster.decreaseHealth(getAttackPower());
        if (monster.getHealth() > 0) {
            decreaseHealth(2); //monster.getDamage()
        }
        if (monster.getHealth() <= 0) {
            monster.getCell().setActor(null);
        }
    }

    private int getAttackPower() {
        return inventory
                .stream()
                .anyMatch(e -> e.getTileName().equals("sword")) ? 7 : 5; //get dmg from item
    }

    private void addItem(Item item) {
        inventory.add(item);
    }

    public List<Item> getInventory() {
        return inventory;
    }
}
