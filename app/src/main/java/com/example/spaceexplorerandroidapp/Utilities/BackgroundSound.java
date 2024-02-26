package com.example.spaceexplorerandroidapp.Utilities;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.spaceexplorerandroidapp.R;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class BackgroundSound {
    private Context context;
    private Executor executor;
    private Handler handler;
    private MediaPlayer mediaPlayer;
    private Dictionary<String, Integer> sound_dict ;

    private static BackgroundSound instance;

    private BackgroundSound(Context context) {
        this.context = context;
    }

    public static BackgroundSound getInstance() {
        return instance;
    }

    public static BackgroundSound initBackgroundSound(Context context) {
        if (instance == null)
            instance = new BackgroundSound(context);


        instance.executor = Executors.newSingleThreadExecutor();
        instance.sound_dict = new Hashtable<>();

        // Adding elements to the dictionary
        instance.sound_dict.put("coin", R.raw.coin_sound);
        instance.sound_dict.put("move", R.raw.move_sound);
        instance.sound_dict.put("menu", R.raw.manu_sound);
        instance. sound_dict.put("game", R.raw.game_sound);
        instance.handler = new Handler() {
            @Override
            public void publish(LogRecord record) {

            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        };
        return instance;
    }


    public void playSound(String sound){
        executor.execute(() -> {
            mediaPlayer = MediaPlayer.create(context, sound_dict.get(sound));
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(1.0f, 1.0f);
            mediaPlayer.start();
        });
    }

    public void stopSound(){
        if(mediaPlayer != null){
            executor.execute(() -> {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            });
        }

    }
}
