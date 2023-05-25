package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.objects.Apple;
import com.codecool.dungeoncrawl.data.objects.Item;
import com.codecool.dungeoncrawl.data.objects.Potion;
import com.codecool.dungeoncrawl.data.objects.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private final List<Item> inventory;
    private int baseAttackPower;

    public Player(Cell cell) {
        super(cell);
        this.inventory = new ArrayList<>();
        this.baseAttackPower = 5;
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        openDoor(nextCell);
        if (nextCell.getActor() instanceof Monster) {
            Monster monster = (Monster) nextCell.getActor();
            if (monster.getTileName().equals("shelob")) {
                attackShelob(monster);
            } else {
                attackMonster(monster);
            }
        }
        super.move(dx, dy);
        pickUpHeal();
        pickUpItem();
    }

    private void pickUpHeal() {
        if (cell.getItem() instanceof Potion) {
            increaseHealth(((Apple) cell.getItem()).getHEALING());
            cell.setItem(null);
        }
    }

    private void pickUpItem() {
        if (cell.getItem() != null) {
            addItem(cell.getItem());
            cell.setItem(null);
        }
    }

    private void openDoor(Cell nextCell) {
        if (nextCell.getType().equals(CellType.CAVE) && inventory
                .stream()
                .anyMatch(e -> e.getTileName().equals("gholum"))) {
            nextCell.setType(CellType.OPENED);
        }
        if (nextCell.getType().equals(CellType.CLOSED) && inventory
                .stream()
                .anyMatch(e -> e.getTileName().equals("key"))) {
            nextCell.setType(CellType.OPENED);
        }
    }

    private void attackMonster(Monster monster) {
        monster.decreaseHealth(getAttackPower());
        if (monster.getHealth() > 0) {
            decreaseHealth(monster.getDamage());
        }
        if (monster.getHealth() <= 0) {
            monster.getCell().setActor(null);
        }
    }

    private void attackShelob(Monster monster) {
        if (inventory.stream().anyMatch(e -> e.getTileName().equals("phial"))) {
            monster.decreaseHealth(monster.health);
            monster.getCell().setActor(null);
        } else {
            decreaseHealth(monster.getDamage());
        }
    }


    private int getAttackPower() {
        return baseAttackPower + inventory
                .stream()
                .filter(e -> e instanceof Weapon)
                .mapToInt(e -> ((Weapon) e).getDamage())
                .sum();
    }

    private void addItem(Item item) {
        inventory.add(item);
    }

    public List<Item> getInventory() {
        return inventory;
    }
}
