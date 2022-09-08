// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IPacmanInstance.java
// Download by http://www.codefans.net
package pacman2000;

import java.awt.*;

public interface IPacmanInstance
{

    public abstract Component getComponent();

    public abstract void setBufferSize(int i, int j);

    public abstract void drawBufferToScreen(Graphics g, Image image);
}
