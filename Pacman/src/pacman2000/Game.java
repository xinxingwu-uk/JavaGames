// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Game.java

package pacman2000;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.PrintStream;
import java.util.Random;
import pacman2000.maze.Maze;

// Referenced classes of package pacman2000:
//            ScoreTable, Splash, Intermission, Ghost, 
//            Effect, Pacman, Fruit, Hiscore, 
//            IPacmanInstance, SoundManager, ImageLoader

public class Game
    implements Runnable
{

    public Game(IPacmanInstance instance)
    {
        this(instance, 40);
    }

    public Game(IPacmanInstance instance, int fps)
    {
        splashDisplay = 0;
        splashLoaded = false;
        level = 1;
        frameNumber = -1;
        fruitFrameNumber = 0;
        deathTimer = 0;
        winTimer = 0;
        ghostMunchCount = 0;
        bufferReady = false;
        lifeTimer = 0;
        dotSound = 0;
        energiserTime = 200;
        readWriteEnabled = true;
        minMazeWidth = 13;
        maxMazeWidth = 24;
        minMazeHeight = 13;
        maxMazeHeight = 17;
        suicide = false;
        paused = false;
        loadedMainImages = false;
        this.instance = instance;
        component = instance.getComponent();
        this.fps = fps;
        random = new Random(System.currentTimeMillis());
    }

    public void execute()
    {
        scoreTable = new ScoreTable("scores.hi", 10);
        if(readWriteEnabled)
            scoreTable.load();
        delay = fps <= 0 ? 100 : 1000 / fps;
        splash = new Splash(this);
        inter = new Intermission();
        splash();
        setup();
    }

    public void startAnimation()
    {
        animatorThread = new Thread(this);
        animatorThread.start();
    }

    public void stopAnimation()
    {
        animatorThread = null;
    }

    public void processKeyEvent(KeyEvent e)
    {
        if(e.getID() == 402)
            return;
        int keyInt = e.getKeyCode();
        if(keyInt == 0)
            return;
        lastKey = keyInt;
        lastKeyFrames = 10;
        if(frameNumber > 0)
        {
            if(lastKey == 27)
                suicide = true;
            if(lastKey == 19 && splashDisplay == 0)
                paused = !paused;
        }
    }

    private void updateScoreText()
    {
        scoretext = (new StringBuilder()).append("Score: ").append(player.score).append(" (").append(player.nextLifeScore).append(")").toString();
    }

    public void run()
    {
        lastKey = 0;
        lastKeyFrames = 0;
        long startTime = System.currentTimeMillis();
        Thread currentThread = Thread.currentThread();
        wait(1000);
        do
        {
            if(currentThread != animatorThread)
                break;
            if(paused)
            {
                try
                {
                    component.repaint();
                    Thread.sleep(100L);
                    continue;
                }
                catch(InterruptedException e) { }
                break;
            }
            frameNumber++;
            fruitFrameNumber++;
            lifeTimer++;
            if(lastKeyFrames > 0)
            {
                player.keyAct(lastKey, theMaze);
                lastKeyFrames--;
            }
            player.movePacMan(theMaze);
            dotCheck();
            if(player.score >= player.nextLifeScore)
            {
                player.lives++;
                player.displayLives++;
                player.nextLifeScore *= 2;
                SoundManager.playSound(SoundManager.EXTRA_LIFE);
                updateScoreText();
            }
            if(suicide && player.lives > 0)
            {
                if(frameNumber > 0)
                {
                    player.lives = 1;
                    player.displayLives = 1;
                    pacmanDeath();
                }
                suicide = false;
            }
            if(theMaze.getTileset() == 4 && lifeTimer > 5 && !player.dead)
                spawnBubble(player.x, player.y);
            Ghost.gameFrame++;
            for(int i = 0; i < Ghost.noOfGhosts; i++)
            {
                ghost[i].moveGhost(theMaze, player);
                if(ghost[i].dead)
                    spawnEffect(ghost[i].x - 8, ghost[i].y - 8, 4);
                else
                if(theMaze.getTileset() == 4 && lifeTimer > 5 && checkChance(50))
                    spawnBubble(ghost[i].x, ghost[i].y);
                if(ghost[i].shock && ghost[i].pausedTime == 20)
                {
                    spawnOverEffect(ghost[i].x - 8, ghost[i].y - 8, 92);
                    spawnOverEffect(ghost[i].x - 16, ghost[i].y, 92);
                    spawnOverEffect(ghost[i].x + 8, ghost[i].y - 4, 92);
                    spawnEffect(ghost[i].x - 16, ghost[i].y - 16, 4);
                    spawnEffect(ghost[i].x + 16, ghost[i].y + 16, 4);
                    spawnEffect(ghost[i].x - 8, ghost[i].y - 8, 4);
                    spawnEffect(ghost[i].x - 24, ghost[i].y - 16, 5);
                    spawnEffect(ghost[i].x + 8, ghost[i].y + 16, 5);
                }
            }

            if(fruit.fruitCheck())
            {
                fruit.x = ((theMaze.getMazeX() - 1) * 16) / 2;
                fruit.y = (theMaze.getMazeY() / 2 + 5) * 16;
                fruit.type = (int)(Math.random() * (double)Fruit.NUMBER_OF_FRUITS);
                if(fruit.type >= level)
                    fruit.type = level - 1;
                SoundManager.playSound(SoundManager.SPAWN);
                spawnEffect(fruit.x - 8, fruit.y - 8, 4);
            }
            collisionDetect();
            startTime += delay;
            long currentTime = System.currentTimeMillis();
            if(startTime < currentTime)
                startTime = currentTime;
            else
                try
                {
                    Thread.sleep(Math.max(0L, startTime - currentTime));
                }
                catch(InterruptedException e)
                {
                    break;
                }
            if(frameNumber <= 0)
                winTimer = 0;
            component.repaint();
            if(winTimer > 0)
            {
                if(winTimer == 1)
                    wait(2000);
                if(winTimer >= 2)
                    levelUp();
                winTimer++;
            }
            processEffects();
            if(frameNumber == 0)
            {
                wait(500);
                SoundManager.playSound(SoundManager.START);
                wait(4500);
            } else
            if(lifeTimer == 0)
                wait(1000);
        } while(true);
    }

    private void draw(Image image, int x, int y)
    {
        bufferGraphics.drawImage(image, x, y, component);
    }

    private void paintPacman(boolean rambo)
    {
        int frame = player.getPacFrame(rambo);
        bufferGraphics.drawImage(pacImages[frame], player.x - 8, player.y - 8, component);
        if(player.x - 16 < 0)
            draw(pacImages[frame], (player.x - 8) + (theMaze.getMazeX() - 1) * 16, player.y - 8);
        else
        if(player.x + 16 + 8 > (theMaze.getMazeX() - 1) * 16)
            draw(pacImages[frame], player.x - 8 - (theMaze.getMazeX() - 1) * 16, player.y - 8);
    }

    private void paintGhost(int ghostIndex)
    {
        int i = ghostIndex;
        int frame = ghost[i].getGhostFrame();
        draw(ghostImages[frame], ghost[i].x - 8, ghost[i].y - 8);
        if(ghost[i].shock)
            draw(effectImages[91], ghost[i].x - 40, ghost[i].y - 40);
        else
        if(ghost[i].fear)
            draw(effectImages[89], ghost[i].x - 40, ghost[i].y - 40);
        if(ghost[i].x - 16 < 0)
            draw(ghostImages[frame], (ghost[i].x - 8) + (theMaze.getMazeX() - 1) * 16, ghost[i].y - 8);
        else
        if(ghost[i].x + 16 + 8 > (theMaze.getMazeX() - 1) * 16)
            draw(ghostImages[frame], ghost[i].x - 8 - (theMaze.getMazeX() - 1) * 16, ghost[i].y - 8);
    }

    public void updateBuffer()
    {
        if(!bufferReady)
            return;
        if(frameNumber < 0)
            return;
        if(splashDisplay == 0)
        {
            if(winTimer == 2)
            {
                bufferGraphics.setColor(Color.black);
                bufferGraphics.fillRect(0, 0, dim.width, dim.height);
                bufferGraphics.drawImage(effectImages[45], (theMaze.getMazeX() * 16) / 2 - Effect.effectWidth[45] / 2, (theMaze.getMazeY() * 16) / 2 - Effect.effectHeight[45] / 2, component);
                return;
            }
            bufferGraphics.setColor(Color.black);
            bufferGraphics.drawImage(maze, 0, 0, component);
            if(fruit.type != -1)
                bufferGraphics.drawImage(fruitImages[fruit.type], fruit.x - 8, fruit.y - 8, component);
            for(int i = 0; i < 50; i++)
                if(effect[i].type != -1)
                    bufferGraphics.drawImage(effectImages[effect[i].type], effect[i].x, effect[i].y, component);

            boolean rambo = false;
            for(int i = 0; i < Ghost.noOfGhosts; i++)
                if(ghost[i].scared)
                {
                    paintGhost(i);
                    rambo = true;
                }

            paintPacman(rambo);
            for(int i = 0; i < Ghost.noOfGhosts; i++)
                if(!ghost[i].scared)
                    paintGhost(i);

            for(int i = 0; i < 50; i++)
                if(overEffect[i].type != -1)
                    bufferGraphics.drawImage(effectImages[overEffect[i].type], overEffect[i].x, overEffect[i].y, component);

            if(lifeTimer <= 1)
                bufferGraphics.drawImage(effectImages[0], (theMaze.getMazeX() * 16) / 2 - Effect.effectWidth[0] / 2, (theMaze.getMazeY() * 16) / 2 - Effect.effectHeight[0] / 2, component);
            if(lifeTimer <= 10 && lifeTimer > 1)
                bufferGraphics.drawImage(effectImages[1], (theMaze.getMazeX() * 16) / 2 - Effect.effectWidth[1] / 2, (theMaze.getMazeY() * 16) / 2 - Effect.effectHeight[1] / 2, component);
            if(lifeTimer <= 15 && lifeTimer > 10)
                bufferGraphics.drawImage(effectImages[2], (theMaze.getMazeX() * 16) / 2 - Effect.effectWidth[2] / 2, (theMaze.getMazeY() * 16) / 2 - Effect.effectHeight[2] / 2, component);
            if(lifeTimer <= 20 && lifeTimer > 15)
                bufferGraphics.drawImage(effectImages[3], (theMaze.getMazeX() * 16) / 2 - Effect.effectWidth[3] / 2, (theMaze.getMazeY() * 16) / 2 - Effect.effectHeight[3] / 2, component);
            if(winTimer > 0)
                bufferGraphics.drawImage(effectImages[44], (theMaze.getMazeX() * 16) / 2 - Effect.effectWidth[44] / 2, (theMaze.getMazeY() * 16) / 2 - Effect.effectHeight[44] / 2, component);
            if(player.lives == 0)
                bufferGraphics.drawImage(effectImages[46], (theMaze.getMazeX() * 16) / 2 - Effect.effectWidth[46] / 2, (theMaze.getMazeY() * 16) / 2 - Effect.effectHeight[46] / 2, component);
            bufferGraphics.setColor(Color.white);
            bufferGraphics.drawString(scoretext, 16, dim.height - 28);
            bufferGraphics.drawString((new StringBuilder()).append("Level: ").append(level).toString(), 16, dim.height - 12);
            for(int i = 0; i < player.displayLives; i++)
            {
                int frame;
                if(i == 0)
                    frame = player.getPacFrame(rambo);
                else
                    frame = 25;
                bufferGraphics.drawImage(pacImages[frame], dim.width - (i + 1) * 3 * 16, dim.height - 42, component);
            }

            if(paused)
            {
                bufferGraphics.setColor(Color.white);
                bufferGraphics.drawString("PAUSED", 8, 18);
            }
        } else
        if(splashDisplay == -1)
        {
            bufferGraphics.drawImage(splashImages[splash.background_frame], 0, 0, component);
            bufferGraphics.drawImage(splashImages[splash.pac_frame], splash.pac_x, splash.pac_y, component);
            bufferGraphics.setColor(Color.ORANGE);
            bufferGraphics.drawString(splash.scrollLeftText, splash.scrollLeftX, 420);
            bufferGraphics.setColor(Color.YELLOW);
            bufferGraphics.drawString(splash.scrollRightText, splash.scrollRightX, 445);
            bufferGraphics.drawImage(splashImages[10], 0, 0, component);
        } else
        if(splashDisplay == 1)
        {
            bufferGraphics.drawImage(interImages[inter.background_frame], 0, 0, component);
            if(inter.quad)
                bufferGraphics.drawImage(interImages[inter.quad_frame], inter.quad_x, inter.quad_y, component);
            bufferGraphics.drawImage(interImages[inter.pink_frame], inter.pink_x, inter.pink_y, component);
            bufferGraphics.drawImage(interImages[inter.red_frame], inter.red_x, inter.red_y, component);
            if(inter.quad)
                draw(interImages[inter.pac_frame], inter.pac_x, inter.pac_y);
            else
                draw(interImages[inter.pac_frame + 13], inter.pac_x, inter.pac_y);
        } else
        if(splashDisplay == 2)
        {
            draw(interImages[inter.background_frame], 0, 0);
            if(inter.safe <= 0)
                draw(interImages[inter.pac_frame], inter.pac_x, inter.pac_y);
            else
                draw(interImages[inter.pac_frame + 51], inter.pac_x, inter.pac_y);
            draw(interImages[inter.sand_frame], inter.sand_x, inter.sand_y);
            draw(interImages[inter.crab_frame], inter.crab_x, inter.crab_y);
            draw(interImages[inter.pink_frame], inter.pink_x, inter.pink_y);
            draw(interImages[inter.red_frame], inter.red_x, inter.red_y);
            draw(interImages[inter.green_frame], inter.green_x, inter.green_y);
        } else
        if(splashDisplay == -2)
        {
            draw(effectImages[96], frameNumber, 0);
            draw(effectImages[96], frameNumber + Effect.effectWidth[96], 0);
            draw(effectImages[96], frameNumber - Effect.effectWidth[96], 0);
            draw(effectImages[95], 0, 0);
            draw(effectImages[97], 144 + hiscore.x, 128 + hiscore.y);
            bufferGraphics.setColor(Color.yellow);
            bufferGraphics.drawString(hiscore.name, 200, 105);
        }
    }

    public void paint(Graphics g)
    {
        if(!bufferReady)
            return;
        if(frameNumber < 0)
        {
            return;
        } else
        {
            updateBuffer();
            instance.drawBufferToScreen(g, buffer);
            return;
        }
    }

    public void loadAllImages()
    {
        int total = 0;
        total += 31;
        wallImages = new Image[31];
        MediaTracker tracker = new MediaTracker(component);
        for(int i = 0; i < 31; i++)
        {
            wallImages[i] = ImageLoader.loadImage(Maze.wallImageFilename[i]);
            tracker.addImage(wallImages[i], i);
        }

        int num = 6;
        total += num;
        decorImages = new Image[num];
        for(int i = 0; i < num; i++)
        {
            decorImages[i] = ImageLoader.loadImage(Maze.decorImageFilename[i]);
            tracker.addImage(decorImages[i], i);
        }

        if(!loadedMainImages)
        {
            num = 64;
            total += num;
            pacImages = new Image[num];
            for(int i = 0; i < num; i++)
            {
                pacImages[i] = ImageLoader.loadImage(Pacman.pacImageFilename[i]);
                tracker.addImage(pacImages[i], i);
            }

            num = 113;
            total += num;
            ghostImages = new Image[num];
            for(int i = 0; i < num; i++)
            {
                ghostImages[i] = ImageLoader.loadImage(Ghost.ghostImageFilename[i]);
                tracker.addImage(ghostImages[i], i);
            }

            num = Fruit.NUMBER_OF_FRUITS;
            total += num;
            fruitImages = new Image[num];
            for(int i = 0; i < num; i++)
            {
                fruitImages[i] = ImageLoader.loadImage(Fruit.fruitImageFilename[i]);
                tracker.addImage(fruitImages[i], i);
            }

            num = 98;
            total += num;
            effectImages = new Image[num];
            for(int i = 0; i < num; i++)
            {
                effectImages[i] = ImageLoader.loadImage(Effect.effectImageFilename[i]);
                tracker.addImage(effectImages[i], i);
            }

            loadedMainImages = true;
        }
        for(int i = 0; i < total; i++)
            try
            {
                tracker.waitForID(i);
            }
            catch(InterruptedException e) { }

    }

    public void mazeDraw()
    {
        maze = component.createImage(dim.width, dim.height);
        mazeGraphics = maze.getGraphics();
        mazeGraphics.setColor(Color.black);
        mazeGraphics.fillRect(0, 0, dim.width, dim.height);
        for(int i = 0; i < theMaze.getMazeX(); i++)
        {
            for(int j = 0; j < theMaze.getMazeY(); j++)
                mazeGraphics.drawImage(wallImages[theMaze.getBlock(i, j)], i * 16, j * 16, component);

        }

        for(int i = 0; i < theMaze.getMazeX() / 2 - 2; i++)
        {
label0:
            for(int j = 0; j < theMaze.getMazeY(); j++)
            {
                if(!theMaze.realWall(i, j))
                    continue;
                boolean accept = true;
                int a;
label1:
                for(a = i; a <= i + 2; a++)
                {
                    int b = j;
                    do
                    {
                        if(b > j + 2)
                            continue label1;
                        if(!theMaze.realWall(a, b))
                        {
                            accept = false;
                            continue label1;
                        }
                        b++;
                    } while(true);
                }

                int num;
                if(accept)
                {
                    if(!checkChance(50))
                        break;
                    num = 4 + randomiser(0, 1);
                    mazeGraphics.drawImage(decorImages[num], i * 16, j * 16, component);
                    mazeGraphics.drawImage(decorImages[num], (theMaze.getMazeX() - (3 + i)) * 16, j * 16, component);
                    a = i;
                    do
                    {
                        if(a > i + 2)
                            continue label0;
                        for(int b = j; b <= j + 2; b++)
                            theMaze.setBlock(a, b, -1);

                        a++;
                    } while(true);
                }
                accept = true;
label2:
                for(a = i; a <= i + 1; a++)
                {
                    int b = j;
                    do
                    {
                        if(b > j + 1)
                            continue label2;
                        if(!theMaze.realWall(a, b))
                        {
                            accept = false;
                            continue label2;
                        }
                        b++;
                    } while(true);
                }

                if(!accept)
                    continue;
                if(!checkChance(50))
                    break;
                num = randomiser(0, 3);
                mazeGraphics.drawImage(decorImages[num], i * 16, j * 16, component);
                mazeGraphics.drawImage(decorImages[num], (theMaze.getMazeX() - (2 + i)) * 16, j * 16, component);
                a = i;
                do
                {
                    if(a > i + 1)
                        continue label0;
                    for(int b = j; b <= j + 1; b++)
                        theMaze.setBlock(a, b, -1);

                    a++;
                } while(true);
            }

        }

        for(int i = 0; i < theMaze.getMazeX(); i++)
        {
            for(int j = 0; j < theMaze.getMazeY(); j++)
                if(theMaze.getBlock(i, j) == -1)
                    theMaze.setBlock(i, j, 22);

        }

    }

    public void setupVariables()
    {
        ghostMunchCount = 0;
        frameNumber = -1;
        fruitFrameNumber = 0;
        lifeTimer = 0;
        winTimer = 0;
        suicide = false;
        energiserTime = 400 - (level - 1) * 33;
        if(energiserTime < 100)
            energiserTime = 100;
    }

    public int calculateMazeWidth()
    {
        if(level % 5 == 0)
        {
            return minMazeWidth;
        } else
        {
            int lowerBound = minMazeWidth;
            int upperBound = Math.min(maxMazeWidth, minMazeWidth + 4 + level);
            int size = randomiser(lowerBound, upperBound);
            return size;
        }
    }

    public int calculateMazeHeight()
    {
        if(level % 5 == 0)
        {
            return minMazeHeight;
        } else
        {
            int lowerBound = minMazeHeight;
            int upperBound = Math.min(maxMazeHeight, minMazeHeight + level);
            int size = randomiser(lowerBound, upperBound);
            return size;
        }
    }

    public int randomiser(int low, int high)
    {
        if(low >= high)
        {
            return low;
        } else
        {
            high++;
            return random.nextInt(high - low) + low;
        }
    }

    public boolean checkChance(int chance)
    {
        int rnd = randomiser(0, 99);
        return rnd < chance;
    }

    public void setupGhosts()
    {
        if(level == 1)
            Ghost.noOfGhosts = 2;
        else
        if(level == 2 || level == 3)
            Ghost.noOfGhosts = 3;
        else
            Ghost.noOfGhosts = 4;
        ghost = new Ghost[Ghost.noOfGhosts];
        Ghost.setLevel(level);
        Ghost.setSpeed(1.0D);
        ghostReset();
    }

    void ghostReset()
    {
        Ghost.ghostCount = 0;
        if(level == 1)
        {
            ghost[0] = new Ghost(((theMaze.getMazeX() - 1) * 16) / 2, (theMaze.getMazeY() / 2 - 3) * 16, 3, 3);
            ghost[1] = new Ghost(((theMaze.getMazeX() - 1) * 16) / 2, (theMaze.getMazeY() / 2) * 16, 2, 2);
        } else
        if(level == 2)
        {
            ghost[0] = new Ghost(((theMaze.getMazeX() - 1) * 16) / 2, (theMaze.getMazeY() / 2 - 3) * 16, 0, 0);
            ghost[1] = new Ghost(((theMaze.getMazeX() - 1) * 16) / 2, (theMaze.getMazeY() / 2) * 16, 2, 3);
            ghost[2] = new Ghost(((theMaze.getMazeX() - 3) * 16) / 2 - 8, (theMaze.getMazeY() / 2 + 2) * 16, 3, 2);
        } else
        if(level == 3)
        {
            ghost[0] = new Ghost(((theMaze.getMazeX() - 1) * 16) / 2, (theMaze.getMazeY() / 2 - 3) * 16, 0, 0);
            ghost[1] = new Ghost(((theMaze.getMazeX() - 1) * 16) / 2, (theMaze.getMazeY() / 2) * 16, 1, 1);
            ghost[2] = new Ghost(((theMaze.getMazeX() - 3) * 16) / 2 - 8, (theMaze.getMazeY() / 2 + 2) * 16, 2, 2);
        } else
        {
            ghost[0] = new Ghost(((theMaze.getMazeX() - 1) * 16) / 2, (theMaze.getMazeY() / 2 - 3) * 16, 0, 0);
            ghost[1] = new Ghost(((theMaze.getMazeX() - 1) * 16) / 2, (theMaze.getMazeY() / 2) * 16, 1, 1);
            ghost[2] = new Ghost(((theMaze.getMazeX() - 3) * 16) / 2 - 8, (theMaze.getMazeY() / 2 + 2) * 16, 2, 2);
            ghost[3] = new Ghost(((theMaze.getMazeX() + 1) * 16) / 2 + 8, (theMaze.getMazeY() / 2 + 2) * 16, 3, 3);
        }
    }

    private void setBufferSize(int x, int y)
    {
        System.out.println((new StringBuilder()).append("setting buffer size to: ").append(x).append(", ").append(y).toString());
        bufferReady = false;
        instance.setBufferSize(x, y);
        dim = new Dimension(x, y);
        buffer = component.createImage(dim.width, dim.height);
        bufferGraphics = buffer.getGraphics();
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.clearRect(0, 0, dim.width, dim.height);
        bufferReady = true;
    }

    public void levelUp()
    {
        level++;
        System.out.println((new StringBuilder()).append("initialising level ").append(level).toString());
        int mazeX = calculateMazeWidth() * 2;
        int mazeY = calculateMazeHeight() * 2;
        theMaze = new Maze(mazeX, mazeY);
        player.pacSetup(((theMaze.getMazeX() - 1) * 16) / 2, theMaze.getPacStart() * 16);
        Pacman.setLevel(level);
        Pacman.setSpeed(1.0D);
        setupGhosts();
        effect = new Effect[50];
        overEffect = new Effect[50];
        Effect.effectInit();
        for(int i = 0; i < 50; i++)
        {
            effect[i] = new Effect(0, 0, -1);
            overEffect[i] = new Effect(0, 0, -1);
        }

        fruitReset();
        setupVariables();
        loadAllImages();
        if(level % 5 == 0)
            intermission();
        setBufferSize(mazeX * 16, mazeY * 16 + 50);
        mazeDraw();
        startAnimation();
    }

    public void setup()
    {
        System.out.println("entering main game loop");
        System.out.println("initialising level 1");
        level = 1;
        int mazeX = calculateMazeWidth() * 2;
        int mazeY = calculateMazeHeight() * 2;
        if(mazeX % 2 != 0)
            mazeX--;
        if(mazeY % 2 != 0)
            mazeY--;
        theMaze = new Maze(mazeX, mazeY);
        player = new Pacman(((mazeX - 1) * 16) / 2, theMaze.getPacStart() * 16);
        Pacman.setLevel(level);
        Pacman.setSpeed(1.0D);
        updateScoreText();
        setupGhosts();
        effect = new Effect[50];
        overEffect = new Effect[50];
        Effect.effectInit();
        for(int i = 0; i < 50; i++)
        {
            effect[i] = new Effect(0, 0, -1);
            overEffect[i] = new Effect(0, 0, -1);
        }

        fruitReset();
        setupVariables();
        loadAllImages();
        setBufferSize(mazeX * 16, mazeY * 16 + 50);
        mazeDraw();
        SoundManager.loadAllSounds();
        startAnimation();
    }

    public void dotCheck()
    {
        if(player.x % 16 == 0 && player.y % 16 == 0)
        {
            int i = player.x / 16;
            int j = player.y / 16;
            if(theMaze.getBlock(i, j) == 1)
            {
                theMaze.setBlock(i, j, 0);
                theMaze.decreaseDots();
                player.score += 10;
                updateScoreText();
                mazeGraphics.drawImage(wallImages[theMaze.getBlock(i, j)], i * 16, j * 16, component);
                if(dotSound == 0)
                {
                    SoundManager.playSound(SoundManager.DOT);
                    dotSound = 1;
                } else
                if(dotSound == 1)
                {
                    SoundManager.playSound(SoundManager.DOT2);
                    dotSound = 0;
                }
                if(theMaze.getDots() == 0)
                {
                    winTimer = 1;
                    SoundManager.playSound(SoundManager.WIN);
                }
            } else
            if(theMaze.getBlock(i, j) == 2)
            {
                theMaze.setBlock(i, j, 0);
                player.score += 50;
                updateScoreText();
                mazeGraphics.drawImage(wallImages[theMaze.getBlock(i, j)], i * 16, j * 16, component);
                SoundManager.playSound(SoundManager.ENERGISER);
                ghostMunchCount = 0;
                for(int a = 0; a < Ghost.noOfGhosts; a++)
                    if(!ghost[a].dead)
                        ghost[a].getScared(theMaze, energiserTime);

            }
        }
    }

    public void processEffects()
    {
        if(frameNumber % 2 == 0)
            return;
        for(int i = 0; i < 50; i++)
        {
            if(effect[i].type == -1)
                continue;
            int t = effect[i].type;
            if(t >= 4 && t <= 6)
            {
                effect[i].type++;
                continue;
            }
            if(t == 7)
                effect[i].type = -1;
        }

        for(int i = 0; i < 50; i++)
        {
            if(overEffect[i].type == -1)
                continue;
            int t = overEffect[i].type;
            if(t >= 8 && t <= 12)
            {
                overEffect[i].type++;
                continue;
            }
            if(t == 13)
            {
                overEffect[i].type = -1;
                continue;
            }
            if(t >= 14 && t <= 18)
            {
                overEffect[i].type++;
                continue;
            }
            if(t == 19)
            {
                overEffect[i].type = -1;
                continue;
            }
            if(t >= 20 && t <= 24)
            {
                overEffect[i].type++;
                continue;
            }
            if(t == 25)
            {
                overEffect[i].type = -1;
                continue;
            }
            if(t >= 26 && t <= 30)
            {
                overEffect[i].type++;
                continue;
            }
            if(t == 31)
            {
                overEffect[i].type = -1;
                continue;
            }
            if(t >= 32 && t <= 36)
            {
                overEffect[i].type++;
                continue;
            }
            if(t == 37)
            {
                overEffect[i].type = -1;
                continue;
            }
            if(t >= 38 && t <= 42)
            {
                overEffect[i].type++;
                continue;
            }
            if(t == 43)
            {
                overEffect[i].type = -1;
                continue;
            }
            if(t >= 47 && t <= 51)
            {
                overEffect[i].type++;
                continue;
            }
            if(t == 52)
            {
                overEffect[i].type = -1;
                continue;
            }
            if(t >= 65 && t <= 69)
            {
                overEffect[i].type++;
                continue;
            }
            if(t == 70)
            {
                overEffect[i].type = -1;
                continue;
            }
            if(t >= 71 && t <= 75)
            {
                overEffect[i].type++;
                continue;
            }
            if(t == 76)
            {
                overEffect[i].type = -1;
                continue;
            }
            if(t >= 77 && t <= 81)
            {
                overEffect[i].type++;
                continue;
            }
            if(t == 82)
            {
                overEffect[i].type = -1;
                continue;
            }
            if(t >= 83 && t <= 87)
            {
                overEffect[i].type++;
                continue;
            }
            if(t == 88)
            {
                overEffect[i].type = -1;
                continue;
            }
            if(t >= 59 && t <= 60)
            {
                if(frameNumber % 3 == 0)
                    overEffect[i].type++;
                continue;
            }
            if(t == 61)
            {
                if(frameNumber % 3 == 0)
                    overEffect[i].type = -1;
                continue;
            }
            if(t >= 62 && t <= 63)
            {
                if(frameNumber % 3 == 0)
                    overEffect[i].type++;
                continue;
            }
            if(t == 64)
            {
                if(frameNumber % 3 == 0)
                    overEffect[i].type = -1;
                continue;
            }
            if(t >= 92 && t <= 93)
            {
                overEffect[i].type++;
                continue;
            }
            if(t == 94)
            {
                overEffect[i].type = -1;
                continue;
            }
            if(t >= 53 && t <= 57)
            {
                overEffect[i].type++;
                continue;
            }
            if(t == 58)
                overEffect[i].type = -1;
        }

    }

    public void spawnBubble(int x, int y)
    {
        if(checkChance(10))
        {
            int rand = randomiser(0, 1);
            int rndx = randomiser(0, 16);
            int rndy = randomiser(0, 16);
            if(rand == 0)
                spawnOverEffect((x - 16) + rndx, (y - 16) + rndy, 59);
            else
                spawnOverEffect((x - 16) + rndx, (y - 16) + rndy, 62);
        }
    }

    public void spawnEffect(int x, int y, int type)
    {
        int i = 49;
        do
        {
            if(i < 0)
                break;
            if(effect[i].type == -1)
            {
                effect[i] = new Effect(x, y, type);
                break;
            }
            i--;
        } while(true);
    }

    public void spawnOverEffect(int x, int y, int type)
    {
        int i = 49;
        do
        {
            if(i < 0)
                break;
            if(overEffect[i].type == -1)
            {
                overEffect[i] = new Effect(x, y, type);
                break;
            }
            i--;
        } while(true);
    }

    public void collisionDetect()
    {
        if(player.dead)
        {
            deathTimer++;
            if(deathTimer > 60)
                if(player.lives > 0)
                {
                    player.displayLives--;
                    resetActors();
                } else
                {
                    gameOver();
                }
            return;
        }
        if(fruit.type != -1 && Math.abs(fruit.x - player.x) < 12 && Math.abs(fruit.y - player.y) < 12)
        {
            SoundManager.playSound(SoundManager.FRUIT);
            int sc = fruit.fruitEaten();
            if(sc == 100)
                spawnOverEffect(fruit.x - 40, fruit.y - 40, 8);
            else
            if(sc == 300)
                spawnOverEffect(fruit.x - 40, fruit.y - 40, 38);
            else
            if(sc == 500)
                spawnOverEffect(fruit.x - 40, fruit.y - 40, 47);
            else
            if(sc == 700)
                spawnOverEffect(fruit.x - 40, fruit.y - 40, 53);
            else
            if(sc == 1000)
                spawnOverEffect(fruit.x - 40, fruit.y - 40, 65);
            else
            if(sc == 2000)
                spawnOverEffect(fruit.x - 40, fruit.y - 40, 71);
            else
            if(sc == 3000)
                spawnOverEffect(fruit.x - 40, fruit.y - 40, 77);
            else
            if(sc == 5000)
                spawnOverEffect(fruit.x - 40, fruit.y - 40, 83);
            player.score += sc;
            updateScoreText();
            fruit.type = -1;
        }
        for(int i = 0; i < Ghost.noOfGhosts; i++)
        {
            if(ghost[i].dead || Math.abs(ghost[i].x - player.x) >= 12 || Math.abs(ghost[i].y - player.y) >= 12)
                continue;
            if(ghost[i].scared)
            {
                ghost[i].dead = true;
                ghost[i].scared = false;
                ghost[i].speed = 8;
                ghost[i].recalcSpeed();
                ghost[i].x = (ghost[i].x / 8) * 8;
                ghost[i].y = (ghost[i].y / 8) * 8;
                ghost[i].leavingHouse = false;
                ghost[i].pausedTime = 0;
                ghost[i].fear = false;
                SoundManager.playSound(SoundManager.EAT_GHOST);
                ghostMunchCount++;
                int sc = 0;
                if(ghostMunchCount == 1)
                {
                    sc = 200;
                    spawnOverEffect(ghost[i].x - 40, ghost[i].y - 40, 14);
                } else
                if(ghostMunchCount == 2)
                {
                    sc = 400;
                    spawnOverEffect(ghost[i].x - 40, ghost[i].y - 40, 20);
                } else
                if(ghostMunchCount == 3)
                {
                    sc = 800;
                    spawnOverEffect(ghost[i].x - 40, ghost[i].y - 40, 26);
                } else
                if(ghostMunchCount >= 4)
                {
                    sc = 1600;
                    spawnOverEffect(ghost[i].x - 40, ghost[i].y - 40, 32);
                }
                player.score += sc;
                updateScoreText();
            } else
            {
                pacmanDeath();
            }
        }

    }

    void fruitReset()
    {
        fruit = new Fruit();
        fruit.type = -1;
        fruit.fruitInit();
    }

    void resetActors()
    {
        ghostReset();
        fruitReset();
        player.pacSetup(((theMaze.getMazeX() - 1) * 16) / 2, theMaze.getPacStart() * 16);
        lifeTimer = 0;
    }

    void pacmanDeath()
    {
        SoundManager.playSound(SoundManager.DEATH);
        player.dead = true;
        player.mouth = 1;
        player.count = 6;
        deathTimer = 0;
        player.lives--;
    }

    void gameOver()
    {
        SoundManager.playSound(SoundManager.GAME_OVER);
        wait(1000);
        stopAnimation();
        wait(1000);
        hiscore();
        wait(1000);
        splash();
        setup();
    }

    void hiscore()
    {
        int sc = player.score;
        if(!scoreTable.isScoreGoodEnough(sc))
            return;
        String name = new String("");
        hiscore = new Hiscore();
        splashDisplay = -2;
        setBufferSize(512, 384);
        frameNumber = 0;
        lastKey = 0;
        lastKeyFrames = 0;
        do
        {
            if(hiscore.finished)
                break;
            frameNumber++;
            if(frameNumber > Effect.effectWidth[96])
                frameNumber = 0;
            hiscore.keyAct(lastKey);
            lastKey = 0;
            lastKeyFrames = 0;
            component.repaint();
            try
            {
                Thread.sleep(25L);
                continue;
            }
            catch(InterruptedException e) { }
            break;
        } while(true);
        name = hiscore.name;
        scoreTable.addScore(name, sc);
    }

    static void wait(int a)
    {
        for(long startTime = System.currentTimeMillis(); System.currentTimeMillis() - startTime < (long)a;);
    }

    void intermission()
    {
        if(!splashLoaded)
            splashLoad();
        lastKey = 0;
        lastKeyFrames = 0;
        int interNo = randomiser(1, 2);
        inter.interInit(interNo);
        splashDisplay = interNo;
        setBufferSize(640, 369);
        frameNumber = 0;
        do
        {
            if(lastKey != 0)
                break;
            frameNumber++;
            inter.interProcess(interNo);
            component.repaint();
            try
            {
                Thread.sleep(25L);
                continue;
            }
            catch(InterruptedException e) { }
            break;
        } while(true);
        frameNumber = -1;
        splashDisplay = 0;
    }

    void splash()
    {
        System.out.println("entering title screen loop");
        if(!splashLoaded)
            splashLoad();
        lastKey = 0;
        lastKeyFrames = 0;
        splash.splashInit();
        splashDisplay = -1;
        setBufferSize(640, 480);
        frameNumber = 0;
        do
        {
            if(lastKey != 0)
                break;
            frameNumber++;
            splash.splashProcess();
            component.repaint();
            try
            {
                Thread.sleep(25L);
                continue;
            }
            catch(InterruptedException e) { }
            break;
        } while(true);
        frameNumber = -1;
        splashDisplay = 0;
    }

    public void splashLoad()
    {
        int total = 0;
        MediaTracker tracker = new MediaTracker(component);
        int num = 11;
        total += num;
        splashImages = new Image[num];
        for(int i = 0; i < num; i++)
        {
            splashImages[i] = ImageLoader.loadImage(splash.splashImageFilename[i]);
            tracker.addImage(splashImages[i], i);
        }

        num = 61;
        total += num;
        interImages = new Image[num];
        for(int i = 0; i < num; i++)
        {
            interImages[i] = ImageLoader.loadImage(inter.interImageFilename[i]);
            tracker.addImage(interImages[i], i);
        }

        for(int i = 0; i < total; i++)
            try
            {
                tracker.waitForID(i);
            }
            catch(InterruptedException e) { }

    }

    public void finish()
    {
        if(readWriteEnabled)
            scoreTable.save();
        stopAnimation();
    }

    public boolean isReadWriteEnabled()
    {
        return readWriteEnabled;
    }

    public void setReadWriteEnabled(boolean readWriteEnabled)
    {
        this.readWriteEnabled = readWriteEnabled;
    }

    private IPacmanInstance instance;
    private Component component;
    static final int BLOCKSIZE = 16;
    static final int DEATH_TIME = 60;
    static final int LOADING_PAUSE = 1000;
    static final int FRAME_PAUSE = 25;
    static final int NO_OF_EFFECTS = 50;
    static final double GAME_SPEED = 1D;
    static final int DECOR_CHANCE = 50;
    static final int BUBBLE_CHANCE = 10;
    static final int GHOST_BUBBLE_CHANCE = 50;
    int splashDisplay;
    boolean splashLoaded;
    int level;
    int frameNumber;
    int fruitFrameNumber;
    int delay;
    int deathTimer;
    int winTimer;
    int ghostMunchCount;
    boolean bufferReady;
    int lifeTimer;
    Thread animatorThread;
    Image buffer;
    Graphics bufferGraphics;
    Dimension dim;
    Image maze;
    Graphics mazeGraphics;
    SoundManager snd;
    int dotSound;
    int energiserTime;
    static Image wallImages[];
    static Image decorImages[];
    static Image pacImages[];
    static Image ghostImages[];
    static Image fruitImages[];
    static Image effectImages[];
    static Image splashImages[];
    static Image interImages[];
    Maze theMaze;
    Pacman player;
    Ghost ghost[];
    Fruit fruit;
    Effect effect[];
    Effect overEffect[];
    Splash splash;
    Intermission inter;
    ScoreTable scoreTable;
    Hiscore hiscore;
    static final int FRAMES_PER_KEY = 10;
    static int lastKey = 0;
    static int lastKeyFrames = 0;
    private int fps;
    private boolean readWriteEnabled;
    private Random random;
    private int minMazeWidth;
    private int maxMazeWidth;
    private int minMazeHeight;
    private int maxMazeHeight;
    private boolean suicide;
    private boolean paused;
    private boolean loadedMainImages;
    private String scoretext;

}
