package com.example.gymapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymapplication.activity.CheeseDetailActivity;
import com.example.gymapplication.util.GlideImageLoader;
import com.example.gymapplication.R;
import com.example.gymapplication.model.SportsModel;
import com.example.gymapplication.adapter.CategoryAdapter;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class ShowFragment extends Fragment implements OnBannerListener {
    View rootView;
    Banner banner;
    List<Integer> images;
    CategoryAdapter mAdapter;

    public static ShowFragment newInstance() {
        Bundle args = new Bundle();
        ShowFragment fragment = new ShowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_show, container, false);
        banner = (Banner) rootView.findViewById(R.id.banner);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //简单使用
        images = new ArrayList<>();
        images.add(R.drawable.trainer4);
        images.add(R.drawable.banner_gym);
        images.add(R.drawable.trainer6);

        banner.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
//        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int i) {
//                Random random = new Random();
//                int val = random.nextInt(10);
//                return (val % 3) + 1;
//            }
//        });
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CategoryAdapter(getContext(), SportsModel.getSports());
        mAdapter.setOnSportItemClick(new CategoryAdapter.OnSportItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), CheeseDetailActivity.class);
                intent.putExtra(CheeseDetailActivity.BundleKeyImage, R.drawable.banner_gym);
                intent.putExtra(CheeseDetailActivity.BundleKeyName, mAdapter.getModelByPos(position).name);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void OnBannerClick(int position) {

        Intent intent = new Intent(getContext(), CheeseDetailActivity.class);
        intent.putExtra(CheeseDetailActivity.BundleKeyName, "Gym");
        intent.putExtra(CheeseDetailActivity.BundleKeyImage, images.get(position));
        startActivity(intent);
    }
}
