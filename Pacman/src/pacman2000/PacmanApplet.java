// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PacmanApplet.java

package pacman2000;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.PrintStream;

// Referenced classes of package pacman2000:
//            Game, KeyEventMonitor, IPacmanInstance

public class PacmanApplet extends Applet
    implements IPacmanInstance, Runnable
{

    public PacmanApplet()
    {
    }

    public Component getComponent()
    {
        return this;
    }

    public void init()
    {
        super.init();
    }

    public void start()
    {
        super.start();
        System.out.println("creating new game");
        game = new Game(this);
        game.setReadWriteEnabled(false);
        Thread t = new Thread(this);
        t.start();
    }

    public void run()
    {
        game.execute();
    }

    public void stop()
    {
        System.out.println("stopping game");
        if(game != null)
            game.finish();
        super.stop();
    }

    public void destroy()
    {
        System.out.println("destroying game");
        super.destroy();
    }

    public void processKeyEvent(KeyEvent e)
    {
        game.processKeyEvent(e);
    }

    public void setBufferSize(int x, int y)
    {
        validate();
        addKeyListener(new KeyEventMonitor());
        clear = true;
        bufferX = x;
        bufferY = y;
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        if(game != null)
            game.paint(g);
    }

    public void resize(int width, int height)
    {
        super.resize(width, height);
        clear = true;
    }

    public void drawBufferToScreen(Graphics g, Image buffer)
    {
        if(clear)
        {
            setBackground(Color.BLACK);
            g.clearRect(0, 0, getWidth(), getHeight());
            clear = false;
        }
        g.drawImage(buffer, (getWidth() - bufferX) / 2, (getHeight() - bufferY) / 2, this);
    }

    private Game game;
    private boolean clear;
    private int bufferX;
    private int bufferY;
}
