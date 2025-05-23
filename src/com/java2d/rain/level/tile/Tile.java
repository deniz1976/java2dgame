package com.java2d.rain.level.tile;

import com.java2d.rain.graphics.Screen;
import com.java2d.rain.graphics.Sprite;
import com.java2d.rain.level.tile.spawn_level.*;

public class Tile
{
//    public int x;
//    public int y;

    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile rock = new RockTile(Sprite.rock);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
    public static Tile spawn_hedge = new SpawnHedgeTile(Sprite.spawn_hedge);
    public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
    public static Tile spawn_wall1 = new SpawnWallTile(Sprite.spawn_wall1);
    public static Tile spawn_wall2 = new SpawnWallTile(Sprite.spawn_wall2);
    public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);

    public final static int col_spawn_grass = 0x00ff00;
    public final static int col_spawn_hedge = 0x000000;
    public final static int col_spawn_water = 0x0000ff;
    public final static int col_spawn_wall1 = 0x808080;
    public final static int col_spawn_wall2 = 0x303030;
    public final static int col_spawn_floor = 0x724715;





    public Tile( Sprite sprite)
    {
        this.sprite = sprite;
    }

    public void render(int x , int y , Screen screen)
    {

    }

    public boolean solid()
    {
        return false;
    }
}
