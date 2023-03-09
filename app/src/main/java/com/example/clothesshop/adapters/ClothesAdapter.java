package com.example.clothesshop.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.R;
import com.example.clothesshop.models.Clothes;

import java.util.List;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ClothesViewHolder> {
    List<Clothes> ListClothes;
    public ClothesAdapter (List<Clothes> ListClothes){
        this.ListClothes = ListClothes;
    }

    @NonNull
    @Override
    public ClothesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clothes_layout,parent,false);

        return new ClothesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothesViewHolder holder, int position) {
        Clothes clothes = ListClothes.get(position);
        if(clothes == null){
            return;
        }
        holder.img.setImageResource(clothes.getImage());
        holder.name.setText(clothes.getName());
        holder.price.setText(clothes.getPrice());
    }

    @Override
    public int getItemCount() {
        if(ListClothes != null){
            return ListClothes.size();
        }
        return 0;
    }

    public class ClothesViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView price;
        public ClothesViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_item);
            name = itemView.findViewById(R.id.name_item);
            price = itemView.findViewById(R.id.price_item);
        }
    }
}
