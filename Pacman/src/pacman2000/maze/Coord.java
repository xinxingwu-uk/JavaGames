// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Coord.java
// Download by http://www.codefans.net
package pacman2000.maze;


public class Coord
{

    public Coord(int newA, int newB)
    {
        a = newA;
        b = newB;
    }

    public int getA()
    {
        return a;
    }

    public int getB()
    {
        return b;
    }

    private int a;
    private int b;
}
