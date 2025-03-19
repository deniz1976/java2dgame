package com.java2d.rain.entity.projectile;


import com.java2d.rain.entity.spawner.ParticleSpawner;
import com.java2d.rain.graphics.Screen;
import com.java2d.rain.graphics.Sprite;

public class WizardProjectile extends Projectile
{

    public static final int FIRE_RATE = 15;

    public WizardProjectile(double x, double y, double dir)
    {
        super(x, y, dir);
        range = 125;
        damage = 20;
        speed = 2;
        angle = dir;
        sprite = Sprite.projectile_wizard;
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }


    @Override
    public void update()
    {
        if(level.tileCollision((int)(x + nx), (int)(y + ny), 7,5,4))
        {
            remove();
            level.add(new ParticleSpawner((int) x, (int) y ,44  , 50, level));
        }
        move();
    }

    @Override
    protected void move()
    {
        x+= nx;
        y+= ny;

        if(distance() > range)
        {
            remove();
        }
    }

    public void render(Screen screen)
    {
        screen.renderProjectile((int)x - 12,(int)y - 2,this);
    }

    private double distance()
    {
        return Math.sqrt(Math.pow(xOrigin - x ,2) + Math.pow(yOrigin - y ,2));

    }
}
