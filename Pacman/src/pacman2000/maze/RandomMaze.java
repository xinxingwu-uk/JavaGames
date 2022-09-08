// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RandomMaze.java
// Download by http://www.codefans.net
package pacman2000.maze;

import java.util.Random;
import java.util.Vector;
import pacman2000.Position;

public class RandomMaze
{
    private class Row
    {

        public void add(Integer value)
        {
            cells.add(value);
        }

        public void set(int index, Integer value)
        {
            cells.set(index, value);
        }

        public Integer get(int index)
        {
            return (Integer)cells.get(index);
        }

        public void remove(int index)
        {
            cells.remove(index);
        }

        public int getSize()
        {
            return cells.size();
        }

        Vector cells;


        public Row()
        {
       
            cells = new Vector(0, 1);
        }
    }


    public RandomMaze(int xVal, int yVal)
    {
        MAPX = 20;
        MAPY = 20;
        suround = new int[8];
        rows = new Vector(0, 1);
        rnd = new Random(System.currentTimeMillis());
        MAPX = xVal / 2 + 1;
        MAPY = yVal;
        rows.clear();
        for(int j = 0; j < MAPY; j++)
        {
            Row row = new Row();
            for(int i = 0; i < MAPX + 2; i++)
                row.add(new Integer(2));

            rows.add(row);
        }

        initializer();
        mazeMaker();
    }

    public int getValue(int x, int y)
    {
        Row row = (Row)rows.elementAt(y);
        return row.get(x).intValue();
    }

    public int getRowSize(int y)
    {
        Row row = (Row)rows.elementAt(y);
        return row.getSize();
    }

    public void setValue(int x, int y, int newValue)
    {
        Row row = (Row)rows.elementAt(y);
        row.set(x, Integer.valueOf(newValue));
    }

    public void initializer()
    {
        int i = 0;
        int j;
        for(j = 0; j < MAPY; j++)
            setValue(i, j, 2);

        i = 1;
        for(j = 0; j < MAPY; j++)
            setValue(i, j, 2);

        j = MAPY / 2;
        for(i = 0; i < MAPX - 5; i++)
            setValue(i, j, 1);

        for(i = 0; i < 3; i++)
            setValue(i, j, 1);

        i = MAPX - 6;
        for(j = MAPY / 2 - 3; j < MAPY / 2 + 6; j++)
            setValue(i, j, 1);

        j = MAPY / 2 + 5;
        for(i = MAPX - 5; i < MAPX; i++)
            setValue(i, j, 1);

        j = MAPY / 2 - 3;
        for(i = MAPX - 6; i < MAPX; i++)
            setValue(i, j, 1);

        i = MAPX - 1;
        for(j = 1; j < MAPY - 1; j++)
            setValue(i, j, 1);

        j = 0;
        for(i = 0; i < MAPX; i++)
            setValue(i, j, 2);

        j = 1;
        for(i = 0; i < MAPX; i++)
            setValue(i, j, 2);

        j = MAPY - 1;
        for(i = 0; i < MAPX; i++)
            setValue(i, j, 2);

        j = MAPY - 2;
        for(i = 0; i < MAPX; i++)
            setValue(i, j, 2);

        j = MAPY / 2 + 3;
        for(i = MAPX - 5; i < MAPX; i++)
            setValue(i, j, 2);

        j = MAPY / 2 + 4;
        for(i = MAPX - 5; i < MAPX; i++)
            setValue(i, j, 2);

        i = MAPX - 4;
        for(j = MAPY / 2 - 2; j < MAPY / 2 + 4; j++)
            setValue(i, j, 2);

        i = MAPX - 5;
        for(j = MAPY / 2 - 2; j < MAPY / 2 + 4; j++)
            setValue(i, j, 2);

        j = MAPY / 2 - 2;
        i = MAPX - 3;
        setValue(i, j, 2);
        j = MAPY / 2 - 1;
        i = MAPX - 3;
        setValue(i, j, 2);
        i = MAPX - 2;
        for(j = MAPY / 2 - 2; j < MAPY / 2 + 3; j++)
            setValue(i, j, 1);

        i = MAPX - 3;
        for(j = MAPY / 2; j < MAPY / 2 + 3; j++)
            setValue(i, j, 1);

        j = 2;
        for(i = 2; i < MAPX; i++)
            setValue(i, j, 1);

        j = MAPY - 3;
        for(i = 2; i < MAPX; i++)
            setValue(i, j, 1);

        i = 2;
        for(j = 2; j < MAPY - 2; j++)
            setValue(i, j, 1);

    }

