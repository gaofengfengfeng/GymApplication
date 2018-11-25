package com.example.gymapplication;

import android.app.Application;

import com.example.gymapplication.model.Trainer;
import com.example.gymapplication.model.User;

import java.util.List;

public class MyApplication extends Application {

    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    private User loginUser;
    private List<Trainer> trainerList;

    private Integer activeFragment = 0;

    public void setLoginUser(User user) {
        this.loginUser = user;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public List<Trainer> getTrainerList() {
        return trainerList;
    }

    public void setTrainerList(List<Trainer> trainerList) {
        this.trainerList = trainerList;
    }

    public Integer getActiveFragment() {
        return activeFragment;
    }

    public void setActiveFragment(Integer activeFragment) {
        this.activeFragment = activeFragment;
    }
}
