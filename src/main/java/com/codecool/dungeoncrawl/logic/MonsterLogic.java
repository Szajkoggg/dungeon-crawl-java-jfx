package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Monster;
import com.codecool.dungeoncrawl.logic.moves.MovementGenerator;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;
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
        movementGenerator = new MovementGenerator(random, logic);
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
                    List<Monster> deadMonsters = new ArrayList<>();
                    for (Monster monster : monsters) {
                        if (monster.getHealth() > 0) {
                            moveMonster(monster);
                        } else {
                            deadMonsters.add(monster);
                        }
                    }
                    deadMonsters.forEach(monsters::remove);
                    ui.refresh();
                    TimeUnit.MILLISECONDS.sleep(250);
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
