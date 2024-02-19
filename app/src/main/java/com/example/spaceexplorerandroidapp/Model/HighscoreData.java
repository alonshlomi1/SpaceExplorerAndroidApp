package com.example.spaceexplorerandroidapp.Model;

import java.util.Date;

public class HighscoreData {
    private Date date = null;
    private int score = 0;
    private double lat = 0;
    private double lon = 0;

    public HighscoreData() {
    }

    public Date getDate() {
        return date;
    }

    public HighscoreData setDate(Date date) {
        this.date = date;
        return this;
    }

    public int getScore() {
        return score;
    }

    public HighscoreData setScore(int score) {
        this.score = score;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public HighscoreData setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public HighscoreData setLon(double lon) {
        this.lon = lon;
        return this;
    }

    @Override
    public String toString() {
        return "HighscoreData{" +
                "date=" + date +
                ", score=" + score +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
