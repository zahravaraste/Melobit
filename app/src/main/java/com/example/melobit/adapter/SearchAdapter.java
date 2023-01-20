package com.example.melobit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melobit.R;
import com.example.melobit.models.SearchResult;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{
    Context context;
    List<SearchResult> list;

    public SearchAdapter(Context context, List<SearchResult> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.today_music , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        SearchResult search = list.get(position);
        if(search.getType().equals("song")){
            Picasso.get().load(search.getSong().getImage().getCover().getUrl()).into(holder.img_music);
            holder.txt_musicName.setText(String.valueOf(search.getSong().getTitle()));
            holder.txt_singerName.setText(String.valueOf(search.getSong().getArtists().get(0).getFullName()));
            holder.txt_count.setText(String.valueOf(search.getSong().getDownloadCount()));
        }
        else if (search.getType().equals("album")){
            Picasso.get().load(search.getAlbum().getImage().getCover().getUrl()).into(holder.img_music);
            holder.txt_musicName.setText("Album: "+search.getAlbum().getName());
            holder.txt_singerName.setText(String.valueOf(search.getAlbum().getArtists().get(0).getFullName()));
            holder.txt_count.setText("");
        }
        else {
            Picasso.get().load(search.getArtist().getImage().getCover().getUrl()).into(holder.img_music);
            holder.txt_musicName.setText("Artist: "+search.getArtist().getFullName());
            holder.txt_singerName.setText("");
            holder.txt_count.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        ImageView img_music;
        TextView txt_musicName, txt_singerName, txt_count ;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            img_music = itemView.findViewById(R.id.img_music2);
            txt_musicName = itemView.findViewById(R.id.txt_musicName2);
            txt_singerName = itemView.findViewById(R.id.txt_singerName2);
            txt_count = itemView.findViewById(R.id.txt_count);
        }
    }
}