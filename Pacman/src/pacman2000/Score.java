// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Score.java

package pacman2000;


public class Score
{

    public Score(String name, int value)
    {
        this.name = name;
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public String getName()
    {
        return name;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    private int value;
    private String name;
}
