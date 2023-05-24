package com.codecool.dungeoncrawl.data.objects;

import com.codecool.dungeoncrawl.data.Cell;

public class Phial extends Item{
    public Phial(Cell cell) {
        super(cell);
    }
    public String getTileName() {
        return "phial";
    }
}
