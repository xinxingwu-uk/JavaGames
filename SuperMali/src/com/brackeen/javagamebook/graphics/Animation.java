package com.brackeen.javagamebook.graphics;
//Download by http://www.codefans.net
import java.awt.Image;
import java.util.ArrayList;

import com.brackeen.javagamebook.graphics.Animation.AnimFrame;
/*
 * AnimFrame 是一个记录游戏进程，以及当前所处的像素段的类。
 * 
 * 它作用于整个过程。对于处理整个图片显示有着至关重要的作用。

*/
public class Animation {

    private ArrayList<AnimFrame> frames;   //---元素记录时间和图像---------
    private long totalDuration; //---总时间     ，记录ArryList中时间的和--，实际是为增加一个新的AniFrame服务的，因为每一个新的服务都会由图片和结束时间来定义。------
    
    
    private int currFrameIndex;    //-当前的AnimFrame下标。其实也是为增加一个新的AniFrame服务的，还有就是对于调用Animation的component函数进行显示当前的界面。
    private long animTime;        //---当前像素    哈哈哈哈！！！！！-----So easy!!

    /**
        Creates a new, empty Animation.
    */
    public Animation() {
        this(new ArrayList(), 0);  //---只是为了方便？外界只能构造一个空的Animation;
    }
//---为什么要这样设计？让构造方法私有，然后公有调用。
    
    private Animation(ArrayList frames, long totalDuration) {
        this.frames = frames;
        this.totalDuration = totalDuration;
        start();    //---开局了，哈哈哈！！！！！
    }
    /**
        Creates a duplicate of this animation. The list of frames
        are shared between the two Animations, but each Animation
        can be animated independently.
    */
    public Object clone() {
        return new Animation(frames, totalDuration);
    }


    /**
        Adds an image to the animation with the specified
        duration (time to display the image).
    */
    //-----------向ArryList数组中增加元素----------这个函数时是加锁的函数。
    public synchronized void addFrame(Image image,long duration)   //--我认为时间应该表示的是像素的长度。duration，表示的是图片像素的长度。
    {
        totalDuration += duration;
        frames.add(new AnimFrame(image, totalDuration));  //---增加一个新的AnimFrame,frames是一个ArryList数组。
    }

    /**
        Starts this animation over from the beginning.对线程加锁！一次游戏开始！！
    */
    public synchronized void start() {
        animTime = 0;
        currFrameIndex = 0;    //------开始启动----
    }

    /**
        Updates this animation's current image (frame), if
        necessary.
    */
    
  //---下面的函数是用于外调的，当触发向前走的世间的时候会调用此函数。将当前活跃的下标前进1；
    public synchronized void update(long elapsedTime) {
        if (frames.size() > 1)
        {
            animTime += elapsedTime; //---当前活动的时间----

            if (animTime >= totalDuration) {
                animTime = animTime % totalDuration;  //----?????这是为什么呢？？
                currFrameIndex = 0;   //---
            }

            while (animTime > getFrame(currFrameIndex).endTime) {
                currFrameIndex++;   //----找到当前应该活跃的图片的下标。
            }
        }
    }


    /**
        Gets this Animation's current image. Returns null if this
        animation has no images.   得到激活状态的图片。
    */
    public synchronized Image getImage() {
        if (frames.size() == 0) {
            return null;
        }
        else {
            return getFrame(currFrameIndex).image;
        }
    }


    private AnimFrame getFrame(int i) {
        return frames.get(i);
    }


    public class AnimFrame {   //----------------记录了时间和图像
    	
        public AnimFrame(Image image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
        Image image;
        long endTime;
    }
}
