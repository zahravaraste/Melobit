package com.example.melobit;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.melobit.adapter.SearchAdapter;
import com.example.melobit.manager.RequestManager;
import com.example.melobit.models.SearchResult;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link searchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class searchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public searchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment searchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static searchFragment newInstance(String param1, String param2) {
        searchFragment fragment = new searchFragment();
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
    SearchAdapter adapter;
    Button btn_search;
    EditText edt_search;
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        btn_search=view.findViewById(R.id.btn_search);
        edt_search=view.findViewById(R.id.edt_search);
        recyclerview =view.findViewById(R.id.recycler_music2);
        manager = new RequestManager(getActivity());


        btn_search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String query=edt_search.getText().toString().trim();
                if (!query.equals("")) {
                    if (query.contains(" "))
                    {
                        query = query.replace(" ","%20");
                    }
                    recyclerview.setVisibility(View.VISIBLE);
                    dialog = new ProgressDialog(getActivity());
                    manager.searchMusic(listener,query);
                    edt_search.setText("");
                    dialog.setTitle("Searching...⌛");
                    dialog.show();
                }
                else{
                    Toast.makeText(getActivity(),"Enter what you want...",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    private final SearchResponseListener listener = new SearchResponseListener () {
        @Override
        public void didFetch(List<SearchResult> list, String status) {
            dialog.dismiss();
            showMusic(list);
        }
        @Override
        public void didError(String status) {
            Toast.makeText(getActivity(), status, Toast.LENGTH_SHORT).show();
        }
    };
    private void showMusic(List<SearchResult> list) {
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new SearchAdapter(getActivity(),list);
        recyclerview.setAdapter(adapter);
    }
}