package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.moves.MovementGenerator;

public class Golum extends Monster {
    int moveOnEveryNthTurn;

    public Golum(Cell cell) {
        super(cell);
        moveOnEveryNthTurn = 1;
    }

    @Override
    public String getTileName() {
        return "golum";
    }

    @Override
    public int[] getMovementCoordinates(MovementGenerator movementGenerator, int turnCounter) {
        int[] coordinates = {0, 0};
        if (turnCounter % moveOnEveryNthTurn == 0) {
            coordinates = movementGenerator.moveOneTileInRandomDirection();
        }
        return coordinates;
    }
}
