package com.java2d.rain.entity;

import com.java2d.rain.graphics.Screen;
import com.java2d.rain.level.Level;

import java.util.Random;

public abstract class Entity
{
    public int x;
    public int y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update()
    {

    }

    public void render(Screen screen)
    {

    }

    public void remove()
    {
        removed = true;
    }

    public boolean isRemoved()
    {
        return removed;
    }

    public void init(Level level)
    {
        this.level = level;
    }


}
