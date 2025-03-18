package com.java2d.rain.entity.spawner;

import com.java2d.rain.entity.Entity;
import com.java2d.rain.level.Level;

public abstract class Spawner extends Entity
{
    public enum Type
    {
        MOB,
        PARTICLE
    }

    private final Type type;

    public Spawner(int x , int y, Type type, int amount, Level level)
    {
        this.x = x;
        this.y = y;
        this.type = type;

    }

}
