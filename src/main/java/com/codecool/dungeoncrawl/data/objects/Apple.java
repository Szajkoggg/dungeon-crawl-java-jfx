package com.codecool.dungeoncrawl.data.objects;

import com.codecool.dungeoncrawl.data.Cell;

public class Apple extends Potion{

    private int HEALING=10;
    public Apple(Cell cell) {
        super(cell);
    }
    public String getTileName() {
        return "apple";
    }
    public int getHEALING(){
        return HEALING;
    }
}
