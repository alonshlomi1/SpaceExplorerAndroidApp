package com.example.spaceexplorerandroidapp.Model;

import com.example.spaceexplorerandroidapp.R;

public class Coin extends GameObject{
    private static final int src = R.drawable.coin2;
    private static final int COIN_POINTS = 15;

    public Coin(){
        setImage(getSrc());
    }

       public static int getSrc(){return src;}

    public static int getPoints() {
        return COIN_POINTS;
    }
}
