package com.abrsoftware.speeddytest.service;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiServiceInterface {

    @GET(Routes.LANG_ES)
    Call<String> getNewsEs();

    @GET(Routes.LANG_EN)
    Call<String> getNewsEn();
}
