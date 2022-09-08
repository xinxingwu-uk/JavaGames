// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Ghost.java

package pacman2000;

import pacman2000.maze.Graph;
import pacman2000.maze.Maze;

// Referenced classes of package pacman2000:
//            SoundManager, Pacman

public class Ghost
{

    public Ghost()
    {
    }

    public Ghost(int i, int j, int col, int ai)
    {
        x = i;
        y = j;
        dir = 2;
        frame = 1;
        scared = false;
        dead = false;
        scaredTime = 0;
        fear = false;
        shock = false;
        pausedTime = 0;
        if(ai == 2 || ai == 3)
            speed = 4;
        else
        if(ai == 0)
            speed = 3;
        else
        if(ai == 1)
            speed = 5;
        if(level <= 4)
            speed--;
        if(level >= 10)
            speed++;
        if(level >= 15)
            speed++;
        defaultSpeed = speed;
        colour = col;
        AItype = ai;
        canPassDoor = false;
        leavingHouse = false;
        if(ghostCount == 0)
        {
            homeTime = 0;
            dx = -speed;
            dy = 0;
        } else
        if(ghostCount == 1)
        {
            homeTime = ghostCount * timeCount;
            dx = 0;
            dy = 3;
        } else
        {
            homeTime = ghostCount * timeCount;
            dx = 0;
            dy = -3;
        }
        ghostCount++;
        initFilenames();
    }

