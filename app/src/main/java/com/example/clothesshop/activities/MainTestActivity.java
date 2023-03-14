package com.example.clothesshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.clothesshop.R;
import com.example.clothesshop.adapters.ClothesAdapter;
import com.example.clothesshop.models.Clothes;

import java.util.ArrayList;
import java.util.List;

public class MainTestActivity extends AppCompatActivity {
    RecyclerView recClothes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);

        //Item list RecycleView
        recClothes = findViewById(R.id.rec_clothes);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recClothes.setLayoutManager(gridLayoutManager);

        ClothesAdapter adapter = new ClothesAdapter(getListClothes());
        recClothes.setAdapter(adapter);

        //Image Slider
        ImageSlider imageSlider = findViewById(R.id.img_slider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.slider1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider5, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider6, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider7, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider8, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }

    private List<Clothes> getListClothes() {
        List<Clothes> list = new ArrayList<>();
        list.add(new Clothes(R.drawable.ao_mu, "Áo MU đáy xã hội","$ 1000" ));
        list.add(new Clothes(R.drawable.kimetsu, "Áo diệt quỷ","$ 1" ));
        list.add(new Clothes(R.drawable.harry, "Áo làm pháp sư","1000$" ));
        list.add(new Clothes(R.drawable.ao_wibu, "Áo WIBU","1000$" ));
        list.add(new Clothes(R.drawable.uchiha, "Áo WIBU đỉnh cao xã hội","1000$" ));
        list.add(new Clothes(R.drawable.hokage, "Áo Hokage","1000$" ));
        list.add(new Clothes(R.drawable.messi_kit, "Áo Messi","1000$" ));
        list.add(new Clothes(R.drawable.akatsuki, "áo Akatsuki cho Wibu","1000$" ));
        list.add(new Clothes(R.drawable.ao_mu, "Áo MU đáy xã hội","$ 1000" ));
        list.add(new Clothes(R.drawable.kimetsu, "Áo diệt quỷ","$ 1" ));
        list.add(new Clothes(R.drawable.harry, "Áo làm pháp sư","1000$" ));
        list.add(new Clothes(R.drawable.ao_wibu, "Áo WIBU","1000$" ));
        list.add(new Clothes(R.drawable.uchiha, "Áo WIBU đỉnh cao xã hội","1000$" ));
        list.add(new Clothes(R.drawable.hokage, "Áo Hokage","1000$" ));
        list.add(new Clothes(R.drawable.messi_kit, "Áo Messi","1000$" ));
        list.add(new Clothes(R.drawable.akatsuki, "áo Akatsuki cho Wibu","1000$" ));

        return list;
    }
}