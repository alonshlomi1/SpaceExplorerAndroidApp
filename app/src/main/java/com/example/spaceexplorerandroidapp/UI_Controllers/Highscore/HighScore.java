package com.example.spaceexplorerandroidapp.UI_Controllers.Highscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.example.spaceexplorerandroidapp.Interfaces.HighscoreCallback;
import com.example.spaceexplorerandroidapp.Model.HighscoreData;
import com.example.spaceexplorerandroidapp.Model.HighscoreDataList;
import com.example.spaceexplorerandroidapp.R;
import com.example.spaceexplorerandroidapp.UI_Controllers.MenuActivity;
import com.example.spaceexplorerandroidapp.Utilities.SharedPreferencesManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


public class HighScore extends AppCompatActivity {
    public static final String HIGHSCORE = "HIGHSCORE";

    private FrameLayout main_FRAME_list;
    private FrameLayout main_FRAME_map;
    private MaterialButton score_BTN_main;

    private HighscoreMap mapFragment;
    private HighscoreList listFragment;
    private ShapeableImageView score_IMG_background;

    private HighscoreDataList highscoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        findViews();
        Glide.with(this)
                .load(R.drawable.space_backround1)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(score_IMG_background);
        getData();

        mapFragment = new HighscoreMap();
        listFragment = new HighscoreList(highscoreList, getApplicationContext());
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_list, listFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_map, mapFragment).commit();
        listFragment.setCallbackHighScoreClicked(new HighscoreCallback() {
            @Override
            public void highscoreClicked(HighscoreData highscoreData, int position) {
                mapFragment.setMap(highscoreData.getLat(),highscoreData.getLon());
            }
        });


        score_BTN_main.setOnClickListener(v -> {
            startActivity(new Intent(this, MenuActivity.class));
            finish();
        });

    }

    private void getData() {
        highscoreList = new Gson().fromJson(SharedPreferencesManager.getInstance().getString(HIGHSCORE, ""), HighscoreDataList.class);
        if(highscoreList == null)
            highscoreList = new HighscoreDataList().setHighscoreArrayList(new ArrayList<>());
        Log.d("@@@@@@@@@@@@@@@@@@@@@@@@@", String.valueOf(highscoreList));
        Collections.sort(highscoreList.getHighscoreArrayList(), Comparator.comparingInt(HighscoreData::getScore).reversed());
    }

    private void findViews() {

        main_FRAME_list = findViewById(R.id.main_FRAME_list);
        main_FRAME_map = findViewById(R.id.main_FRAME_map);
        score_BTN_main = findViewById(R.id.score_BTN_main);
        score_IMG_background = findViewById(R.id.score_IMG_background);
    }
}