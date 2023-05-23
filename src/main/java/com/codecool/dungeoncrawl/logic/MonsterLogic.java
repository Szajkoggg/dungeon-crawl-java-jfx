package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.ui.UI;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MonsterLogic {
    UI ui;
    GameLogic logic;
    GameMap map;

    public MonsterLogic(UI ui, GameLogic logic){
        this.ui=ui;
        this.logic=logic;
        map = logic.getMap();
    }

    private void run() throws InterruptedException {
        boolean isRunning = true;

        while (isRunning) {
            Set<Actor> monsters = map.getMonsters();
            for (Actor monster : monsters) {
                monster.move(0,1);
            }
            ui.refresh();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
