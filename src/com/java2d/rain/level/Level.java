package com.java2d.rain.level;

import com.java2d.rain.entity.Entity;
import com.java2d.rain.entity.mob.Player;
import com.java2d.rain.entity.particle.Particle;
import com.java2d.rain.entity.projectile.Projectile;
import com.java2d.rain.graphics.Screen;
import com.java2d.rain.level.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Level
{
    protected int width;
    protected int height;
    protected int[] tilesInt;
    protected int[] tiles;
    public static Level spawn = new SpawnLevel("/textures/levels/spawn.png");

    private final List<Entity> entities = new ArrayList<>();
    private final List<Projectile> projectiles = new ArrayList<>();
    private final List<Particle> particles = new ArrayList<>();

    private List<Player> players = new ArrayList<>();


    public Level(int width, int height)
    {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    }

    public Level(String path)
    {
        loadLevel(path);
        generateLevel();

    }

    public List<Player> getPlayer()
    {
        return this.players;
    }

    public Player getPlayerAt(int index)
    {
        return players.get(index);
    }

    public Player getClientPlayer()
    {
        return players.get(0);
    }

    public void add(Entity e)
    {
        e.init(this);
        if(e instanceof Particle)
        {
            particles.add((Particle) e);
        }
        else if(e instanceof Projectile)
        {
            projectiles.add((Projectile) e);
        }

        else if(e instanceof Player)
        {
            players.add((Player) e);
        }
        else
        {
            entities.add(e);
        }

    }

    public boolean tileCollision(int x , int y ,int size,int xOffset, int yOffset)
    {
        boolean solid = false;
        for(int c = 0 ; c < 4 ; c++)
        {
            int xt = (x - c % 2 * size + xOffset) >> 4;
            int yt = (y + c / 2 * size + yOffset) >> 4;
            if(getTile(xt,yt).solid()) solid = true;
        }
        return solid;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    private void time()
    {

    }

    protected void loadLevel(String path)
    {

    }

    public List<Projectile> getProjectiles()
    {
        return projectiles;
    }

    private void remove()
    {
        entities.removeIf(Entity::isRemoved);
        projectiles.removeIf(Projectile::isRemoved);
        particles.removeIf(Particle::isRemoved);
        players.removeIf(Player::isRemoved);

    }

    public void update()
    {
        List<Entity> entitiesToRemove = new ArrayList<>();
        for (Entity entity : entities)
        {
            if(entity.isRemoved()) entitiesToRemove.add(entity);
            else entity.update();
        }
        entities.removeAll(entitiesToRemove);
        
        List<Projectile> projectilesToRemove = new ArrayList<>();
        for (Projectile projectile : projectiles)
        {
            if(projectile.isRemoved()) projectilesToRemove.add(projectile);
            else projectile.update();
        }
        projectiles.removeAll(projectilesToRemove);
        
        List<Particle> particlesToRemove = new ArrayList<>();
        for (Particle particle : particles)
        {
            if(particle.isRemoved()) particlesToRemove.add(particle);
            else particle.update();
        }
        particles.removeAll(particlesToRemove);

        List<Player> playersToRemove = new ArrayList<>();
        for (Player player : players)
        {
            if(player.isRemoved()) playersToRemove.add(player);
            else player.update();
        }
        players.removeAll(playersToRemove);
    }

    public void render(int xScroll, int yScroll, Screen screen)
    {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16 ) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for(int y = y0; y < y1; y++ )
        {
            for(int x = x0; x < x1; x++)
            {
                getTile(x, y).render(x,y,screen);


            }
        }

        for (Entity entity : entities)
        {
            entity.render(screen);
        }

        for (Projectile projectile : projectiles)
        {
            projectile.render(screen);
        }

        for (Particle particle : particles)
        {
            particle.render(screen);
        }

        for (Player player: players)
        {
            player.render(screen);
        }


    }

    protected void generateLevel()
    {

    }

    public List<Entity> getEntities(Entity e , int radius)
    {
        List<Entity> result = new ArrayList<>();
        int ex = (int) e.getX();
        int ey = (int)e.getY();
        entities.forEach(entity -> {
            int x = (int)entity.getX();
            int y = (int)entity.getY();
            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);
            double distance = Math.sqrt((dx * dx) + (dy * dy));
            if (distance <= radius) {
                result.add(entity);
            }
        });
        return result;
    }

    public List<Player> getPlayers(Entity e , int radius)
    {
        List<Player> result = new ArrayList<>();
        int ex =(int) e.getX();
        int ey = (int) e.getY();
        for (Player player : players) {
            int x = (int) player.getX();
            int y = (int) player.getY();
            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);
            double distance = Math.sqrt((dx * dx) + (dy * dy));
            if (distance <= radius) {
                result.add(player);
            }

        }
        return result;
    }

    public Tile getTile(int x, int y)
    {
        if(x < 0 || x >= width || y < 0 || y >= height)
            return Tile.voidTile;
        
        int tileColor = tiles[x + y * width] & 0xffffff;
        
        if(tileColor == Tile.col_spawn_floor)  return Tile.spawn_floor;
        if(tileColor == Tile.col_spawn_grass)  return Tile.spawn_grass;
        if(tileColor == Tile.col_spawn_hedge)  return Tile.spawn_hedge;
        if(tileColor == Tile.col_spawn_wall1)  return Tile.spawn_wall1;
        if(tileColor == Tile.col_spawn_wall2)  return Tile.spawn_wall2;
        if(tileColor == Tile.col_spawn_water)  return Tile.spawn_water;

        
        return Tile.spawn_floor;
    }
}
