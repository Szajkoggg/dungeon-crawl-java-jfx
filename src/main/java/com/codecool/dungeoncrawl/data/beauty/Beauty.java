package com.codecool.dungeoncrawl.data.beauty;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Beauty implements Drawable {
    private final Cell cell;

    public Beauty(Cell cell) {
        this.cell=cell;
        this.cell.setBeauty(this);
    }
    public Cell getCell() {
        return cell;
    }

}
