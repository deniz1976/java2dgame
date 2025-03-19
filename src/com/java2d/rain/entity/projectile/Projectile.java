package com.java2d.rain.entity.projectile;

import com.java2d.rain.entity.Entity;
import com.java2d.rain.graphics.Sprite;

import java.util.Random;

public abstract class Projectile extends Entity
{

    protected final double xOrigin;
    protected final double yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double nx;
    protected double ny;
    protected double speed;
    protected double range;
    protected double damage;
    protected double x;
    protected double y;
    protected double distance;

    protected final Random random = new Random();

    public Projectile(double x,double y,double dir)
    {
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;
    }

    public Sprite getSprite()
    {
        return sprite;
    }

    public int getSpriteSize()
    {
        return sprite.SIZE;
    }


    protected void move()
    {

    }
}
