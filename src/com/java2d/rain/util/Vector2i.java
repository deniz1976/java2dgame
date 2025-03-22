package com.java2d.rain.util;

public class Vector2i
{

    private int x;
    private int y;

    public Vector2i(int x,int y)
    {
        set(x,y);
    }

//    public void add(Vector2i v)
//    {
//        this.x += v.x;
//        this.y += v.y;
//    }

    public Vector2i add(Vector2i v)
    {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Vector2i)) return false;
        Vector2i v = (Vector2i)obj;
        return v.getX() == this.x && v.getY() == this.y;
    }

    public Vector2i subtract(Vector2i v)
    {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    public Vector2i(Vector2i v)
    {
        set(v.x, v.y);
    }

    public Vector2i()
    {
        set(0,0);
    }

    public void set(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Vector2i setX(final int x)
    {
        this.x = x;
        return this;
    }

    public Vector2i setY(final int y)
    {
        this.y = y;
        return this;
    }
}
