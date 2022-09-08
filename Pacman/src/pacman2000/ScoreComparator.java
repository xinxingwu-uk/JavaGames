// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScoreComparator.java

package pacman2000;

import java.util.Comparator;

// Referenced classes of package pacman2000:
//            Score

public class ScoreComparator
    implements Comparator
{

    public ScoreComparator()
    {
    }

    public int compare(Score s1, Score s2)
    {
        return s1.getValue() - s2.getValue();
    }

    public int compare(Object x0, Object x1)
    {
        return compare((Score)x0, (Score)x1);
    }
}
