package com.Hatchback.Vigor.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Hatchback.Vigor.Meditation.MeditationActivity;
import com.Hatchback.Vigor.R;

import java.util.ArrayList;
import java.util.List;

public class MeditationAdapter extends RecyclerView.Adapter<MeditationHolder> {

    Context c;
    List<MeditationModel> models; //creates a list of array of models

    public MeditationAdapter(Context c, ArrayList<MeditationModel> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MeditationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_row, null);
        View view = LayoutInflater.from(c).inflate(R.layout.home_row,parent,false);
        final MeditationHolder vHolder = new MeditationHolder(view);

        vHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gTitle = models.get(vHolder.getAdapterPosition()).getTitle();
                String gDes = models.get(vHolder.getAdapterPosition()).getDescription();

                //intent to meditation activity
                Intent intent = new Intent(c, MeditationActivity.class);
                intent.putExtra("iTitle", gTitle);
                intent.putExtra("iDes", gDes);
                c.startActivity(intent);
            }
        });

        return vHolder; //return view to holder class
    }

    @Override
    public void onBindViewHolder(@NonNull final MeditationHolder holder, int position) {
        holder.mTitle.setText(models.get(position).getTitle());
        holder.mDes.setText(models.get(position).getDescription());
        holder.mImageView.setImageResource(models.get(position).getImg());

        //set card bg based on position
//        if(models.get(position).getTitle().equalsIgnoreCase("Sleep") || models.get(position).getTitle().equalsIgnoreCase("10 min guides"))
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#C6DEF1"));
//
//        else if (models.get(position).getTitle().equalsIgnoreCase("Stress & Anxiety") || models.get(position).getTitle().equalsIgnoreCase("White Noise"))
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#F7D9C4"));
//
//        else if (models.get(position).getTitle().equalsIgnoreCase("Self care"))
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#DBCDF0"));
//
//        else if (models.get(position).getTitle().equalsIgnoreCase("Midnight Thoughts") || models.get(position).getTitle().equalsIgnoreCase("Nature"))
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#E2CFC4"));
//
//        else if (models.get(position).getTitle().equalsIgnoreCase("Slow down"))
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#C9E4DE"));
//
//        else if (models.get(position).getTitle().equalsIgnoreCase("Piano"))
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#E2E2DF"));
//
//        else if (models.get(position).getTitle().equalsIgnoreCase("Rainy days"))
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#FAEDCB"));


    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
