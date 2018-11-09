package com.example.gymapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FigureModel {
    public int avatar;
    public String name;
    public String intro;
    public FigureModel(int icon, String name, String intro){
        this.avatar = icon;
        this.name = name;
        this.intro = intro;
    }

    public static List<FigureModel> getRandomFigures(int size) {
        Random random = new Random();
        List<FigureModel> figures = new ArrayList<>(size);
        while (figures.size() < size) {
            int num = (random.nextInt(size) % Constant.AvatarImages.length);
            figures.add(new FigureModel(Constant.AvatarImages[num], Constant.PLAYERS[num], Constant.EXPERIENCE[num]));
        }
        return figures;
    }
}
