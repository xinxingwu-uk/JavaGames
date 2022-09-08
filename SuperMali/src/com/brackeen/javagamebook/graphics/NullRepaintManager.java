package com.brackeen.javagamebook.graphics;

import javax.swing.RepaintManager;
import javax.swing.JComponent;

/** 
     The NullRepaintManager is a RepaintManager that doesn't
    do any repainting. Useful    when all the rendering is done
    manually by the application.
*/
/**
 * RepaintManager是一个重绘管理类。来管理重绘请求，最大限度的减少重绘次数

*/
/*
 * @author Administrator
 
 */

public class NullRepaintManager extends RepaintManager {

    /**
        Installs the NullRepaintManager.
    */
    public static void install() {
        RepaintManager repaintManager = new NullRepaintManager();
        repaintManager.setDoubleBufferingEnabled(false);
        RepaintManager.setCurrentManager(repaintManager);
    }

    public void addInvalidComponent(JComponent c) {
        // do nothing
    }

    public void addDirtyRegion(JComponent c, int x, int y,
        int w, int h)
    {
        // do nothing
    }

    public void markCompletelyDirty(JComponent c) {
        // do nothing
    }

    public void paintDirtyRegions() {
        // do nothing
    }

}
