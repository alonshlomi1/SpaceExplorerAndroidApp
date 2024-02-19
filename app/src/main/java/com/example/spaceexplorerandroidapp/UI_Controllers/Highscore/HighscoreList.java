package com.example.spaceexplorerandroidapp.UI_Controllers.Highscore;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spaceexplorerandroidapp.Adapters.HighscoreAdapter;
import com.example.spaceexplorerandroidapp.Model.HighscoreDataList;
import com.example.spaceexplorerandroidapp.R;


public class HighscoreList extends Fragment {

    private HighscoreDataList highscoreList;
    private Context applicationContext;
    private RecyclerView main_LST_highscore;
    public HighscoreList(HighscoreDataList highscores, Context applicationContext) {
        highscoreList = highscores;
        applicationContext = applicationContext;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_highscore_list, container, false);

        findViews(view);
        initViews(view);

        return view;
    }

    private void initViews(View view) {
        HighscoreAdapter highscoreAdapter = new HighscoreAdapter(applicationContext, highscoreList.getHighscoreArrayList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(applicationContext);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_highscore.setLayoutManager(linearLayoutManager);
        main_LST_highscore.setAdapter(highscoreAdapter);

    }

    private void findViews(View view) {
        main_LST_highscore = view.findViewById(R.id.main_LST_highscore);
    }
}