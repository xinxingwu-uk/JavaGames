// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Maze.java

package pacman2000.maze;

import java.io.PrintStream;

// Referenced classes of package pacman2000.maze:
//            RandomMaze, Graph

public class Maze
{

    public Maze()
    {
        mazeX = 0;
        mazeY = 0;
    }

    public Maze(int x, int y)
    {
        mazeX = x;
        mazeY = y;
        block = new int[mazeX][mazeY];
        randomMaze(x, y);
        String path = "maze/";
        tilesetFilename = new String[12];
        tilesetFilename[0] = (new StringBuilder()).append(path).append("original/").toString();
        tilesetFilename[1] = (new StringBuilder()).append(path).append("grate/").toString();
        tilesetFilename[2] = (new StringBuilder()).append(path).append("cobble/").toString();
        tilesetFilename[3] = (new StringBuilder()).append(path).append("brick/").toString();
        tilesetFilename[4] = (new StringBuilder()).append(path).append("water/").toString();
        tilesetFilename[5] = (new StringBuilder()).append(path).append("hedge/").toString();
        tilesetFilename[6] = (new StringBuilder()).append(path).append("green/").toString();
        tilesetFilename[7] = (new StringBuilder()).append(path).append("blur/").toString();
        tilesetFilename[8] = (new StringBuilder()).append(path).append("hardblack/").toString();
        tilesetFilename[9] = (new StringBuilder()).append(path).append("noise/").toString();
        tilesetFilename[10] = (new StringBuilder()).append(path).append("piping/").toString();
        tilesetFilename[11] = (new StringBuilder()).append(path).append("granite/").toString();
        double rnd = Math.random();
        tileset = (int)(rnd * 12D);
        wallImageFilename = new String[31];
        wallImageFilename[0] = "null.gif";
        wallImageFilename[1] = "dot.gif";
        wallImageFilename[2] = "powerpill.gif";
        wallImageFilename[3] = "null.gif";
        wallImageFilename[4] = "null.gif";
        wallImageFilename[5] = "null.gif";
        wallImageFilename[6] = "null.gif";
        wallImageFilename[7] = "null.gif";
        wallImageFilename[8] = "null.gif";
        wallImageFilename[9] = "null.gif";
        wallImageFilename[10] = "walln.gif";
        wallImageFilename[11] = "walle.gif";
        wallImageFilename[12] = "walls.gif";
        wallImageFilename[13] = "wallw.gif";
        wallImageFilename[14] = "wallw-n.gif";
        wallImageFilename[15] = "wallw-s.gif";
        wallImageFilename[16] = "walle-n.gif";
        wallImageFilename[17] = "walle-s.gif";
        wallImageFilename[18] = "wallne.gif";
        wallImageFilename[19] = "wallse.gif";
        wallImageFilename[20] = "wallsw.gif";
        wallImageFilename[21] = "wallnw.gif";
        wallImageFilename[22] = "wall.gif";
        wallImageFilename[23] = "null.gif";
        wallImageFilename[24] = "null.gif";
        wallImageFilename[25] = "null.gif";
        wallImageFilename[26] = "null.gif";
        wallImageFilename[27] = "null.gif";
        wallImageFilename[28] = "null.gif";
        wallImageFilename[29] = "null.gif";
        wallImageFilename[30] = "door.gif";
        decorImageFilename = new String[6];
        decorImageFilename[0] = "decor2_1.gif";
        decorImageFilename[1] = "decor2_2.gif";
        decorImageFilename[2] = "decor2_3.gif";
        decorImageFilename[3] = "decor2_4.gif";
        decorImageFilename[4] = "decor3_1.gif";
        decorImageFilename[5] = "decor3_2.gif";
        for(int i = 0; i < 31; i++)
            wallImageFilename[i] = (new StringBuilder()).append(tilesetFilename[tileset]).append(wallImageFilename[i]).toString();

        for(int i = 0; i < 6; i++)
            decorImageFilename[i] = (new StringBuilder()).append(tilesetFilename[tileset]).append(decorImageFilename[i]).toString();

    }

