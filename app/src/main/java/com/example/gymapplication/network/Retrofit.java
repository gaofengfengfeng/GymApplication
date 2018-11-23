package com.example.gymapplication.network;


import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    private static retrofit2.Retrofit userRetrofit;

    public static retrofit2.Retrofit getUserRetrofit() {
        if (userRetrofit == null) {
            userRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("http://192.168.0.116:8088/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return userRetrofit;
    }

}
