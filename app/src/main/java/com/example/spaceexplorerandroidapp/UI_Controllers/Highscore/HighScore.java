package com.example.spaceexplorerandroidapp.UI_Controllers.Highscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.example.spaceexplorerandroidapp.R;
import com.example.spaceexplorerandroidapp.UI_Controllers.MainActivity;
import com.example.spaceexplorerandroidapp.UI_Controllers.MenuActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

public class HighScore extends AppCompatActivity {

    private FrameLayout main_FRAME_list;
    private FrameLayout main_FRAME_map;
    private MaterialButton score_BTN_main;

    private HighscoreMap mapFragment;
    private HighscoreList listFragment;
    private ShapeableImageView score_IMG_background;

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

        listFragment = new HighscoreList();
        mapFragment = new HighscoreMap();
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_list, listFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_map, mapFragment).commit();

        score_BTN_main.setOnClickListener(v -> {
            startActivity(new Intent(this, MenuActivity.class));
            finish();
        });
    }

    private void findViews() {

        main_FRAME_list = findViewById(R.id.main_FRAME_list);
        main_FRAME_map = findViewById(R.id.main_FRAME_map);
        score_BTN_main = findViewById(R.id.score_BTN_main);
        score_IMG_background = findViewById(R.id.score_IMG_background);
    }
}