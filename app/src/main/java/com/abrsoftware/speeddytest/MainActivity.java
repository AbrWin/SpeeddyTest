package com.abrsoftware.speeddytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ResponsiveUIstate state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void changeFragment(ResponsiveUIstate responsiveUIstate) {
        this.state = ResponsiveUIstate.setState(responsiveUIstate);
        this.state.execute(this);
    }
}
