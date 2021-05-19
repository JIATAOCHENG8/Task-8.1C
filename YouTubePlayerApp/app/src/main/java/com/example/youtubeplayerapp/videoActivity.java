package com.example.youtubeplayerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.MediaController;
import android.widget.VideoView;

public class videoActivity extends AppCompatActivity {

    VideoView videoView;
    private int mCurrentPosition = 0;
    private int mStatePosition = 0 ;
    public static final String PLAYBACK_TIME = "play_time";
    Intent intent = getIntent();
    String URL = intent.getStringExtra("url");
    String MEDIA_NAME = URL;

    private Uri getMedia(String mediaName){
            if(URLUtil.isValidUrl(mediaName)){
                return Uri.parse(mediaName);
            }else{
                return Uri.parse("android.resource://" + getPackageName() + "/raw/" + mediaName);
            }
    }

    private void initializePlayer()
    {
        Uri videoUri = getMedia(MEDIA_NAME);
        videoView.setVideoURI(videoUri);

        if(mCurrentPosition > 0){
            videoView.seekTo(mCurrentPosition);
        }
        else {
            videoView.seekTo(1);
        }

        videoView.start();
    }

    private void releasePlayer()
    {
        videoView.stopPlayback();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView = findViewById(R.id.videoView);

        if(savedInstanceState != null){
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }

        MediaController mediaController = new MediaController(this);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializePlayer();
    }
    @Override
    protected void onResume() {
        super.onResume();
        initializePlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mStatePosition = videoView.getCurrentPosition();

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
        {
            videoView.pause();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PLAYBACK_TIME, mStatePosition);
    }


}