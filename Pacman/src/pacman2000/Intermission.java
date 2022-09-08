// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Intermission.java

package pacman2000;


// Referenced classes of package pacman2000:
//            SoundManager

public class Intermission
{

    public Intermission()
    {
        frameNo = 0;
        interInit();
    }

    public void interInit()
    {
        SoundManager.loadAllSounds();
        String path = "inter/";
        interImageFilename = new String[61];
        interImageFilename[0] = (new StringBuilder()).append(path).append("quake/quake1.jpg").toString();
        interImageFilename[1] = (new StringBuilder()).append(path).append("quake/quake2.jpg").toString();
        interImageFilename[2] = (new StringBuilder()).append(path).append("quake/quake3.jpg").toString();
        interImageFilename[3] = (new StringBuilder()).append(path).append("generic/right_big1.gif").toString();
        interImageFilename[4] = (new StringBuilder()).append(path).append("generic/right_big2.gif").toString();
        interImageFilename[5] = (new StringBuilder()).append(path).append("generic/right_big3.gif").toString();
        interImageFilename[6] = (new StringBuilder()).append(path).append("generic/right_big4.gif").toString();
        interImageFilename[7] = (new StringBuilder()).append(path).append("generic/right_big5.gif").toString();
        interImageFilename[8] = (new StringBuilder()).append(path).append("generic/right_big6.gif").toString();
        interImageFilename[9] = (new StringBuilder()).append(path).append("generic/right_big7.gif").toString();
        interImageFilename[10] = (new StringBuilder()).append(path).append("quake/quad1.gif").toString();
        interImageFilename[11] = (new StringBuilder()).append(path).append("quake/quad2.gif").toString();
        interImageFilename[12] = (new StringBuilder()).append(path).append("quake/quad3.gif").toString();
        interImageFilename[13] = (new StringBuilder()).append(path).append("quake/quad4.gif").toString();
        interImageFilename[14] = (new StringBuilder()).append(path).append("quake/quad5.gif").toString();
        interImageFilename[15] = (new StringBuilder()).append(path).append("quake/quad6.gif").toString();
        interImageFilename[16] = (new StringBuilder()).append(path).append("quake/rambo_left1.gif").toString();
        interImageFilename[17] = (new StringBuilder()).append(path).append("quake/rambo_left2.gif").toString();
        interImageFilename[18] = (new StringBuilder()).append(path).append("quake/rambo_left3.gif").toString();
        interImageFilename[19] = (new StringBuilder()).append(path).append("quake/rambo_left4.gif").toString();
        interImageFilename[20] = (new StringBuilder()).append(path).append("quake/rambo_left5.gif").toString();
        interImageFilename[21] = (new StringBuilder()).append(path).append("quake/rambo_left6.gif").toString();
        interImageFilename[22] = (new StringBuilder()).append(path).append("quake/rambo_left7.gif").toString();
        interImageFilename[23] = (new StringBuilder()).append(path).append("generic/pink_right1.gif").toString();
        interImageFilename[24] = (new StringBuilder()).append(path).append("generic/pink_right2.gif").toString();
        interImageFilename[25] = (new StringBuilder()).append(path).append("generic/pink_right3.gif").toString();
        interImageFilename[26] = (new StringBuilder()).append(path).append("generic/pink_right4.gif").toString();
        interImageFilename[27] = (new StringBuilder()).append(path).append("generic/pink_right5.gif").toString();
        interImageFilename[28] = (new StringBuilder()).append(path).append("generic/pink_right6.gif").toString();
        interImageFilename[29] = (new StringBuilder()).append(path).append("generic/pink_right7.gif").toString();
        interImageFilename[30] = (new StringBuilder()).append(path).append("generic/red_right1.gif").toString();
        interImageFilename[31] = (new StringBuilder()).append(path).append("generic/red_right2.gif").toString();
        interImageFilename[32] = (new StringBuilder()).append(path).append("generic/red_right3.gif").toString();
        interImageFilename[33] = (new StringBuilder()).append(path).append("generic/red_right4.gif").toString();
        interImageFilename[34] = (new StringBuilder()).append(path).append("generic/red_right5.gif").toString();
        interImageFilename[35] = (new StringBuilder()).append(path).append("generic/red_right6.gif").toString();
        interImageFilename[36] = (new StringBuilder()).append(path).append("generic/red_right7.gif").toString();
        interImageFilename[37] = (new StringBuilder()).append(path).append("generic/green_right1.gif").toString();
        interImageFilename[38] = (new StringBuilder()).append(path).append("generic/green_right2.gif").toString();
        interImageFilename[39] = (new StringBuilder()).append(path).append("generic/green_right3.gif").toString();
        interImageFilename[40] = (new StringBuilder()).append(path).append("generic/green_right4.gif").toString();
        interImageFilename[41] = (new StringBuilder()).append(path).append("generic/green_right5.gif").toString();
        interImageFilename[42] = (new StringBuilder()).append(path).append("generic/green_right6.gif").toString();
        interImageFilename[43] = (new StringBuilder()).append(path).append("generic/green_right7.gif").toString();
        interImageFilename[44] = (new StringBuilder()).append(path).append("beach/sandcastle.gif").toString();
        interImageFilename[45] = (new StringBuilder()).append(path).append("beach/crabmove1.gif").toString();
        interImageFilename[46] = (new StringBuilder()).append(path).append("beach/crabmove2.gif").toString();
        interImageFilename[47] = (new StringBuilder()).append(path).append("beach/crabmove3.gif").toString();
        interImageFilename[48] = (new StringBuilder()).append(path).append("beach/crabmove4.gif").toString();
        interImageFilename[49] = (new StringBuilder()).append(path).append("beach/crab1.gif").toString();
        interImageFilename[50] = (new StringBuilder()).append(path).append("beach/crab2.gif").toString();
        interImageFilename[51] = (new StringBuilder()).append(path).append("beach/crab3.gif").toString();
        interImageFilename[52] = (new StringBuilder()).append(path).append("beach/crab4.gif").toString();
        interImageFilename[53] = (new StringBuilder()).append(path).append("beach/beach.jpg").toString();
        interImageFilename[54] = (new StringBuilder()).append(path).append("generic/left_big1.gif").toString();
        interImageFilename[55] = (new StringBuilder()).append(path).append("generic/left_big2.gif").toString();
        interImageFilename[56] = (new StringBuilder()).append(path).append("generic/left_big3.gif").toString();
        interImageFilename[57] = (new StringBuilder()).append(path).append("generic/left_big4.gif").toString();
        interImageFilename[58] = (new StringBuilder()).append(path).append("generic/left_big5.gif").toString();
        interImageFilename[59] = (new StringBuilder()).append(path).append("generic/left_big6.gif").toString();
        interImageFilename[60] = (new StringBuilder()).append(path).append("generic/left_big7.gif").toString();
    }

