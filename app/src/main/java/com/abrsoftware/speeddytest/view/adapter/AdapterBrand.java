package com.abrsoftware.speeddytest.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.model.Brand;

import java.util.List;

/**
 * Created by AbrWin on 29/10/17.
 */

public class AdapterBrand extends RecyclerView.Adapter<AdapterBrand.BrandHolder> {
    private List<Brand> brandList;
    private onItemClickListener listener;

    public AdapterBrand(List<Brand> brandList, onItemClickListener listener) {
        this.brandList = brandList;
        this.listener = listener;
    }

    @Override
    public BrandHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new BrandHolder(view);
    }

    @Override
    public void onBindViewHolder(final BrandHolder holder, int position) {
        holder.singleBrand = brandList.get(position);
        holder.nameBran.setText(holder.singleBrand.getName());
        holder.resume.setText(holder.singleBrand.getResume());

        //in some cases, it will prevent unwanted situations
        holder.checkBox.setOnCheckedChangeListener(null);

        //if true, your checkbox will be selected, else unselected
        holder.checkBox.setChecked(holder.checkBox.isSelected());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set your object's last status
                holder.checkBox.setSelected(isChecked);
            }
        });

    }

    @Override
    public int getItemCount() {
        return brandList != null && brandList.size() > 0 ? brandList.size() : 0;
    }

    public class BrandHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {
        private TextView nameBran;
        private TextView resume;
        private CheckBox checkBox;
        public Brand singleBrand;

        public BrandHolder(View itemView) {
            super(itemView);
            nameBran = itemView.findViewById(R.id.nameBrand);
            resume = itemView.findViewById(R.id.resumeBrand);
            checkBox = itemView.findViewById(R.id.checkBox);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClickRecipe(this);
        }
    }


    public interface onItemClickListener {
        void onClickRecipe(BrandHolder itemHolder);
    }
}
