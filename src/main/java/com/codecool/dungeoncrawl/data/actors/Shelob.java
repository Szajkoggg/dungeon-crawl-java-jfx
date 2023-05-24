package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.moves.MovementGenerator;

public class Shelob extends Monster{
    private final int MOVE_ON_EVERY_NTH_TURN = 2;
    private final int FOLLOW_DISTANCE = 4;
    private final int DAMAGE = 10;
    public Shelob(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "shelob";
    }
    @Override
    public int[] getMovementCoordinates(MovementGenerator movementGenerator, int turnCounter) {
        if (turnCounter % MOVE_ON_EVERY_NTH_TURN == 0) {
            return movementGenerator.moveTowardsHero(cell.getX(),cell.getY(), FOLLOW_DISTANCE);
        } else return new int[]{0,0};
    }
    @Override
    public int getDamage() {
        return DAMAGE;
    }
}
