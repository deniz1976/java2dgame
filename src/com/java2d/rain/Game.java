package com.java2d.rain;

import com.java2d.rain.entity.mob.Player;
import com.java2d.rain.graphics.Screen;
import com.java2d.rain.graphics.Sprite;
import com.java2d.rain.graphics.SpriteSheet;
import com.java2d.rain.input.Keyboard;
import com.java2d.rain.input.Mouse;
import com.java2d.rain.level.Level;
import com.java2d.rain.level.RandomLevel;
import com.java2d.rain.level.SpawnLevel;
import com.java2d.rain.level.TileCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.Serial;

public class Game extends Canvas implements Runnable
{
    @Serial
    private static final long serialVersionUID = 1L;

    private static int width = 450;
    private static int height = width / 16*9;
    private static int scale = 3;
    public int hz = 60;

    public static String title = "test";

    private Thread thread;
    private final JFrame frame;
    private final Keyboard key;
    private boolean running = false;
    private final Player player;
    private final Level level;

    private final Screen screen;

    private final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private final int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game()
    {
        Dimension size = new Dimension(width*scale  , height*scale);
        setPreferredSize(size);
        frame = new JFrame();
        screen = new Screen(width, height);
        key = new Keyboard();
        TileCoordinate playerSpawn = new TileCoordinate(19,62);
        level = Level.spawn;
        player = new Player(playerSpawn.x(), playerSpawn.y(), key);
        level.add(player);
        addKeyListener(key);

        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public static int getWindowWidth()
    {
        return width * scale;
    }

    public static int getWindowHeight()
    {
        return height * scale;
    }

    public synchronized void start()
    {
        running = true;
        thread = new Thread(this,"Display");
        thread.start();
    }

    public synchronized void stop()
    {
        running = false;
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void run()
    {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 80;
        int frames = 0;
        long updates = 0;
        double delta = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1)
            {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    public void update()
    {
        key.update();
//        player.update();
        level.update();
    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null)
        {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        int xScroll = player.getX() - screen.width / 2;
        int yScroll = player.getY() - screen.height / 2;
        level.render(xScroll, yScroll, screen);
//        player.render(screen);

        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.setFont(new Font("Verdana", Font.PLAIN, 50));
        g.setColor(Color.WHITE);
        g.dispose();
        bs.show();


    }

    public static void main(String[] args) {
        Game game = new Game();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        DisplayMode displayMode = gd.getDisplayMode();
        int refreshRate = displayMode.getRefreshRate();

        if (refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN)
            refreshRate = 60;

        game.hz = refreshRate;
        game.frame.setResizable(false);
        game.frame.setTitle(Game.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();








    }
}
