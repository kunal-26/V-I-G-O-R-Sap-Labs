package com.Hatchback.Vigor.User;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Hatchback.Vigor.About.AboutActivity;
import com.Hatchback.Vigor.AvatarSelect.AvatarSelectActivity;
import com.Hatchback.Vigor.EditProfile.EditProfileActivity;
import com.Hatchback.Vigor.Login.LoginActivity;
import com.Hatchback.Vigor.Notification.Notification;
import com.Hatchback.Vigor.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class UserAdaptor extends RecyclerView.Adapter<UserViewHolder> {

    private static final String TAG = "UserAdaptor";
    private ArrayList<String> data;

    public UserAdaptor(ArrayList<String> settingsList){
        data = settingsList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_settings_row, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        holder.settingText.setText(data.get(position));
        String text = holder.settingText.getText().toString();
        if (text.equals("About")){
            holder.settingCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // GO TO ABOUT ACTIVITY
                    Intent intent = new Intent(view.getContext(), AboutActivity.class);
                    view.getContext().startActivity(intent);
                }
            });
        }
        else if (text.equals(("Change Avatar"))){
            holder.settingCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), AvatarSelectActivity.class);
                    view.getContext().startActivity(intent);

                }
            });
        }
        else if (text.equals(("Notifications"))){
            holder.settingCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Notification.class);
                    view.getContext().startActivity(intent);
                }
            });
        }
        else if (text.equals(("Edit Profile"))){
            holder.settingCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), EditProfileActivity.class);
                    view.getContext().startActivity(intent);
                }
            });
        }
        else if (text.equals("Log Out")){
            holder.settingCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
                    view.getContext().startActivity(intent);

                }
            });
        }
        else{
            holder.settingCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.v(TAG, "Button not assigned");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
