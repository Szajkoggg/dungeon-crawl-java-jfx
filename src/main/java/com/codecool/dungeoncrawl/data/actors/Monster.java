package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.moves.MovementGenerator;

public abstract class Monster extends Actor{
    private int damage;

    public Monster(Cell cell) {
        super(cell);
        damage = 2;
    }

    public int[] getMovementCoordinates(MovementGenerator movementGenerator, int turnCounter) {
        return new int[]{0,0};
    }
    public int getDamage() {
        return damage;
    }
}
