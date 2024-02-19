package com.example.spaceexplorerandroidapp.UI_Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.spaceexplorerandroidapp.R;
import com.example.spaceexplorerandroidapp.UI_Controllers.Highscore.HighScore;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.materialswitch.MaterialSwitch;

public class MenuActivity extends AppCompatActivity {

    private ShapeableImageView menu_IMG_background;
    private MaterialSwitch menu_SWITCH_sensors;
    private MaterialButton menu_BTN_start;
    private MaterialButton menu_BTN_score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViews();
        Glide.with(this)
                .load(R.drawable.space_backround1)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(menu_IMG_background);
    }

    private void findViews() {
        menu_IMG_background = findViewById(R.id.menu_IMG_background);
        menu_SWITCH_sensors = findViewById(R.id.menu_SWITCH_sensors);
        menu_BTN_start = findViewById(R.id.menu_BTN_start);
        menu_BTN_score = findViewById(R.id.menu_BTN_score);

        menu_BTN_start.setOnClickListener(v -> {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        });

        menu_BTN_score.setOnClickListener(v -> {
            startActivity(new Intent(this, HighScore.class));
            finish();
        });
    }
}