    public void interInit(int a)
    {
        if(a == 1)
        {
            SoundManager.playSound(SoundManager.WINDFLY);
            background_frame = 0;
            pac_x = -64;
            pac_y = 280;
            pac_frame = 3;
            pink_x = -450;
            pink_y = 270;
            pink_frame = 23;
            red_x = -490;
            red_y = 280;
            red_frame = 30;
            quad = true;
            quad_x = 560;
            quad_y = 270;
            quad_frame = 10;
            frameNo = 0;
        } else
        if(a == 2)
        {
            background_frame = 53;
            pac_x = -64;
            pac_y = 280;
            pac_frame = 3;
            pink_x = -450;
            pink_y = 285;
            pink_frame = 23;
            red_x = -490;
            red_y = 295;
            red_frame = 30;
            green_x = -530;
            green_y = 290;
            green_frame = 37;
            crab_x = 480;
            crab_y = 150;
            crab_frame = 45;
            sand_x = 480;
            sand_y = 240;
            sand_frame = 44;
            safe = -1;
        }
    }

    public void interProcess(int a)
    {
        if(a == 1)
        {
            frameNo++;
            if(frameNo > 4)
            {
                background_frame++;
                if(background_frame > 2)
                    background_frame = 0;
                quad_frame++;
                if(quad_frame > 15)
                    quad_frame = 10;
                frameNo = 0;
            }
            pac_frame++;
            if(pac_frame > 9)
                pac_frame = 3;
            pink_frame++;
            if(pink_frame > 29)
                pink_frame = 23;
            red_frame++;
            if(red_frame > 36)
                red_frame = 30;
            if(quad)
            {
                pac_x += 4;
                pink_x += 6;
                red_x += 6;
            } else
            {
                pac_x -= 4;
                pink_x -= 4;
                red_x -= 5;
            }
            if(pac_x > quad_x)
            {
                quad = false;
                SoundManager.playSound(SoundManager.QUAD);
            }
        } else
        if(a == 2)
        {
            frameNo++;
            pac_frame++;
            if(pac_frame > 9)
                pac_frame = 3;
            if(frameNo > 4)
            {
                frameNo = 0;
                crab_frame++;
                if(safe <= 0)
                {
                    if(crab_frame > 48)
                        crab_frame = 45;
                } else
                if(crab_frame > 52)
                    crab_frame = 49;
            }
            pink_frame++;
            if(pink_frame > 29)
                pink_frame = 23;
            red_frame++;
            if(red_frame > 36)
                red_frame = 30;
            green_frame++;
            if(green_frame > 43)
                green_frame = 37;
            if(safe == -1)
            {
                pac_x += 4;
                if(pac_x >= 500)
                    safe = 0;
            } else
            if(safe == 0)
            {
                if(pink_x > 640)
                {
                    safe = 1;
                    crab_frame = 49;
                    SoundManager.playSound(SoundManager.LAUGH);
                }
            } else
            if(safe == 1)
                pac_x -= 4;
            pink_x += 6;
            red_x += 6;
            green_x += 6;
        }
    }

