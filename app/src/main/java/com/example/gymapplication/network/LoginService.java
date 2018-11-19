package com.example.gymapplication.network;


import com.example.gymapplication.model.network.LoginReq;
import com.example.gymapplication.model.network.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST(value = "/user/login")
    Call<LoginResponse> login(@Body LoginReq loginReq);
}
