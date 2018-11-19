package com.example.gymapplication.network;

import com.example.gymapplication.model.network.LoginReq;
import com.example.gymapplication.model.network.LoginResponse;
import com.example.gymapplication.model.network.LoginRet;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

public class LoginServiceTest {

    @Test
    public void login() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8088/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginService loginService = retrofit.create(LoginService.class);

        LoginReq loginReq = new LoginReq();
        loginReq.setUsername("gaofeng");
        loginReq.setPassword("123456");
        Call<LoginResponse> call = loginService.login(loginReq);
        try {
            System.out.println(call.execute().body().getData().getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}