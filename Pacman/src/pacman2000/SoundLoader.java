// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SoundLoader.java
// Download by http://www.codefans.net
package pacman2000;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.PrintStream;

public class SoundLoader
{

    public SoundLoader()
    {
    }

    public static AudioClip loadSound(String filename)
    {
        java.net.URL url = SoundLoader.class.getResource((new StringBuilder()).append("data/sound/").append(filename).toString());
        if(url != null)
        {
            System.out.println((new StringBuilder()).append("Loading sound: ").append(filename).toString());
            return Applet.newAudioClip(url);
        } else
        {
            System.out.println((new StringBuilder()).append("Could not load sound: ").append(filename).toString());
            return null;
        }
    }

    public static final String soundPath = "data/sound/";
}
