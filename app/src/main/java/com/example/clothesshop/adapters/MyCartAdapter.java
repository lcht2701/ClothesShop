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
import com.example.clothesshop.models.MyCartModel;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    Context context;
    List<MyCartModel> myCartModels;
    int amountTotalPrice = 0;

    public MyCartAdapter(Context context, List<MyCartModel> myCartModels) {
        this.context = context;
        this.myCartModels = myCartModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCartAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_time,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(myCartModels.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(myCartModels.get(position).getProductName());
        holder.price.setText(myCartModels.get(position).getProductPrice());
        holder.date.setText(myCartModels.get(position).getCurrentDate());
        holder.time.setText(myCartModels.get(position).getCurrentTime());
        holder.quantity.setText(myCartModels.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(myCartModels.get(position).getTotalPrice()));


        //pass total amount to My Cart Fragment
        amountTotalPrice = amountTotalPrice + myCartModels.get(position).getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",amountTotalPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return myCartModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,date,time,quantity,totalPrice;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            quantity = itemView.findViewById(R.id.total_quantity);
            totalPrice = itemView.findViewById(R.id.total_price);
            imageView = itemView.findViewById(R.id.cart_image);
        }
    }
}
