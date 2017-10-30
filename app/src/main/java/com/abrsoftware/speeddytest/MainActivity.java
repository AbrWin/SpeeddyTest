package com.abrsoftware.speeddytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.abrsoftware.speeddytest.helper.FirebaseHelper;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    private ResponsiveUIstate state;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (FirebaseHelper.getInstance().getAuthReference().getCurrentUser() == null) {
            changeFragment(ResponsiveUIstate.LOGIN);
        } else {
            changeFragment(ResponsiveUIstate.HOME);
        }
    }



    public void changeFragment(ResponsiveUIstate responsiveUIstate) {
        this.state = ResponsiveUIstate.setState(responsiveUIstate);
        this.state.execute(this);
    }

    public void showToolbar(boolean show, String title) {
        toolbar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        toolbar.setTitle(title);

    }
}
