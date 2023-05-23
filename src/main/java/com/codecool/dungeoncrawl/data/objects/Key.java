package com.codecool.dungeoncrawl.data.objects;

import com.codecool.dungeoncrawl.data.Cell;

public class Key extends Item{
    public Key(Cell cell) {
        super(cell);
    }
    public String getTileName() {
        return "key";
    }
}
