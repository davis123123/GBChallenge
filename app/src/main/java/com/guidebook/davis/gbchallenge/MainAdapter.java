package com.guidebook.davis.gbchallenge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainDataHolder>{
    List<UpcomingGuides.Data> list;
    Context context;

    MainAdapter(List dataList, Context context){
        list = dataList;
        this.context = context;
    }

    public void add(UpcomingGuides.Data item){
        list.add(item);
    }
    @Override
    public MainDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MainDataHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainDataHolder holder, int position) {
        UpcomingGuides.Data data = (UpcomingGuides.Data) this.getItem(position);
        holder.tvName.setText(data.name);

        holder.tvCity.setText(data.venue.city);
        holder.tvState.setText(data.venue.state);
        holder.tvEndDate.setText(data.endDate);
        holder.getImage(data.icon);
    }

    private Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MainDataHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvCity, tvState, tvEndDate;
        public ImageView ivImage;

        public MainDataHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvCity = view.findViewById(R.id.tvCity);
            tvState = view.findViewById(R.id.tvState);
            tvEndDate = view.findViewById(R.id.tvEndDate);
            ivImage = view.findViewById(R.id.imgIcon);
        }
        private void getImage(String imgURL){
            final String url = imgURL;

            Picasso.with(context)
                    .load(url)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .resize(450, 400)
                    .into(ivImage, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            Picasso.with(context)
                                    .load(url)
                                    .resize(450, 400)
                                    .into(ivImage, new Callback() {
                                        @Override
                                        public void onSuccess() {
                                        }

                                        @Override
                                        public void onError() {
                                            //ivImage.setVisibility(View.GONE);
                                            Log.e("Picasso","Could not get image");
                                        }
                                    });
                        }
                    });
        }
    }

}
