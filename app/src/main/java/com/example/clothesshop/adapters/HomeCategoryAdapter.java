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
import com.example.clothesshop.activities.ViewAllActivity;
import com.example.clothesshop.models.CategoryModel;

import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {
    Context context;
    List<CategoryModel> categoryModelList;

    public HomeCategoryAdapter(Context context, List<CategoryModel> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeCategoryAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(categoryModelList.get(position).getImg_url()).into(holder.cateImg);
        holder.name.setText(categoryModelList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",categoryModelList.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cateImg;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cateImg = itemView.findViewById(R.id.home_cate_img);
            name = itemView.findViewById(R.id.home_cate_name);

        }
    }
}
