package com.finansalbt.onlinequiz.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.finansalbt.onlinequiz.R;

public class ScoreDeteilViewHolder extends RecyclerView.ViewHolder {

    public TextView txt_name,txt_score;

    public ScoreDeteilViewHolder(View itemView) {
        super(itemView);

        txt_name=itemView.findViewById(R.id.txt_name);
        txt_score=itemView.findViewById(R.id.txt_score);
    }
}
