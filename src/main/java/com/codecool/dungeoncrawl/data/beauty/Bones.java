package com.codecool.dungeoncrawl.data.beauty;

import com.codecool.dungeoncrawl.data.Cell;

public class Bones extends Beauty{
    public Bones(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "bones";
    }
}
