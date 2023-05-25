package com.codecool.dungeoncrawl.data.beauty;

import com.codecool.dungeoncrawl.data.Cell;

public class Rivendell extends Beauty{
    public Rivendell(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "rivendell";
    }
}
