package com.example.spaceexplorerandroidapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spaceexplorerandroidapp.Interfaces.HighscoreCallback;
import com.example.spaceexplorerandroidapp.Model.HighscoreData;
import com.example.spaceexplorerandroidapp.R;
import com.example.spaceexplorerandroidapp.Utilities.ImageLoader;
import com.google.android.material.textview.MaterialTextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HighscoreAdapter extends RecyclerView.Adapter<HighscoreAdapter.HighscoreViewHolder> {
    private Context context;
    private ArrayList<HighscoreData> highscores;
    private HighscoreCallback highscoreCallback;


    public HighscoreAdapter(Context context, ArrayList<HighscoreData> highscore) {
        this.context = context;
        this.highscores = highscore;
    }

    public HighscoreAdapter setHighscoreCallback(HighscoreCallback highscoreCallback) {
        this.highscoreCallback = highscoreCallback;
        return this;
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
        DateFormat dateFormat = new SimpleDateFormat("hh:mm dd/mm/yy");
        String strDate = dateFormat.format(highscoreData.getDate());
        holder.card_LBL_date.setText(strDate);
        holder.card_LBL_score.setText(highscoreData.getScore() + "");

        holder.card_LST_highscore.setOnClickListener(v ->
        {
            highscoreCallback.highscoreClicked(getItem(holder.getAdapterPosition()),holder.getAdapterPosition());

        });
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
        private CardView card_LST_highscore;
        public HighscoreViewHolder(@NonNull View itemView) {
            super(itemView);
            card_LBL_date = itemView.findViewById(R.id.card_LBL_date);
            card_LBL_score = itemView.findViewById(R.id.card_LBL_score);
            card_LST_highscore = itemView.findViewById(R.id.card_LST_highscore);

        }
    }
}
