package com.example.melobit;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.melobit.manager.RequestManager;
import com.example.melobit.models.MusicData;
import com.example.melobit.models.MusicResponse;
import com.example.melobit.models.Song;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SongActivity extends AppCompatActivity {
    ImageView img;
    TextView txt_musicName,txt_singerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        txt_musicName = findViewById(R.id.txt_musicName3);
        txt_singerName = findViewById(R.id.txt_singerName3);
        img = findViewById(R.id.img_music3);
        String extras = getIntent().getStringExtra("id");

        if (extras != null) {
            RequestManager manager = new RequestManager(this);
            SongResponseListener listener = new SongResponseListener() {
                @Override
                public void didFetch(Song response, String status) {
                   Picasso.get().load(response.getImage().getCover().getUrl()).into(img);
                    txt_musicName.setText(response.getTitle());
                    txt_singerName.setText(response.getArtists().get(0).getFullName());

                }

                @Override
                public void didError(String status) {
                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                }
            };
            manager.getSong(listener, extras);
        }



    }

}