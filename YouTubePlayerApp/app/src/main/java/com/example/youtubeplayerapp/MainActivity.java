package com.example.youtubeplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText urlEditText = findViewById(R.id.urlEditText);
        Button playButton = findViewById(R.id.playButton);
        String url = urlEditText.getText().toString();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(MainActivity.this,videoActivity.class);
                playIntent.putExtra("url",url);
                startActivity(playIntent);
            }
        });
    }
}