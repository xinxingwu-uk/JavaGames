// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Splash.java

package pacman2000;


// Referenced classes of package pacman2000:
//            Game, ScoreTable

public class Splash
{

    public Splash(Game game)
    {
        this.game = game;
        splashInit();
    }

    public void splashInit()
    {
        pac_x = -32;
        pac_y = 240;
        pac_frame = 0;
        background_x = 127;
        background_y = 155;
        background_frame = 7;
        scrollLeftText = "                         === PACMAN 2000 ===                         A Java version of Pacman, with randomly generated maze layouts                        Use arrow keys or Q, A, O and P to move                        Game engine by Daniel Fielding                        Random map generator by Dan Tomlinson                        AI Pathing by Chris Ball                        Intermissions and extra bells and whistles by Dave Godfrey                        Some research by Roger Taylor (where'd he go?!)                        Supervised by Graham Hutton";
        scrollRightText = (new StringBuilder()).append(game.scoreTable.getDisplayString()).append("                        ").append(" === HIGH SCORES === ").toString();
        resetScrollLeft();
        resetScrollRight();
        scrollLeftLimit = scrollLeftText.length() * 6;
        scrollRightLimit = scrollRightText.length() * 6;
        String path = "splash/";
        splashImageFilename = new String[11];
        splashImageFilename[0] = (new StringBuilder()).append(path).append("right1.gif").toString();
        splashImageFilename[1] = (new StringBuilder()).append(path).append("right2.gif").toString();
        splashImageFilename[2] = (new StringBuilder()).append(path).append("right3.gif").toString();
        splashImageFilename[3] = (new StringBuilder()).append(path).append("right4.gif").toString();
        splashImageFilename[4] = (new StringBuilder()).append(path).append("right5.gif").toString();
        splashImageFilename[5] = (new StringBuilder()).append(path).append("right6.gif").toString();
        splashImageFilename[6] = (new StringBuilder()).append(path).append("right7.gif").toString();
        splashImageFilename[7] = (new StringBuilder()).append(path).append("splash1.gif").toString();
        splashImageFilename[8] = (new StringBuilder()).append(path).append("splash2.gif").toString();
        splashImageFilename[9] = (new StringBuilder()).append(path).append("splash3.gif").toString();
        splashImageFilename[10] = (new StringBuilder()).append(path).append("splash_foreground.gif").toString();
    }

    private void resetScrollLeft()
    {
        scrollLeftX = 680;
    }

    private void resetScrollRight()
    {
        scrollRightX = -scrollRightLimit;
    }

    public void splashProcess()
    {
        pac_x += 4;
        pac_frame++;
        if(pac_frame > 6)
            pac_frame = 0;
        if(pac_x > 640)
            pac_x = -32;
        if(background_frame == 7 && pac_x >= 210)
            background_frame = 8;
        else
        if(background_frame == 8 && pac_x >= 406)
            background_frame = 9;
        scrollLeftX -= 2;
        if(scrollLeftX < -scrollLeftLimit)
            resetScrollLeft();
        scrollRightX += 2;
        if(scrollRightX > 1000)
            resetScrollRight();
    }

    static final int NO_OF_SPLASHES = 11;
    static final int PACMAN1 = 0;
    static final int PACMAN2 = 1;
    static final int PACMAN3 = 2;
    static final int PACMAN4 = 3;
    static final int PACMAN5 = 4;
    static final int PACMAN6 = 5;
    static final int PACMAN7 = 6;
    static final int PACMAN_FIRST = 0;
    static final int PACMAN_LAST = 6;
    static final int BG1 = 7;
    static final int BG2 = 8;
    static final int BG3 = 9;
    static final int FG = 10;
    String splashImageFilename[];
    int pac_x;
    int pac_y;
    int pac_frame;
    int background_frame;
    int background_x;
    int background_y;
    String scrollLeftText;
    int scrollLeftX;
    int scrollLeftLimit;
    String scrollRightText;
    int scrollRightX;
    int scrollRightLimit;
    private Game game;
}