    public void textDisplay()
    {
        for(int j = 0; j < mazeY; j++)
        {
            for(int i = 0; i < mazeX; i++)
            {
                if(block[i][j] == 0)
                {
                    System.out.print(" ");
                    continue;
                }
                if(block[i][j] == 1)
                {
                    System.out.print(".");
                    continue;
                }
                if(block[i][j] == 2)
                {
                    System.out.print("O");
                    continue;
                }
                if(wall(i, j))
                    System.out.print("#");
                else
                    System.out.print("-");
            }

            System.out.println();
        }

    }

    public int getDots()
    {
        return dots;
    }

    public int getBlock(int i, int j)
    {
        if(i < 0 || j < 0 || i >= mazeX || j >= mazeY)
            return 22;
        else
            return block[i][j];
    }

    private void dotCount()
    {
        int dotsLeft = 0;
        for(int i = 0; i < mazeX; i++)
        {
            for(int j = 0; j < mazeY; j++)
                if(block[i][j] == 1)
                    dotsLeft++;

        }

        dots = dotsLeft;
    }

    public boolean wall(int i, int j)
    {
        if(i < 0 || j < 0 || i >= mazeX || j >= mazeY)
            return true;
        return block[i][j] >= 10 && block[i][j] <= 29;
    }

    public boolean realWall(int i, int j)
    {
        if(i < 0 || j < 0 || i >= mazeX || j >= mazeY)
            return false;
        return block[i][j] == 22;
    }

    public boolean surroundedByWalls(int x, int y)
    {
        for(int i = x - 1; i <= x + 1; i++)
        {
            for(int j = y - 1; j <= y + 1; j++)
                if((x != i || y != j) && !wall(i, j))
                    return false;

        }

        return true;
    }

    private void wallShape()
    {
        for(int i = 0; i < mazeX; i++)
        {
            for(int j = 0; j < mazeY; j++)
            {
                if(block[i][j] != 22 || !wall(i, j))
                    continue;
                if(!wall(i + 1, j))
                    block[i][j] = 11;
                if(!wall(i - 1, j))
                    block[i][j] = 13;
                if(!wall(i, j - 1))
                    block[i][j] = 10;
                if(!wall(i, j + 1))
                    block[i][j] = 12;
                if(wall(i + 1, j) && wall(i, j - 1) && !wall(i + 1, j - 1))
                {
                    block[i][j] = 16;
                    continue;
                }
                if(wall(i + 1, j) && wall(i, j + 1) && !wall(i + 1, j + 1))
                {
                    block[i][j] = 17;
                    continue;
                }
                if(wall(i - 1, j) && wall(i, j - 1) && !wall(i - 1, j - 1))
                {
                    block[i][j] = 14;
                    continue;
                }
                if(wall(i - 1, j) && wall(i, j + 1) && !wall(i - 1, j + 1))
                {
                    block[i][j] = 15;
                    continue;
                }
                if(surroundedByWalls(i, j))
                {
                    block[i][j] = 22;
                    continue;
                }
                if(!wall(i + 1, j) && !wall(i, j - 1) && !wall(i + 1, j - 1))
                {
                    block[i][j] = 18;
                    continue;
                }
                if(!wall(i - 1, j) && !wall(i, j - 1) && !wall(i - 1, j - 1))
                {
                    block[i][j] = 21;
                    continue;
                }
                if(!wall(i + 1, j) && !wall(i, j + 1) && !wall(i + 1, j + 1))
                {
                    block[i][j] = 19;
                    continue;
                }
                if(!wall(i - 1, j) && !wall(i, j + 1) && !wall(i - 1, j + 1))
                    block[i][j] = 20;
            }

        }

        int j = mazeY / 2 - 2;
        block[mazeX / 2 - 2][j] = 30;
        block[mazeX / 2 - 1][j] = 30;
        block[mazeX / 2][j] = 30;
        block[mazeX / 2 + 1][j] = 30;
    }

