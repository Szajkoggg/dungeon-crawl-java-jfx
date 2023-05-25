package com.codecool.dungeoncrawl.data.beauty;

import com.codecool.dungeoncrawl.data.Cell;

public class HobbitHouse extends Beauty{
    public HobbitHouse(Cell cell) {
        super(cell);
    }
    @Override
    public String getTileName() {
        return "hobbithouse";
    }
}
