package com.example.degabriel.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class carritoAdapter extends RecyclerView.Adapter<carritoAdapter.carritoViewHolder>{
    @NonNull
    @Override
    public carritoAdapter.carritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull carritoAdapter.carritoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class carritoViewHolder extends RecyclerView.ViewHolder {
        public carritoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