    public int getPacStart()
    {
        for(int j = mazeY / 2 + 6; j < mazeY; j++)
            if(!wall(mazeX / 2, j))
                return j;

        return mazeY - 3;
    }

    private void dotInit()
    {
        for(int j = 2; j < mazeY - 2; j++)
        {
            for(int i = 2; i < mazeX - 2; i++)
                if(block[i][j] == 0 && (i <= mazeX / 2 - 6 || i >= mazeX / 2 + 5 || j <= mazeY / 2 - 4 || j >= mazeY / 2 + 6))
                    block[i][j] = 1;

        }

    }

    private void energiserInit()
    {
        int i = 0;
        int j;
        double rnd;
        for(j = 0; wall(i, j); j = (int)(rnd * 7D + 1.0D))
        {
            rnd = Math.random();
            i = (int)(rnd * 7D + 1.0D);
            rnd = Math.random();
        }

        block[i][j] = 2;
        block[mazeX - (i + 1)][j] = 2;
        i = 0;
     
        for(j = 0; wall(i, mazeY - (j + 1)); j = (int)(rnd * 7D + 1.0D))
        {
            rnd = Math.random();
            i = (int)(rnd * 7D + 1.0D);
            rnd = Math.random();
        }

        block[i][mazeY - (j + 1)] = 2;
        block[mazeX - (i + 1)][mazeY - (j + 1)] = 2;
    }

    private void randomMaze(int x, int y)
    {
        RandomMaze randomMaze = null;
        int vals[][];
        for(boolean inaccess = true; inaccess;)
        {
            randomMaze = new RandomMaze(x, y);
            vals = new int[x][y];
            for(int i = 0; i < x; i++)
            {
                for(int j = 0; j < y; j++)
                    vals[i][j] = randomMaze.getValue(i, j);

            }

            graph = new Graph(vals, x, y);
            inaccess = false;
            try
            {
                int i = 0;
                while(i < x) 
                {
                    for(int j = 0; j < y; j++)
                        if(vals[i][j] == 1)
                        {
                            char test = graph.newDir('u', 2, y / 2, i, j);
                            System.out.println((new StringBuilder()).append("x: ").append(i).append(" y: ").append(j).append(" newDir: ").append(test).toString());
                        }

                    i++;
                }
            }
            catch(NullPointerException e)
            {
                inaccess = true;
            }
        }

        vals = new int[x][y];
        for(int i = 0; i < x; i++)
        {
            for(int j = 0; j < y; j++)
                vals[i][j] = randomMaze.getValue(i, j);

        }

        graph = new Graph(vals, x, y);
        for(int i = 0; i < mazeX; i++)
        {
            for(int j = 0; j < mazeY; j++)
                if(randomMaze.getValue(i, j) == 2)
                    block[i][j] = 22;
                else
                    block[i][j] = 0;

        }

        wallShape();
        dotInit();
        energiserInit();
        dotCount();
    }

