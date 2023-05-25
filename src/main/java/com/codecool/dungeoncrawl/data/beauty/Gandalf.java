package com.codecool.dungeoncrawl.data.beauty;

import com.codecool.dungeoncrawl.data.Cell;

public class Gandalf extends Beauty{
    public Gandalf(Cell cell) {
        super(cell);
    }
    @Override
    public String getTileName() {
        return "wizard";
    }
}
