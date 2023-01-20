package com.example.melobit.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melobit.R;
import com.example.melobit.SelectListener;
import com.example.melobit.models.MusicData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WeekAdapter extends RecyclerView.Adapter<WeekViewHolder> {
    Context context;
    List<MusicData> list;
    SelectListener listener;

    public WeekAdapter(Context context, List<MusicData> list, SelectListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;

    }

    @NonNull
    @Override
    public WeekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeekViewHolder(LayoutInflater.from(context).inflate(R.layout.today_music , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeekViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(list.get(position).getImage().getCover().getUrl()).into(holder.img_music);
        holder.txt_musicName.setText(list.get(position).getTitle());
        holder.txt_singerName.setText(list.get(position).getArtists().get(0).getFullName());
        holder.txt_count.setText(list.get(position).getDownloadCount());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                listener.OnMusicClicked(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 11;
    }
}

class WeekViewHolder extends RecyclerView.ViewHolder {
    ImageView img_music;
    TextView txt_musicName, txt_singerName, txt_count ;

    public WeekViewHolder(@NonNull View itemView) {
        super(itemView);

        img_music = itemView.findViewById(R.id.img_music2);
        txt_musicName = itemView.findViewById(R.id.txt_musicName2);
        txt_singerName = itemView.findViewById(R.id.txt_singerName2);
        txt_count = itemView.findViewById(R.id.txt_count);
    }
}