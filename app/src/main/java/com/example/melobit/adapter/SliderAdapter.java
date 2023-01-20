package com.example.melobit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.melobit.R;
import com.example.melobit.SongActivity;
import com.example.melobit.models.MusicData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    Context context;
    List<MusicData> list;

    public SliderAdapter(Context context, List<MusicData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=(LayoutInflater.from(context).inflate(R.layout.slider_image, container , false));
        ImageView img_slider=view.findViewById(R.id.img_slider);
        Picasso.get().load(list.get(position).getImage().getSlider().getUrl()).into(img_slider);
        view.setOnClickListener(view1 -> {
            Intent intent = new Intent(context, SongActivity.class);
            intent.putExtra("id",list.get(position).getId());
            context.startActivity(intent);
        });
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
