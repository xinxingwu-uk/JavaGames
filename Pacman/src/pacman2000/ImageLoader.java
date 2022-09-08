// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageLoader.java

package pacman2000;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.PrintStream;

public class ImageLoader
{

    public ImageLoader()
    {
    }

    public static Image loadImage(String filename)
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        java.net.URL url = ImageLoader.class.getResource((new StringBuilder()).append("data/images/").append(filename).toString());
        if(url != null)
        {
            System.out.println((new StringBuilder()).append("Loading image: ").append(filename).toString());
            Image result = toolkit.getImage(url);
            return result;
        } else
        {
            System.out.println((new StringBuilder()).append("Could not find image: ").append(filename).toString());
            return null;
        }
    }

    public static final String imagePath = "data/images/";
}