    public void initFilenames()
    {
        String path = "ghost/";
        ghostImageFilename = new String[113];
        ghostImageFilename[0] = (new StringBuilder()).append(path).append("null.gif").toString();
        ghostImageFilename[1] = (new StringBuilder()).append(path).append("redn1.gif").toString();
        ghostImageFilename[2] = (new StringBuilder()).append(path).append("redn2.gif").toString();
        ghostImageFilename[3] = (new StringBuilder()).append(path).append("redn3.gif").toString();
        ghostImageFilename[4] = (new StringBuilder()).append(path).append("redn4.gif").toString();
        ghostImageFilename[5] = (new StringBuilder()).append(path).append("reds1.gif").toString();
        ghostImageFilename[6] = (new StringBuilder()).append(path).append("reds2.gif").toString();
        ghostImageFilename[7] = (new StringBuilder()).append(path).append("reds3.gif").toString();
        ghostImageFilename[8] = (new StringBuilder()).append(path).append("reds4.gif").toString();
        ghostImageFilename[9] = (new StringBuilder()).append(path).append("redw1.gif").toString();
        ghostImageFilename[10] = (new StringBuilder()).append(path).append("redw2.gif").toString();
        ghostImageFilename[11] = (new StringBuilder()).append(path).append("redw3.gif").toString();
        ghostImageFilename[12] = (new StringBuilder()).append(path).append("redw4.gif").toString();
        ghostImageFilename[13] = (new StringBuilder()).append(path).append("rede1.gif").toString();
        ghostImageFilename[14] = (new StringBuilder()).append(path).append("rede2.gif").toString();
        ghostImageFilename[15] = (new StringBuilder()).append(path).append("rede3.gif").toString();
        ghostImageFilename[16] = (new StringBuilder()).append(path).append("rede4.gif").toString();
        ghostImageFilename[17] = (new StringBuilder()).append(path).append("purplen1.gif").toString();
        ghostImageFilename[18] = (new StringBuilder()).append(path).append("purplen2.gif").toString();
        ghostImageFilename[19] = (new StringBuilder()).append(path).append("purplen3.gif").toString();
        ghostImageFilename[20] = (new StringBuilder()).append(path).append("purplen4.gif").toString();
        ghostImageFilename[21] = (new StringBuilder()).append(path).append("purples1.gif").toString();
        ghostImageFilename[22] = (new StringBuilder()).append(path).append("purples2.gif").toString();
        ghostImageFilename[23] = (new StringBuilder()).append(path).append("purples3.gif").toString();
        ghostImageFilename[24] = (new StringBuilder()).append(path).append("purples4.gif").toString();
        ghostImageFilename[25] = (new StringBuilder()).append(path).append("purplew1.gif").toString();
        ghostImageFilename[26] = (new StringBuilder()).append(path).append("purplew2.gif").toString();
        ghostImageFilename[27] = (new StringBuilder()).append(path).append("purplew3.gif").toString();
        ghostImageFilename[28] = (new StringBuilder()).append(path).append("purplew4.gif").toString();
        ghostImageFilename[29] = (new StringBuilder()).append(path).append("purplee1.gif").toString();
        ghostImageFilename[30] = (new StringBuilder()).append(path).append("purplee2.gif").toString();
        ghostImageFilename[31] = (new StringBuilder()).append(path).append("purplee3.gif").toString();
        ghostImageFilename[32] = (new StringBuilder()).append(path).append("purplee4.gif").toString();
        ghostImageFilename[33] = (new StringBuilder()).append(path).append("greenn1.gif").toString();
        ghostImageFilename[34] = (new StringBuilder()).append(path).append("greenn2.gif").toString();
        ghostImageFilename[35] = (new StringBuilder()).append(path).append("greenn3.gif").toString();
        ghostImageFilename[36] = (new StringBuilder()).append(path).append("greenn4.gif").toString();
        ghostImageFilename[37] = (new StringBuilder()).append(path).append("greens1.gif").toString();
        ghostImageFilename[38] = (new StringBuilder()).append(path).append("greens2.gif").toString();
        ghostImageFilename[39] = (new StringBuilder()).append(path).append("greens3.gif").toString();
        ghostImageFilename[40] = (new StringBuilder()).append(path).append("greens4.gif").toString();
        ghostImageFilename[41] = (new StringBuilder()).append(path).append("greenw1.gif").toString();
        ghostImageFilename[42] = (new StringBuilder()).append(path).append("greenw2.gif").toString();
        ghostImageFilename[43] = (new StringBuilder()).append(path).append("greenw3.gif").toString();
        ghostImageFilename[44] = (new StringBuilder()).append(path).append("greenw4.gif").toString();
        ghostImageFilename[45] = (new StringBuilder()).append(path).append("greene1.gif").toString();
        ghostImageFilename[46] = (new StringBuilder()).append(path).append("greene2.gif").toString();
        ghostImageFilename[47] = (new StringBuilder()).append(path).append("greene3.gif").toString();
        ghostImageFilename[48] = (new StringBuilder()).append(path).append("greene4.gif").toString();
        ghostImageFilename[49] = (new StringBuilder()).append(path).append("cyann1.gif").toString();
        ghostImageFilename[50] = (new StringBuilder()).append(path).append("cyann2.gif").toString();
        ghostImageFilename[51] = (new StringBuilder()).append(path).append("cyann3.gif").toString();
        ghostImageFilename[52] = (new StringBuilder()).append(path).append("cyann4.gif").toString();
        ghostImageFilename[53] = (new StringBuilder()).append(path).append("cyans1.gif").toString();
        ghostImageFilename[54] = (new StringBuilder()).append(path).append("cyans2.gif").toString();
        ghostImageFilename[55] = (new StringBuilder()).append(path).append("cyans3.gif").toString();
        ghostImageFilename[56] = (new StringBuilder()).append(path).append("cyans4.gif").toString();
        ghostImageFilename[57] = (new StringBuilder()).append(path).append("cyanw1.gif").toString();
        ghostImageFilename[58] = (new StringBuilder()).append(path).append("cyanw2.gif").toString();
        ghostImageFilename[59] = (new StringBuilder()).append(path).append("cyanw3.gif").toString();
        ghostImageFilename[60] = (new StringBuilder()).append(path).append("cyanw4.gif").toString();
        ghostImageFilename[61] = (new StringBuilder()).append(path).append("cyane1.gif").toString();
        ghostImageFilename[62] = (new StringBuilder()).append(path).append("cyane2.gif").toString();
        ghostImageFilename[63] = (new StringBuilder()).append(path).append("cyane3.gif").toString();
        ghostImageFilename[64] = (new StringBuilder()).append(path).append("cyane4.gif").toString();
        ghostImageFilename[65] = (new StringBuilder()).append(path).append("scaredn1.gif").toString();
        ghostImageFilename[66] = (new StringBuilder()).append(path).append("scaredn2.gif").toString();
        ghostImageFilename[67] = (new StringBuilder()).append(path).append("scaredn3.gif").toString();
        ghostImageFilename[68] = (new StringBuilder()).append(path).append("scaredn4.gif").toString();
        ghostImageFilename[69] = (new StringBuilder()).append(path).append("scareds1.gif").toString();
        ghostImageFilename[70] = (new StringBuilder()).append(path).append("scareds2.gif").toString();
        ghostImageFilename[71] = (new StringBuilder()).append(path).append("scareds3.gif").toString();
        ghostImageFilename[72] = (new StringBuilder()).append(path).append("scareds4.gif").toString();
        ghostImageFilename[73] = (new StringBuilder()).append(path).append("scaredw1.gif").toString();
        ghostImageFilename[74] = (new StringBuilder()).append(path).append("scaredw2.gif").toString();
        ghostImageFilename[75] = (new StringBuilder()).append(path).append("scaredw3.gif").toString();
        ghostImageFilename[76] = (new StringBuilder()).append(path).append("scaredw4.gif").toString();
        ghostImageFilename[77] = (new StringBuilder()).append(path).append("scarede1.gif").toString();
        ghostImageFilename[78] = (new StringBuilder()).append(path).append("scarede2.gif").toString();
        ghostImageFilename[79] = (new StringBuilder()).append(path).append("scarede3.gif").toString();
        ghostImageFilename[80] = (new StringBuilder()).append(path).append("scarede4.gif").toString();
        ghostImageFilename[81] = (new StringBuilder()).append(path).append("scared2n1.gif").toString();
        ghostImageFilename[82] = (new StringBuilder()).append(path).append("scared2n2.gif").toString();
        ghostImageFilename[83] = (new StringBuilder()).append(path).append("scared2n3.gif").toString();
        ghostImageFilename[84] = (new StringBuilder()).append(path).append("scared2n4.gif").toString();
        ghostImageFilename[85] = (new StringBuilder()).append(path).append("scared2s1.gif").toString();
        ghostImageFilename[86] = (new StringBuilder()).append(path).append("scared2s2.gif").toString();
        ghostImageFilename[87] = (new StringBuilder()).append(path).append("scared2s3.gif").toString();
        ghostImageFilename[88] = (new StringBuilder()).append(path).append("scared2s4.gif").toString();
        ghostImageFilename[89] = (new StringBuilder()).append(path).append("scared2w1.gif").toString();
        ghostImageFilename[90] = (new StringBuilder()).append(path).append("scared2w2.gif").toString();
        ghostImageFilename[91] = (new StringBuilder()).append(path).append("scared2w3.gif").toString();
        ghostImageFilename[92] = (new StringBuilder()).append(path).append("scared2w4.gif").toString();
        ghostImageFilename[93] = (new StringBuilder()).append(path).append("scared2e1.gif").toString();
        ghostImageFilename[94] = (new StringBuilder()).append(path).append("scared2e2.gif").toString();
        ghostImageFilename[95] = (new StringBuilder()).append(path).append("scared2e3.gif").toString();
        ghostImageFilename[96] = (new StringBuilder()).append(path).append("scared2e4.gif").toString();
        ghostImageFilename[97] = (new StringBuilder()).append(path).append("eyesn1.gif").toString();
        ghostImageFilename[98] = (new StringBuilder()).append(path).append("eyesn2.gif").toString();
        ghostImageFilename[99] = (new StringBuilder()).append(path).append("eyesn3.gif").toString();
        ghostImageFilename[100] = (new StringBuilder()).append(path).append("eyesn4.gif").toString();
        ghostImageFilename[101] = (new StringBuilder()).append(path).append("eyess1.gif").toString();
        ghostImageFilename[102] = (new StringBuilder()).append(path).append("eyess2.gif").toString();
        ghostImageFilename[103] = (new StringBuilder()).append(path).append("eyess3.gif").toString();
        ghostImageFilename[104] = (new StringBuilder()).append(path).append("eyess4.gif").toString();
        ghostImageFilename[105] = (new StringBuilder()).append(path).append("eyesw1.gif").toString();
        ghostImageFilename[106] = (new StringBuilder()).append(path).append("eyesw2.gif").toString();
        ghostImageFilename[107] = (new StringBuilder()).append(path).append("eyesw3.gif").toString();
        ghostImageFilename[108] = (new StringBuilder()).append(path).append("eyesw4.gif").toString();
        ghostImageFilename[109] = (new StringBuilder()).append(path).append("eyese1.gif").toString();
        ghostImageFilename[110] = (new StringBuilder()).append(path).append("eyese2.gif").toString();
        ghostImageFilename[111] = (new StringBuilder()).append(path).append("eyese3.gif").toString();
        ghostImageFilename[112] = (new StringBuilder()).append(path).append("eyese4.gif").toString();
    }

