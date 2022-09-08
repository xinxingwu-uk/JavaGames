// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Position.java

package pacman2000;


public class Position
{

    public Position(int x, int y)
    {
        xcoord = x;
        ycoord = y;
    }

    public int getX()
    {
        return xcoord;
    }

    public int getY()
    {
        return ycoord;
    }

    public void setX(int x)
    {
        xcoord = x;
    }

    public void setY(int y)
    {
        ycoord = y;
    }

    public int xcoord;
    public int ycoord;
}
