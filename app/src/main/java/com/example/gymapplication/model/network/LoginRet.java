package com.example.gymapplication.model.network;

import java.util.List;

public class LoginRet {
    private String username;
    private String nickname;
    private String token;
    private String portrait;
    private List<Friend> friendList;

    public static class Friend {
        private String portrait;
        private String name;
        private String username;

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

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

    public List<Friend> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Friend> friendList) {
        this.friendList = friendList;
    }
}

