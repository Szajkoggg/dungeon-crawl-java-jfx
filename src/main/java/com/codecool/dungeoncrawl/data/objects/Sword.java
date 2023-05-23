package com.codecool.dungeoncrawl.data.objects;

import com.codecool.dungeoncrawl.data.Cell;

public class Sword extends Item{
    public Sword(Cell cell) {
        super(cell);
    }
    public String getTileName() {
        return "sword";
    }
}
