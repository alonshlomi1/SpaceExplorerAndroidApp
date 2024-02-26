package com.example.spaceexplorerandroidapp.UI_Controllers;

import static com.example.spaceexplorerandroidapp.UI_Controllers.Highscore.HighScore.HIGHSCORE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.spaceexplorerandroidapp.Interfaces.TiltCallback;
import com.example.spaceexplorerandroidapp.Logic.GameManager;
import com.example.spaceexplorerandroidapp.Model.GameObject;
import com.example.spaceexplorerandroidapp.Model.HighscoreData;
import com.example.spaceexplorerandroidapp.Model.HighscoreDataList;
import com.example.spaceexplorerandroidapp.R;
import com.example.spaceexplorerandroidapp.UI_Controllers.Highscore.HighScore;
import com.example.spaceexplorerandroidapp.Utilities.GPSManager;
import com.example.spaceexplorerandroidapp.Utilities.SharedPreferencesManager;
import com.example.spaceexplorerandroidapp.Utilities.ShortSound;
import com.example.spaceexplorerandroidapp.Utilities.SignalManager;
import com.example.spaceexplorerandroidapp.Utilities.BackgroundSound;
import com.example.spaceexplorerandroidapp.Utilities.TiltDetector;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ShapeableImageView main_IMG_background;
    private ShapeableImageView[] main_IMG_hearts;
    private MaterialTextView main_LBL_score;
    private ExtendedFloatingActionButton main_FAB_left;
    private ExtendedFloatingActionButton main_FAB_right;
    private ShapeableImageView[][] main_ING_grid;
    private GameManager gameManager;
    private static long frame_delay = 900;
    private static Boolean play_with_sensors = false;

    private boolean timerOn = false;
    private Timer timer;
    private TiltDetector tiltDetector;
    private GPSManager gpsManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getBoolean("speed"))
                frame_delay = 450;
            else
                frame_delay = 900;
            play_with_sensors = extras.getBoolean("sensors");
        }
        findViews();
        Glide.with(this)
                .load(R.drawable.space_backround2)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(main_IMG_background);
        gameManager = new GameManager(main_IMG_hearts.length, main_ING_grid.length, main_ING_grid[0].length).setStart();
        refreshUI();
        if(!play_with_sensors){
            main_FAB_left.setOnClickListener(view -> arrowClick(-1));
            main_FAB_right.setOnClickListener(view -> arrowClick(1));
            main_FAB_left.setVisibility(View.VISIBLE);
            main_FAB_right.setVisibility(View.VISIBLE);
        }
        else{
            initTiltDetector();
            main_FAB_left.setVisibility(View.INVISIBLE);
            main_FAB_right.setVisibility(View.INVISIBLE);
        }
        initGpsManager();
        startTimer();
        playSound("game");

    }




    private void initTiltDetector() {
        tiltDetector = new TiltDetector(this, new TiltCallback() {
            @Override
            public void moveRight() {
                arrowClick(-1);
            }
            @Override
            public void moveLeft() {
                arrowClick(1);
            }
            @Override
            public void moveFaster() {
            }
            @Override
            public void moveSlower() {
            }
        });
    }

    private void initGpsManager() {
        gpsManager = new GPSManager(this);
        gpsManager.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
        if(play_with_sensors)
            tiltDetector.start();
        gpsManager.start();
        playSound("game");

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer();
        if(play_with_sensors)
            tiltDetector.stop();
        gpsManager.stop();
        stopSound();

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    private void stopTimer() {
        timerOn = false;
        timer.cancel();
    }

    private void startTimer() {
        if(!timerOn){
            timerOn = true;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> nextFrame());
                }
            }, 0, frame_delay);
        }
    }
    private void nextFrame(){
        gameManager.nextFrame();

        gameManager.randomNewAsteroid();
        refreshUI();
    }
    private void arrowClick(int n) {
        //ShortSound.getInstance().playSound("move");
        gameManager.moveSpaceship(n);
        refreshUI();
    }

    @SuppressLint("DefaultLocale")
    private void refreshUI() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 5; col++) {
                main_ING_grid[row][col].setVisibility(View.INVISIBLE);
            }
        }
        for(GameObject gameObject : gameManager.getGameObjectList()) {
            main_ING_grid[gameObject.getRow()][gameObject.getCol()].setImageResource(gameObject.getImage());
            main_ING_grid[gameObject.getRow()][gameObject.getCol()].setVisibility(View.VISIBLE);
        }


        int flag = gameManager.checkCrush();
        if(flag == 1){
            main_ING_grid[gameManager.getSpaceship().getRow()][gameManager.getSpaceship().getCol()].setImageResource(gameManager.getRandomCrushSrc());
            setCurrentLife();
            if(gameManager.getCrushes() == gameManager.getLife()){ //if game ends
                ShortSound.getInstance().playSound("gameover");
                toastAndVibrate("you Lost!", 1000);
                saveScore();
                startActivity(new Intent(this, HighScore.class));
                finish();
            }
            else {
                toastAndVibrate("BOOM!", 500);
                ShortSound.getInstance().playSound("crash");
            }

        }
        else if (flag == 2){
            ShortSound.getInstance().playSound("coin");
            main_ING_grid[gameManager.getSpaceship().getRow()][gameManager.getSpaceship().getCol()].setImageResource(gameManager.getSpaceship().getImage());
            main_ING_grid[gameManager.getSpaceship().getRow()][gameManager.getSpaceship().getCol()].setVisibility(View.VISIBLE);
        }
        else {
            main_ING_grid[gameManager.getSpaceship().getRow()][gameManager.getSpaceship().getCol()].setImageResource(gameManager.getSpaceship().getImage());
            main_ING_grid[gameManager.getSpaceship().getRow()][gameManager.getSpaceship().getCol()].setVisibility(View.VISIBLE);
        }
        main_LBL_score.setText(String.format("%03d", gameManager.getScore()));
    }

    private void playSound(String sound_name) {
        BackgroundSound.getInstance().playSound(sound_name);
    }
    private void stopSound() {
        BackgroundSound.getInstance().stopSound();
    }

    private void saveScore() {
        HighscoreDataList highscoreList = new Gson().fromJson(SharedPreferencesManager.getInstance().getString(HIGHSCORE, ""), HighscoreDataList.class);
        if(highscoreList == null)
            highscoreList = new HighscoreDataList().setHighscoreArrayList(new ArrayList<>());
        highscoreList.addHighscore(new HighscoreData()
                .setDate(new Date())
                .setScore(gameManager.getScore())
                .setLat(gpsManager.getLat())
                .setLon(gpsManager.getLon()));
        Gson gson = new Gson();
        String highscoreListAsJson = gson.toJson(highscoreList);
        SharedPreferencesManager.getInstance().putString(HIGHSCORE, highscoreListAsJson);
    }

    private void setCurrentLife() {
        for(int i=0; i< main_IMG_hearts.length; i++) {
            main_IMG_hearts[i].setVisibility(main_IMG_hearts.length - gameManager.getCrushes() > i ? View.VISIBLE : View.INVISIBLE);
        }


    }
    private void toastAndVibrate(String text, long milliseconds) {
        SignalManager.getInstance().vibrate(milliseconds);
        SignalManager.getInstance().toast(text);
    }


    private void findViews() {
        main_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3),
        };
        main_LBL_score= findViewById(R.id.main_LBL_score);
        main_FAB_left = findViewById(R.id.main_FAB_left);
        main_FAB_left.setAlpha(0.9f);
        main_FAB_right = findViewById(R.id.main_FAB_right);
        //main_FAB_right.setAlpha(0.75f);
        main_IMG_background = findViewById(R.id.main_IMG_background);

        // Initialize the grid array
        main_ING_grid = new ShapeableImageView[9][5]; // Assuming 8 rows and 3 columns
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 5; col++) {
                @SuppressLint("DiscouragedApi") int viewId = getResources().getIdentifier("main_IMG_grid" + ((row * 5) + col + 1), "id", getPackageName());
                main_ING_grid[row][col] = findViewById(viewId);
                main_ING_grid[row][col].setVisibility(View.INVISIBLE);
            }
        }

    }

}