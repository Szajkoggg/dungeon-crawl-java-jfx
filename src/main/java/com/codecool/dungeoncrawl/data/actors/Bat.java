package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.moves.MovementGenerator;


public class Bat extends Monster{
    public Bat(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "bat";
    }
    @Override
    public int[] getMovementCoordinates(MovementGenerator movementGenerator, int turnCounter) {
        return movementGenerator.moveOneTileInRandomDirection();
    }

}
