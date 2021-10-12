package com.example.baitaptuan5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    ArrayList<Music> mMusics;
    OnClickListener listener;

    public CustomAdapter(ArrayList<Music> mMusics, OnClickListener listener) {
        this.mMusics = mMusics;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layouimagemusic,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return mMusics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Music mMusic;
        ImageView img;
        TextView txtAuthor, txtSongName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgV);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtSongName = itemView.findViewById(R.id.txtSongName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(mMusic);
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Music mMusic = mMusics.get(position);
        holder.mMusic = mMusic;
        holder.img.setImageResource(mMusic.getImage());
        holder.txtAuthor.setText(mMusic.getAuthor());
        holder.txtSongName.setText(mMusic.getSongName());
    }
}
