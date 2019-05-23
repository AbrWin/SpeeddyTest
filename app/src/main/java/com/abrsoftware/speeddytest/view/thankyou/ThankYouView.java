package com.abrsoftware.speeddytest.view.thankyou;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.abrsoftware.speeddytest.MainActivity;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.ResponsiveUIstate;
import com.abrsoftware.speeddytest.view.homeView.HomeView;


public class ThankYouView extends Fragment implements MainActivity.onResult {

    private View rootView;
    private int REQUEST_READ_PHONE_STATE = 1;
    private TextView numberPhone;
    private Button goHome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.view_thank_you, container, false);
        numberPhone = rootView.findViewById(R.id.misdn);
        goHome = rootView.findViewById(R.id.goHome);

        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            String number = ((MainActivity) getActivity()).gettMgr().getLine1Number();
            String numberText = getString(R.string.tu_msisdn_es) + " " + number;
            numberPhone.setText(numberText);
        }
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).changeFragment(HomeView.class, null);
            }
        });

        return rootView;
    }


    @Override
    public void onResultActivity(String number) {
        numberPhone.setText(number);
    }
}
