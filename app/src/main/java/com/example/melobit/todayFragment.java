package com.example.melobit;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.melobit.adapter.TodayAdapter;
import com.example.melobit.manager.RequestManager;
import com.example.melobit.models.MusicData;
import com.example.melobit.models.MusicResponse;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link todayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class todayFragment extends Fragment implements SelectListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public todayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment todayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static todayFragment newInstance(String param1, String param2) {
        todayFragment fragment = new todayFragment();
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
    RecyclerView recyclerview;
    RequestManager manager;
    TodayAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_today, container, false);
        recyclerview=view.findViewById((R.id.recycler_today));
        manager = new RequestManager(getActivity());
        manager.getTopDayMusic(listener);
        return view;
    }

    private final ResponseListener<MusicResponse> listener = new ResponseListener<MusicResponse>() {
        @Override
        public void didFetch(List<MusicData> list, String status) {
            showMusic(list);
        }
        @Override
        public void didError(String status) {
            Toast.makeText(getActivity(), status, Toast.LENGTH_SHORT).show();
        }
    };
    private void showMusic(List<MusicData> list) {
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new TodayAdapter(getActivity(), list,this);
        recyclerview.setAdapter(adapter);
    }
    @Override
    public void OnMusicClicked(MusicData musicData) {
        Intent intent = new Intent(getActivity(),SongActivity.class);
        intent.putExtra("id",musicData.getId());
        startActivity(intent);

    }
}