// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Fruit.java

package pacman2000;


public class Fruit
{

    public Fruit()
    {
        x = 32;
        y = 32;
        type = (int)(Math.random() * (double)NUMBER_OF_FRUITS);
    }

    public Fruit(int x, int y, int type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void fruitInit()
    {
        fruitFrameNumber = (int)((double)FRUIT_RESPAWN_TIME * 0.80000000000000004D);
        String path = "fruit/";
        fruitImageFilename = new String[NUMBER_OF_FRUITS];
        fruitImageFilename[0] = (new StringBuilder()).append(path).append("fruit1.gif").toString();
        fruitImageFilename[1] = (new StringBuilder()).append(path).append("fruit2.gif").toString();
        fruitImageFilename[2] = (new StringBuilder()).append(path).append("fruit3.gif").toString();
        fruitImageFilename[3] = (new StringBuilder()).append(path).append("fruit4.gif").toString();
        fruitImageFilename[4] = (new StringBuilder()).append(path).append("fruit5.gif").toString();
        fruitImageFilename[5] = (new StringBuilder()).append(path).append("fruit6.gif").toString();
        fruitImageFilename[6] = (new StringBuilder()).append(path).append("fruit7.gif").toString();
        fruitImageFilename[7] = (new StringBuilder()).append(path).append("fruit8.gif").toString();
        fruitScore = new int[NUMBER_OF_FRUITS];
        fruitScore[0] = 100;
        fruitScore[1] = 300;
        fruitScore[2] = 500;
        fruitScore[3] = 700;
        fruitScore[4] = 1000;
        fruitScore[5] = 2000;
        fruitScore[6] = 3000;
        fruitScore[7] = 5000;
    }

    public boolean fruitCheck()
    {
        fruitFrameNumber++;
        if(fruitFrameNumber >= FRUIT_RESPAWN_TIME && type == -1)
        {
            fruitFrameNumber = 0;
            return true;
        }
        if(fruitFrameNumber >= FRUIT_ALIVE_TIME && type != -1)
        {
            fruitFrameNumber = 0;
            type = -1;
            return false;
        } else
        {
            return false;
        }
    }

    public int fruitEaten()
    {
        if(type == -1)
        {
            return 0;
        } else
        {
            int pv = fruitScore[type];
            type = -1;
            fruitFrameNumber = 0 - (FRUIT_ALIVE_TIME - fruitFrameNumber);
            return pv;
        }
    }

    static String fruitImageFilename[];
    int fruitScore[];
    int fruitFrameNumber;
    static int NUMBER_OF_FRUITS = 8;
    static int FRUIT_ALIVE_TIME = 400;
    static int FRUIT_RESPAWN_TIME = 500;
    int x;
    int y;
    int type;

}
