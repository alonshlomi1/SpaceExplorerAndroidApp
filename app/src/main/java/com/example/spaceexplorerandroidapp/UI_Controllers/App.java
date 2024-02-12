package com.example.spaceexplorerandroidapp.UI_Controllers;

import android.app.Application;

import com.example.spaceexplorerandroidapp.Utilities.SignalManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SignalManager.init(this);
    }
}
