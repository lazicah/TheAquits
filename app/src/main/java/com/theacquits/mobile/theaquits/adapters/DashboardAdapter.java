package com.theacquits.mobile.theaquits.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theacquits.mobile.theaquits.R;

/**
 * Created by root on 3/9/19.
 */

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyHolder> {

    public DashboardAdapter() {
        super();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class MyHolder  extends RecyclerView.ViewHolder{


        public MyHolder(View itemView) {
            super(itemView);
        }
    }
}
