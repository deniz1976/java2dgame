package com.java2d.rain.level.tile;

import com.java2d.rain.graphics.Screen;
import com.java2d.rain.graphics.Sprite;

public class RockTile extends Tile{
    public RockTile(Sprite sprite)
    {
        super(sprite);
    }

    @Override
    public void render(int x , int y , Screen screen)
    {
        screen.renderTile(x << 4  , y << 4, this);

    }

    public boolean solid()
    {
        return true;
    }
}
