package com.abrsoftware.speeddytest.view.homeView.HomeMVP;

import com.abrsoftware.speeddytest.MyApplication;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.service.ApiService;
import com.abrsoftware.speeddytest.service.ApiServiceSingleton;
import com.abrsoftware.speeddytest.utils.Connectivity;
import com.abrsoftware.speeddytest.view.GeneralEvent;
import com.abrsoftware.speeddytest.view.PostEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
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
    public void getQuotes() {
        final Query query = FirebaseDatabase.getInstance().getReference().child("author");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.exists();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.getMessage();
            }
        });
    }
}
