// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Pacman.java

package pacman2000;

import pacman2000.maze.Maze;

public class Pacman
{

    public Pacman()
    {
    }

    public Pacman(int i, int j)
    {
        score = 0;
        nextLifeScore = 10000;
        lives = 3;
        displayLives = lives;
        x = i;
        y = j;
        dir = 3;
        mouth = 1;
        mouthDir = 1;
        dx = 4;
        dy = 0;
        dead = false;
        initFilenames();
        count = 0;
    }

    public void pacSetup(int i, int j)
    {
        x = i;
        y = j;
        dir = 3;
        mouth = 1;
        mouthDir = 1;
        dx = 4;
        dy = 0;
        dead = false;
        count = 0;
    }

    public static void setLevel(int l)
    {
        level = l;
    }

    public static void setSpeed(double s)
    {
        game_speed = s;
    }

    public void initFilenames()
    {
        String path = "pacman/";
        pacImageFilename = new String[64];
        pacImageFilename[0] = (new StringBuilder()).append(path).append("null.gif").toString();
        pacImageFilename[1] = (new StringBuilder()).append(path).append("pacmann1.gif").toString();
        pacImageFilename[2] = (new StringBuilder()).append(path).append("pacmann2.gif").toString();
        pacImageFilename[3] = (new StringBuilder()).append(path).append("pacmann3.gif").toString();
        pacImageFilename[4] = (new StringBuilder()).append(path).append("pacmann4.gif").toString();
        pacImageFilename[5] = (new StringBuilder()).append(path).append("pacmann5.gif").toString();
        pacImageFilename[6] = (new StringBuilder()).append(path).append("pacmann6.gif").toString();
        pacImageFilename[7] = (new StringBuilder()).append(path).append("pacmann7.gif").toString();
        pacImageFilename[8] = (new StringBuilder()).append(path).append("pacmans1.gif").toString();
        pacImageFilename[9] = (new StringBuilder()).append(path).append("pacmans2.gif").toString();
        pacImageFilename[10] = (new StringBuilder()).append(path).append("pacmans3.gif").toString();
        pacImageFilename[11] = (new StringBuilder()).append(path).append("pacmans4.gif").toString();
        pacImageFilename[12] = (new StringBuilder()).append(path).append("pacmans5.gif").toString();
        pacImageFilename[13] = (new StringBuilder()).append(path).append("pacmans6.gif").toString();
        pacImageFilename[14] = (new StringBuilder()).append(path).append("pacmans7.gif").toString();
        pacImageFilename[15] = (new StringBuilder()).append(path).append("pacmanw1.gif").toString();
        pacImageFilename[16] = (new StringBuilder()).append(path).append("pacmanw2.gif").toString();
        pacImageFilename[17] = (new StringBuilder()).append(path).append("pacmanw3.gif").toString();
        pacImageFilename[18] = (new StringBuilder()).append(path).append("pacmanw4.gif").toString();
        pacImageFilename[19] = (new StringBuilder()).append(path).append("pacmanw5.gif").toString();
        pacImageFilename[20] = (new StringBuilder()).append(path).append("pacmanw6.gif").toString();
        pacImageFilename[21] = (new StringBuilder()).append(path).append("pacmanw7.gif").toString();
        pacImageFilename[22] = (new StringBuilder()).append(path).append("pacmane1.gif").toString();
        pacImageFilename[23] = (new StringBuilder()).append(path).append("pacmane2.gif").toString();
        pacImageFilename[24] = (new StringBuilder()).append(path).append("pacmane3.gif").toString();
        pacImageFilename[25] = (new StringBuilder()).append(path).append("pacmane4.gif").toString();
        pacImageFilename[26] = (new StringBuilder()).append(path).append("pacmane5.gif").toString();
        pacImageFilename[27] = (new StringBuilder()).append(path).append("pacmane6.gif").toString();
        pacImageFilename[28] = (new StringBuilder()).append(path).append("pacmane7.gif").toString();
        pacImageFilename[29] = (new StringBuilder()).append(path).append("pacmandie1.gif").toString();
        pacImageFilename[30] = (new StringBuilder()).append(path).append("pacmandie2.gif").toString();
        pacImageFilename[31] = (new StringBuilder()).append(path).append("pacmandie3.gif").toString();
        pacImageFilename[32] = (new StringBuilder()).append(path).append("pacmandie4.gif").toString();
        pacImageFilename[33] = (new StringBuilder()).append(path).append("pacmandie5.gif").toString();
        pacImageFilename[34] = (new StringBuilder()).append(path).append("pacmandie6.gif").toString();
        pacImageFilename[35] = (new StringBuilder()).append(path).append("pacmandie7.gif").toString();
        pacImageFilename[36] = (new StringBuilder()).append(path).append("rambo/pacmann1.gif").toString();
        pacImageFilename[37] = (new StringBuilder()).append(path).append("rambo/pacmann2.gif").toString();
        pacImageFilename[38] = (new StringBuilder()).append(path).append("rambo/pacmann3.gif").toString();
        pacImageFilename[39] = (new StringBuilder()).append(path).append("rambo/pacmann4.gif").toString();
        pacImageFilename[40] = (new StringBuilder()).append(path).append("rambo/pacmann5.gif").toString();
        pacImageFilename[41] = (new StringBuilder()).append(path).append("rambo/pacmann6.gif").toString();
        pacImageFilename[42] = (new StringBuilder()).append(path).append("rambo/pacmann7.gif").toString();
        pacImageFilename[43] = (new StringBuilder()).append(path).append("rambo/pacmans1.gif").toString();
        pacImageFilename[44] = (new StringBuilder()).append(path).append("rambo/pacmans2.gif").toString();
        pacImageFilename[45] = (new StringBuilder()).append(path).append("rambo/pacmans3.gif").toString();
        pacImageFilename[46] = (new StringBuilder()).append(path).append("rambo/pacmans4.gif").toString();
        pacImageFilename[47] = (new StringBuilder()).append(path).append("rambo/pacmans5.gif").toString();
        pacImageFilename[48] = (new StringBuilder()).append(path).append("rambo/pacmans6.gif").toString();
        pacImageFilename[49] = (new StringBuilder()).append(path).append("rambo/pacmans7.gif").toString();
        pacImageFilename[50] = (new StringBuilder()).append(path).append("rambo/pacmanw1.gif").toString();
        pacImageFilename[51] = (new StringBuilder()).append(path).append("rambo/pacmanw2.gif").toString();
        pacImageFilename[52] = (new StringBuilder()).append(path).append("rambo/pacmanw3.gif").toString();
        pacImageFilename[53] = (new StringBuilder()).append(path).append("rambo/pacmanw4.gif").toString();
        pacImageFilename[54] = (new StringBuilder()).append(path).append("rambo/pacmanw5.gif").toString();
        pacImageFilename[55] = (new StringBuilder()).append(path).append("rambo/pacmanw6.gif").toString();
        pacImageFilename[56] = (new StringBuilder()).append(path).append("rambo/pacmanw7.gif").toString();
        pacImageFilename[57] = (new StringBuilder()).append(path).append("rambo/pacmane1.gif").toString();
        pacImageFilename[58] = (new StringBuilder()).append(path).append("rambo/pacmane2.gif").toString();
        pacImageFilename[59] = (new StringBuilder()).append(path).append("rambo/pacmane3.gif").toString();
        pacImageFilename[60] = (new StringBuilder()).append(path).append("rambo/pacmane4.gif").toString();
        pacImageFilename[61] = (new StringBuilder()).append(path).append("rambo/pacmane5.gif").toString();
        pacImageFilename[62] = (new StringBuilder()).append(path).append("rambo/pacmane6.gif").toString();
        pacImageFilename[63] = (new StringBuilder()).append(path).append("rambo/pacmane7.gif").toString();
    }

