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
    
    private static final int PATH_UPDATE_RATE = 20;
    private Node currentTarget = null;
    private int pathIndex = 0;

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
        
        double distanceToPlayer = Math.sqrt(Math.pow(x - px, 2) + Math.pow(y - py, 2));
        
        if(distanceToPlayer < 20) {
            walking = false;
            return;
        }
        
        if(time % PATH_UPDATE_RATE == 0) {
            Vector2i start = new Vector2i((int)getX() >> 4, (int)getY() >> 4);
            Vector2i destination = new Vector2i(px >> 4, py >> 4);
            path = level.findPath(start, destination);
            pathIndex = path != null && !path.isEmpty() ? path.size() - 1 : -1;
            currentTarget = pathIndex >= 0 ? path.get(pathIndex) : null;
        }
        
        if(path != null && !path.isEmpty() && currentTarget != null) {
            if(isCloseToTarget()) {
                pathIndex--;
                currentTarget = pathIndex >= 0 ? path.get(pathIndex) : null;
            }
            
            if(currentTarget != null) {
                Vector2i vec = currentTarget.tile;
                int targetX = vec.getX() << 4;
                int targetY = vec.getY() << 4;
                
                if(Math.abs(x - targetX) < 2 && Math.abs(y - targetY) < 2) {
                    xa = 0;
                    ya = 0;
                } else {
                    if(x < targetX) xa += speed;
                    if(x > targetX) xa -= speed;
                    if(y < targetY) ya += speed;
                    if(y > targetY) ya -= speed;
                }
            }
        } else {
            if(Math.abs(x - px) < 200 && Math.abs(y - py) < 200) {
                if(x < px) xa += speed * 0.5;
                if(x > px) xa -= speed * 0.5;
                if(y < py) ya += speed * 0.5;
                if(y > py) ya -= speed * 0.5;
            }
        }

        if(xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
    }
    
    private boolean isCloseToTarget() {
        if(currentTarget == null) return false;
        
        int targetX = currentTarget.tile.getX() << 4;
        int targetY = currentTarget.tile.getY() << 4;
        
        double dx = Math.abs(x - targetX);
        double dy = Math.abs(y - targetY);
        
        return dx < 8 && dy < 8;
    }

    @Override
    public void update()
    {
        time++;
        move();

        if(walking) {
            animSprite.update();
        } else {
            animSprite.setFrame(0);
        }
        
        if(ya < 0) {
            animSprite = up;
            dir = DIRECTION.UP;
        } else if(ya > 0) {
            animSprite = down;
            dir = DIRECTION.DOWN;
        }
        
        if(xa < 0) {
            animSprite = left;
            dir = DIRECTION.LEFT;
        } else if(xa > 0) {
            animSprite = right;
            dir = DIRECTION.RIGHT;
        }
    }
}

