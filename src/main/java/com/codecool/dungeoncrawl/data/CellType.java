package com.codecool.dungeoncrawl.data;

public enum CellType {
    EMPTY("empty", true),
    FLOOR("floor", true),
    WALL("wall", false),
    CLOSED("closed", false),
    CAVE("cave", false),
    OPENED("opened", true);
    private final String tileName;
    private final boolean passable;

    CellType(String tileName, boolean passable) {
        this.tileName = tileName;
        this.passable = passable;
    }

    public String getTileName() {
        return tileName;
    }

    public boolean isPassable() {
        return passable;
    }
}
