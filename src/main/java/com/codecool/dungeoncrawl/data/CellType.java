package com.codecool.dungeoncrawl.data;

public enum CellType {
    EMPTY("empty", true),
    FLOOR("floor", true),
    GRASS("grass", true),
    WALL("wall", false),
    BEAUTY("beauty", false),
    FOREST("forest", false),
    DEADFOREST("deadforest", false),
    MARSHROCK("marshrock", true),
    CLOSED("closed", false),
    CAVE("cave", false),
    OPENED("opened", true),
    MT_DOOM("mt_doom", true);
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
