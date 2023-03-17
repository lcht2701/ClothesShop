package com.example.clothesshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;


import com.example.clothesshop.R;
import com.example.clothesshop.adapters.ViewAllAdapter;
import com.example.clothesshop.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ViewAllActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ViewAllAdapter viewAllAdapter;
    List<ViewAllModel> viewAllModels;
    FirebaseFirestore firestore;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        firestore = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.view_all_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewAllModels = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(this, viewAllModels);

        recyclerView.setAdapter(viewAllAdapter);
        //Get Tee
        if (type != null && type.equalsIgnoreCase("tee")) {
            firestore.collection("AllProducts").whereEqualTo("type", "tee")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                                viewAllModels.add(viewAllModel);
                                viewAllAdapter.notifyDataSetChanged();
                            }
                        }
                    });
        }
        //Get Jacket
        if (type != null && type.equalsIgnoreCase("jacket")) {
            firestore.collection("AllProducts").whereEqualTo("type", "jacket")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                                viewAllModels.add(viewAllModel);
                                viewAllAdapter.notifyDataSetChanged();
                            }
                        }
                    });
        }
        //Get Pants
        if (type != null && type.equalsIgnoreCase("pants")) {
            firestore.collection("AllProducts").whereEqualTo("type", "pants")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                                viewAllModels.add(viewAllModel);
                                viewAllAdapter.notifyDataSetChanged();
                            }
                        }
                    });
        }
        //Get Accessories
        if (type != null && type.equalsIgnoreCase("accessories")) {
            firestore.collection("AllProducts").whereEqualTo("type", "accessories")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                                viewAllModels.add(viewAllModel);
                                viewAllAdapter.notifyDataSetChanged();
                            }
                        }
                    });
        }
        //Get Sweats
        if (type != null && type.equalsIgnoreCase("sweat")) {
            firestore.collection("AllProducts").whereEqualTo("type", "sweat")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                                viewAllModels.add(viewAllModel);
                                viewAllAdapter.notifyDataSetChanged();
                            }
                        }
                    });
        }
    }

}