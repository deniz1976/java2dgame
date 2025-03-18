package com.java2d.rain.entity.mob;

import com.java2d.rain.entity.Entity;
import com.java2d.rain.entity.projectile.Projectile;
import com.java2d.rain.entity.projectile.WizardProjectile;
import com.java2d.rain.graphics.Sprite;


public abstract class Mob extends Entity
{
    protected Sprite sprite;
    protected int dir = 0;



    public void move(int xa, int ya)
    {
        if(xa != 0 && ya != 0)
        {
            move(xa, 0);
            move(0, ya);
            return;
        }

        if(xa > 0) dir = 1; // east
        else if(xa < 0) dir = 3; // west
        if(ya > 0) dir = 2; // south
        else if(ya < 0) dir = 0; // north

        if(!collision(xa,ya))
        {
            x += xa;
            y += ya;
        }

    }

    public void update()
    {

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

    public void render()
    {

    }

}
