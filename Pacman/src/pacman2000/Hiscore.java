// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Hiscore.java

package pacman2000;


// Referenced classes of package pacman2000:
//            SoundManager, Game

public class Hiscore
{

    public Hiscore()
    {
        x = 0;
        y = 0;
        destx = 0;
        desty = 0;
        finished = false;
        name = "";
    }

    public void keyAct(int key)
    {
        if(x == destx && y == desty)
            if(key == 111 || key == 79 || key == 37)
            {
                if(x > 0)
                    destx = x - 32;
            } else
            if(key == 112 || key == 80 || key == 39)
            {
                if(x < 192)
                    destx = x + 32;
            } else
            if(key == 113 || key == 81 || key == 38)
            {
                if(y > 0)
                    desty = y - 32;
            } else
            if(key == 97 || key == 65 || key == 40)
            {
                if(y < 96)
                    desty = y + 32;
            } else
            if(key == 32 || key == 32)
            {
                int charNo = name.length();
                int cx = x / 32;
                int cy = y / 32;
                if(cx == 0 && cy == 3)
                {
                    if(charNo > 0)
                        name = name.substring(0, charNo - 1);
                    SoundManager.playSound(SoundManager.FEAR);
                } else
                if(cx == 6 && cy == 3)
                {
                    if(charNo == 0)
                        name = new String("No name");
                    finished = true;
                    SoundManager.playSound(SoundManager.LAUGH);
                } else
                if(charNo < 8)
                {
                    int text = 0;
                    if(cy < 3)
                        text = 65 + cx + cy * 7;
                    else
                        text = 85 + cx;
                    SoundManager.playSound(SoundManager.DOT);
                    Character t = new Character((char)text);
                    name = (new StringBuilder()).append(name).append(t.toString()).toString();
                }
                Game.wait(150);
            }
        if(destx < x)
            x -= 8;
        if(desty < y)
            y -= 8;
        if(destx > x)
            x += 8;
        if(desty > y)
            y += 8;
    }

    static final int MAX_LENGTH = 8;
    public int x;
    public int y;
    public int destx;
    public int desty;
    public boolean finished;
    public String name;
}
