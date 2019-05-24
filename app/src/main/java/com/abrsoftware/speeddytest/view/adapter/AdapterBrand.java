package com.abrsoftware.speeddytest.view.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.abrsoftware.speeddytest.MyApplication;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.model.Qoute;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by AbrWin on 23/05/19.
 */

public class AdapterBrand extends RecyclerView.Adapter<AdapterBrand.BrandHolder> {
    private List<Qoute> qouteList;
    private onItemClickListener listener;

    public AdapterBrand(List<Qoute> qouteList, onItemClickListener listener) {
        this.qouteList = qouteList;
        this.listener = listener;
    }

    @Override
    public BrandHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new BrandHolder(view);
    }

    @Override
    public void onBindViewHolder(final BrandHolder holder, int position) {
        holder.qoute = qouteList.get(position);
        holder.nameBran.setText("\""+holder.qoute.getQuote()+"\"");
        holder.resume.setText(holder.qoute.getAuthor());
        if (holder.qoute != null && !TextUtils.isEmpty(holder.qoute.getImg())){
            Glide.with(MyApplication.getCtx())
                    .load(holder.qoute.getImg()).centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).into(holder.img);
        }

    }

    @Override
    public int getItemCount() {
        return qouteList != null && qouteList.size() > 0 ? qouteList.size() : 0;
    }

    public class BrandHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {
        private TextView nameBran;
        private TextView resume;
        private ImageView img;
        public Qoute qoute;
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
