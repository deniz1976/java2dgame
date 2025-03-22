package com.java2d.rain.entity.mob;


import com.java2d.rain.graphics.AnimatedSprite;
import com.java2d.rain.graphics.Screen;
import com.java2d.rain.graphics.Sprite;
import com.java2d.rain.graphics.SpriteSheet;
import com.java2d.rain.level.Node;
import com.java2d.rain.util.Vector2i;

import java.util.List;

public class Star extends Mob
{
    private final AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down,32,32,3);
    private final AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up,32,32,3);
    private final AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left,32,32,3);
    private final AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right,32,32,3);

    private AnimatedSprite animSprite = down;
    private List<Node> path = null;


    private int time = 0;
    private double xa = 0;
    private double ya = 0;
    private double speed = 0.8;

    public Star(int x,int y)
    {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.dummy;
    }

    @Override
    public void render(Screen screen)
    {
        sprite = animSprite.getSprite();
        screen.renderMob((int)(x - 16),(int)(y - 16),this);
    }

    private void move()
    {
        xa = 0;
        ya = 0;
        int px = (int) level.getPlayerAt(0).getX();
        int py = (int) level.getPlayerAt(0).getY();
        Vector2i start = new Vector2i((int)getX() / 16,(int)getY() / 16);
        Vector2i destination = new Vector2i((px / 16),(py / 16));
        if(time % 3 == 0) path = level.findPath(start,destination);
        if(path != null)
        {
            if(!path.isEmpty())
            {
                Vector2i vec = path.get(path.size() - 1).tile;
                if(x < (vec.getX() * 16)) xa++;
                if(x > (vec.getX() * 16)) xa--;
                if(y < (vec.getY() * 16)) xa++;
                if(y > (vec.getY() * 16)) ya--;
            }
        }

        if(xa != 0 || ya != 0)
        {
            move(xa, ya);
            walking = true;
        }
        else
        {
            walking = false;
        }
    }

    @Override
    public void update()
    {
        move();

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


    }
}
