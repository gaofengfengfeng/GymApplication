package com.example.gymapplication.network;


import com.example.gymapplication.model.network.LoginReq;
import com.example.gymapplication.model.network.LoginResponse;
import com.example.gymapplication.model.network.RegisterReq;
import com.example.gymapplication.model.network.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST(value = "/user/login")
    Call<LoginResponse> login(@Body LoginReq loginReq);

    @POST(value = "/user/register")
    Call<RegisterResponse> register(@Body RegisterReq registerReq);
}
