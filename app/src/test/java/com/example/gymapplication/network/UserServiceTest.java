package com.example.gymapplication.network;

import com.example.gymapplication.model.network.LoginReq;
import com.example.gymapplication.model.network.LoginResponse;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserServiceTest {

    @Test
    public void login() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8088/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);

        LoginReq loginReq = new LoginReq();
        loginReq.setUsername("gaofeng");
        loginReq.setPassword("123456");
        Call<LoginResponse> call = userService.login(loginReq);
        try {
            System.out.println(call.execute().body().getData().getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}