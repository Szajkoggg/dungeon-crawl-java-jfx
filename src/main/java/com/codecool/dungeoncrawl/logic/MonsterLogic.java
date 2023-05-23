package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Monster;
import com.codecool.dungeoncrawl.logic.moves.MovementGenerator;
import javafx.concurrent.Task;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import com.codecool.dungeoncrawl.ui.UI;

public class MonsterLogic {

    private final UI ui;

    private final GameLogic logic;

    private final MovementGenerator movementGenerator;

    private final Random random;

    private int turnCounter;

    public MonsterLogic(UI ui, GameLogic logic) {
        this.ui=ui;
        this.logic=logic;
        random = new Random();
        movementGenerator = new MovementGenerator(random);
        turnCounter = 0;
    }

    public void runMonsterLogic() {
        GameMap map = logic.getMap();
        Task turnCounter = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                boolean isRunning = true;
                while (isRunning) {
                    Set<Monster> monsters = map.getMonsters();
                    for (Monster monster : monsters) {
                        moveMonster(monster);
                    }
                    ui.refresh();
                    TimeUnit.SECONDS.sleep(1);
                    addTurn();
                }
                return null;
            }
        };
        new Thread(turnCounter).start();
    }

    public void moveMonster(Monster monster) {
        int[] coordinates = monster.getMovementCoordinates(movementGenerator, turnCounter);
        monster.move(coordinates[0], coordinates[1]);
    }

    public void addTurn(){
        turnCounter++;
    }
}
