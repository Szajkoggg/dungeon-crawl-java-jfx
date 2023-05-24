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

    private final int VISION_RADIUS = 5;
    private final int CANVAS_X_OFFSET = 10;
    private final int CANVAS_Y_OFFSET = 12;
    private final int CANVAS_HEIGHT = 20;
    private final int CANVAS_WIDTH = 20;
    private final int MAP_X_OFFSET_INITIAL = 5;
    private final int MAP_Y_OFFSET_INITIAL = -3;
    private final double CANVAS_SCALE = 2;

    private int mapXOffset;
    private int mapYOffset;
    private int playerX;
    private int playerY;
    private int prevPlayerX;
    private int prevPlayerY;
    private int leftDrawBorder;
    private int rightDrawBorder;
    private int topDrawBorder;
    private int bottomDrawBorder;

    public UI(GameLogic logic, Set<KeyHandler> keyHandlers) {
        this.canvas = new Canvas(CANVAS_WIDTH*Tiles.TILE_WIDTH,CANVAS_HEIGHT*Tiles.TILE_WIDTH);
        this.logic = logic;
        this.context = canvas.getGraphicsContext2D();
        this.mainStage = new MainStage(canvas);
        this.keyHandlers = keyHandlers;
        prevPlayerX = logic.getMap().getPlayer().getX();
        prevPlayerY = logic.getMap().getPlayer().getY();
        mapXOffset = MAP_X_OFFSET_INITIAL;
        mapYOffset = MAP_Y_OFFSET_INITIAL;
        canvas.setScaleX(CANVAS_SCALE);
        canvas.setScaleY(CANVAS_SCALE);
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
        moveMap();
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
                    Tiles.drawTile(context, cell.getActor(), x+ mapXOffset, y+ mapYOffset);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x+ mapXOffset, y+ mapYOffset);
                } else {
                    Tiles.drawTile(context, cell, x+ mapXOffset, y+ mapYOffset);
                }
            }
        }
    }

    private void fillContext() {
        context.setFill(Color.BLACK);
        context.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
    }

    private void moveMap() {
        mapXOffset += prevPlayerX-playerX;
        mapYOffset += prevPlayerY-playerY;
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
        rightDrawBorder = playerX+VISION_RADIUS+1 <= logic.getMapWidth() ? playerX+VISION_RADIUS+1 : (int) logic.getMapWidth();
        topDrawBorder = Math.max(playerY - VISION_RADIUS, 0);
        bottomDrawBorder = playerY+VISION_RADIUS+1 <= logic.getMapHeight() ? playerY+VISION_RADIUS+1 : (int) logic.getMapHeight();
    }
}