    private void skeletonMaze(int maze_x, int maze_y)
    {
        for(int i = 0; i < maze_x; i++)
        {
            for(int j = 0; j < maze_y; j++)
                block[i][j] = 22;

        }

        for(int i = 1; i < maze_y - 1; i++)
            block[1][i] = 0;

        for(int i = 1; i < maze_x - 1; i++)
        {
            block[i][1] = 0;
            block[i][maze_y - 2] = 0;
        }

        for(int i = 4; i < maze_y - 4; i++)
            block[4][i] = 0;

        for(int i = 4; i < maze_x - 4; i++)
        {
            block[i][4] = 0;
            block[i][maze_y - 5] = 0;
        }

        for(int i = 1; i < maze_y - 1; i++)
        {
            block[maze_x / 2 - 3][i] = 0;
            block[maze_x / 2 + 3][i] = 0;
        }

        for(int i = 1; i < maze_x - 1; i++)
        {
            block[i][9] = 0;
            block[i][maze_y - 9] = 0;
        }

        for(int j = 0; j < maze_y; j++)
        {
            for(int i = 0; i < maze_x / 2; i++)
                block[maze_x - (1 + i)][j] = block[i][j];

        }

        for(int j = 0; j < maze_y; j++)
        {
            for(int i = 0; i < maze_x; i++)
                if(block[i][j] == 0)
                    block[i][j] = 1;

        }

        block[1][1] = 2;
        block[1][maze_y - 2] = 2;
        block[maze_x - 2][1] = 2;
        block[maze_x - 2][maze_y - 2] = 2;
        for(int i = maze_x / 2 - 5; i < maze_x / 2 + 5; i++)
        {
            for(int j = maze_y / 2 - 3; j < maze_y / 2 + 4; j++)
                block[i][j] = 0;

        }

        block[maze_x / 2 - 4][maze_y / 2 - 2] = 21;
        block[maze_x / 2 - 3][maze_y / 2 - 2] = 10;
        block[maze_x / 2 - 2][maze_y / 2 - 2] = 10;
        block[maze_x / 2 - 1][maze_y / 2 - 2] = 30;
        block[maze_x / 2][maze_y / 2 - 2] = 30;
        block[maze_x / 2 + 1][maze_y / 2 - 2] = 10;
        block[maze_x / 2 + 2][maze_y / 2 - 2] = 10;
        block[maze_x / 2 + 3][maze_y / 2 - 2] = 18;
        block[maze_x / 2 - 4][maze_y / 2 - 1] = 13;
        block[maze_x / 2 + 3][maze_y / 2 - 1] = 11;
        block[maze_x / 2 - 4][maze_y / 2] = 13;
        block[maze_x / 2 + 3][maze_y / 2] = 11;
        block[maze_x / 2 - 4][maze_y / 2 + 1] = 13;
        block[maze_x / 2 + 3][maze_y / 2 + 1] = 11;
        block[maze_x / 2 - 4][maze_y / 2 + 2] = 20;
        block[maze_x / 2 - 3][maze_y / 2 + 2] = 12;
        block[maze_x / 2 - 2][maze_y / 2 + 2] = 12;
        block[maze_x / 2 - 1][maze_y / 2 + 2] = 12;
        block[maze_x / 2][maze_y / 2 + 2] = 12;
        block[maze_x / 2 + 1][maze_y / 2 + 2] = 12;
        block[maze_x / 2 + 2][maze_y / 2 + 2] = 12;
        block[maze_x / 2 + 3][maze_y / 2 + 2] = 19;
        wallShape();
        dotCount();
    }

    public int getMazeX()
    {
        return mazeX;
    }

    public int getMazeY()
    {
        return mazeY;
    }

    public void setBlock(int x, int y, int value)
    {
        block[x][y] = value;
    }

    public void decreaseDots()
    {
        dots--;
    }

    public int getTileset()
    {
        return tileset;
    }

    public Graph getGraph()
    {
        return graph;
    }

    int block[][];
    private int mazeX;
    private int mazeY;
    int dots;
    int tileset;
    Graph graph;
    public static final int NO_OF_TILESETS = 12;
    public static final int WATER = 4;
    public static final int DOT = 1;
    public static final int ENERGISER = 2;
    public static final int WALL_N = 10;
    public static final int WALL_E = 11;
    public static final int WALL_S = 12;
    public static final int WALL_W = 13;
    public static final int WALL_W_N = 14;
    public static final int WALL_W_S = 15;
    public static final int WALL_E_N = 16;
    public static final int WALL_E_S = 17;
    public static final int WALL_NE = 18;
    public static final int WALL_SE = 19;
    public static final int WALL_SW = 20;
    public static final int WALL_NW = 21;
    public static final int WALL = 22;
    public static final int DOOR = 30;
    public static final int WALL_MIN = 10;
    public static final int WALL_MAX = 29;
    public static final int NO_OF_IMAGES = 31;
    public static final int SMALL_DECOR = 4;
    public static final int LARGE_DECOR = 2;
    public static final int DOT_SCORE = 10;
    public static final int ENERGISER_SCORE = 50;
    public static String wallImageFilename[];
    public static String decorImageFilename[];
    public static String tilesetFilename[];
}
