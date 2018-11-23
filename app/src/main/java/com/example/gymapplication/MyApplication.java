package com.example.gymapplication;

import android.app.Application;

import com.example.gymapplication.model.User;

public class MyApplication extends Application {

    private User loginUser;

    public void setLoginUser(User user) {
        this.loginUser = user;
    }

    public User getLoginUser() {
        return loginUser;
    }
}