    public static void setLevel(int l)
    {
        level = l;
        if(level <= 5)
            SCARED_SPEED = 2;
        else
            SCARED_SPEED = 3;
    }

    public static void setSpeed(double s)
    {
        game_speed = s;
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

    public int getGhostFrame()
    {
        int num = getDirection();
        num *= 4;
        num += frame;
        if(!scared && !dead)
            num += colour * 16;
        if(scared)
            if(scaredTime > 50)
                num += 64;
            else
            if(scaredTime % 10 < 5)
                num += 64;
            else
                num += 80;
        if(dead)
            num += 96;
        return num;
    }

    public void getScared(Maze theMaze, int t)
    {
        if(leavingHouse)
        {
            speed = SCARED_SPEED;
            scared = true;
            scaredTime = t;
            return;
        }
        if(homeTime > 0)
        {
            homeTime = homeTime + t;
            speed = SCARED_SPEED;
            scared = true;
            scaredTime = t;
            return;
        }
        dx = -dx / speed;
        dy = -dy / speed;
        olddx = dx;
        olddy = dy;
        scared = true;
        scaredTime = t;
        speed = SCARED_SPEED;
        dx = dx * speed;
        dy = dy * speed;
        if(!checkMove(theMaze))
            bestDirection(theMaze);
    }

    public void recalcSpeed()
    {
        if(dx != 0)
            dx = dx / Math.abs(dx);
        if(dy != 0)
            dy = dy / Math.abs(dy);
        dx = dx * speed;
        dy = dy * speed;
    }

    public void moveGhost(Maze theMaze, Pacman player)
    {
        int a = x / 16;
        int b = y / 16;
        if(dead)
            canPassDoor = true;
        if(scared)
        {
            scaredTime--;
            if(scaredTime == 0)
            {
                scared = false;
                speed = defaultSpeed;
                recalcSpeed();
            }
        }
        olddx = dx;
        olddy = dy;
        frame++;
        if(frame > 4)
            frame = 1;
        nodeCloseCheck();
        if(pausedTime > 0)
        {
            pausedTime--;
            if(pausedTime == 0)
            {
                fear = false;
                shock = false;
            }
            if(shock)
                return;
        } else
        if(scared)
        {
            double rnd = Math.random();
            if(rnd < 0.0050000000000000001D * (1.0D / (double)noOfGhosts))
            {
                fear = true;
                pausedTime = 20;
                SoundManager.playSound(SoundManager.FEAR);
            }
        }
        if(canPassDoor && y == (theMaze.getMazeY() / 2) * 16)
            canPassDoor = false;
        if(dead && a >= (theMaze.getMazeX() - 1) / 2 && a <= (theMaze.getMazeX() + 1) / 2 && b >= (theMaze.getMazeY() - 1) / 2 && b <= (theMaze.getMazeY() + 1) / 2)
        {
            canPassDoor = true;
            speed = defaultSpeed;
            dx = 0;
            dy = -speed;
            dead = false;
            scared = false;
            homeTime = 0;
            x = ((theMaze.getMazeX() - 1) * 16) / 2;
            y = (theMaze.getMazeY() * 16) / 2;
            pausedTime = 20;
            shock = true;
        }
        if(homeTime == 0 && !dead && a > theMaze.getMazeX() / 2 - 3 && a <= theMaze.getMazeX() / 2 + 2 && b >= theMaze.getMazeY() / 2 - 2 && b <= theMaze.getMazeY() / 2 + 2)
        {
            canPassDoor = true;
            leavingHouse = true;
            dy = -speed;
            dx = 0;
            if(Math.abs(x - ((theMaze.getMazeX() - 2) * 16) / 2) < 16)
            {
                x = ((theMaze.getMazeX() - 2) * 16) / 2;
                dx = 0;
                dy = -speed;
            }
            if(x < ((theMaze.getMazeX() - 2) * 16) / 2)
            {
                dx = 0;
                dy = -speed;
                x = ((theMaze.getMazeX() - 2) * 16) / 2;
            } else
            if(x > (theMaze.getMazeX() * 16) / 2)
            {
                dx = 0;
                dy = -speed;
                x = (theMaze.getMazeX() * 16) / 2;
            }
        }
        if(homeTime > 0)
        {
            homeTime--;
            if(y <= (theMaze.getMazeY() / 2) * 16 && dy < 0)
                dy = -dy;
            else
            if(y >= (theMaze.getMazeY() / 2 + 2) * 16 && dy > 0)
                dy = -dy;
            y = y + dy;
        }
        if(homeTime > 0)
            return;
        if(x >= ((theMaze.getMazeX() - 2) * 16) / 2 && x <= (theMaze.getMazeX() * 16) / 2 && y == (theMaze.getMazeY() / 2 - 3) * 16 && dy < 0 && !dead)
        {
            double rnd = Math.random();
            if(rnd < 0.5D)
            {
                dx = -speed;
                dy = 0;
            } else
            {
                dx = speed;
                dy = 0;
            }
            canPassDoor = false;
            leavingHouse = false;
        }
        nodeCheck(theMaze, player);
        if(!checkMove(theMaze))
            bestDirection(theMaze);
        if(checkCheat())
            bestDirection(theMaze);
        x = x + dx;
        y = y + dy;
        if(x < 0)
            x = (theMaze.getMazeX() - 1) * 16;
        if(x > (theMaze.getMazeX() - 1) * 16)
            x = 0;
    }

    public boolean checkCheat()
    {
        if(homeTime != 0)
            return false;
        if(olddy == dy && olddx == dx)
            return false;
        return olddy == dy || olddx == dx;
    }

    public void bestDirection(Maze theMaze)
    {
        if(dx < 0)
        {
            dx = 0;
            dy = -speed;
            if(checkMove(theMaze))
                return;
            dx = 0;
            dy = speed;
            if(checkMove(theMaze))
                return;
            dx = speed;
            dy = 0;
            if(checkMove(theMaze))
                return;
        } else
        if(dx > 0)
        {
            dx = 0;
            dy = -speed;
            if(checkMove(theMaze))
                return;
            dx = 0;
            dy = speed;
            if(checkMove(theMaze))
                return;
            dx = -speed;
            dy = 0;
            if(checkMove(theMaze))
                return;
        } else
        if(dy < 0)
        {
            dx = -speed;
            dy = 0;
            if(checkMove(theMaze))
                return;
            dx = speed;
            dy = 0;
            if(checkMove(theMaze))
                return;
            dx = 0;
            dy = speed;
            if(checkMove(theMaze))
                return;
        } else
        if(dy > 0)
        {
            dx = -speed;
            dy = 0;
            if(checkMove(theMaze))
                return;
            dx = speed;
            dy = 0;
            if(checkMove(theMaze))
                return;
            dx = 0;
            dy = -speed;
            if(checkMove(theMaze))
                return;
        }
    }

    public boolean checkMove(Maze theMaze)
    {
        if(x % 16 != 0 && y % 16 == 0 || x % 16 == 0 && y % 16 != 0)
            return true;
        if((x + dx) % 16 != 0 && (y + dy) % 16 != 0)
            return false;
        int tx = x / 16;
        int ty = y / 16;
        if(dx > 0)
            tx++;
        if(dx < 0)
            tx--;
        if(dy > 0)
            ty++;
        if(dy < 0)
            ty--;
        if(tx < 0 || tx >= theMaze.getMazeX())
            return true;
        if(ty < 0 || ty >= theMaze.getMazeY())
            return false;
        if(theMaze.wall(tx, ty))
            return false;
        return theMaze.getBlock(tx, ty) != 30 || canPassDoor || dead;
    }

    public void nodeCloseCheck()
    {
        if(homeTime > 0)
            return;
        int range = speed - 1;
        int i = x % 16;
        int j = y % 16;
        if(i <= range)
            x = x - i;
        else
        if(i >= 16 - range)
            x = x + (16 - i);
        if(j <= range)
            y = y - j;
        else
        if(j >= 16 - range)
            y = y + (16 - j);
    }

    public char randomDirection()
    {
        char d = 'a';
        double rnd = Math.random();
        if(rnd < 0.25D)
            d = 'l';
        else
        if(rnd < 0.5D)
            d = 'r';
        else
        if(rnd < 0.75D)
            d = 'u';
        else
            d = 'd';
        return d;
    }

    public char ghostDir(Maze theMaze, Pacman player)
    {
        char d = 'a';
        if(dx < 0)
            d = 'l';
        if(dx > 0)
            d = 'r';
        if(dy < 0)
            d = 'u';
        if(dy > 0)
            d = 'd';
        if(leavingHouse)
            return d;
        if(dead)
        {
            char ghostdir = theMaze.getGraph().newDir(d, x / 16, y / 16, theMaze.getMazeX() / 2, theMaze.getMazeY() / 2);
            return ghostdir;
        }
        if(AItype == 0)
        {
            char ghostdir = theMaze.getGraph().newDir(d, x / 16, y / 16, player.x / 16, player.y / 16);
            return ghostdir;
        }
        if(AItype == 1)
        {
            double rnd = Math.random();
            if(rnd < 0.10000000000000001D)
            {
                char ghostdir = theMaze.getGraph().newDir(d, x / 16, y / 16, player.x / 16, player.y / 16);
                return ghostdir;
            } else
            {
                char ghostdir = randomDirection();
                return ghostdir;
            }
        }
        if(AItype == 2)
        {
            if(!sameQuadrant(theMaze, player))
            {
                char ghostdir = moveToQuadrant(theMaze, player);
                return ghostdir;
            }
            double rnd = Math.random();
            if(rnd < 0.10000000000000001D)
            {
                char ghostdir = theMaze.getGraph().newDir(d, x / 16, y / 16, player.x / 16, player.y / 16);
                return ghostdir;
            } else
            {
                char ghostdir = randomDirection();
                return ghostdir;
            }
        }
        if(AItype == 3)
        {
            double rnd = Math.random();
            if(rnd < 0.10000000000000001D)
            {
                char ghostdir = theMaze.getGraph().newDir(d, x / 16, y / 16, player.x / 16, player.y / 16);
                return ghostdir;
            }
            rnd = Math.random();
            if(rnd < 0.5D)
            {
                char ghostdir = theMaze.getGraph().newDir(d, x / 16, y / 16, player.x / 16, player.y / 16);
                ghostdir = invertDirection(ghostdir);
                return ghostdir;
            } else
            {
                char ghostdir = randomDirection();
                return ghostdir;
            }
        } else
        {
            return d;
        }
    }

    public char invertDirection(char a)
    {
        char b = 'a';
        if(a == 'u')
            b = 'd';
        if(a == 'd')
            b = 'u';
        if(a == 'l')
            b = 'r';
        if(a == 'r')
            b = 'l';
        return b;
    }

    public char moveToQuadrant(Maze theMaze, Pacman player)
    {
        int a = getQuadrant(theMaze, player.x, player.y);
        int i;
        int j;
        if(a == 1)
        {
            i = 2;
            j = 2;
        } else
        if(a == 2)
        {
            i = theMaze.getMazeX() - 3;
            j = 2;
        } else
        if(a == 3)
        {
            i = 2;
            j = theMaze.getMazeY() - 3;
        } else
        {
            i = theMaze.getMazeX() - 3;
            j = theMaze.getMazeY() - 3;
        }
        char d = 'a';
        if(dx < 0)
            d = 'l';
        if(dx > 0)
            d = 'r';
        if(dy < 0)
            d = 'u';
        if(dy > 0)
            d = 'd';
        char ghostdir = theMaze.getGraph().newDir(d, x / 16, y / 16, i, j);
        return ghostdir;
    }

    public boolean sameQuadrant(Maze theMaze, Pacman player)
    {
        int a = getQuadrant(theMaze, player.x, player.y);
        int b = getQuadrant(theMaze, x, y);
        return a == b;
    }

    public int getQuadrant(Maze theMaze, int i, int j)
    {
        if(i < ((theMaze.getMazeX() - 1) / 2) * 16)
            return j >= ((theMaze.getMazeY() - 1) / 2) * 16 ? 3 : 1;
        return j >= ((theMaze.getMazeY() - 1) / 2) * 16 ? 4 : 2;
    }

    public void nodeCheck(Maze theMaze, Pacman player)
    {
        if(homeTime != 0)
            return;
        if(x % 16 != 0 || y % 16 != 0)
            return;
        char d = 'a';
        if(dx < 0)
            d = 'l';
        if(dx > 0)
            d = 'r';
        if(dy < 0)
            d = 'u';
        if(dy > 0)
            d = 'd';
        char ghostdir = ghostDir(theMaze, player);
        if(d != ghostdir)
            if(!scared)
            {
                if(ghostdir == 'u')
                {
                    dx = 0;
                    dy = -speed;
                } else
                if(ghostdir == 'd')
                {
                    dx = 0;
                    dy = speed;
                } else
                if(ghostdir == 'l')
                {
                    dx = -speed;
                    dy = 0;
                } else
                if(ghostdir == 'r')
                {
                    dx = speed;
                    dy = 0;
                }
            } else
            if(scared)
                if(ghostdir == 'u')
                {
                    dx = 0;
                    dy = speed;
                } else
                if(ghostdir == 'd')
                {
                    dx = 0;
                    dy = -speed;
                } else
                if(ghostdir == 'l')
                {
                    dx = speed;
                    dy = 0;
                } else
                if(ghostdir == 'r')
                {
                    dx = -speed;
                    dy = 0;
                }
    }

    static final int BLOCKSIZE = 16;
    static final int INITIAL_SPEED = 4;
    static final int SPEED = 4;
    static final double FAST_SPEED = 1.25D;
    static final double SLOW_SPEED = 0.90000000000000002D;
    static final double RANDOM_AI = 0.10000000000000001D;
    static final double FEAR_CHANCE = 0.0050000000000000001D;
    static final int FEAR_TIME = 20;
    static int SCARED_SPEED = 2;
    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    static final int FRAMES = 4;
    static final int FIRST_FRAME = 1;
    static final int GHOST_FRAMES = 113;
    static final int SINGLE_GHOST_FRAMES = 16;
    static final int RED = 0;
    static final int PURPLE = 1;
    static final int GREEN = 2;
    static final int CYAN = 3;
    static final int SCARED = 4;
    static final int SCARED2 = 5;
    static final int EYES = 6;
    static final int FOLLOWS = 0;
    static final int FAST = 1;
    static final int QUADRANT = 2;
    static final int STUPID = 3;
    static int ghostCount = 0;
    static int timeCount = 100;
    static int gameFrame = 0;
    static int level = 1;
    static double game_speed = 1.0D;
    int x;
    int y;
    int dir;
    int dx;
    int dy;
    int olddx;
    int olddy;
    int frame;
    int AItype;
    int colour;
    int defaultSpeed;
    int speed;
    int homeTime;
    int scaredTime;
    boolean scared;
    boolean dead;
    boolean canPassDoor;
    boolean leavingHouse;
    boolean fear;
    boolean shock;
    int pausedTime;
    static String ghostImageFilename[];
    static int noOfGhosts = 4;

}
