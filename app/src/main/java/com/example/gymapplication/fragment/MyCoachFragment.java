package com.example.gymapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymapplication.MyApplication;
import com.example.gymapplication.R;
import com.example.gymapplication.activity.CheeseDetailActivity;
import com.example.gymapplication.adapter.CoachAdapter;
import com.example.gymapplication.model.FigureModel;
import com.example.gymapplication.model.Trainer;

import java.util.ArrayList;
import java.util.List;

public class MyCoachFragment extends Fragment {

    View rootView;
    CoachAdapter coachAdapter;
    MyApplication myApplication;

    public static MyCoachFragment newInstance() {
        MyCoachFragment fragment = new MyCoachFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my_coach, container, false);
        myApplication = MyApplication.getInstance();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        List<FigureModel> figureModelList = new ArrayList<>();
        for (Trainer trainer : myApplication.getTrainerList()) {
            int avatar = 1;
            String name = trainer.getName();
            String intro = "我是洪伟辉";
            FigureModel figureModel = new FigureModel(avatar, name, intro);
            figureModelList.add(figureModel);
        }
        //figureModelList = FigureModel.getRandomFigures(5);
        coachAdapter = new CoachAdapter(getContext(), figureModelList);
        recyclerView.setAdapter(coachAdapter);
        coachAdapter.setOnCoachItemClick(new CoachAdapter.OnCoachItemClick() {
            @Override
            public void onCoachClick(int position) {
                FigureModel model = coachAdapter.getModelByPos(position);
                Intent intent = new Intent(getActivity(), CheeseDetailActivity.class);
                intent.putExtra(CheeseDetailActivity.BundleKeyImage, model.avatar);
                intent.putExtra(CheeseDetailActivity.BundleKeyName, model.name);
                startActivity(intent);
            }
        });
    }


}