    public void mazeMaker()
    {
        Vector ptc = new Vector(0, 1);
        ptc.clear();
        int nullSuroundings[] = new int[4];
        Position newptc = new Position(2, MAPY / 2);
        boolean room = false;
        boolean enough = false;
        boolean dead = false;
        boolean diag = false;
        boolean corner = false;
        boolean makeThin = false;
        do
        {
            if(enough)
                break;
            for(int y = 2; y < MAPY - 2; y++)
            {
                for(int x = 2; x < MAPX; x++)
                    if(getValue(x, y) == 1)
                    {
                        newptc = new Position(x, y);
                        ptc.add(newptc);
                    }

            }

            int randomPtc;
            for(; !ptc.isEmpty(); ptc.remove(randomPtc))
            {
                randomPtc = rnd.nextInt(ptc.size());
                newptc = (Position)ptc.elementAt(randomPtc);
                int startX = newptc.getX();
                int startY = newptc.getY();
                lookAbout(startX, startY);
                for(int i = 0; i < 4; i++)
                    nullSuroundings[i] = 7;

                int nullSurCount = 0;
                if(suround[0] == 2)
                {
                    nullSurCount++;
                    nullSuroundings[nullSurCount - 1] = 0;
                }
                if(suround[2] == 2)
                {
                    nullSurCount++;
                    nullSuroundings[nullSurCount - 1] = 2;
                }
                if(suround[4] == 2)
                {
                    nullSurCount++;
                    nullSuroundings[nullSurCount - 1] = 4;
                }
                if(suround[6] == 2)
                {
                    nullSurCount++;
                    nullSuroundings[nullSurCount - 1] = 6;
                }
                if(nullSurCount == 0)
                    continue;
                int randomOpenings = nullSurCount;
                int x;
                int y;
                for(int i = 1; i <= randomOpenings; i++)
                {
                    int randomOpening = i;
                    int pointToOpen = nullSuroundings[randomOpening - 1];
                    if(pointToOpen == 0 && startY > 2)
                    {
                        x = startX + N.getX();
                        y = startY + N.getY();
                        room = checkForRooms(x, y);
                        makeThin = checkForMakeThin(x, y);
                        if(room || makeThin)
                            setValue(x, y, 2);
                        else
                            setValue(x, y, 3);
                    }
                    if(pointToOpen == 2 && startX < MAPX - 2)
                    {
                        x = startX + E.getX();
                        y = startY + E.getY();
                        room = checkForRooms(x, y);
                        makeThin = checkForMakeThin(x, y);
                        if(room || makeThin)
                            setValue(x, y, 2);
                        else
                            setValue(x, y, 3);
                    }
                    if(pointToOpen == 4 && startY < MAPY - 3)
                    {
                        x = startX + S.getX();
                        y = startY + S.getY();
                        room = checkForRooms(x, y);
                        makeThin = checkForMakeThin(x, y);
                        if(room || makeThin)
                            setValue(x, y, 2);
                        else
                            setValue(x, y, 3);
                    }
                    if(pointToOpen != 6 || startX <= 2)
                        continue;
                    x = startX + W.getX();
                    y = startY + W.getY();
                    room = checkForRooms(x, y);
                    makeThin = checkForMakeThin(x, y);
                    if(room || makeThin)
                        setValue(x, y, 2);
                    else
                        setValue(x, y, 3);
                }

                x = startX + N.getX();
                y = startY + N.getY();
                if(getValue(x, y) == 3 && startY > 1)
                {
                    newptc = new Position(x, y);
                    ptc.add(newptc);
                    setValue(x, y, 1);
                }
                if(startX < MAPX)
                {
                    x = startX + E.getX();
                    y = startY + E.getY();
                    if(getValue(x, y) == 3 && startX < MAPX - 2)
                    {
                        newptc = new Position(x, y);
                        ptc.add(newptc);
                        setValue(x, y, 1);
                    }
                }
                x = startX + S.getX();
                y = startY + S.getY();
                if(getValue(x, y) == 3 && startY < MAPY - 2)
                {
                    newptc = new Position(x, y);
                    ptc.add(newptc);
                    setValue(x, y, 1);
                }
                x = startX + W.getX();
                y = startY + W.getY();
                if(getValue(x, y) == 3 && startX > 1)
                {
                    newptc = new Position(x, y);
                    ptc.add(newptc);
                    setValue(x, y, 1);
                }
            }

            for(int y = 0; y < MAPY; y++)
            {
                for(int x = 1; x < MAPX; x++)
                    if(getValue(x, y) == 0)
                        setValue(x, y, 2);

            }

            int deads = 1;
            int diags = 1;
            int rooms = 1;
            for(int corners = 1; diags != 0 || deads != 0 || rooms != 0 || corners != 0;)
            {
                int y;
                for(y = 0; y < MAPY; y++)
                {
                    for(int x = 1; x < MAPX; x++)
                    {
                        if(getValue(x, y) != 1)
                            continue;
                        diag = checkForDiag(x, y);
                        if(diag)
                            setValue(x, y, 2);
                    }

                }

                for(y = 0; y < MAPY; y++)
                {
                    for(int x = 1; x < MAPX; x++)
                    {
                        if(getValue(x, y) != 1)
                            continue;
                        room = checkForRooms(x, y);
                        if(room)
                            setValue(x, y, 2);
                    }

                }

                for(y = 0; y < MAPY; y++)
                {
                    for(int x = 2; x < MAPX; x++)
                    {
                        if(getValue(x, y) != 1)
                            continue;
                        dead = checkForDeads(x, y);
                        if(dead)
                            setValue(x, y, 2);
                    }

                }

                corners = 0;
                for(y = 0; y < MAPY; y++)
                {
                    for(int x = 1; x < MAPX; x++)
                    {
                        if(getValue(x, y) != 2)
                            continue;
                        corner = checkForThinCorner(x, y);
                        if(corner)
                            corners++;
                    }

                }

                deads = 0;
                for(y = 0; y < MAPY; y++)
                {
                    for(int x = 2; x < MAPX; x++)
                    {
                        if(getValue(x, y) != 1)
                            continue;
                        dead = checkForDeads(x, y);
                        if(dead)
                            deads++;
                    }

                }

                rooms = 0;
                for(y = 0; y < MAPY; y++)
                {
                    for(int x = 2; x < MAPX; x++)
                    {
                        if(getValue(x, y) != 1)
                            continue;
                        room = checkForRooms(x, y);
                        if(room)
                            rooms++;
                    }

                }

                diags = 0;
                y = 0;
                while(y < MAPY) 
                {
                    for(int x = 2; x < MAPX; x++)
                    {
                        if(getValue(x, y) != 1)
                            continue;
                        dead = checkForDiag(x, y);
                        if(diag)
                            diags++;
                    }

                    y++;
                }
            }

            initializer();
            double opens = 0.0D;
            double mapSize = MAPX * MAPY;
            for(int y = 0; y < MAPY; y++)
            {
                for(int x = 2; x < MAPX; x++)
                    if(getValue(x, y) == 1)
                        opens++;

            }

            if(opens / mapSize > 0.33000000000000002D)
                enough = true;
        } while(true);
        int mirrorcount = 0;
        for(int y = 0; y < MAPY; y++)
        {
            mirrorcount = MAPX - 2;
            Row row = (Row)rows.elementAt(y);
            row.remove(MAPX + 1);
            row.remove(MAPX);
            row.remove(MAPX - 1);
            for(int x = MAPX - 1; x < (MAPX - 1) * 2; x++)
            {
                int intCode = getValue(mirrorcount, y);
                Integer code = new Integer(intCode);
                row.add(code);
                mirrorcount--;
            }

        }

    }

