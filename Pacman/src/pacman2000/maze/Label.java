// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Label.java
// Download by http://www.codefans.net
package pacman2000.maze;


public class Label
{

    public Label()
    {
        label = -1;
    }

    public void setLabel(int newLabel)
    {
        label = newLabel;
    }

    public int getLabel()
    {
        return label;
    }

    private int label;
}
