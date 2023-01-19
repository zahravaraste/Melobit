package com.example.melobit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.melobit.adapter.ArtistAdapter;
import com.example.melobit.adapter.MusicAdapter;
import com.example.melobit.adapter.SliderAdapter;
import com.example.melobit.manager.RequestManager;
import com.example.melobit.models.ArtistResponse;
import com.example.melobit.models.ArtistResult;
import com.example.melobit.models.MusicData;
import com.example.melobit.models.MusicResponse;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeFragment extends Fragment implements SelectListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    RecyclerView recyclerview,artist_recyclerview;
    ProgressDialog dialog;
    RequestManager manager,sliderManager,artistManager;
    MusicAdapter adapter;
    ArtistAdapter artistAdapter;
    topHitsFragment TopHitsFragment = new topHitsFragment();
    ViewPager viewPager;
    int currentPagerAdapter=0;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Loading...⌛");
        dialog.show();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager=view.findViewById(R.id.viewPagerSlider);
        recyclerview = view.findViewById((R.id.recycler_music1));
        artist_recyclerview = view.findViewById((R.id.recycler_artist));
        manager = new RequestManager(getActivity());
        manager.getMusic(listener);
        sliderManager = new RequestManager(getActivity());
        sliderManager.getSlider(listener2);
        artistManager = new RequestManager(getActivity());
        artistManager.getTrendArtist(listenerArtist);
        Button btn_topHits = view.findViewById(R.id.btn_topHits);
        btn_topHits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment, TopHitsFragment).commit();
            }
        }
        );
        return view;
    }
    private final ResponseListener<MusicResponse> listener2 = new ResponseListener<MusicResponse>() {

        @Override
        public void didFetch(List<MusicData> list, String status) {
            viewPager.setAdapter(new SliderAdapter(getActivity(),list));
            final Handler handler=new Handler();
            final Runnable runnable=new Runnable(){
                @Override
                public void run() {
                    if(currentPagerAdapter==list.size()){
                        currentPagerAdapter=0;
                    }
                    viewPager.setCurrentItem(currentPagerAdapter++,true);
                }
            };
            Timer timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(runnable);
                }
            },2500,5000);
        }

        @Override
        public void didError(String status) {
            Toast.makeText(getActivity(), status, Toast.LENGTH_SHORT).show();
        }
    };

        private final ResponseListener<MusicResponse> listener = new ResponseListener<MusicResponse>() {
        @Override
        public void didFetch(List<MusicData> list, String status) {
            showMusic(list);
            dialog.dismiss();
        }
        @Override
        public void didError(String status) {
            Toast.makeText(getActivity(), status, Toast.LENGTH_SHORT).show();
        }
    };

    private void showMusic(List<MusicData> list) {
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new MusicAdapter(getActivity(), list,this);
        recyclerview.setAdapter(adapter);
    }

    private final ArtistResponseListener<ArtistResponse> listenerArtist = new ArtistResponseListener<ArtistResponse>() {
        @Override
        public void didFetch(List<ArtistResult> list, String status) {
            artist_recyclerview.setHasFixedSize(true);
            artist_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            artistAdapter = new ArtistAdapter(getActivity(), list);
            artist_recyclerview.setAdapter(artistAdapter);
        }
        @Override
        public void didError(String status) {
            Toast.makeText(getActivity(), status, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void OnMusicClicked(MusicData musicData) {
        Intent intent = new Intent(getActivity(),SongActivity.class);
        intent.putExtra("id",musicData.getId());
        startActivity(intent);

    }
}
