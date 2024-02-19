package com.example.spaceexplorerandroidapp.Model;

import com.example.spaceexplorerandroidapp.UI_Controllers.Highscore.HighScore;

import java.util.ArrayList;

public class HighscoreDataList {

    private ArrayList<HighscoreData> highScoreArrayList = new ArrayList<>();

    public HighscoreDataList(){}

    public ArrayList<HighscoreData> getHighscoreArrayList() {
        return highScoreArrayList;
    }

    public HighscoreDataList setHighscoreArrayList(ArrayList<HighscoreData> highScoreArrayList) {
        this.highScoreArrayList = highScoreArrayList;
        return this;
    }

    public HighscoreDataList addHighscore(HighscoreData highscore) {
        this.highScoreArrayList.add(highscore);
        return this;
    }

    @Override
    public String toString() {
        return "HighscoreList{" +
                "highScoreArrayList=" + highScoreArrayList +
                '}';
    }
}