    static final int NO_OF_INTERS = 61;
    static final int QUAKE1 = 0;
    static final int QUAKE2 = 1;
    static final int QUAKE3 = 2;
    static final int PACMAN1 = 3;
    static final int PACMAN2 = 4;
    static final int PACMAN3 = 5;
    static final int PACMAN4 = 6;
    static final int PACMAN5 = 7;
    static final int PACMAN6 = 8;
    static final int PACMAN7 = 9;
    static final int PACMAN_FIRST = 3;
    static final int PACMAN_LAST = 9;
    static final int QUAD1 = 10;
    static final int QUAD2 = 11;
    static final int QUAD3 = 12;
    static final int QUAD4 = 13;
    static final int QUAD5 = 14;
    static final int QUAD6 = 15;
    static final int QUAD_FIRST = 10;
    static final int QUAD_LAST = 15;
    static final int RAMBO1 = 16;
    static final int RAMBO2 = 17;
    static final int RAMBO3 = 18;
    static final int RAMBO4 = 19;
    static final int RAMBO5 = 20;
    static final int RAMBO6 = 21;
    static final int RAMBO7 = 22;
    static final int RAMBO_FIRST = 16;
    static final int RAMBO_LAST = 22;
    static final int PINK1 = 23;
    static final int PINK2 = 24;
    static final int PINK3 = 25;
    static final int PINK4 = 26;
    static final int PINK5 = 27;
    static final int PINK6 = 28;
    static final int PINK7 = 29;
    static final int PINK_FIRST = 23;
    static final int PINK_LAST = 29;
    static final int RED1 = 30;
    static final int RED2 = 31;
    static final int RED3 = 32;
    static final int RED4 = 33;
    static final int RED5 = 34;
    static final int RED6 = 35;
    static final int RED7 = 36;
    static final int RED_FIRST = 30;
    static final int RED_LAST = 36;
    static final int GREEN1 = 37;
    static final int GREEN2 = 38;
    static final int GREEN3 = 39;
    static final int GREEN4 = 40;
    static final int GREEN5 = 41;
    static final int GREEN6 = 42;
    static final int GREEN7 = 43;
    static final int GREEN_FIRST = 37;
    static final int GREEN_LAST = 43;
    static final int SANDCASTLE = 44;
    static final int CRAB1 = 45;
    static final int CRAB2 = 46;
    static final int CRAB3 = 47;
    static final int CRAB4 = 48;
    static final int CRAB5 = 49;
    static final int CRAB6 = 50;
    static final int CRAB7 = 51;
    static final int CRAB8 = 52;
    static final int CRAB_FIRST = 45;
    static final int CRAB_LAST = 52;
    static final int BEACH = 53;
    static final int PACMANLEFT1 = 54;
    static final int PACMANLEFT2 = 55;
    static final int PACMANLEFT3 = 56;
    static final int PACMANLEFT4 = 57;
    static final int PACMANLEFT5 = 58;
    static final int PACMANLEFT6 = 59;
    static final int PACMANLEFT7 = 60;
    static final int PACMANLEFT_FIRST = 54;
    static final int PACMANLEFT_LAST = 60;
    static final int FRAME_WAIT = 4;
    static final int RAMBO_MODIFIER = 13;
    static final int LEFT_MODIFIER = 51;
    String interImageFilename[];
    int background_frame;
    int pac_x;
    int pac_y;
    int pac_frame;
    int quad_x;
    int quad_y;
    int quad_frame;
    int pink_x;
    int pink_y;
    int pink_frame;
    int red_x;
    int red_y;
    int red_frame;
    int green_x;
    int green_y;
    int green_frame;
    int sand_x;
    int sand_y;
    int sand_frame;
    int crab_x;
    int crab_y;
    int crab_frame;
    int safe;
    int frameNo;
    boolean quad;
}
