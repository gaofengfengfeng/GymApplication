package com.example.gymapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymapplication.activity.CheeseDetailActivity;
import com.example.gymapplication.R;
import com.example.gymapplication.model.SportsModel;
import com.example.gymapplication.adapter.SportsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SportFragment extends Fragment {
    View rootView;
    SportsAdapter mAdapter;
    List<SportsModel> allSports = new ArrayList<>();

    public static SportFragment newInstance() {

        Bundle args = new Bundle();

        SportFragment fragment = new SportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_sport, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        rootView.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), SearchListActivity.class);
//                intent.putExtra(SearchListActivity.BundleKeyTitle, "search");
//                startActivity(intent);
//            }
//        });
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new SportsAdapter(getContext(), SportsModel.getSports());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnSportItemClick(new SportsAdapter.OnSportItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), CheeseDetailActivity.class);
                intent.putExtra(CheeseDetailActivity.BundleKeyImage, R.drawable.banner_gym);
                intent.putExtra(CheeseDetailActivity.BundleKeyName, mAdapter.getModelByPos(position).name);
                startActivity(intent);
            }
        });
    }

    public void searchSport(String word) {
        List<SportsModel> sportsModels = filterDataset(word);
        if (word == null || word.equals("")) {
            sportsModels = getAllSports();
        }
        mAdapter.updateDataset(sportsModels);
    }


    public List<SportsModel> getAllSports() {
        if (allSports == null || allSports.size() == 0) {
            allSports = SportsModel.getSports();
        }
        return allSports;
    }

    public List<SportsModel> filterDataset(String word) {
        List<SportsModel> ds = new ArrayList<>();
        if (TextUtils.isEmpty(word)) {
            return ds;
        }
        for (SportsModel item : getAllSports()) {
            if (item.name.toLowerCase().contains(word.toLowerCase())) {
                ds.add(item);
            }
        }
        return ds;
    }
}
