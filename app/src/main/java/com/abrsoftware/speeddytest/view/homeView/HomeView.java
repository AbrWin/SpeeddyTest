package com.abrsoftware.speeddytest.view.homeView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abrsoftware.speeddytest.MainActivity;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.ResponsiveUIstate;
import com.abrsoftware.speeddytest.dummy.DummyBrand;
import com.abrsoftware.speeddytest.model.Brand;
import com.abrsoftware.speeddytest.view.adapter.AdapterBrand;

import java.util.ArrayList;
import java.util.List;

public class HomeView extends Fragment implements AdapterBrand.onItemClickListener {

    private View rootView;
    private AdapterBrand adapterBrand;
    private List<Brand> brandList;
    private List<Brand> newList;
    private LinearLayoutManager linearLayout;
    private RecyclerView recyclerBrands;
    private Button btnAcept;
    private Button btnCancel;

    public HomeView() {
        // Required empty public constructor
    }

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
        DummyBrand dummyBrand = new DummyBrand();
        brandList = dummyBrand.getListBrand();
        newList = new ArrayList<>();
        lisInView(brandList);

        btnAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newList != null && newList.size() > 0) {
                    lisInView(newList);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (brandList != null && brandList.size() > 0) {
                    lisInView(brandList);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).showToolbar(true, "Bienvenido: SpeedyMobil");
    }

    @Override
    public void onClickRecipe(AdapterBrand.BrandHolder itemHolder) {
        Brand brand = itemHolder.singleBrand;
        Bundle bundle = new Bundle();
        bundle.putSerializable("brand", brand);
        ((MainActivity) getActivity()).changeFragment(ResponsiveUIstate.DETAILBRAND.setBundle(bundle));
    }

    @Override
    public void onItemCheck(AdapterBrand.BrandHolder item) {
        item.singleBrand.setChecked(true);
        newList.add(item.singleBrand);
    }

    @Override
    public void onItemUncheck(AdapterBrand.BrandHolder item) {
        newList.remove(item.singleBrand);
    }

    private void lisInView(List<Brand> brandList) {
        adapterBrand = new AdapterBrand(brandList, this);
        recyclerBrands.setLayoutManager(linearLayout);
        recyclerBrands.setAdapter(adapterBrand);
        adapterBrand.notifyDataSetChanged();
    }
}
