// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Edge.java
// Download by http://www.codefans.net
package pacman2000.maze;


public class Edge
{

    public Edge()
    {
    }

    public Edge(int newA, int newB, int newLength)
    {
        a = newA;
        b = newB;
        length = newLength;
    }

    public void setA(int newA)
    {
        a = newA;
    }

    public void setB(int newB)
    {
        b = newB;
    }

    public void setLength(int newLength)
    {
        length = newLength;
    }

    public int getA()
    {
        return a;
    }

    public int getB()
    {
        return b;
    }

    public int getLength()
    {
        return length;
    }

    private int a;
    private int b;
    private int length;
}
