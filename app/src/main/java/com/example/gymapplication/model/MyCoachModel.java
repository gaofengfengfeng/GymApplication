package com.example.gymapplication.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyCoachModel {
    public int avatar;
    public String name;
    public String intro;
    public String phone;
    public MyCoachModel(int icon, String name, String intro, String phone){
        this.avatar = icon;
        this.name = name;
        this.intro = intro;
        this.phone = phone;
    }

    public static List<MyCoachModel> getRandomFigures(int size) {
        List<MyCoachModel> figures = new ArrayList<>();
        for (int i = 0; i < Constant.AvatarImages.length; i++) {
            figures.add(new MyCoachModel(Constant.AvatarImages[i], Constant.PLAYERS[i], Constant.EXPERIENCE[i], Constant.PHONE[i]));
        }
        return figures;
    }
}
