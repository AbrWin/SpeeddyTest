package com.abrsoftware.speeddytest.view.detailBrand;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.abrsoftware.speeddytest.MainActivity;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.ResponsiveUIstate;
import com.abrsoftware.speeddytest.model.Brand;


public class DetailBrandView extends Fragment {


    private View rootView;
    private TextView nameBrand;
    private TextView resumeBrand;
    private Button btnDownload;
    private Button goHome;


    public DetailBrandView() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.view_detail_brand, container, false);
        nameBrand = rootView.findViewById(R.id.nameBrand);
        resumeBrand = rootView.findViewById(R.id.resumeBrand);
        goHome = rootView.findViewById(R.id.goHome);

        if (getArguments() != null) {
            Brand brand = (Brand) getArguments().getSerializable("brand");
            nameBrand.setText(brand.getName());
            resumeBrand.setText(brand.getResume());
        }

        btnDownload = rootView.findViewById(R.id.download);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).changeFragment(ResponsiveUIstate.THANK_YOU);
            }
        });

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).changeFragment(ResponsiveUIstate.HOME);
            }
        });
        return rootView;
    }

}
