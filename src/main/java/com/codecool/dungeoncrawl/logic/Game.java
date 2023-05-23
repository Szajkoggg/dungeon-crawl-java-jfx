package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.ui.UI;
import com.codecool.dungeoncrawl.ui.keyeventhandler.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.stage.Stage;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Game extends Application {
    private UI ui;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.keyHandlers = Set.of(new Up(), new Down(), new Left(), new Right());
        this.logic = new GameLogic();
        this.ui = new UI(logic, keyHandlers);
        ui.setUpPain(primaryStage);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();


        GameMap map = logic.getMap();
        Task task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                boolean isRunning = true;
                while (isRunning) {
                    Set<Actor> monsters = map.getMonsters();
                    for (Actor monster : monsters) {
                        monster.move(0, 1);
                    }
                    ui.refresh();
                    TimeUnit.SECONDS.sleep(1);
                }
                return null;
            }
        };

        new Thread(task).start();

    }


}
