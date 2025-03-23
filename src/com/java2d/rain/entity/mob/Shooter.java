package com.java2d.rain.entity.mob;

import com.java2d.rain.entity.Entity;
import com.java2d.rain.graphics.AnimatedSprite;
import com.java2d.rain.graphics.Screen;
import com.java2d.rain.graphics.Sprite;
import com.java2d.rain.graphics.SpriteSheet;
import com.java2d.rain.util.Vector2i;

import java.util.List;

public class Shooter extends Mob
{
    private int time = 0;
    private int xa = 0;
    private int ya = 0;
    private final AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down,32,32,3);
    private final AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up,32,32,3);
    private final AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left,32,32,3);
    private final AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right,32,32,3);

    private AnimatedSprite animSprite = down;

    public Shooter(int x, int y)
    {
        this.x= x << 4;
        this.y= y << 4;
        sprite = Sprite.dummy;

    }
    @Override
    public void update()
    {
        time++;
        if(time % (random.nextInt(50) + 30) == 0)
        {
            xa = random.nextInt(3) - 1;
            ya = random.nextInt(3) - 1;
            if(random.nextInt(4) == 0)
            {
                xa = 0;
                ya = 0;
            }
        }
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
        List<Entity> entities = level.getEntities(this,500);

        Player player  = level.getClientPlayer();
        entities.add(player);

        double min = 0 ;
        Entity closest = null;

        for(int i = 0 ; i < entities.size() ; i++)
        {
            Entity e = entities.get(i);
            double distance = Vector2i.getDistance(new Vector2i((int)x,(int)y),new Vector2i((int)e.getX(),(int)e.getY()));
            if(i == 0 || distance < min)
            {
                min = distance;
                closest = e;
            }

        }


            double dx = closest.getX() - x;
            double dy = closest.getY() - y;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);


    }

    @Override
    public void render(Screen screen)
    {
        sprite = animSprite.getSprite();
        screen.renderMob((int)x - 16,(int)y - 16,this);
    }
}
