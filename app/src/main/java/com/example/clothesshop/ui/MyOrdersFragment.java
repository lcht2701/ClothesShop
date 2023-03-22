package com.example.clothesshop.ui;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.clothesshop.R;
import com.example.clothesshop.adapters.MyOrdersAdapter;
import com.example.clothesshop.models.MyOrdersModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersFragment extends Fragment {
    FirebaseFirestore db;
    FirebaseAuth auth;
    TextView overTotalAmount;
    RecyclerView recyclerView;
    MyOrdersAdapter myOrdersAdapter;
    List<MyOrdersModel> myOrdersModels;
    ProgressBar progressBar;

    public MyOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_orders, container, false);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        progressBar = root.findViewById(R.id.order_progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = root.findViewById(R.id.order_recycler_view);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        overTotalAmount = root.findViewById(R.id.textView6);

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageRecevier,new IntentFilter("MyTotalAmount"));

        myOrdersModels = new ArrayList<>();
        myOrdersAdapter = new MyOrdersAdapter(getActivity(),myOrdersModels);
        recyclerView.setAdapter(myOrdersAdapter);
        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("MyOrder").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            if(task.getResult().isEmpty()) {
                                root.findViewById(R.id.order_constrain1).setVisibility(View.VISIBLE);
                            }
                            for(DocumentSnapshot documentSnapshot : task.getResult()) {


                                MyOrdersModel myOrdersModel = documentSnapshot.toObject(MyOrdersModel.class);
                                assert myOrdersModel != null;
                                myOrdersModels.add(myOrdersModel);
                                myOrdersAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
        return root;
    }
    public BroadcastReceiver mMessageRecevier = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill = intent.getIntExtra("totalAmount",0);
            overTotalAmount.setText("Total Bill : " + totalBill + "$");

        }
    };
}