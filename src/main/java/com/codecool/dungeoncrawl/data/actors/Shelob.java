package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.moves.MovementGenerator;

public class Shelob extends Monster{
    int moveOnEveryNthTurn;
    public Shelob(Cell cell) {
        super(cell);
        moveOnEveryNthTurn = 2;
    }

    @Override
    public String getTileName() {
        return "shelob";
    }
    @Override
    public int[] getMovementCoordinates(MovementGenerator movementGenerator, int turnCounter) {
        int[] coordinates = {0,0};
        if (turnCounter % moveOnEveryNthTurn == 0) {
            coordinates = movementGenerator.moveOneTileInRandomDirection();
        }
        return coordinates;
    }
}
