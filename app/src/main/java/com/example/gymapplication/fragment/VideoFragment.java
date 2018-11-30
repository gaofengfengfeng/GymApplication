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

import com.example.gymapplication.R;
import com.example.gymapplication.activity.CheeseVideoActivity;
import com.example.gymapplication.adapter.VideoAdapter;
import com.example.gymapplication.model.VideoModel;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {
    View rootView;
    VideoAdapter mAdapter;
    List<VideoModel> allVideo = new ArrayList<>();

    public static VideoFragment newInstance() {

        Bundle args = new Bundle();

        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_video, container, false);
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
        RecyclerView recyclerView = rootView.findViewById(R.id.video_list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new VideoAdapter(getContext(), VideoModel.getVideo());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnVideoItemClick(new VideoAdapter.OnVideoItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), CheeseVideoActivity.class);
                intent.putExtra(CheeseVideoActivity.BundleKeyImage, R.drawable.banner_gym);
                intent.putExtra(CheeseVideoActivity.BundleKeyName, mAdapter.getModelByPos(position).name);
                intent.putExtra(CheeseVideoActivity.BundleKeyURL, mAdapter.getModelByPos(position).url);
                startActivity(intent);
            }
        });
    }

    public void searchVideo(String word) {
        List<VideoModel> videoModels = filterDataset(word);
        if (word == null || word.equals("")) {
            videoModels = getAllVideo();
        }
        mAdapter.updateDataset(videoModels);
    }


    public List<VideoModel> getAllVideo() {
        if (allVideo == null || allVideo.size() == 0) {
            allVideo = VideoModel.getVideo();
        }
        return allVideo;
    }

    public List<VideoModel> filterDataset(String word) {
        List<VideoModel> ds = new ArrayList<>();
        if (TextUtils.isEmpty(word)) {
            return ds;
        }
        for (VideoModel item : getAllVideo()) {
            if (item.name.toLowerCase().contains(word.toLowerCase())) {
                ds.add(item);
            }
        }
        return ds;
    }
}
