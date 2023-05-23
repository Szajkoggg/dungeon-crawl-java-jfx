package com.codecool.dungeoncrawl.logic.moves;

import java.util.Random;

public class MovementGenerator {

    Random random;

    public MovementGenerator(Random random) {
        this.random=random;
    }

    public int[] moveOneTileInRandomDirection() {
        int x = 0;
        int y = 0;
        if (isMovingOnXAxis()) {
            x = isPositiveCoordinateValueChange()?++x:--x;
        } else {
            y = isPositiveCoordinateValueChange()?++y:--y;
        }
        return new int[]{x,y};
    }

    public boolean isMovingOnXAxis() {
        return getRandomBoolean();
    }

    public boolean isPositiveCoordinateValueChange() {
        return getRandomBoolean();
    }

    private boolean getRandomBoolean() {
        return random.nextBoolean();
    }
}
