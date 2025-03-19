package com.java2d.rain.entity;

import com.java2d.rain.graphics.Screen;
import com.java2d.rain.graphics.Sprite;
import com.java2d.rain.level.Level;

import java.util.Random;

public  class Entity
{
    protected double x;
    protected double y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    public Sprite sprite;

    public  void update(){}


    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public void render(Screen screen)
    {
        if(sprite != null) screen.renderSprite((int)x,(int)y,sprite,true);
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

    public Sprite getSprite()
    {
        return sprite;
    }


}
