package com.example.melobit;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.melobit.manager.RequestManager;
import com.example.melobit.models.Song;
import com.github.mfathi91.time.PersianDate;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class SongActivity extends AppCompatActivity {
    ImageView img;
    TextView txt_musicName, txt_singerName, txt_date, txt_downloadC, txt_duration;
    SeekBar seekBar;
    Button btn_play, btn_prev, btn_next;
    MediaPlayer mediaPlayer = new MediaPlayer();
    LinearLayout btn_lyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        txt_musicName = findViewById(R.id.txt_musicName3);
        txt_singerName = findViewById(R.id.txt_singerName3);
        txt_date = findViewById(R.id.txt_date);
        txt_downloadC = findViewById(R.id.txt_downloadC);
        txt_duration = findViewById(R.id.txt_duration);
        seekBar = findViewById(R.id.seekBar);
        img = findViewById(R.id.img_music3);
        btn_next = findViewById(R.id.btn_next);
        btn_play = findViewById(R.id.btn_play);
        btn_prev = findViewById(R.id.btn_prev);
        btn_lyrics = findViewById(R.id.btn_lyrics);
        String extras = getIntent().getStringExtra("id");
        Bundle data=new Bundle();
        if (extras != null) {
            RequestManager manager = new RequestManager(this);
            SongResponseListener listener = new SongResponseListener() {
                @Override
                public void didFetch(Song response, String status) {
                    Picasso.get().load(response.getImage().getCover().getUrl()).into(img);
                    txt_musicName.setText(response.getTitle());
                    txt_singerName.setText(response.getArtists().get(0).getFullName());
                    data.putString("hi",response.getLyrics());
                    txt_date.setText(convertToShamsi(response.getReleaseDate()));
                    txt_downloadC.setText(response.getDownloadCount());
                    play(response.getAudio().getMedium().getUrl());
                }

                @Override
                public void didError(String status) {
                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                }
            };
            manager.getSong(listener, extras);

            btn_lyrics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragment myDialogFragment =new myDialogFragment();
                    myDialogFragment.setArguments(data);
                    myDialogFragment.show(getSupportFragmentManager(),"DialogFragment");
                }
            });
        }

    }

    private void play(String url) {
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        );
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
            /*seekBar.setProgress(0);*/
            seekBar.setMax(mediaPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* SongActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(mediaPlayer!=null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    txt_duration.setText(convertTomms(mediaPlayer.getCurrentPosition()+""));
                }
                new Handler().postDelayed(this,1000);
            }
        });*/
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    txt_duration.setText(convertTomms(mediaPlayer.getCurrentPosition() + ""));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    btn_play.setBackgroundResource(R.drawable.play);
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition());
                    btn_play.setBackgroundResource(R.drawable.pause);
                    mediaPlayer.start();
                }
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btn_play.setBackgroundResource(R.drawable.play);
                mediaPlayer.seekTo(0);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    private String convertTomms(String duration) {
        Long milis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(milis) % TimeUnit.HOURS.toMinutes(1), TimeUnit.MILLISECONDS.toSeconds(milis) % TimeUnit.MINUTES.toSeconds(1));
    }

    private String convertToShamsi(String date) {
        String[] date2;
        date2 = date.split("T");
        LocalDate gregDate = LocalDate.parse(date2[0]);
        PersianDate persianDate = PersianDate.fromGregorian(gregDate);
        return persianDate.toString();
    }
}