package com.example.clothesshop.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clothesshop.R;
import com.example.clothesshop.activities.DetailActivity;
import com.example.clothesshop.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder>{
    Context context;
    List<ViewAllModel> allModels;

    public ViewAllAdapter(Context context, List<ViewAllModel> allModels) {
        this.context = context;
        this.allModels = allModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewAllAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(allModels.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(allModels.get(position).getName());
        holder.description.setText(allModels.get(position).getDescription());
        holder.rating.setText(allModels.get(position).getRating());
        holder.price.setText(allModels.get(position).getPrice()+"$");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detail", allModels.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,description,price,rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.view_img);
            name = itemView.findViewById(R.id.view_name);
            description= itemView.findViewById(R.id.view_description);
            price = itemView.findViewById(R.id.view_price);
            rating= itemView.findViewById(R.id.view_rating);

        }
    }
}
