package com.java2d.rain.graphics;

public class Sprite
{
    public final int SIZE;
    private int x;
    private int y;
    public int[] pixels;
    public SpriteSheet sheet;
    private int width;
    private int height;


    public static Sprite grass = new Sprite(16,0,5,SpriteSheet.tiles);
    public static Sprite flower = new Sprite(16,1,0,SpriteSheet.tiles);
    public static Sprite rock = new Sprite(16,2,0,SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(16,0x1B87E0);

    public static Sprite spawn_grass = new Sprite(16,0,0,SpriteSheet.spawn_level);
    public static Sprite spawn_hedge = new Sprite(16,1,0,SpriteSheet.spawn_level);
    public static Sprite spawn_water = new Sprite(16,2,0,SpriteSheet.spawn_level);
    public static Sprite spawn_wall1 = new Sprite(16,0,1,SpriteSheet.spawn_level);
    public static Sprite spawn_wall2 = new Sprite(16,0,2,SpriteSheet.spawn_level);
    public static Sprite spawn_floor = new Sprite(16,1,1,SpriteSheet.spawn_level);


    public static Sprite player_forward = new Sprite(32,0,5,SpriteSheet.tiles);
    public static Sprite player_back = new Sprite(32,2,5,SpriteSheet.tiles);
    public static Sprite player_side = new Sprite(32,1,5,SpriteSheet.tiles);

    public static Sprite player_forward_1 = new Sprite(32,0,6,SpriteSheet.tiles);
    public static Sprite player_forward_2 = new Sprite(32,0,7,SpriteSheet.tiles);

    public static Sprite player_side_1 = new Sprite(32,1,6,SpriteSheet.tiles);
    public static Sprite player_side_2 = new Sprite(32,1,7,SpriteSheet.tiles);

    public static Sprite player_back_1 = new Sprite(32,2,6,SpriteSheet.tiles);
    public static Sprite player_back_2 = new Sprite(32,2,7,SpriteSheet.tiles);

    public static Sprite projectile_wizard = new Sprite(16,0,0,SpriteSheet.projectile_wizard);

    public static Sprite particle_normal = new Sprite(3,0xAAAAAA);
    public static Sprite dummy = new Sprite(32,0,0,SpriteSheet.dummy_down);





    public Sprite(int size,int x,int y,SpriteSheet sheet)
    {
        this.SIZE = size;
        this.width = size;
        this.height = size;
        this.x =  (x * SIZE);
        this.y =  (y * SIZE);
        this.sheet = sheet;
        pixels = new int[SIZE*SIZE];
        load();
    }

    protected Sprite( SpriteSheet sheet, int width, int height)
    {
        SIZE = (width == height) ? width : -1;

        this.width = width;
        this.height = height;
        this.sheet = sheet;
    }

    public Sprite(int[] pixels,int width,int height)
    {
        SIZE = (width == height) ? width : -1;
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public Sprite(int width,int height,int colour)
    {
        SIZE = -1;
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
        SetColour(colour);
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public Sprite(int size , int colour)
    {
        this.SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE*SIZE];
        SetColour(colour);
    }

    private void SetColour(int colour)
    {
        for(int i = 0 ; i < width*height ; i++)
        {
            pixels[i] = colour;
        }
    }

    private void load()
    {
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
            }
        }
    }

}
