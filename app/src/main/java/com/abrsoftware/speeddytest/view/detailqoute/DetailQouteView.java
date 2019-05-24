package com.abrsoftware.speeddytest.view.detailqoute;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abrsoftware.speeddytest.MainActivity;
import com.abrsoftware.speeddytest.MyApplication;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.model.Qoute;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailQouteView extends Fragment {

    private View rootView;

    @BindView(R.id.nameBrand)
    public TextView nameBrand;

    @BindView(R.id.description)
    public TextView resumeBrand;

    @BindView(R.id.imgDatailNews)
    public ImageView imgDeatil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.view_detail_brand, container, false);
        ButterKnife.bind(this, rootView);


        if (getArguments() != null && getArguments().getSerializable("qoute") != null) {
            Qoute qoute = (Qoute) getArguments().getSerializable("qoute");
            nameBrand.setText("\""+qoute.getQuote()+"\"");
            resumeBrand.setText(qoute.getAuthor());
            Glide.with(MyApplication.getCtx())
                    .load(qoute.getImg()).centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).into(imgDeatil);
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).showBackBtn(true);
    }
}
