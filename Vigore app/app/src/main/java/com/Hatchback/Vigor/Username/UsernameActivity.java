package com.Hatchback.Vigor.Username;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Hatchback.Vigor.Login.LoginActivity;
import com.Hatchback.Vigor.MainActivity;
import com.Hatchback.Vigor.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UsernameActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText usernameInput;
    private Button usernameButton;

    private static final String TAG = "UsernameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        auth = FirebaseAuth.getInstance();

        usernameInput = (EditText) findViewById(R.id.usernameInput);
        usernameButton = (Button) findViewById(R.id.usernameButton);

        usernameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Username Button Clicked");
                final String usernameInputText = usernameInput.getEditableText().toString();
                if (usernameInputText.trim().isEmpty() || usernameInputText.isEmpty()){
                    Toast.makeText(UsernameActivity.this, "Please provide a valid Username", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    // Access a Cloud Firestore instance from your Activity
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    try{
                        DocumentReference docRef = db.collection("users").document(auth.getCurrentUser().getUid());
                        docRef.update("name", usernameInputText)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) { // Database update successful
                                        Log.v(TAG, "Successfully updated name field in database"); // Log success
                                        System.out.println(usernameInputText);
                                        SharedPreferences userPref = UsernameActivity.this.getSharedPreferences("com.android.meditate.User", Context.MODE_PRIVATE);
                                        userPref.edit().putString("name", usernameInputText).apply(); // Edit sharedPref "name" field
                                        Intent toMain = new Intent(UsernameActivity.this, MainActivity.class); // Intent to MainActivity
                                        startActivity(toMain); // start MainActivity
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() { // Database update failed
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.v(TAG, "Failed to update name field in database"); // Log failure
                                        Toast.makeText(getApplicationContext(), "Failed to set Username. Please try again.", Toast.LENGTH_SHORT).show(); // Toast message for failure
                                    }
                                });

                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Unable to retrieve user data", Toast.LENGTH_SHORT).show();
                        Log.v(TAG, "Cannot find document. Document may be deleted");
                        Intent intent = new Intent(UsernameActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        });

    }

}
