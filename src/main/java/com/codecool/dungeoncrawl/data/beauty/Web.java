package com.codecool.dungeoncrawl.data.beauty;

import com.codecool.dungeoncrawl.data.Cell;

public class Web extends Beauty{
    public Web(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "web";
    }
}
