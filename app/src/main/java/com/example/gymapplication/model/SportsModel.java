package com.example.gymapplication.model;

import com.example.gymapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SportsModel {
    static final String[] AllSports = {
            "Boxing","Football","Lon Tennis",
            "Tennis","VolleryBall","Weight",
            "Archary","Baseketball","Bowing"
    };
    static final int[] AllIcons = {
            R.drawable.ic_icon_boxing,
            R.drawable.ic_icon_football,
            R.drawable.ic_icon_lontenis,
            R.drawable.ic_icon_tenis,
            R.drawable.ic_icon_volleyball,
            R.drawable.ic_icon_weight_lefting,
            R.drawable.ic_icon_archary,
            R.drawable.ic_icon_basketball,
            R.drawable.ic_icon_bowling,
    };
    static final int[] AllBgs = {
            R.color.colorGrass,
            R.color.colorBlueJeans,
            R.color.colorLavander,
            R.color.colorMint,
            R.color.colorAqua,
            R.color.colorPinkRose,
            R.color.colorGrapeFruit,
            R.color.colorBitterSweet,
            R.color.colorFlower
    };

    static final int[] AllBgsDark = {
            R.color.colorGrassDark,
            R.color.colorBlueJeansDark,
            R.color.colorLavanderDark,
            R.color.colorMintDark,
            R.color.colorAquaDark,
            R.color.colorPinkRoseDark,
            R.color.colorGrapeFruitDark,
            R.color.colorBitterSweetDark,
            R.color.colorFlowerDark
    };

    public String name;
    public int icon;
    public int bg;
    public int bgDark;

    public SportsModel(String name, int icon, int bg, int bg2) {
        this.name = name;
        this.icon = icon;
        this.bg = bg;
        this.bgDark = bg2;
    }

    public static List<SportsModel> getSports() {
        List<SportsModel> datas = new ArrayList<>();
        for (int i = 0; i < AllSports.length; i++) {
            datas.add(new SportsModel(AllSports[i], AllIcons[i], AllBgs[i], AllBgsDark[i]));
        }
        return datas;
    }
}