    public boolean checkForRooms(int startX, int startY)
    {
        boolean room = false;
        lookAbout(startX, startY);
        if(suround[0] == 1 && suround[1] == 1 && suround[2] == 1)
            room = true;
        else
        if(suround[2] == 1 && suround[3] == 1 && suround[4] == 1)
            room = true;
        else
        if(suround[4] == 1 && suround[5] == 1 && suround[6] == 1)
            room = true;
        else
        if(suround[6] == 1 && suround[7] == 1 && suround[0] == 1)
            room = true;
        return room;
    }

    public boolean checkForDiag(int startX, int startY)
    {
        boolean diag = false;
        int start = getValue(startX, startY);
        if(start != 1)
            return false;
        lookAbout(startX, startY);
        if(suround[0] == 2 && suround[1] == 1 && suround[2] == 2)
            diag = true;
        else
        if(suround[2] == 2 && suround[3] == 1 && suround[4] == 2)
            diag = true;
        else
        if(suround[4] == 2 && suround[5] == 1 && suround[6] == 2)
            diag = true;
        else
        if(suround[6] == 2 && suround[7] == 1 && suround[0] == 2)
            diag = true;
        return diag;
    }

    public boolean checkForThinCorner(int startX, int startY)
    {
        boolean corner = false;
        int start = getValue(startX, startY);
        if(start != 2)
            return false;
        lookAbout(startX, startY);
        if(suround[0] == 2 && suround[1] == 1 && suround[2] == 2 && suround[3] == 2 && suround[4] == 1 && suround[5] == 1 && suround[6] == 2 && suround[7] == 2)
        {
            corner = true;
            setValue(startX + 1, startY - 1, 2);
            setValue(startX, startY + 1, 2);
            setValue(startX - 1, startY + 1, 2);
        } else
        if(suround[0] == 2 && suround[1] == 1 && suround[2] == 1 && suround[3] == 2 && suround[4] == 2 && suround[5] == 1 && suround[6] == 2 && suround[7] == 2)
        {
            corner = true;
            setValue(startX + 1, startY - 1, 2);
            setValue(startX + 1, startY, 2);
            setValue(startX - 1, startY + 1, 2);
        } else
        if(suround[0] == 2 && suround[1] == 1 && suround[2] == 2 && suround[3] == 2 && suround[4] == 2 && suround[5] == 1 && suround[6] == 2 && suround[7] == 2)
        {
            corner = true;
            setValue(startX + 1, startY - 1, 2);
            setValue(startX - 1, startY + 1, 2);
        } else
        if(suround[0] == 2 && suround[1] == 2 && suround[2] == 2 && suround[3] == 1 && suround[4] == 1 && suround[5] == 2 && suround[6] == 2 && suround[7] == 1)
        {
            corner = true;
            setValue(startX + 1, startY + 1, 2);
            setValue(startX, startY + 1, 2);
            setValue(startX - 1, startY - 1, 2);
        } else
        if(suround[0] == 2 && suround[1] == 2 && suround[2] == 2 && suround[3] == 1 && suround[4] == 2 && suround[5] == 2 && suround[6] == 1 && suround[7] == 1)
        {
            corner = true;
            setValue(startX + 1, startY + 1, 2);
            setValue(startX - 1, startY, 2);
            setValue(startX - 1, startY - 1, 2);
        } else
        if(suround[0] == 2 && suround[1] == 2 && suround[2] == 2 && suround[3] == 1 && suround[4] == 2 && suround[5] == 2 && suround[6] == 2 && suround[7] == 1)
        {
            corner = true;
            setValue(startX + 1, startY + 1, 2);
            setValue(startX - 1, startY - 1, 2);
        } else
        if(suround[0] == 1 && suround[1] == 1 && suround[2] == 2 && suround[3] == 2 && suround[4] == 2 && suround[5] == 1 && suround[6] == 2 && suround[7] == 2)
        {
            corner = true;
            setValue(startX, startY - 1, 2);
            setValue(startX + 1, startY - 1, 2);
            setValue(startX - 1, startY + 1, 2);
        } else
        if(suround[0] == 2 && suround[1] == 1 && suround[2] == 2 && suround[3] == 2 && suround[4] == 2 && suround[5] == 1 && suround[6] == 1 && suround[7] == 2)
        {
            corner = true;
            setValue(startX + 1, startY - 1, 2);
            setValue(startX - 1, startY + 1, 2);
            setValue(startX - 1, startY, 2);
        } else
        if(suround[0] == 2 && suround[1] == 2 && suround[2] == 1 && suround[3] == 1 && suround[4] == 2 && suround[5] == 2 && suround[6] == 2 && suround[7] == 1)
        {
            corner = true;
            setValue(startX + 1, startY, 2);
            setValue(startX + 1, startY + 1, 2);
            setValue(startX - 1, startY - 1, 2);
        } else
        if(suround[0] == 1 && suround[1] == 2 && suround[2] == 2 && suround[3] == 1 && suround[4] == 2 && suround[5] == 2 && suround[6] == 2 && suround[7] == 1)
        {
            corner = true;
            setValue(startX, startY - 1, 2);
            setValue(startX + 1, startY + 1, 2);
            setValue(startX - 1, startY - 1, 2);
        }
        return corner;
    }

