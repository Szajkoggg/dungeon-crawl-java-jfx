package com.codecool.dungeoncrawl.data.objects;

import com.codecool.dungeoncrawl.data.Cell;

public class Gholum extends Item{
    public Gholum(Cell cell) {
        super(cell);
    }
    public String getTileName() {
        return "gholum";
    }
}
