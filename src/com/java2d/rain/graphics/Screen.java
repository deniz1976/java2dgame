package com.java2d.rain.graphics;

import com.java2d.rain.entity.projectile.Projectile;
import com.java2d.rain.level.tile.Tile;

import java.util.Arrays;
import java.util.Random;

public class Screen
{
    public final int width;
    public final int height;
    public final int MAP_SIZE = 64;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int xOffset;
    public int yOffset;

    public int[] pixels;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

    public Screen(int width, int height)
    {
         Random random = new Random();
         this.width = width;
         this.height = height;
         pixels = new int[width*height];

         for(int i = 0 ; i < MAP_SIZE*MAP_SIZE ; i++ )
         {
             tiles[i] = random.nextInt(0xffffff);
         }

    }

    public void clear()
    {
        Arrays.fill(pixels, 0);
    }

    public void renderTile(int xp, int yp, Tile tile)
    {
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0; y < tile.sprite.SIZE; y++)
        {
            int ya = yp + y;
            for(int x = 0; x < tile.sprite.SIZE; x++)
            {
                int xa = xp + x;
                if(xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) continue;
                if(xa < 0) xa = 0;
                int col = tile.sprite.pixels[x + y * tile.sprite.SIZE];
                if(col != 0xffff00ff)
                    pixels[xa + ya * width] = col;
            }
        }
    }

    public void renderProjectile(int xp, int yp, Projectile p){
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < p.getSpriteSize(); y++){
            int ya = y + yp;
            for (int x = 0; x <p.getSpriteSize(); x++){
                int xa = x + xp;
                if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height ) break;
                if (xa < 0) xa = 0;
                int col = p.getSprite().pixels[x + y * p.getSpriteSize()];
                if(col != 0xffff00ff)
                    pixels[xa + ya * width] = col;
            }
        }
    }

    public void renderPlayer(int xp, int yp, Sprite sprite, int flip)
    {
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0; y < 32; y++)
        {
            int ya = yp + y;
            int ys = y;
            if(flip == 2 || flip == 3) ys = 31 - y;
            for(int x = 0; x < 32; x++)
            {
                int xa = xp + x;
                int xs = x;
                if(flip == 1 || flip == 3) xs = 31 - x;
                if(xa < -32 || xa >= width || ya < 0 || ya >= height) continue;
                if(xa < 0) xa = 0;
                int col = sprite.pixels[xs + ys * 32];
                if(col != 0xffff00ff)
                    pixels[xa + ya * width] = col;
            }
        }
    }

    public void renderSprite(int xp , int yp ,Sprite sprite, boolean fixed)
    {
        if(fixed)
        {
            xp -= xOffset;
            yp -= yOffset;
        }

        for(int y = 0 ; y < sprite.getHeight(); y++)
        {
            int ya = y + yp;
            for(int x = 0 ; x < sprite.getWidth(); x++)
            {
                int xa = xp + x;
                if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];

            }
        }



    }

    public void renderSheet(int xp, int yp, SpriteSheet sheet , boolean fixed)
    {
        if(fixed)
        {
            xp -= xOffset;
            yp -= yOffset;
        }

        for(int y = 0 ; y < sheet.HEIGHT; y++)
        {
            int ya = y + yp;
            for(int x = 0 ; x < sheet.WIDTH; x++)
            {
                int xa = xp + x;
                if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                pixels[xa + ya * width] = sheet.pixels[x + y * sheet.WIDTH];

            }
        }


    }

    public void setOffset(int xOffset, int yOffset)
    {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