    public boolean checkForDeads(int startX, int startY)
    {
        boolean dead = false;
        int opens = 0;
        lookAbout(startX, startY);
        for(int i = 0; i < 7; i += 2)
            if(suround[i] == 1)
                opens++;

        if(opens == 0 || opens == 1)
            dead = true;
        return dead;
    }

    public boolean checkForThins(int startX, int startY)
    {
        boolean thin = true;
        lookAbout(startX, startY);
        if(suround[0] == 2 && suround[1] == 2 && suround[2] == 2)
            thin = false;
        if(suround[2] == 2 && suround[3] == 2 && suround[4] == 2)
            thin = false;
        if(suround[4] == 2 && suround[5] == 2 && suround[6] == 2)
            thin = false;
        if(suround[6] == 2 && suround[7] == 2 && suround[0] == 2)
            thin = false;
        return thin;
    }

    public boolean checkForMakeThin(int startX, int startY)
    {
        boolean makeThin = false;
        boolean thin = false;
        setValue(startX, startY, 1);
        int x = startX + N.getX();
        int y = startY + N.getY();
        if(getValue(x, y) == 2)
        {
            thin = checkForThins(x, y);
            if(thin)
                makeThin = true;
        }
        x = startX + NE.getX();
        y = startY + NE.getY();
        if(getValue(x, y) == 2)
        {
            thin = checkForThins(x, y);
            if(thin)
                makeThin = true;
        }
        x = startX + E.getX();
        y = startY + E.getY();
        if(getValue(x, y) == 2)
        {
            thin = checkForThins(x, y);
            if(thin)
                makeThin = true;
        }
        x = startX + SE.getX();
        y = startY + SE.getY();
        if(getValue(x, y) == 2)
        {
            thin = checkForThins(x, y);
            if(thin)
                makeThin = true;
        }
        x = startX + S.getX();
        y = startY + S.getY();
        if(getValue(x, y) == 2)
        {
            thin = checkForThins(x, y);
            if(thin)
                makeThin = true;
        }
        x = startX + SW.getX();
        y = startY + SW.getY();
        if(getValue(x, y) == 2)
        {
            thin = checkForThins(x, y);
            if(thin)
                makeThin = true;
        }
        x = startX + W.getX();
        y = startY + W.getY();
        if(getValue(x, y) == 2)
        {
            thin = checkForThins(x, y);
            if(thin)
                makeThin = true;
        }
        x = startX + NW.getX();
        y = startY + NW.getY();
        if(getValue(x, y) == 2)
        {
            thin = checkForThins(x, y);
            if(thin)
                makeThin = true;
        }
        setValue(startX, startY, 2);
        return makeThin;
    }

