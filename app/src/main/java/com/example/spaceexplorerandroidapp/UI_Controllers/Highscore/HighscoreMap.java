package com.example.spaceexplorerandroidapp.UI_Controllers.Highscore;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spaceexplorerandroidapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

public class HighscoreMap extends Fragment implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private MaterialTextView map_LBL_cord;
    public HighscoreMap() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_highscore_map, container, false);
        findView(view);
        return view;
    }

    private void findView(View view) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        Log.d("@@@@@@@@@@@@@@@@@@@@@@@", String.valueOf(mapFragment));
        mapFragment.getMapAsync(this);
        //map_LBL_cord = view.findViewById(R.id.map_LBL_cord);
    }

    public void setMap(double lat, double lon) {
       // map_LBL_cord.setText("lat: "+ lat+ " lon: "+ lon);
        LatLng location = new LatLng(lat, lon);
        googleMap.addMarker(new MarkerOptions().position(location));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng location = new LatLng(31.4117257, 35.0818155);
        //googleMap.addMarker(new MarkerOptions().position(location));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 7));
    }
}