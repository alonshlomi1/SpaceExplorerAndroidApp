package com.example.spaceexplorerandroidapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spaceexplorerandroidapp.Model.HighscoreData;
import com.example.spaceexplorerandroidapp.R;
import com.example.spaceexplorerandroidapp.Utilities.ImageLoader;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class HighscoreAdapter extends RecyclerView.Adapter<HighscoreAdapter.HighscoreViewHolder> {
    private Context context;
    private ArrayList<HighscoreData> highscores;

    public HighscoreAdapter(Context context, ArrayList<HighscoreData> highscores) {
        this.context = context;
        this.highscores = highscores;
    }


    @NonNull
    @Override
    public HighscoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_highscore_item, parent, false);
        return new HighscoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HighscoreViewHolder holder, int position) {
        HighscoreData highscoreData = getItem(position);

        holder.card_LBL_date.setText(highscoreData.getDate().toString());
        holder.card_LBL_score.setText(highscoreData.getScore() + "");
    }

    @Override
    public int getItemCount() {
        return highscores == null ? 0 : highscores.size();
    }

    private HighscoreData getItem(int position) {
        return highscores.get(position);
    }

    public class HighscoreViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView card_LBL_date;
        private MaterialTextView card_LBL_score;

        public HighscoreViewHolder(@NonNull View itemView) {
            super(itemView);
            card_LBL_date = itemView.findViewById(R.id.card_LBL_date);
            card_LBL_score = itemView.findViewById(R.id.card_LBL_score);
        }
    }
}
