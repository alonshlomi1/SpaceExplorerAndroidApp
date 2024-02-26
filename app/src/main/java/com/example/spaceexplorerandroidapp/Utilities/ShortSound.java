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

public class ShortSound{
    private Context context;
    private Executor executor;
    private Handler handler;
    private MediaPlayer mediaPlayer;
    private Dictionary<String, Integer> sound_dict ;

    private static ShortSound instance;

    private ShortSound(Context context) {
        this.context = context;
    }

    public static ShortSound getInstance() {
        return instance;
    }

    public static ShortSound initShortSound(Context context) {
        if (instance == null)
            instance = new ShortSound(context);


        instance.executor = Executors.newSingleThreadExecutor();
        instance.sound_dict = new Hashtable<>();

        // Adding elements to the dictionary
        instance.sound_dict.put("coin", R.raw.coin_sound);
        instance.sound_dict.put("move", R.raw.move_sound);
        instance.sound_dict.put("crash", R.raw.crash_sound);
        instance.sound_dict.put("gameover", R.raw.game_over_sound);

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
            mediaPlayer.setLooping(false);
            mediaPlayer.setVolume(1.0f, 1.0f);
            mediaPlayer.start();
        });
    }

}
