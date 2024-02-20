package com.example.spaceexplorerandroidapp.UI_Controllers.Highscore;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spaceexplorerandroidapp.Adapters.HighscoreAdapter;
import com.example.spaceexplorerandroidapp.Interfaces.HighscoreCallback;
import com.example.spaceexplorerandroidapp.Model.HighscoreData;
import com.example.spaceexplorerandroidapp.Model.HighscoreDataList;
import com.example.spaceexplorerandroidapp.R;

import java.util.ArrayList;


public class HighscoreList extends Fragment {

    private HighscoreDataList highscoreList;
    private Context applicationContext;
    private RecyclerView main_LST_highscore;
    private HighscoreCallback callbackHighScoreClicked;

    public HighscoreList(HighscoreDataList highscore, Context Context) {
        int limit = Math.min(highscore.getHighscoreArrayList().size(), 10);
        highscoreList = new HighscoreDataList().setHighscoreArrayList(new ArrayList<>(highscore.getHighscoreArrayList().subList(0, limit)));
        applicationContext = Context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_highscore_list, container, false);

        findViews(view);
        initViews(view);

        return view;
    }

    public void setCallbackHighScoreClicked(HighscoreCallback callbackHighScoreClicked) {
        this.callbackHighScoreClicked = callbackHighScoreClicked;
    }

    private void initViews(View view) {
        HighscoreAdapter highscoreAdapter = new HighscoreAdapter(applicationContext, highscoreList.getHighscoreArrayList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(applicationContext);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_highscore.setLayoutManager(linearLayoutManager);
        main_LST_highscore.setAdapter(highscoreAdapter);
        highscoreAdapter.setHighscoreCallback(this.callbackHighScoreClicked);



    }

    private void findViews(View view) {
        main_LST_highscore = view.findViewById(R.id.main_LST_highscore);
    }
}