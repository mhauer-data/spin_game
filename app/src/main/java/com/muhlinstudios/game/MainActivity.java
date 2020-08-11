package com.muhlinstudios.game;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
    public void onGameOver() {
        Intent theNextIntent = new Intent(getApplicationContext(), VerlorenActivity.class);
        startActivity(theNextIntent);
        this.finish();
    }
}
