package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.moves.MovementGenerator;

public class Ghost extends Monster {
    public Ghost(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

}
