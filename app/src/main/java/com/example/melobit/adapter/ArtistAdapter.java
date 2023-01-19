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
import com.example.melobit.models.ArtistResult;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistViewHolder>{
    Context context;
    List<ArtistResult> list;

    public ArtistAdapter(Context context, List<ArtistResult> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArtistViewHolder(LayoutInflater.from(context).inflate(R.layout.list_artist , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getImage().getCover().getUrl()).into(holder.img_artist);
        holder.txt_artistName.setText(String.valueOf(list.get(position).getFullName()));
        holder.txt_follower.setText("Followers: "+list.get(position).getFollowersCount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class ArtistViewHolder extends RecyclerView.ViewHolder {
    ImageView img_artist;
    TextView txt_artistName, txt_follower;

    public ArtistViewHolder(@NonNull View itemView) {
        super(itemView);
        img_artist = itemView.findViewById(R.id.img_artist);
        txt_artistName = itemView.findViewById(R.id.txt_artistName);
        txt_follower = itemView.findViewById(R.id.txt_followers);
    }
}
