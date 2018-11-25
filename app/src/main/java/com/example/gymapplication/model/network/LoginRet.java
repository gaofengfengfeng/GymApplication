package com.example.gymapplication.model.network;

import com.example.gymapplication.model.Trainer;

import java.util.List;

public class LoginRet {
    private String username;
    private String nickname;
    private String token;
    private String portrait;
    private List<Trainer> friendList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public List<Trainer> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Trainer> friendList) {
        this.friendList = friendList;
    }
}

