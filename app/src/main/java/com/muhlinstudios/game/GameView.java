package com.muhlinstudios.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Michael on 08.03.2016.
 */
public class GameView extends SurfaceView {

    private SurfaceHolder surfaceHolder;
    private Bitmap bmp;
    private Bitmap Zahl0,Zahl1,Zahl2,Zahl3;
    private Bitmap Verloren;
    private GameLoopThread theGameLoopThread;
    private Sprite theSprite;
    private number Nummer0,Nummer1,Nummer2,Nummer3;
    public boolean t;
    private int pos=0; /// public int pos=3;
    private int pos1=1;
    private int pos2=2;
    private int pos3=3;
    protected int lange = 5;
    public boolean neu;
    boolean push;
    public int n;
    private int r0,r1,r2,r3;
    public boolean p0=true;
    public boolean p1=true;
    public boolean p2=true;
    public boolean p3=true;
    public boolean q0=true;
    public boolean q1=true;
    public boolean q2=true;
    public boolean q3=true;
    public boolean Anf=true;
    private int Inhalt,Inhalt1,Inhalt2,Inhalt3;
    public boolean f=true;
    public boolean Zahltouchvar=false;
    public boolean u=true;
    private MainActivity theMainActivity = new MainActivity();
    private long time1 = 1500;
    public double location;
    public double direction;
    public boolean zufall=true;

    @SuppressLint("WrongCall") public GameView(Context context) {
        super(context);
        theMainActivity = (MainActivity) context;
        theGameLoopThread = new GameLoopThread(this);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                theGameLoopThread.setRunning(false);
                while(retry){
                    try {
                        theGameLoopThread.join();
                        retry=false;
                    }catch(InterruptedException e){

                    }
                }

            }

