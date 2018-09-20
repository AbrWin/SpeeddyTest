package com.abrsoftware.speeddytest.service;

public class ApiServiceSingleton {
    public static ApiServiceSingleton apiServiceHolder;
    public ApiService apiService;

    public ApiServiceSingleton() {
        this.apiService = new ApiService();
    }

    public static ApiServiceSingleton getInstance(){
        if(apiServiceHolder == null){
            apiServiceHolder = new ApiServiceSingleton();
        }
        return apiServiceHolder;
    }
}
