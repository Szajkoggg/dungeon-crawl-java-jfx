package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Monster;
import com.codecool.dungeoncrawl.logic.moves.MovementGenerator;
import javafx.concurrent.Task;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import com.codecool.dungeoncrawl.ui.UI;

public class MonsterLogic {

    private UI ui;

    private GameLogic logic;

    private MovementGenerator movementGenerator;

    private Random random;

    public MonsterLogic(UI ui, GameLogic logic) {
        this.ui=ui;
        this.logic=logic;
        random = new Random();
        movementGenerator = new MovementGenerator(random);
    }

    public void moveMonsters() {
        GameMap map = logic.getMap();
        Task turnCounter = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                boolean isRunning = true;
                int turnCounter = 0;
                while (isRunning) {
                    Set<Monster> monsters = map.getMonsters();
                    for (Monster monster : monsters) {
                        int[] coordinates = monster.getMovementCoordinates(movementGenerator, turnCounter);
                        monster.move(coordinates[0], coordinates[1]);
                    }
                    ui.refresh();
                    TimeUnit.SECONDS.sleep(1);
                    turnCounter++;
                }
                return null;
            }
        };

        new Thread(turnCounter).start();
    }
}
