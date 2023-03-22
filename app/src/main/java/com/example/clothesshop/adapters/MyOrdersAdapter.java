package com.example.clothesshop.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clothesshop.R;
import com.example.clothesshop.models.MyOrdersModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {
    Context context;
    List<MyOrdersModel> myOrdersModels;
    int amountTotalPrice = 0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public MyOrdersAdapter(Context context, List<MyOrdersModel> myOrdersModels) {
        this.context = context;
        this.myOrdersModels = myOrdersModels;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyOrdersAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_time,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(myOrdersModels.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(myOrdersModels.get(position).getProductName());
        holder.price.setText(myOrdersModels.get(position).getProductPrice());
        holder.date.setText(myOrdersModels.get(position).getCurrentDate());
        holder.time.setText(myOrdersModels.get(position).getCurrentTime());
        holder.quantity.setText(myOrdersModels.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(myOrdersModels.get(position).getTotalPrice()));
        //pass total amount to My Cart Fragment
        amountTotalPrice = amountTotalPrice + myOrdersModels.get(position).getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",amountTotalPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return myOrdersModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,date,time,quantity,totalPrice;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.order_name);
            price = itemView.findViewById(R.id.order_price);
            date = itemView.findViewById(R.id.order_current_date);
            time = itemView.findViewById(R.id.order_current_time);
            quantity = itemView.findViewById(R.id.order_total_quantity);
            totalPrice = itemView.findViewById(R.id.order_total_price);
            imageView = itemView.findViewById(R.id.order_image);
        }
    }
}
