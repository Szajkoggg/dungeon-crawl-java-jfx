package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.moves.MovementGenerator;

public class Skeleton extends Monster{
    private final int MOVE_ON_EVERY_NTH_TURN = 4;
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
    @Override
    public int[] getMovementCoordinates(MovementGenerator movementGenerator, int turnCounter) {
        int[] coordinates = {0,0};
        if (turnCounter % MOVE_ON_EVERY_NTH_TURN == 0) {
            coordinates = movementGenerator.moveOneTileInRandomDirection();
        }
        return coordinates;
    }
}
