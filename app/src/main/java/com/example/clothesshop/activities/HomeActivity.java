package com.example.clothesshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.clothesshop.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        if (auth.getCurrentUser() != null){
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            Toast.makeText(this, "Please wait ...", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void Login(View view){
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
    }

    public void Register(View view){
        startActivity(new Intent(HomeActivity.this,RegistrationActivity.class));
    }
}