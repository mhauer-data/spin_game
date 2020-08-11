package com.muhlinstudios.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;

import java.util.Random;

/**
 * Created by Michael on 08.03.2016.
 */
public class Sprite {
    /*  private int x;
      private int y;
      private int yersatz;
      private int i=0;
      private int xSpeed;
      private int ySpeed;*/
    private int i = 0;
    public int width;
    public int height;
    private Bitmap bmp;
    private GameView theGameView;
    private double xSpeed;
    private double ySpeed;
    private double x;
    private double y;
    private double yersatz;
    private double yersatzAnf;
    double xAnf;
    public int x1;
    public int y2;
    public double lokation = 1;
    public double richtung = 1;


    public Sprite(GameView theGameView, Bitmap bmp) {
        this.theGameView = theGameView;
        this.bmp = bmp;
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();
        Random rnd = new Random();
    }

    public void bounceOff() {
        //Punkt 2
        if (x > theGameView.getWidth() - width - xSpeed) {
            xSpeed = -xSpeed;
            x = 2 * xAnf;
            y = yersatzAnf;
            String LOG_TAG = Sprite.class.getSimpleName();
            Log.e(LOG_TAG, "Punkt 2");
            lokation = lokation + richtung;
        }
        //Punkt 4
        if (x + xSpeed < 0) {
            xSpeed = -xSpeed;
            x = 0;
            y = yersatzAnf;
            String LOG_TAG = Sprite.class.getSimpleName();
            Log.e(LOG_TAG, "Punkt 4");
            lokation = lokation + richtung;
        }
        //Punkt 3
        if (y > theGameView.getHeight() - height - ySpeed) {
            ySpeed = -ySpeed;
            x = xAnf;
            y = yersatzAnf * 2;
            String LOG_TAG = Sprite.class.getSimpleName();
            Log.e(LOG_TAG, "Punkt 3");
            lokation = lokation + richtung;
        }
        //Punkt 1
        if (y + ySpeed < 0) {
            ySpeed = -ySpeed;
            x = xAnf;
            y = 0;
            String LOG_TAG = Sprite.class.getSimpleName();
            Log.e(LOG_TAG, "Punkt 1");
            if (richtung==1)
            { lokation =1; }
            else { lokation = 4;
            }
        }
        y = y + ySpeed;
        x = x + xSpeed;
    }

    public void change() {
        //Punkt 2
        if (x > theGameView.getWidth() - width - xSpeed) {
            xSpeed = -xSpeed;
            ySpeed = -ySpeed;
            x = 2 * xAnf;
            y = yersatzAnf;
            String LOG_TAG = Sprite.class.getSimpleName();
            Log.e(LOG_TAG, "Punkt 2");
            richtung = -richtung;
        }
        //Punkt 4
        if (x + xSpeed < 0) {
            xSpeed = -xSpeed;
            ySpeed = -ySpeed;
            x = 0;
            y = yersatzAnf;
            String LOG_TAG = Sprite.class.getSimpleName();
            Log.e(LOG_TAG, "Punkt 4");
            richtung = -richtung;
        }
        //Punkt 3
        if (y > theGameView.getHeight() - height - ySpeed) {
            xSpeed = -xSpeed;
            ySpeed = -ySpeed;
            x = xAnf;
            y = yersatzAnf * 2;
            String LOG_TAG = Sprite.class.getSimpleName();
            Log.e(LOG_TAG, "Punkt 3");
            richtung = -richtung;
        }
        //Punkt 1
        if (y + ySpeed < 0) {
            xSpeed = -xSpeed;
            ySpeed = -ySpeed;
            x = xAnf;
            y = 0;
            String LOG_TAG = Sprite.class.getSimpleName();
            Log.e(LOG_TAG, "Punkt 1");
            richtung = -richtung;
        }
        y = y + ySpeed;
        x = x + xSpeed;
    }

    public void onDraw(Canvas canvas) {

         x1 = (int) Math.round(x);
         y2 = (int) Math.round(y);
        canvas.drawBitmap(bmp, x1, y2, null);
        String LOG_TAG = Sprite.class.getSimpleName();
        Log.v(LOG_TAG, "bmp:  " + width + "");
        Log.v(LOG_TAG, "Richtung:  " + richtung + "");  //
        Log.v(LOG_TAG, "lokation:  " + lokation + "");  //
        Log.d(LOG_TAG, "bmp:  " + height + "");
        Log.i(LOG_TAG, "x:  " + x + "");
        Log.w(LOG_TAG, "y:  " + y + "");
        Log.e(LOG_TAG, "yersatz:  " + yersatz + "");
    }

    public void Berechnung()
    {
        if (i == 0) {
            xAnf = ((theGameView.getWidth() - width) / 2);
            yersatzAnf = ((theGameView.getHeight() - height) / 2);
            x = ((theGameView.getWidth() - width) / 2);
            yersatz = ((theGameView.getHeight() - height) / 2);
            ySpeed = yersatz / 100;
            xSpeed = x / 100;


        }
        i++;
    }
}