package com.example.melobit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melobit.R;
import com.example.melobit.SelectListener;
import com.example.melobit.SongActivity;
import com.example.melobit.models.MusicData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicViewHolder>   {
    Context context;
    List<MusicData> list;
    SelectListener listener;

    public MusicAdapter(Context context, List<MusicData> list,SelectListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }
    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicViewHolder(LayoutInflater.from(context).inflate(R.layout.list_music , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {

        Picasso.get().load(list.get(position).getImage().getCover().getUrl()).into(holder.img_music);
        holder.txt_musicName.setText(list.get(position).getTitle());
        holder.txt_singerName.setText(list.get(position).getArtists().get(0).getFullName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                listener.OnMusicClicked(list.get(position));
                }
            });
    }
    @Override
    public int getItemCount()  {
        return list.size();
    }
}
class MusicViewHolder extends RecyclerView.ViewHolder {
    ImageView img_music;
    TextView txt_musicName, txt_singerName;
    CardView card_music;

    public MusicViewHolder(@NonNull View itemView) {
        super(itemView);
        img_music = itemView.findViewById(R.id.img_music);
        txt_musicName = itemView.findViewById(R.id.txt_musicName);
        txt_singerName = itemView.findViewById(R.id.txt_singerName);
        card_music = itemView.findViewById(R.id.card_music);
    }
}
