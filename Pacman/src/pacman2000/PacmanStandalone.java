// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PacmanStandalone.java
// Download by http://www.codefans.net
package pacman2000;

import java.awt.*;
import java.awt.event.KeyEvent;

// Referenced classes of package pacman2000:
//            Game, GameWindowMonitor, KeyEventMonitor, IPacmanInstance

public class PacmanStandalone extends Frame
    implements IPacmanInstance
{

    public PacmanStandalone()
    {
        super("Pacman 2000");
        game = new Game(this);
        game.setReadWriteEnabled(true);
        game.execute();
    }

    public Component getComponent()
    {
        return this;
    }

    public void setBufferSize(int x, int y)
    {
        setSize(x, y + 20);
        setResizable(false);
        setVisible(true);
        addWindowListener(new GameWindowMonitor(game));
        addKeyListener(new KeyEventMonitor());
    }

    public void processKeyEvent(KeyEvent e)
    {
        game.processKeyEvent(e);
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

    public void drawBufferToScreen(Graphics g, Image buffer)
    {
        g.drawImage(buffer, 0, 20, this);
    }

    public static void main(String args[])
    {
        PacmanStandalone p = new PacmanStandalone();
    }

    private Game game;
}
