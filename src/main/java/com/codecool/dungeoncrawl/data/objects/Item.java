package com.codecool.dungeoncrawl.data.objects;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Item implements Drawable {
    private final Cell cell;

    public Item(Cell cell) {
        this.cell=cell;
        this.cell.setItem(this);
    }
    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
