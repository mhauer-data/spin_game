package com.muhlinstudios.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VerlorenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verloren);
    }

        public void buttononClick(View v) {
            Button button=(Button) v;
           // ((Button) v).setText("pressed");
            Intent theNextIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(theNextIntent);
            this.finish();
        }
}
