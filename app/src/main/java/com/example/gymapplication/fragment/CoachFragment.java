package com.example.gymapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymapplication.activity.CheeseDetailActivity;
import com.example.gymapplication.model.FigureModel;
import com.example.gymapplication.R;
import com.example.gymapplication.adapter.CoachAdapter;
import com.example.gymapplication.model.SportsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class CoachFragment extends Fragment {
    View rootView;
    SwipeRefreshLayout mRefreshLayout;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    CoachAdapter mAdapter;
    List<FigureModel> allCoaches = new ArrayList<>();


    public static CoachFragment newInstance() {
        Bundle args = new Bundle();

        CoachFragment fragment = new CoachFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_coach, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRefreshLayout = rootView.findViewById(R.id.swiperefresh);
        mRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        fetchData();
                    }
                }
        );

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        mAdapter = new CoachAdapter(FigureModel.getRandomFigures(10));
        mAdapter.setOnCoachItemClick(new CoachAdapter.OnCoachItemClick() {
            @Override
            public void onCoachClick(int position) {
                FigureModel model = mAdapter.getModelByPos(position);
                Intent intent = new Intent(getActivity(), CheeseDetailActivity.class);
                intent.putExtra(CheeseDetailActivity.BundleKeyImage, model.avatar);
                intent.putExtra(CheeseDetailActivity.BundleKeyName, model.name);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void fetchData() {
        Log.d("martin", "fetchData");
        Observable.just("mock data").delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("martin", "accept" + s);
                        mAdapter.updateDataset(FigureModel.getRandomFigures(10));
                        mRefreshLayout.setRefreshing(false);
                    }
                });
    }

    public void searchCoach(String word) {
        List<FigureModel> coachModels = filterDataset(word);
        if (word == null || word.equals("")) {
            coachModels = getAllCoaches();
        }
        mAdapter.updateDataset(coachModels);
    }


    public List<FigureModel> getAllCoaches() {
        if (allCoaches == null || allCoaches.size() == 0) {
            allCoaches = FigureModel.getRandomFigures(20);
        }
        return allCoaches;
    }

    public List<FigureModel> filterDataset(String word) {
        List<FigureModel> ds = new ArrayList<>();
        if (TextUtils.isEmpty(word)) {
            return ds;
        }
        for (FigureModel item : getAllCoaches()) {
            if (item.name.toLowerCase().contains(word.toLowerCase())) {
                ds.add(item);
            }
        }
        return ds;
    }
}
