package com.codecool.dungeoncrawl.data.objects;

import com.codecool.dungeoncrawl.data.Cell;

public class Sword extends Weapon{
    public Sword(Cell cell) {
        super(cell, 2);
    }
    public String getTileName() {
        return "sword";
    }
}