    public void lookAbout(int startX, int startY)
    {
        int x = startX + N.getX();
        int y = startY + N.getY();
        if(y > 1)
            suround[0] = getValue(x, y);
        else
            suround[0] = 2;
        x = startX + NE.getX();
        y = startY + NE.getY();
        if(y > 1 && x < MAPX + 2)
            suround[1] = getValue(x, y);
        else
            suround[1] = 2;
        x = startX + E.getX();
        y = startY + E.getY();
        if(x < MAPX + 2)
            suround[2] = getValue(x, y);
        else
            suround[2] = 2;
        x = startX + SE.getX();
        y = startY + SE.getY();
        if(y < MAPY && x < MAPX + 2)
            suround[3] = getValue(x, y);
        else
            suround[3] = 2;
        x = startX + S.getX();
        y = startY + S.getY();
        if(y < MAPY)
            suround[4] = getValue(x, y);
        else
            suround[4] = 2;
        x = startX + SW.getX();
        y = startY + SW.getY();
        if(y < MAPY && x > 1)
            suround[5] = getValue(x, y);
        else
            suround[5] = 2;
        x = startX + W.getX();
        y = startY + W.getY();
        if(x > 1)
            suround[6] = getValue(x, y);
        else
            suround[6] = 2;
        x = startX + NW.getX();
        y = startY + NW.getY();
        if(y > 1 && x > 1)
            suround[7] = getValue(x, y);
        else
            suround[7] = 2;
    }

    public int MAPX;
    public int MAPY;
    public int suround[];
    public static final Position N = new Position(0, -1);
    public static final Position S = new Position(0, 1);
    public static final Position E = new Position(1, 0);
    public static final Position W = new Position(-1, 0);
    public static final Position NE = new Position(1, -1);
    public static final Position SE = new Position(1, 1);
    public static final Position SW = new Position(-1, 1);
    public static final Position NW = new Position(-1, -1);
    private Vector rows;
    private Random rnd;

}
