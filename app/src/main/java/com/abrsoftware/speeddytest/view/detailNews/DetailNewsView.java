package com.abrsoftware.speeddytest.view.detailNews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.abrsoftware.speeddytest.MainActivity;
import com.abrsoftware.speeddytest.MyApplication;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.ResponsiveUIstate;
import com.abrsoftware.speeddytest.model.Brand;
import com.abrsoftware.speeddytest.model.News;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class DetailNewsView extends Fragment {


    private View rootView;
    private TextView nameBrand;
    private TextView resumeBrand;
    private ImageView imgDeatil;


    public DetailNewsView() {
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
        resumeBrand = rootView.findViewById(R.id.description);
        imgDeatil = rootView.findViewById(R.id.imgDatailNews);

        if (getArguments() != null) {
            News news = (News) getArguments().getSerializable("news");
            nameBrand.setText(news.getTitle());
            resumeBrand.setText(Html.fromHtml(news.getText()));
            Glide.with(MyApplication.getCtx())
                    .load(news.getUrlImagenHD()).centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).into(imgDeatil);
        }


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).showBackBtn(true);
    }
}
