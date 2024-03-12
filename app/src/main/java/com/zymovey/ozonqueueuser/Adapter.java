package com.zymovey.ozonqueueuser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ItemViewHolder> {
    ArrayList<Courier> itemData;
    private CouriersService couriersService;
    // передаем лист через конструктор
    public Adapter(ArrayList<Courier> itemData) {
        this.itemData = itemData;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutInflater = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ItemViewHolder(layoutInflater);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Courier item = itemData.get(position);

// подставляем нужную картинку и имя в каждый элемент списка
       //holder.image.setBackgroundResource(item.getImage());
        holder.name.setText(item.getLastname());
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name ;
        ImageView image;

        ItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
        }
    }
}