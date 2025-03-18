package com.java2d.rain.entity.mob;

import com.java2d.rain.Game;
import com.java2d.rain.entity.projectile.Projectile;
import com.java2d.rain.entity.projectile.WizardProjectile;
import com.java2d.rain.graphics.AnimatedSprite;
import com.java2d.rain.graphics.Screen;
import com.java2d.rain.graphics.Sprite;
import com.java2d.rain.graphics.SpriteSheet;
import com.java2d.rain.input.Keyboard;
import com.java2d.rain.input.Mouse;

public class Player extends Mob
{

    private final Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private boolean walking = false;
    private int fireRate = 0;
    private final AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down,32,32,3);
    private final AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up,32,32,3);
    private final AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left,32,32,3);
    private final AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right,32,32,3);

    private AnimatedSprite animSprite = down;




    public Player(Keyboard input)
    {
        this.input = input;
        this.sprite = Sprite.player_forward;
    }

    public Player(int x, int y,Keyboard input)
    {
        this.x = x;
        this.y = y;
        this.input = input;
        this.sprite = Sprite.player_forward;
        fireRate = WizardProjectile.FIRE_RATE;
    }

    public void update()
    {
        if (walking) animSprite.update();
        else animSprite.setFrame(0);
        if(fireRate > 0) fireRate--;
        int xa = 0 ; int ya = 0 ;
        if(anim < 7500) anim++;
        else anim = 0;
        if(input.up)
        {
            ya--;
            animSprite = up;
        }
        if(input.down)
        {
            ya++;
            animSprite = down;
        }
        if(input.left)
        {
            xa--;
            animSprite = left;
        }
        if(input.right)
        {
            xa++;
            animSprite = right;
        }


        if(xa != 0 ||ya != 0)
        {
            move(xa, ya);
            walking = true;
        }
        else
            walking = false;

        clear();
        updateShooting();


    }

    @SuppressWarnings("SuspiciousListRemoveInLoop")
    private void clear()
    {
        for(int i = 0 ; i < level.getProjectiles().size(); i++)
        {
            Projectile p = level.getProjectiles().get(i);
            if(p.isRemoved()){ level.getProjectiles().remove(i); }
        }
    }

    private void updateShooting()
    {
        if(Mouse.getButton() == 1 && fireRate == 0)
        {
            double dx = Mouse.getX() - (double) Game.getWindowWidth() / 2;
            double dy = Mouse.getY() - (double) Game.getWindowHeight() / 2;
            double dir = Math.atan2(dy, dx);
            shoot(x,y,dir);
            fireRate = WizardProjectile.FIRE_RATE;
        }
    }

    public void render(Screen screen)
    {
        int flip = 0;
//        if(dir == 0)
//        {
//            sprite = Sprite.player_forward;
//            if(walking)
//            {
//                if(anim % 20 > 10 )
//                {
//                    sprite = Sprite.player_forward_1;
//                }
//                else
//                {
//                    sprite = Sprite.player_forward_2;
//                }
//            }
//        }
//
//        else if(dir == 1)
//        {
//            sprite = Sprite.player_side;
//            if(walking)
//            {
//                if(anim % 20 > 10 )
//                {
//                    sprite = Sprite.player_side_1;
//                }
//                else
//                {
//                    sprite = Sprite.player_side_2;
//                }
//            }
//        }
//        else if(dir == 2)
//        {
//            sprite = Sprite.player_back;
//            if(walking)
//            {
//                if(anim % 20 > 10 )
//                {
//                    sprite = Sprite.player_back_1;
//                }
//                else
//                {
//                    sprite = Sprite.player_back_2;
//                }
//            }
//        }
//        else if(dir == 3)
//        {
//            sprite = Sprite.player_side;
//            if(walking)
//            {
//                if(anim % 20 > 10 )
//                {
//                    sprite = Sprite.player_side_1;
//                }
//                else
//                {
//                    sprite = Sprite.player_side_2;
//                }
//            }
//            flip = 1;
//        }
        sprite = animSprite.getSprite();
        screen.renderPlayer(x-16,y-16,sprite,flip);

    }
}
