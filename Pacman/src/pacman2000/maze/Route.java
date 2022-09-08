// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Route.java

package pacman2000.maze;


public class Route
{

    public Route(int d, int p)
    {
        distance = d;
        nextNode = p;
    }

    public int getDistance()
    {
        return distance;
    }

    public int getNextNode()
    {
        return nextNode;
    }

    private int distance;
    private int nextNode;
}
