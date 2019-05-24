package com.abrsoftware.speeddytest.view.homeView.HomeMVP;

import com.abrsoftware.speeddytest.model.Qoute;
import com.abrsoftware.speeddytest.service.ApiService;
import com.abrsoftware.speeddytest.service.ApiServiceSingleton;
import com.abrsoftware.speeddytest.view.GeneralEvent;
import com.abrsoftware.speeddytest.view.PostEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;


public class HomeRepositoryImp extends PostEvent implements HomeMvp.Repository{

    @Override
    public void getQuotes() {
        final Query query = FirebaseDatabase.getInstance().getReference().child("author");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    GenericTypeIndicator<ArrayList<Qoute>> objectsGTypeInd = new GenericTypeIndicator<ArrayList<Qoute>>() {};
                    List<Qoute> qouteList = dataSnapshot.getValue(objectsGTypeInd);
                    query.removeEventListener(this);
                    postEvent(GeneralEvent.SUCCES_RESPONCE, qouteList);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.getMessage();
                postEvent(GeneralEvent.ERROR_RESPONCE, databaseError.getMessage());
            }
        });
    }
}
