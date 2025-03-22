package com.java2d.rain.level;

import com.java2d.rain.entity.mob.Chaser;
import com.java2d.rain.entity.mob.Dummy;
import com.java2d.rain.entity.mob.Star;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class SpawnLevel extends Level
{

    public SpawnLevel(int width, int height)
    {
        super(width, height);
    }

    public SpawnLevel(String path)
    {
        super(path);
    }

    @Override
    protected void loadLevel(String path)
    {
        try
        {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(SpawnLevel.class.getResource(path)));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];
            image.getRGB(0, 0, w, h, tiles, 0, w);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error loading level");
        }

//        add(new Dummy(22,55));
//        add(new Chaser(20,55));
        add(new Star(17,35));
    }

    @Override
    protected void generateLevel()
    {


    }
}
