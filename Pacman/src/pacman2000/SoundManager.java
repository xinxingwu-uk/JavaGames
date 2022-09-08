// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SoundManager.java
// Download by http://www.codefans.net
package pacman2000;

import java.applet.AudioClip;
import java.io.PrintStream;

// Referenced classes of package pacman2000:
//            SoundLoader

public class SoundManager
{

    public SoundManager()
    {
    }

    public static void loadAllSounds()
    {
        if(loaded)
        {
            return;
        } else
        {
            sound = new AudioClip[NO_OF_SOUNDS];
            System.out.println("Loading sounds");
            sound[1] = SoundLoader.loadSound("dot.au");
            sound[2] = SoundLoader.loadSound("energiser.au");
            sound[3] = SoundLoader.loadSound("start.au");
            sound[4] = SoundLoader.loadSound("death.au");
            sound[5] = SoundLoader.loadSound("eatghost.au");
            sound[6] = SoundLoader.loadSound("dot2.au");
            sound[7] = SoundLoader.loadSound("fruit.au");
            sound[8] = SoundLoader.loadSound("spawn.au");
            sound[9] = SoundLoader.loadSound("win.au");
            sound[10] = SoundLoader.loadSound("extralife.au");
            sound[11] = SoundLoader.loadSound("gameover.au");
            sound[12] = SoundLoader.loadSound("scared.au");
            sound[13] = SoundLoader.loadSound("quad.au");
            sound[14] = SoundLoader.loadSound("windfly.au");
            sound[15] = SoundLoader.loadSound("laugh.au");
            loaded = true;
            return;
        }
    }

    public static void playSound(int a)
    {
        if(!loaded)
            return;
        if(a == 0)
            return;
        if(sound[a] == null)
        {
            return;
        } else
        {
            sound[a].play();
            return;
        }
    }

    static int NO_OF_SOUNDS = 16;
    static int DOT = 1;
    static int ENERGISER = 2;
    static int START = 3;
    static int DEATH = 4;
    static int EAT_GHOST = 5;
    static int DOT2 = 6;
    static int FRUIT = 7;
    static int SPAWN = 8;
    static int WIN = 9;
    static int EXTRA_LIFE = 10;
    static int GAME_OVER = 11;
    static int FEAR = 12;
    static int QUAD = 13;
    static int WINDFLY = 14;
    static int LAUGH = 15;
    static AudioClip sound[];
    static boolean loaded = false;

}
