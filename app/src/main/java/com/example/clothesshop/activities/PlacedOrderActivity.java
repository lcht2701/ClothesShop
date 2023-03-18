package com.example.clothesshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.clothesshop.R;
import com.example.clothesshop.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlacedOrderActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        List<MyCartModel> list = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");

        if(list != null && list.size() > 0) {
            for(MyCartModel myCartModel: list) {
                final HashMap<String,Object> cartMap = new HashMap<>();

                cartMap.put("productName", myCartModel.getProductName());
                cartMap.put("productPrice", myCartModel.getProductPrice());
                cartMap.put("currentDate", myCartModel.getCurrentDate());
                cartMap.put("currentTime", myCartModel.getCurrentTime());
                cartMap.put("totalQuantity", myCartModel.getTotalQuantity());
                cartMap.put("totalPrice", myCartModel.getTotalPrice());

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(PlacedOrderActivity.this, "Your Order Has Been Placed", Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        }
    }
}