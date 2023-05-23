package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.moves.MovementGenerator;

public abstract class Monster extends Actor{

    public Monster(Cell cell) {
        super(cell);
    }

    public int[] getMovementCoordinates(MovementGenerator movementGenerator, int turnCounter) {
        int[] coordinates = {0,0};
        return coordinates;
    }
}
