package com.java2d.rain.entity.mob;

import com.java2d.rain.entity.Entity;
import com.java2d.rain.entity.projectile.Projectile;
import com.java2d.rain.entity.projectile.WizardProjectile;
import com.java2d.rain.graphics.Screen;
import com.java2d.rain.graphics.Sprite;


public abstract class Mob extends Entity
{
    protected boolean moving;
    protected boolean walking;

    protected enum DIRECTION {
        UP, DOWN, LEFT, RIGHT
    }

    protected DIRECTION dir;



    public void move(int xa, int ya)
    {
        if(xa != 0 && ya != 0)
        {
            move(xa, 0);
            move(0, ya);
            return;
        }

        if(xa > 0) dir = DIRECTION.UP; // east
        else if(xa < 0) dir = DIRECTION.LEFT; // west
        if(ya > 0) dir = DIRECTION.DOWN; // south
        else if(ya < 0) dir = DIRECTION.UP; // north

        if(!collision(xa,ya))
        {
            x += xa;
            y += ya;
        }

    }

    public Sprite getSprite()
    {
        return sprite;
    }


    protected void shoot(int x , int y , double dir)
    {
        Projectile p = new WizardProjectile(x,y,dir);
        level.add(p);
    }



    private boolean collision(int xa, int ya)
    {
        int left = (x + xa - 8) / 16;
        int top = (y + ya + 3) / 16;
        int right = (x + xa + 6) / 16;
        int bottom = (y + ya + 15) / 16;

        left = Math.max(0, left);
        top = Math.max(0, top);
        right = Math.min(level.getWidth() - 1, right);
        bottom = Math.min(level.getHeight() - 1, bottom);

        for (int xt = left; xt <= right; xt++) {
            for (int yt = top; yt <= bottom; yt++) {
                if (level.getTile(xt, yt).solid()) {
                    return true;
                }
            }
        }

        return false;
    }

    public abstract void render(Screen screen);

    public abstract void update();

}
