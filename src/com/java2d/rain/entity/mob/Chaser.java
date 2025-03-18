package com.java2d.rain.entity.mob;

import com.java2d.rain.graphics.AnimatedSprite;
import com.java2d.rain.graphics.Screen;
import com.java2d.rain.graphics.Sprite;
import com.java2d.rain.graphics.SpriteSheet;

public class Chaser extends Mob
{
    private final AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down,32,32,3);
    private final AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up,32,32,3);
    private final AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left,32,32,3);
    private final AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right,32,32,3);

    private AnimatedSprite animSprite = down;

    private int time = 0;
    private int xa = 0;
    private int ya = 0;

    public Chaser(int x,int y)
    {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.dummy;
    }

    @Override
    public void render(Screen screen)
    {
        sprite = animSprite.getSprite();
        screen.renderMob(x,y,this);
    }

    @Override
    public void update()
    {
        if(walking)
        {
            animSprite.update();
        }
        else
        {
            animSprite.setFrame(0);
        }
        if(ya < 0)
        {
            animSprite = up;
            dir = DIRECTION.UP;
        }
        else if(ya > 0 )
        {
            animSprite = down;
            dir = DIRECTION.DOWN;
        }
        if(xa < 0)
        {
            animSprite = left;
            dir = DIRECTION.LEFT;
        }
        else if(xa > 0)
        {
            animSprite = right;
            dir = DIRECTION.RIGHT;
        }

        if(xa != 0 ||ya != 0)
        {
            move(xa, ya);
            walking = true;
        }
        else
        {
            walking = false;
        }
    }
}
