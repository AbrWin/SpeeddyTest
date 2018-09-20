package com.abrsoftware.speeddytest.service;

import com.abrsoftware.speeddytest.MyApplication;
import com.abrsoftware.speeddytest.R;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiService {
    public ApiServiceInterface apiClient;

    public void initApiService() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(MyApplication.getCtx().getString(R.string.end_point))
                .client(TrustOkHttpClient.getUnsafeOkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create());

        apiClient = builder.build().create(ApiServiceInterface.class);
    }

}

