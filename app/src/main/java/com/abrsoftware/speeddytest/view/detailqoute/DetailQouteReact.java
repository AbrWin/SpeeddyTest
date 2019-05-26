package com.abrsoftware.speeddytest.view.detailqoute;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abrsoftware.speeddytest.MainActivity;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.view.BaseView;
import com.facebook.react.ReactRootView;
public class DetailQouteReact extends BaseView {
    private ReactRootView mReactRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.detail_react, container, false);
        mReactRootView = rootView.findViewById(R.id.reacView);
        try {
            mReactRootView.startReactApplication(((MainActivity) activity).getmReactInstanceManager(), "Qoutes", null);

        } catch (Exception e) {
            Log.d("msj", e.getMessage());
        }

        return rootView;
    }

}
