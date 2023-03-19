package com.example.clothesshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothesshop.R;
import com.example.clothesshop.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    Button signUp;
    EditText name, email, password;
    TextView signIn;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressBar progressBar;
    RelativeLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        loadingLayout = (RelativeLayout) findViewById(R.id.loading_layout);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        loadingLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        signUp = findViewById(R.id.register_btn);
        name = findViewById(R.id.name_register);
        email = findViewById(R.id.email_register);
        password = findViewById(R.id.password_register);
        signIn = findViewById(R.id.sign_in);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
                loadingLayout.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
    private void createUser() {
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        if(TextUtils.isEmpty(userName)) {
            Toast.makeText(this,"Name is empty!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this,"Mail is empty!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this,"Password is empty!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPassword.length() < 6) {
            Toast.makeText(this,"Password length must be greater than 6 letters!",Toast.LENGTH_SHORT).show();
            return;
        }
        //Create User
        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {

                    UserModel userModel = new UserModel(userName,userEmail,userPassword);
                    String id = Objects.requireNonNull(task.getResult().getUser()).getUid();
                    database.getReference().child("Users").child(id).setValue(userModel);
                    loadingLayout.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    loadingLayout.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegistrationActivity.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}