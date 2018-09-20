package com.abrsoftware.speeddytest.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.abrsoftware.speeddytest.MyApplication;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.model.Brand;
import com.abrsoftware.speeddytest.model.News;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by AbrWin on 29/10/17.
 */

public class AdapterBrand extends RecyclerView.Adapter<AdapterBrand.BrandHolder> {
    private List<News> brandList;
    private onItemClickListener listener;

    public AdapterBrand(List<News> brandList, onItemClickListener listener) {
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
        holder.singleNew = brandList.get(position);
        holder.nameBran.setText(holder.singleNew.getTitle());
        holder.resume.setText(holder.singleNew.getDescription());
        if (holder.singleNew != null && !TextUtils.isEmpty(holder.singleNew.getUrlImagen())){
            Glide.with(MyApplication.getCtx())
                    .load(holder.singleNew.getUrlImagen()).centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).into(holder.img);
        }

    }

    @Override
    public int getItemCount() {
        return brandList != null && brandList.size() > 0 ? brandList.size() : 0;
    }

    public class BrandHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {
        private TextView nameBran;
        private TextView resume;
        private ImageView img;
        public News singleNew;
        public BrandHolder(View itemView) {
            super(itemView);
            nameBran = itemView.findViewById(R.id.title);
            resume = itemView.findViewById(R.id.description);
            img = itemView.findViewById(R.id.imgNews);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClickRecipe(this);
        }
    }


    public interface onItemClickListener {
        void onClickRecipe(BrandHolder itemHolder);

        void onItemCheck(BrandHolder item);

        void onItemUncheck(BrandHolder item);
    }
}
