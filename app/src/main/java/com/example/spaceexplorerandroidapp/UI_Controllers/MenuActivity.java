package com.example.spaceexplorerandroidapp.UI_Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.spaceexplorerandroidapp.R;
import com.example.spaceexplorerandroidapp.UI_Controllers.Highscore.HighScore;
import com.example.spaceexplorerandroidapp.Utilities.BackgroundSound;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.materialswitch.MaterialSwitch;

public class MenuActivity extends AppCompatActivity {

    private ShapeableImageView menu_IMG_background;
    private MaterialSwitch menu_SWITCH_sensors;
    private MaterialSwitch menu_SWITCH_speed;
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
    @Override
    protected void onResume() {
        super.onResume();
        playSound("menu");

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopSound();

    }

    private void findViews() {
        menu_IMG_background = findViewById(R.id.menu_IMG_background);
        menu_SWITCH_sensors = findViewById(R.id.menu_SWITCH_sensors);
        menu_SWITCH_speed = findViewById(R.id.menu_SWITCH_speed);
        menu_BTN_start = findViewById(R.id.menu_BTN_start);
        menu_BTN_score = findViewById(R.id.menu_BTN_score);

        menu_BTN_start.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("speed",menu_SWITCH_speed.isChecked());
            i.putExtra("sensors",menu_SWITCH_sensors.isChecked());
            startActivity(i);
            finish();
        });

        menu_BTN_score.setOnClickListener(v -> {
            startActivity(new Intent(this, HighScore.class));
            finish();
        });
    }
    private void playSound(String sound_name) {
        BackgroundSound.getInstance().playSound(sound_name);
    }
    private void stopSound() {
        BackgroundSound.getInstance().stopSound();
    }
}