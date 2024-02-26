package com.example.spaceexplorerandroidapp.UI_Controllers;

import android.app.Application;

import com.example.spaceexplorerandroidapp.Utilities.BackgroundSound;
import com.example.spaceexplorerandroidapp.Utilities.ImageLoader;
import com.example.spaceexplorerandroidapp.Utilities.ShortSound;
import com.example.spaceexplorerandroidapp.Utilities.SignalManager;
import com.example.spaceexplorerandroidapp.Utilities.SharedPreferencesManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SignalManager.init(this);
        SharedPreferencesManager.init(this);
        ImageLoader.initImageLoader(this);
        BackgroundSound.initBackgroundSound(this);
        ShortSound.initShortSound(this);
    }
}
