package com.abrsoftware.speeddytest.view.homeView.HomeMVP;

import com.abrsoftware.speeddytest.MyApplication;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.service.ApiService;
import com.abrsoftware.speeddytest.service.ApiServiceSingleton;
import com.abrsoftware.speeddytest.utils.Connectivity;
import com.abrsoftware.speeddytest.view.GeneralEvent;
import com.abrsoftware.speeddytest.view.PostEvent;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepositoryImp extends PostEvent implements HomeMvp.Repository{

    private final String TAG = HomeRepositoryImp.class.getName();
    private ApiService apiService;
    private Gson gson;

    public HomeRepositoryImp() {
        apiService = ApiServiceSingleton.apiServiceHolder.apiService;
        this.gson = new Gson();
    }
    @Override
    public void getNewsEs() {
        if (!Connectivity.isOnline(MyApplication.getCtx())) {
            postEvent(GeneralEvent.onShowMsjError, MyApplication.getCtx().getString(R.string.error_connec));
            return;
        }
        postEvent(GeneralEvent.showLoading);
        apiService.apiClient.getNewsEs().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                        NewsResponce responce = gson.fromJson(response.body(), NewsResponce.class);
                        postEventResponce(GeneralEvent.SuccesGetNews, responce);
                }else {
                    postEvent(GeneralEvent.onShowMsjError, MyApplication.getCtx().getString(R.string.error_connec));
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                postEvent(GeneralEvent.ErrorGetNews, MyApplication.getCtx().getString(R.string.error_connec));
            }
        });
    }

    @Override
    public void getNewsEn() {
        if (!Connectivity.isOnline(MyApplication.getCtx())) {
            postEvent(GeneralEvent.onShowMsjError, MyApplication.getCtx().getString(R.string.error_connec));
            return;
        }
        postEvent(GeneralEvent.showLoading);
        apiService.apiClient.getNewsEn().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    if(response.isSuccessful()){
                        NewsResponce responce = gson.fromJson(response.body(), NewsResponce.class);
                        postEventResponce(GeneralEvent.SuccesGetNews, responce);
                    }else {
                        postEvent(GeneralEvent.onShowMsjError, MyApplication.getCtx().getString(R.string.error_connec));
                    }
                }else {
                    postEvent(GeneralEvent.onShowMsjError, MyApplication.getCtx().getString(R.string.error_connec));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                postEvent(GeneralEvent.ErrorGetNews, MyApplication.getCtx().getString(R.string.error_connec));
            }
        });
    }
}
