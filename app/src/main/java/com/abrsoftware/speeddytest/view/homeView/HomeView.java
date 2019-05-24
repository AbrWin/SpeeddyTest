package com.abrsoftware.speeddytest.view.homeView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abrsoftware.speeddytest.MainActivity;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.model.Qoute;
import com.abrsoftware.speeddytest.view.BaseView;
import com.abrsoftware.speeddytest.view.adapter.AdapterBrand;
import com.abrsoftware.speeddytest.view.homeView.HomeMVP.HomeMvp;
import com.abrsoftware.speeddytest.view.homeView.HomeMVP.HomePresenterImp;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class HomeView extends BaseView implements HomeMvp.View, AdapterBrand.onItemClickListener {

    private View rootView;
    private AdapterBrand adapterBrand;
    private List<Qoute> qouteList;
    private LinearLayoutManager linearLayout;
    private RecyclerView recyclerBrands;
    private HomePresenterImp presenter;
    private BottomSheetDialog bottomSheetDialog;
    private Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.view_home, container, false);
        bundle = new Bundle();

        linearLayout = new LinearLayoutManager(getContext());
        recyclerBrands = rootView.findViewById(R.id.recyclerBrands);
        presenter = new HomePresenterImp(this);
        presenter.oncreate();
        if (qouteList != null && qouteList.size() > 0) {
            lisInView(qouteList);
        } else {

            presenter.getQuotes();
        }

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
        ((MainActivity) getActivity()).showToolbar(true, getString(R.string.welcome));
    }

    @Override
    public void onClickContext(AdapterBrand.BrandHolder itemHolder) {
        if(itemHolder.qoute != null){
            ((MainActivity)getActivity()).inflateBottomSheet(R.layout.bottom_menu, R.style.BottomSheetDialogContext);
            ((MainActivity)getActivity()).showBottomSheet();

            Qoute qoute = itemHolder.qoute;

            bundle.putSerializable("qoute", qoute);
            ((MainActivity)getActivity()).setBundle(bundle);
        }
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
    public void succesGetQoutes(List<Qoute> qouteList) {

        if (qouteList.size() > 0) {
            lisInView(qouteList);
        }
    }

    private void lisInView(List<Qoute> qoute) {
        qouteList = qoute;
        adapterBrand = new AdapterBrand(qoute, this);
        recyclerBrands.setLayoutManager(linearLayout);
        recyclerBrands.setAdapter(adapterBrand);
        adapterBrand.notifyDataSetChanged();
    }

    @Override
    public void showMsj(String msj) {
        Toast.makeText(getContext(), msj, Toast.LENGTH_LONG).show();
    }
}
