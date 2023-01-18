package com.example.melobit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class myDialogFragment extends androidx.fragment.app.DialogFragment {
    TextView txt_view;
    String lyrics;
    String n;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.lyricsdialog,container,false);
        n="";
        txt_view=view.findViewById(R.id.txt_lyrics);
        Bundle d=getArguments();
        lyrics=d.getString("hi");
        if(!lyrics.equals(n)){
            txt_view.setText(lyrics);
        }
        else{
            txt_view.setText("There is no lyrics");
        }

        return  view;
    }
}
