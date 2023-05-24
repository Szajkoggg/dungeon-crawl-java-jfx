package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.ui.elements.MainStage;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Set;

public class UI {
    private final Canvas canvas;
    private final GraphicsContext context;

    private final MainStage mainStage;
    private final GameLogic logic;
    private final Set<KeyHandler> keyHandlers;

    int VISION_RADIUS = 30;
    int CANVAS_X_OFFSET = 13;
    int CANVAS_Y_OFFSET = 18;
    int CANVAS_HEIGHT = 30;
    int CANVAS_WIDTH = 30;

    int MAP_X_OFFSET = 10;

    int MAP_Y_OFFSET = 0;
    int playerX;
    int playerY;

    int prevPlayerX;

    int prevPlayerY;
    int leftDrawBorder;
    int rightDrawBorder;
    int topDrawBorder;
    int bottomDrawBorder;

    public UI(GameLogic logic, Set<KeyHandler> keyHandlers) {
        this.canvas = new Canvas(CANVAS_WIDTH*Tiles.TILE_WIDTH,CANVAS_HEIGHT*Tiles.TILE_WIDTH);
        this.logic = logic;
        this.context = canvas.getGraphicsContext2D();
        this.mainStage = new MainStage(canvas);
        this.keyHandlers = keyHandlers;
        prevPlayerX = logic.getMap().getPlayer().getX();
        prevPlayerY = logic.getMap().getPlayer().getY();
    }

    public void setUpPain(Stage primaryStage) {
        Scene scene = mainStage.getScene();
        primaryStage.setScene(scene);
        logic.setup();
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        for (KeyHandler keyHandler : keyHandlers) {
                keyHandler.perform(keyEvent, logic.getMap());
        }
        refresh();
    }

    public void refresh() {
        fillContext();
        setPlayerCoordinates();
        MAP_X_OFFSET += prevPlayerX-playerX;
        MAP_Y_OFFSET += prevPlayerY-playerY;
        //moveCanvas();
        setDrawBorders();
        draw();
        mainStage.setHealthLabelText(logic.getPlayerHealth());
        mainStage.setInventoryLabelText(logic.getPlayerInventory());
        setPrevPlayerCoordinates();
    }

    private void draw() {
        for (int x = leftDrawBorder; x < rightDrawBorder; x++) {
            for (int y = topDrawBorder; y < bottomDrawBorder; y++) {
                Cell cell = logic.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x+MAP_X_OFFSET, y+MAP_Y_OFFSET);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x+MAP_X_OFFSET, y+MAP_Y_OFFSET);
                } else {
                    Tiles.drawTile(context, cell, x+MAP_X_OFFSET, y+MAP_Y_OFFSET);
                }
            }
        }
    }

    private void fillContext() {
        context.setFill(Color.BLACK);
        context.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
    }

    private void moveCanvas() {
        canvas.setTranslateX((CANVAS_X_OFFSET*Tiles.TILE_WIDTH)-playerX*Tiles.TILE_WIDTH);
        canvas.setTranslateY((CANVAS_Y_OFFSET*Tiles.TILE_WIDTH)-playerY*Tiles.TILE_WIDTH);
    }

    private void setPlayerCoordinates() {
        playerX = logic.getMap().getPlayer().getX();
        playerY = logic.getMap().getPlayer().getY();
    }

    private void setPrevPlayerCoordinates() {
        prevPlayerX = playerX;
        prevPlayerY = playerY;
    }

    private void setDrawBorders() {
        leftDrawBorder = Math.max(playerX - VISION_RADIUS, 0);
        rightDrawBorder = playerX+VISION_RADIUS <= logic.getMapWidth() ? playerX+VISION_RADIUS : (int) logic.getMapWidth();
        topDrawBorder = Math.max(playerY - VISION_RADIUS, 0);
        bottomDrawBorder = playerY+VISION_RADIUS <= logic.getMapHeight() ? playerY+VISION_RADIUS : (int) logic.getMapHeight();
    }
}
