package com.example.clothesshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.clothesshop.R;
import com.example.clothesshop.models.ViewAllModel;

public class DetailActivity extends AppCompatActivity {
    ImageView detailedImg;
    TextView name,price,rating,description;
    Button addToCart;
    ImageView addItem, removeItem;
    Toolbar toolbar;
    ViewAllModel viewAllModel = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel) {
            viewAllModel = (ViewAllModel) object;
        }

        detailedImg = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);
        price = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);
        name = findViewById(R.id.detailed_name);
        description = findViewById(R.id.detailed_rec);

        if(viewAllModel != null) {
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            name.setText(viewAllModel.getName());
            price.setText("Price: " + viewAllModel.getPrice() + "$");

        }
        addToCart = findViewById(R.id.add_to_cart);
    }
}