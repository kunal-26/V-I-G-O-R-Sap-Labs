package com.Hatchback.Vigor.Mood;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.Hatchback.Vigor.R;

public class moodCalendarHolder extends RecyclerView.ViewHolder {

    TextView date;
    CardView cardView;

    public moodCalendarHolder(@NonNull View itemView) {
        super(itemView);

        this.date = itemView.findViewById(R.id.itemDate);
        this.cardView = itemView.findViewById(R.id.moodItemCard);
    }
}
