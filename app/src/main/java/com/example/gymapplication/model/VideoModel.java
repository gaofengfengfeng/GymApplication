package com.example.gymapplication.model;

import com.example.gymapplication.R;

import java.util.ArrayList;
import java.util.List;

public class VideoModel {
    static final String[] AllSports = {
            "hit高强版减脂瘦身有氧锻炼方法（7天训练课程）-简单无器械健身计划",
            "超级帅气的动感单车课程",
            "【Fit课程】暴汗！HIIT零基础减脂训练",
            "Fit课程：零基础减脂训练",
            "【Victoria's Secret】像维密天使一样健身 大师课程",
            "阴瑜伽教学课程视频",
            "你最想要的三大项力量课来了～",
            "维密天使训练课程",
            "【Fit健身学院】一周激活隐藏的马甲线！"
    };
    static final String[] AllURL = {
            "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
            "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
            "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
            "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
            "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
            "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
            "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
            "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
            "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
    };
    static final int[] AllIcons = {
            R.drawable.video_1,
            R.drawable.video_2,
            R.drawable.video_3,
            R.drawable.video_4,
            R.drawable.video_5,
            R.drawable.video_6,
            R.drawable.video_7,
            R.drawable.video_7,
            R.drawable.video_8,
            R.drawable.video_9
    };

    public String name;
    public int icon;
    public String url;

    public VideoModel(String name, int icon, String url) {
        this.name = name;
        this.icon = icon;
        this.url = url;
    }

    public static List<VideoModel> getVideo() {
        List<VideoModel> datas = new ArrayList<>();
        for (int i = 0; i < AllSports.length; i++) {
            datas.add(new VideoModel(AllSports[i], AllIcons[i], AllURL[i]));
        }
        return datas;
    }
}
