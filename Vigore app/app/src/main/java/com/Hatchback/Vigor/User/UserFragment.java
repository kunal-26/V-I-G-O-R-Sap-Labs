package com.Hatchback.Vigor.User;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Hatchback.Vigor.AvatarSelect.AvatarSelectActivity;
import com.Hatchback.Vigor.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private View v;
    public static ImageView userAvatar;
    private static final String TAG = "UserFragment";
    private ArrayList<String> settingsList;
    SharedPreferences userPref, avatarPref;
    private TextView userName;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_user, container, false);

        userAvatar = v.findViewById(R.id.userAvatar);
        String resName = userPref.getString("selectedAvatar", "avatar_trees");
        int resId = getResources().getIdentifier(resName, "drawable", getContext().getPackageName());
        userAvatar.setImageResource(resId);
        userAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserFragment.this.getActivity(), AvatarSelectActivity.class);
                startActivity(intent);
            }
        });
        //userAvatar.setImageURI(Uri.parse("android.resource://" + v.getContext().getPackageName() + "/" + R.drawable.avatar_trees));

        userName = v.findViewById(R.id.userName);
        String username = userPref.getString("name", "Error getting Username");
        if (username.isEmpty()){
            userName.setText("No Username Set");
        }
        else{
            userName.setText(username);
        }

        RecyclerView userSettingsRecyclerView = v.findViewById(R.id.userSettingsRecyclerView);
        UserAdaptor userAdaptor = new UserAdaptor(settingsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());

        userSettingsRecyclerView.setLayoutManager(linearLayoutManager);
        userSettingsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        userSettingsRecyclerView.setAdapter(userAdaptor);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        userPref = this.getActivity().getSharedPreferences("com.android.meditate.User", Context.MODE_PRIVATE);

        settingsList = new ArrayList<>();
        settingsList.add("About");
        settingsList.add("Change Avatar");
        settingsList.add("Edit Profile");
        settingsList.add("Notifications");
        settingsList.add("Log Out");
    }
}