    public int getDirection()
    {
        if(dx < 0)
            return 2;
        if(dx > 0)
            return 3;
        if(dy < 0)
            return 0;
        return dy <= 0 ? 0 : 1;
    }

    public int getPacFrame(boolean rambo)
    {
        int num = getDirection();
        if(rambo)
            num += 5;
        if(dead)
            num = 4;
        num *= 7;
        num += mouth;
        return num;
    }

    public void movePacMan(Maze theMaze)
    {
        if(dead)
        {
            count--;
            if(count == 0)
            {
                mouth++;
                count = 6;
            }
            if(mouth > 7)
                mouth = 7;
            return;
        }
        if(mouthDir == 1)
        {
            mouth++;
            if(mouth == 7)
                mouthDir = -1;
        } else
        if(mouthDir == -1)
        {
            mouth--;
            if(mouth == 1)
                mouthDir = 1;
        }
        if(checkPacManMove(theMaze))
        {
            x = x + dx;
            y = y + dy;
            if(x >= (theMaze.getMazeX() - 1) * 16)
                x = 0;
            if(x < 0)
                x = (theMaze.getMazeX() - 1) * 16;
        }
    }

    public boolean checkPacManMove(Maze theMaze)
    {
        return !checkPacCollision(x + dx, y + dy, theMaze);
    }

    public boolean checkPacCollision(int i, int j, Maze theMaze)
    {
        int cx = i;
        int cy = j;
        if(!isPassable(cx, cy, theMaze))
            return true;
        cx = i + 15;
        cy = j;
        if(!isPassable(cx, cy, theMaze))
            return true;
        cx = i;
        cy = j + 15;
        if(!isPassable(cx, cy, theMaze))
            return true;
        cx = i + 15;
        cy = j + 15;
        return !isPassable(cx, cy, theMaze);
    }

    public void keyAct(int key, Maze theMaze)
    {
        int old_dx = dx;
        int old_dy = dy;
        if(key == 113 || key == 81 || key == 38)
        {
            dx = 0;
            dy = -4;
        } else
        if(key == 97 || key == 65 || key == 40)
        {
            dx = 0;
            dy = 4;
        } else
        if(key == 111 || key == 79 || key == 37)
        {
            dx = -4;
            dy = 0;
        } else
        if(key == 112 || key == 80 || key == 39)
        {
            dx = 4;
            dy = 0;
        }
        if(!checkPacManMove(theMaze))
        {
            dx = old_dx;
            dy = old_dy;
        }
    }

    public static boolean isPassable(int i, int j, Maze theMaze)
    {
        i /= 16;
        j /= 16;
        return theMaze.getBlock(i, j) < 10;
    }

    static final int BLOCKSIZE = 16;
    static final int INITIAL_SPEED = 4;
    static final int SPEED = 4;
    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    static final int MAX_MOUTH = 7;
    static final int CLOSED = 1;
    static final int OPENING = 1;
    static final int CLOSING = -1;
    static final int DEFAULT_LIVES = 3;
    static final int PAC_FRAMES = 64;
    static final int FIRST_LIFE = 10000;
    int x;
    int y;
    int dir;
    int dx;
    int dy;
    int mouth;
    int mouthDir;
    int score;
    int nextLifeScore;
    int lives;
    int displayLives;
    int count;
    boolean dead;
    static int level;
    static double game_speed;
    static String pacImageFilename[];
}
