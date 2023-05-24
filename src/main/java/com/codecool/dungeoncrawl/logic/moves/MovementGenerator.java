package com.codecool.dungeoncrawl.logic.moves;

import com.codecool.dungeoncrawl.logic.GameLogic;

import java.util.Random;

public class MovementGenerator {
    Random random;
    GameLogic gameLogic;

    public MovementGenerator(Random random, GameLogic gameLogic) {
        this.random=random;
        this.gameLogic=gameLogic;
    }

    public int[] moveTowardsHero(int currentX, int currentY, int followDistance) {
        int heroX = gameLogic.getMap().getPlayer().getX();
        int heroY = gameLogic.getMap().getPlayer().getY();
        int[] coordinates = new int[2];
        if (isBelowFollowDistance(currentX,currentY,followDistance, heroX, heroY)) {
            coordinates[0] = Integer.compare(heroX, currentX);
            coordinates[1] = Integer.compare(heroY, currentY);
        }
        return coordinates;
    }

    private boolean isBelowFollowDistance(int followerX, int followerY, int followDistance, int followedX, int followedY) {
        return (Math.abs(followedX-followerX)<followDistance+1 && Math.abs(followedY-followerY)<followDistance+1);
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
