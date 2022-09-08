// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GameWindowMonitor.java

package pacman2000;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Referenced classes of package pacman2000:
//            Game

public class GameWindowMonitor extends WindowAdapter
{

    public GameWindowMonitor(Game game)
    {
        this.game = game;
    }

    public void windowClosing(WindowEvent e)
    {
        game.finish();
        Window w = e.getWindow();
        w.setVisible(false);
        w.dispose();
        System.exit(0);
    }

    private Game game;
}
