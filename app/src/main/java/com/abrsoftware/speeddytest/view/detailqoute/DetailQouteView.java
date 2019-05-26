package com.abrsoftware.speeddytest.view.detailqoute;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.abrsoftware.speeddytest.MainActivity;
import com.abrsoftware.speeddytest.MyApplication;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.model.Qoute;
import com.abrsoftware.speeddytest.view.BaseView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.button.MaterialButton;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;


public class DetailQouteView extends BaseView {

    @Nullable
    @BindView(R.id.nameBrand)
    public TextView nameBrand;

    @Nullable
    @BindView(R.id.description)
    public TextView resumeBrand;

    @Nullable
    @BindView(R.id.imgDatailNews)
    public ImageView imgDeatil;

    @Nullable
    @BindView(R.id.starsRating)
    public RatingBar starsRating;

    @Nullable
    @BindView(R.id.titleRating)
    public TextView titleRating;

    @Nullable
    @BindView(R.id.icStart)
    public ImageView icStart;

    @Nullable
    @BindView(R.id.fishRating)
    public MaterialButton fishRating;

    @Nullable
    @BindView(R.id.contentRating)
    public ConstraintLayout contentRating;

    @Nullable
    @BindView(R.id.ratinValue)
    public TextView ratinValue;

    private Qoute qoute;
    private View rootView;
    private double rating;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.view_detail_brand, container, false);
        ButterKnife.bind(this, rootView);


        if (getArguments() != null && getArguments().getSerializable("qoute") != null) {
            qoute = (Qoute) getArguments().getSerializable("qoute");
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
        ((MainActivity) getActivity()).showToolbar(true, qoute.getAuthor());
    }

    @Optional
    @OnClick(R.id.btn_rating)
    public void initRating() {
        inflateCustomDialog(R.layout.dialog_rating);
        ButterKnife.bind(this, getViewCustomDialog());
        showCustomDialog();
    }

    @Optional
    @OnClick(R.id.closeMenu)
    public void closeMenu() {
        hiddeCustomDialog();
        ButterKnife.bind(this, rootView);
        if(rating > 0){
            contentRating.setVisibility(View.VISIBLE);
            ratinValue.setText(String.valueOf(rating));
        }
    }

    @Optional
    @OnClick(R.id.starsRating)
    public void starsRating() {
        starsRating.getNumStars();
    }

    @Optional
    @OnClick(R.id.fishRating)
    public void fishRating() {
        rating = starsRating.getRating();
        titleRating.setText(getString(R.string.rating_succes)+"\n"+starsRating.getRating());
        starsRating.setVisibility(View.GONE);
        fishRating.setVisibility(View.GONE);
        icStart.setVisibility(View.VISIBLE);
        //hiddeCustomDialog();
    }
}