            public void surfaceCreated(SurfaceHolder holder) {
                theGameLoopThread.setRunning(true);
                theGameLoopThread.start();
            }

            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                       int height) {
                // TODO Auto-generated method stub

            }
        });
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.spielfigur);
        theSprite = new Sprite(this, bmp);
        Zahl0 = BitmapFactory.decodeResource(getResources(), R.drawable.number0gruen_klein);
        Zahl1 = BitmapFactory.decodeResource(getResources(), R.drawable.number1gelb_klein);
        Zahl2 = BitmapFactory.decodeResource(getResources(), R.drawable.number2orange_klein);
        Zahl3 = BitmapFactory.decodeResource(getResources(), R.drawable.number3rot_klein);
        Nummer0 = new number(this, Zahl0, pos);
        Nummer1 = new number(this, Zahl0, pos);
        Nummer2 = new number(this, Zahl0, pos2);
        Nummer3 = new number(this, Zahl0, pos3);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Interpret MotionEvent data
        // Handle touch here
        int eventAction = event.getAction();
        // you may need the x/y location
        int x = (int)event.getX();
        int y = (int)event.getY();

        // put your code in here to handle the event
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                t=true;
                break;
            case MotionEvent.ACTION_UP:
                t=false;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        // tell the View that we handled the event
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.DKGRAY);
        theSprite.Berechnung();
        if (t == true) {
        theSprite.change();
             direction = theSprite.richtung;
        }
        else{
            theSprite.bounceOff();
             location = theSprite.lokation;
        }
        theSprite.onDraw(canvas);
        Anfang();
    /*   if(((theSprite.x1+(theSprite.width/lange))>Nummer0.a && (Nummer0.a +(Nummer0.width/lange))>theSprite.x1) &&
                (((theSprite.y2+(theSprite.height/lange))>Nummer0.b)&& theSprite.y2<(Nummer0.b +(Nummer0.height/lange)))||
               ((theSprite.x1+(theSprite.width/lange))>Nummer1.a && (Nummer1.a +(Nummer1.width/lange))>theSprite.x1) &&
                       (((theSprite.y2+(theSprite.height/lange))>Nummer1.b)&& theSprite.y2<(Nummer1.b +(Nummer1.height/lange)))||
               ((theSprite.x1+(theSprite.width/lange))>Nummer2.a && (Nummer2.a +(Nummer2.width/lange))>theSprite.x1) &&
                       (((theSprite.y2+(theSprite.height/lange))>Nummer2.b)&& theSprite.y2<(Nummer2.b +(Nummer2.height/lange)))||
               ((theSprite.x1+(theSprite.width/lange))>Nummer3.a && (Nummer3.a +(Nummer3.width/lange))>theSprite.x1) &&
                       (((theSprite.y2+(theSprite.height/lange))>Nummer3.b)&& theSprite.y2<(Nummer3.b +(Nummer3.height/lange))))
        {
            push = true;

        }  */
        if(((theSprite.x1+(theSprite.width/lange))>Nummer0.a && (Nummer0.a +(Nummer0.width/lange))>theSprite.x1) &&
                (((theSprite.y2+(theSprite.height/lange))>Nummer0.b)&& theSprite.y2<(Nummer0.b +(Nummer0.height/lange)))) {
            push = true;
            if (Inhalt != 0) {
                Nummer0.onDraw(canvas);
                Nummer1.onDraw(canvas);
                Nummer2.onDraw(canvas);
                Nummer3.onDraw(canvas);

               /* canvas.drawBitmap(Zahl0, 0, 0, null);
                Verloren = BitmapFactory.decodeResource(getResources(), R.drawable.verloren);
                    int breite = Verloren.getWidth();
                    int hoehe = Verloren.getWidth();
                int Bildbr = this.getWidth();
                int Bildho = this.getWidth();
                 //   canvas.drawBitmap(Verloren, (Bildbr-breite)/2,(Bildho-hoehe)/2 , null);
                canvas.drawBitmap(Verloren,0,0 , null);
                canvas.drawBitmap(bmp,0,0 , null);   */
                try {
                    Thread.sleep(time1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                theGameLoopThread.setRunning(false);
                theMainActivity.onGameOver();
            }
        }
                else if (((theSprite.x1+(theSprite.width/lange))>Nummer1.a && (Nummer1.a +(Nummer1.width/lange))>theSprite.x1) &&
                        (((theSprite.y2+(theSprite.height/lange))>Nummer1.b)&& theSprite.y2<(Nummer1.b +(Nummer1.height/lange))))
        {
                push = true;
            if (Inhalt1 != 0) {
                Nummer0.onDraw(canvas);
                Nummer1.onDraw(canvas);
                Nummer2.onDraw(canvas);
                Nummer3.onDraw(canvas);
                try {
                    Thread.sleep(time1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                theGameLoopThread.setRunning(false);
                theMainActivity.onGameOver();
            }
        }
                else if (((theSprite.x1+(theSprite.width/lange))>Nummer2.a && (Nummer2.a +(Nummer2.width/lange))>theSprite.x1) &&
                        (((theSprite.y2+(theSprite.height/lange))>Nummer2.b)&& theSprite.y2<(Nummer2.b +(Nummer2.height/lange))))
        {
            push = true;
            if (Inhalt2 != 0) {
                Nummer0.onDraw(canvas);
                Nummer1.onDraw(canvas);
                Nummer2.onDraw(canvas);
                Nummer3.onDraw(canvas);
                try {
                    Thread.sleep(time1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                theGameLoopThread.setRunning(false);
                theMainActivity.onGameOver();
            }
        }
                else if(((theSprite.x1+(theSprite.width/lange))>Nummer3.a && (Nummer3.a +(Nummer3.width/lange))>theSprite.x1) &&
                        (((theSprite.y2+(theSprite.height/lange))>Nummer3.b)&& theSprite.y2<(Nummer3.b +(Nummer3.height/lange))))
        {
            push = true;
            if (Inhalt3 != 0) {
                Nummer0.onDraw(canvas);
                Nummer1.onDraw(canvas);
                Nummer2.onDraw(canvas);
                Nummer3.onDraw(canvas);
                try {
                    Thread.sleep(time1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                theGameLoopThread.setRunning(false);
                theMainActivity.onGameOver();
            }
        }
        else{
            if(push==true) {
                andereZahl();
                push = false;
            }
            Nummer0.onDraw(canvas);
           Nummer1.onDraw(canvas);
           Nummer2.onDraw(canvas);
           Nummer3.onDraw(canvas);
    }
    }

    void andereZahl()
    {
        if(Inhalt==0) {
            if(p0==true) {

                ///////////////////////////////////
                switch((int)(location+direction))
                {
                    case 0:
                        if(Inhalt3!=1)
                        {
                        zufall = false;
                        }
                    case 2:
                        if(Inhalt1!=1)
                        {
                            zufall = false;
                        }
                }
                ///////////////////////////////////////7
                if(zufall=true) {
                    Random rnd = new Random();
                    r0 = rnd.nextInt(4);
                }
                else{
                    r0 = 0;
                }
            }
                sw0();
        }
        else {
            Zuweis0();
        }

        if(Inhalt1==0) {
            if(p1==true) {
                ///////////////////////////////////
                switch ((int) (location + direction)) {
                    case 1:
                        if (Inhalt != 1) {
                            zufall = false;
                        }
                    case 3:
                        if (Inhalt2 != 1) {
                            zufall = false;
                        }
                }
                ///////////////////////////////////////7
                if (zufall = true) {
                    Random rnd = new Random();
                    r1 = rnd.nextInt(4);
                } else {
                    r1 = 0;
                }
            }
            sw1();
        }
        else {
            Zuweis1();
        }

        if(Inhalt2==0) {
            if(p2==true) {
                ///////////////////////////////////
                switch((int)(location+direction))
                {
                    case 2:
                        if(Inhalt1!=1)
                        {
                            zufall = false;
                        }
                    case 4:
                        if(Inhalt3!=1)
                        {
                            zufall = false;
                        }
                }
                ///////////////////////////////////////7
                if(zufall=true) {
                    Random rnd = new Random();
                    r2 = rnd.nextInt(4);
                }
                else{
                    r2 = 0;
                }
            }
            sw2();
        }
        else {
            Zuweis2();
        }

        if(Inhalt3==0) {
            if(p3==true) {
                ///////////////////////////////////
                switch((int)(location+direction))
                {
                    case 3:
                        if(Inhalt2!=1)
                        {
                            zufall = false;
                        }
                    case 5:
                        if(Inhalt!=1)
                        {
                            zufall = false;
                        }
                }
                ///////////////////////////////////////7
                if(zufall=true) {
                    Random rnd = new Random();
                    r3 = rnd.nextInt(4);
                }
                else{
                    r3 = 0;
                }
            }
            sw3();
        }
        else {
            Zuweis3();
        }

    }



    void Anfang() {
        if(Anf==true){
            Anf=false;
            Random rnd = new Random();
            r1 = (rnd.nextInt(2)+1);
            switch (r1) {
                case 1:
                    Nummer1 = new number(this, Zahl1, pos1);
                    Inhalt1 = 1;
                    r1 = rnd.nextInt(2);
                    p1 = false;
                        switch (r1)
                        {
                            case 0: r1=0;
                                    break;
                            case 1: r1=2;
                                    break;
                        }
                    switch (r1)
                    {
                        case 0:
                            Nummer2 = new number(this, Zahl0, pos2);
                            Inhalt2 = 0;
                            Nummer3 = new number(this, Zahl3, pos3);
                            Inhalt3 = 3;
                            break;
                        case 2:
                            Nummer2 = new number(this, Zahl2, pos2);
                            Inhalt2 = 2;
                            r2 = rnd.nextInt(4);
                            p2 = false;
                                switch (r1)
                                {
                                    case 0:
                                        r3 = rnd.nextInt(4);
                                        sw3();
                                        break;
                                    case 1:case 2: case 3:
                                    Nummer3 = new number(this, Zahl3, pos3);
                                    Inhalt3 = 3;
                                    break;
                                }
                    }
                    break;
                case 2:
                    Nummer1 = new number(this, Zahl2, pos1);
                    Inhalt1 = 2;
                    r0 = 0;
                    p0 = false;
                    Nummer3 = new number(this, Zahl2, pos3);
                    Inhalt3 = 2;
                        r3 = rnd.nextInt(4);
                        p3 = false;
                            switch (r1)
                            {
                            case 0:
                                Nummer2 = new number(this, Zahl3, pos3);
                                Inhalt2 = 3;
                                break;
                            case 1:case 2: case 3:
                                r2 = rnd.nextInt(4);
                                p2 = false;
                                sw2();
                                break;
                    }
                    break;
            }

        /*    Nummer2 = new number(this, Zahl0, pos2);
            Nummer3 = new number(this, Zahl0, pos3); */
        }
    }
    void sw0()
    {
        switch (r0) {
            case 0:
                Nummer0 = new number(this, Zahl0, pos);
                Inhalt = 0;
                p0=true;
                break;
            case 1:
                Nummer0 = new number(this, Zahl1, pos);
                Inhalt = 1;
                p0=true;
                break;
            case 2:
                Nummer0 = new number(this, Zahl2, pos);
                Inhalt = 2;
                p0=true;
                break;
            case 3:
                Nummer0 = new number(this, Zahl3, pos);
                Inhalt = 3;
                p0=true;
                break;
        }
    }

    void Zuweis0()
    {
        Inhalt=Inhalt-1;
        switch (Inhalt) {
            case 0:
                Nummer0 = new number(this, Zahl0, pos);
                Inhalt = 0;
                break;
            case 1:
                Nummer0 = new number(this, Zahl1, pos);
                Inhalt = 1;
                break;
            case 2:
                Nummer0 = new number(this, Zahl2, pos);
                Inhalt = 2;
                break;
            case 3:
                Nummer0 = new number(this, Zahl3, pos);
                Inhalt = 3;
                break;
        }
    }
    void sw1()
    {
        switch (r1) {
            case 0:
                Nummer1 = new number(this, Zahl0, pos1);
                Inhalt1 = 0;
                p1=true;
                break;
            case 1:
                Nummer1 = new number(this, Zahl1, pos1);
                Inhalt1 = 1;
                p1=true;
                break;
            case 2:
                Nummer1 = new number(this, Zahl2, pos1);
                Inhalt1 = 2;
                p1=true;
                break;
            case 3:
                Nummer1 = new number(this, Zahl3, pos1);
                Inhalt1 = 3;
                p1=true;
                break;
        }
    }

    void Zuweis1()
    {
        Inhalt1=Inhalt1-1;
        switch (Inhalt1) {
            case 0:
                Nummer1 = new number(this, Zahl0, pos1);
                Inhalt1 = 0;
                break;
            case 1:
                Nummer1 = new number(this, Zahl1, pos1);
                Inhalt1 = 1;
                break;
            case 2:
                Nummer1 = new number(this, Zahl2, pos1);
                Inhalt1 = 2;
                break;
            case 3:
                Nummer1 = new number(this, Zahl3, pos1);
                Inhalt1 = 3;
                break;
        }
    }
    void sw2()
    {
        switch (r2) {
            case 0:
                Nummer2 = new number(this, Zahl0, pos2);
                Inhalt2 = 0;
                p2=true;
                break;
            case 1:
                Nummer2 = new number(this, Zahl1, pos2);
                Inhalt2 = 1;
                p2=true;
                break;
            case 2:
                Nummer2 = new number(this, Zahl2, pos2);
                Inhalt2 = 2;
                p2=true;
                break;
            case 3:
                Nummer2 = new number(this, Zahl3, pos2);
                Inhalt2 = 3;
                p2=true;
                break;
        }
    }
    void Zuweis2()
    {
        Inhalt2=Inhalt2-1;
        switch (Inhalt2) {
            case 0:
                Nummer2 = new number(this, Zahl0, pos2);
                Inhalt2 = 0;
                break;
            case 1:
                Nummer2 = new number(this, Zahl1, pos2);
                Inhalt2 = 1;
                break;
            case 2:
                Nummer2 = new number(this, Zahl2, pos2);
                Inhalt2 = 2;
                break;
            case 3:
                Nummer2 = new number(this, Zahl3, pos2);
                Inhalt2 = 3;
                break;
        }
    }
    void sw3()
    {
        switch (r3) {
            case 0:
                Nummer3 = new number(this, Zahl0, pos3);
                Inhalt3 = 0;
                p3=true;
                break;
            case 1:
                Nummer3 = new number(this, Zahl1, pos3);
                Inhalt3 = 1;
                p3=true;
                break;
            case 2:
                Nummer3 = new number(this, Zahl2, pos3);
                Inhalt3 = 2;
                p3=true;
                break;
            case 3:
                Nummer3 = new number(this, Zahl3, pos3);
                Inhalt3 = 3;
                p3=true;
                break;
        }
    }
    void Zuweis3()
    {
        Inhalt3=Inhalt3-1;
        switch (Inhalt3) {
            case 0:
                Nummer3 = new number(this, Zahl0, pos3);
                Inhalt3 = 0;
                break;
            case 1:
                Nummer3 = new number(this, Zahl1, pos3);
                Inhalt3 = 1;
                break;
            case 2:
                Nummer3 = new number(this, Zahl2, pos3);
                Inhalt3 = 2;
                break;
            case 3:
                Nummer3 = new number(this, Zahl3, pos3);
                Inhalt3 = 3;
                break;
        }
    }

}

