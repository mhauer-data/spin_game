package com.muhlinstudios.game;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

/**
 * Created by Michael on 08.03.2016.
 */
public class GameLoopThread extends Thread {
    static final long FPS = 60;
    private GameView theView;
    private boolean isRunning = false;

    public GameLoopThread(GameView theView) {
        this.theView = theView;
    }

    public void setRunning(boolean run) {
        isRunning = run;
    }

    @SuppressLint("WrongCall") @Override
    public void run() {
        long TPS = 1000 / FPS;
        long startTime, sleepTime;
        while (isRunning) {
            Canvas theCanvas = null;
            startTime = System.currentTimeMillis();
            try {
                theCanvas = theView.getHolder().lockCanvas();
                synchronized (theView.getHolder()) {
                    theView.onDraw(theCanvas);
                }
            } finally {
                if (theCanvas != null) {
                    theView.getHolder().unlockCanvasAndPost(theCanvas);
                }
            }
            sleepTime = TPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime == 0)
                sleep(sleepTime);
                else
                sleep(10);
            } catch (Exception e) {

            }
        }
    }
}
