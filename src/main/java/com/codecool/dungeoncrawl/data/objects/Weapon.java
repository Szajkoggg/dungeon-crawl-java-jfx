package com.codecool.dungeoncrawl.data.objects;

import com.codecool.dungeoncrawl.data.Cell;

public abstract class Weapon extends Item{
    private int damage;

    public Weapon(Cell cell, int damage) {
        super(cell);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
