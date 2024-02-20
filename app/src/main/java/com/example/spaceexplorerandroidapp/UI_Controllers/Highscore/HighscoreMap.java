package com.example.spaceexplorerandroidapp.UI_Controllers.Highscore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spaceexplorerandroidapp.R;
import com.google.android.material.textview.MaterialTextView;

public class HighscoreMap extends Fragment {
    private MaterialTextView map_LBL_cord;
    public HighscoreMap() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_highscore_map, container, false);
        findView(view);
        return view;
    }

    private void findView(View view) {
        map_LBL_cord = view.findViewById(R.id.map_LBL_cord);
    }

    public void setMap(double lat, double lon) {
        map_LBL_cord.setText("lat: "+ lat+ " lon: "+ lon);
    }
}