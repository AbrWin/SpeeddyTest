package com.abrsoftware.speeddytest.view.homeView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.abrsoftware.speeddytest.MainActivity;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.ResponsiveUIstate;
import com.abrsoftware.speeddytest.model.Brand;
import com.abrsoftware.speeddytest.model.News;
import com.abrsoftware.speeddytest.view.BaseView;
import com.abrsoftware.speeddytest.view.adapter.AdapterBrand;
import com.abrsoftware.speeddytest.view.homeView.HomeMVP.HomeMvp;
import com.abrsoftware.speeddytest.view.homeView.HomeMVP.HomePresenterImp;

import java.util.List;

public class HomeView extends BaseView implements HomeMvp.View, AdapterBrand.onItemClickListener {

    private View rootView;
    private AdapterBrand adapterBrand;
    private List<News> newList;
    private LinearLayoutManager linearLayout;
    private RecyclerView recyclerBrands;
    private Button btnAcept;
    private Button btnCancel;
    private HomePresenterImp presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.view_home, container, false);

        btnAcept = rootView.findViewById(R.id.btnAccept);
        btnCancel = rootView.findViewById(R.id.btnCancel);
        linearLayout = new LinearLayoutManager(getContext());
        recyclerBrands = rootView.findViewById(R.id.recyclerBrands);
        presenter = new HomePresenterImp(this);
        presenter.oncreate();
        if (newList != null && newList.size() > 0){
            lisInView(newList);
        }else {
            btnAcept.setVisibility(View.INVISIBLE);
            btnCancel.setVisibility(View.INVISIBLE);
            presenter.getNewsEs();
        }

        changeEs();
        changeEn();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.ondestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).showBackBtn(false);
        ((MainActivity) getActivity()).showToolbar(true, "Bienvenido");
    }

    @Override
    public void onClickRecipe(AdapterBrand.BrandHolder itemHolder) {
        News news = itemHolder.singleNew;
        Bundle bundle = new Bundle();
        bundle.putSerializable("news", news);
        ((MainActivity) getActivity()).changeFragment(ResponsiveUIstate.DETAILNEWS.setBundle(bundle));
    }

    @Override
    public void onItemCheck(AdapterBrand.BrandHolder item) {
        //item.singleBrand.setChecked(true);
       // newList.add(item.singleBrand);
    }

    @Override
    public void onItemUncheck(AdapterBrand.BrandHolder item) {
        //newList.remove(item.singleBrand);
    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            inflateCustomDialog(R.layout.loading);
            showCustomDialog();
        } else {
            hiddeCustomDialog();
        }
    }

    @Override
    public void succesGetNews(List<News> news) {
        btnAcept.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
        if(news.size() > 0){
            lisInView(news);
        }
    }

    private void lisInView(List<News> news) {
        newList = news;
        adapterBrand = new AdapterBrand(news, this);
        recyclerBrands.setLayoutManager(linearLayout);
        recyclerBrands.setAdapter(adapterBrand);
        adapterBrand.notifyDataSetChanged();
    }

    private void changeEn() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAcept.setVisibility(View.INVISIBLE);
                btnCancel.setVisibility(View.INVISIBLE);
                presenter.getNewsEn();
            }
        });
    }

    private void changeEs() {
        btnAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAcept.setVisibility(View.INVISIBLE);
                btnCancel.setVisibility(View.INVISIBLE);
                presenter.getNewsEs();
            }
        });
    }

    @Override
    public void showMsj(String msj) {
        btnAcept.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), msj, Toast.LENGTH_LONG).show();
    }
}
