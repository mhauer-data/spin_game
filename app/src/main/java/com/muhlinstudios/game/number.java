package com.muhlinstudios.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

/**
 * Created by Michael on 10.03.2016.
 */
public class number {
    private int i = 0;
    public int width;
    public int height;
    private Bitmap Ziffer;
    private int pos;
    private GameView theGameView;
    private double xSpeed;
    private double ySpeed;
    private double x;
    private double y;
    private double yersatz;
    public int yersatzAnf;
    public int xAnf;
    public int a,b;

    public number(GameView theGameView, Bitmap Ziffer,int pos){
        this.theGameView = theGameView;
        this.Ziffer = Ziffer;
        this.pos = pos;
        this.width = Ziffer.getWidth();
        this.height = Ziffer.getHeight();
        Random rnd = new Random();
    }
    public void onDraw(Canvas canvas) {

        if (i== 0) {
            xAnf = ((theGameView.getWidth() - width)/ 2);
            yersatzAnf = ((theGameView.getHeight()- height) / 2);
            yersatz = ((theGameView.getHeight()- height) / 2);
        }
        i++;
        switch(pos)
        {
            case 0: a=((xAnf*3)/2);
                b=(yersatzAnf/2);
                break;
            case 1:  a=((xAnf*3)/2);
                b=((yersatzAnf*3)/2);
                break;
            case 2: a=(xAnf/2);
                b=((yersatzAnf*3)/2);
                break;
            case 3: a=(xAnf/2);
                b=(yersatzAnf/2);
                break;
        }
        canvas.drawBitmap(Ziffer,a ,b, null);
    }
}

