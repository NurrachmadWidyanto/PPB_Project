package com.e.ppb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterList extends RecyclerView.Adapter<AdapterList.HolderItem> {

    List<ModelList> mListItem;
    Context context;

    public AdapterList(List<ModelList> mListItem, Context context){
        this.mListItem = mListItem;
        this.context = context;
    }

    @Override
    public HolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list,parent,false);
        HolderItem holder = new HolderItem(layout);

        return holder;
    }

    @Override
    public void onBindViewHolder(HolderItem holder, int position) {
        final ModelList mlist = mListItem.get(position);

        final String idx = mlist.getId();

        holder.tv_judul.setText(mlist.getJudul());
        holder.tv_waktu.setText(mlist.getWaktu());
        holder.tv_isi.setText(mlist.getIsi());

        //load Image
        Glide.with(context).load(mlist.getImage())
                .override(200, 200)
                .fitCenter()
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public class HolderItem extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        CardView card_view;
        TextView tv_judul, tv_waktu, tv_isi;

        public HolderItem(View v){
            super(v);

            thumbnail = (ImageView) v.findViewById(R.id.imgberita);
            tv_judul = (TextView) v.findViewById(R.id.judul);
            tv_waktu = (TextView) v.findViewById(R.id.waktu);
            tv_isi  = (TextView) v.findViewById(R.id.isi);
            card_view = (CardView) v.findViewById(R.id.card_view);

        }

    }

}