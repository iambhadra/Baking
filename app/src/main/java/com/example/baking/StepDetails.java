package com.example.baking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baking.models.stepsitem;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

public class StepDetails extends AppCompatActivity {

    Button btn_next_step;
    TextView tv_recipeDescription;
    private SimpleExoPlayer player;
    private PlayerView vv_step_video;
    private List<stepsitem> procedureItem;
    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady;
    private int StepPosition=0;
    TextView tv_no_video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);
        Intent intent = getIntent();
        procedureItem = (List<stepsitem>)intent.getSerializableExtra("Steps");
        StepPosition = intent.getIntExtra("StepPosition",0);
        tv_no_video = findViewById(R.id.tv_no_video);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tb_stepdetailsheading);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Step Description");
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.colorPrimary));
        vv_step_video = findViewById(R.id.vv_step_video);
        tv_recipeDescription = findViewById(R.id.tv_recipeDescription);
        btn_next_step = findViewById(R.id.btn_next_step);

        tv_recipeDescription.setText(procedureItem.get(StepPosition).getDescription());
        if(StepPosition == (procedureItem.size()-2)){
            btn_next_step.setText("Finish");
        }
        btn_next_step.setText("Next Step");
        btn_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StepPosition == (procedureItem.size()-2)){
                    btn_next_step.setText("Finish");
                }
                if(StepPosition ==(procedureItem.size()-1)){
                    finish();
                }else{
                    StepPosition= StepPosition+1;

                    if(procedureItem.get(StepPosition).getDescription() != null ||
                            !procedureItem.get(StepPosition).getDescription().isEmpty()){
                        tv_recipeDescription.setText(procedureItem.get(StepPosition).getDescription());
                    }else{
                        tv_recipeDescription.setText("Description not available");
                    }
                    if(!procedureItem.get(StepPosition).getVideoURl().isEmpty()) {
                        vv_step_video.setVisibility(View.VISIBLE);
                        tv_no_video.setVisibility(View.GONE);
                        initializePlayer();
                    }else{
                        vv_step_video.setVisibility(View.GONE);
                        tv_no_video.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }
    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }
    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        vv_step_video.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("baking")).
                createMediaSource(uri);
    }
    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(this),
                new DefaultTrackSelector(), new DefaultLoadControl());

        vv_step_video.setPlayer(player);
        Uri uri = Uri.parse(procedureItem.get(StepPosition).getVideoURl());
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
    }
}
