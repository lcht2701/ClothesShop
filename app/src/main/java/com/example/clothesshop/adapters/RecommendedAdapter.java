package com.example.clothesshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clothesshop.R;
import com.example.clothesshop.models.RecommendedModel;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {
    Context context;
    List<RecommendedModel> list;

    public RecommendedAdapter(Context context, List<RecommendedModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommendedAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home_recommended, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imgView);
        holder.name.setText(list.get(position).getName());
        holder.des.setText(list.get(position).getDescription());
        holder.rating.setText(list.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView name, des, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.recommended_img);
            name = itemView.findViewById(R.id.recommended_name);
            des = itemView.findViewById(R.id.recommended_des);
            rating = itemView.findViewById(R.id.recommended_rating);
        }
    }
}
