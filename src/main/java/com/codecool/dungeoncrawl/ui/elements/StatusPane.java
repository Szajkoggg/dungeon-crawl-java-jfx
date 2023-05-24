package com.codecool.dungeoncrawl.ui.elements;

import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.objects.Item;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.stream.Collectors;

public class StatusPane {
    public static final int RIGHT_PANEL_WIDTH = 200;
    public static final int RIGHT_PANEL_PADDING = 10;
    private GridPane ui;
    private Label healthTextLabel;
    private Label healthValueLabel;
    private Label inventoryTextLabel;
    private Label inventoryListLabel;

    public StatusPane() {
        ui = new GridPane();
        ui.setStyle("-fx-background-color: #C0C0C0;");
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
        inventoryTextLabel = new Label("Inventory: ");
        inventoryListLabel = new Label();
    }

    public BorderPane build(Canvas canvas) {

        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));
        ui.setMinWidth(RIGHT_PANEL_WIDTH);

        ui.add(healthTextLabel, 0, 0);
        ui.add(healthValueLabel, 1, 0);
        ui.add(inventoryTextLabel, 0, 1);
        ui.add(inventoryListLabel, 1, 1);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(ui);
        return borderPane;
    }

    public void setHealthValue(String text) {
        healthValueLabel.setText(text);
    }

    public void setInventoryList (List<Item> inventory) {
        String inventoryText = "empty";
        if (!inventory.isEmpty()) {
            inventoryText = inventory.stream().map(Drawable::getTileName).collect(Collectors.joining(", "));
        }
        inventoryListLabel.setText(inventoryText);
    }
}
