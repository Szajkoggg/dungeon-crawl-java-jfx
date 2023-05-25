package com.codecool.dungeoncrawl.data.objects;

import com.codecool.dungeoncrawl.data.Cell;

public class Ring extends Item{
    public Ring(Cell cell) {
        super(cell);
    }
    public String getTileName() {
        return "ring";
    }
}
