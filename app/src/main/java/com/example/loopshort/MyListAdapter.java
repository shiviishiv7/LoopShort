package com.example.loopshort;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private ArrayList<MyListData> listdata;
    SecondActivity secondActivity;
    // RecyclerView recyclerView;
    public MyListAdapter(SecondActivity secondActivity, ArrayList<MyListData> listdata) {
        this.listdata = listdata;
        this.secondActivity = secondActivity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    interface setVideoClick{
        void positioncallBack(String i);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyListData myListData = listdata.get(position);
        holder.textView.setText(myListData.getTITLE());
        //holder.imageView.setImageResource(myListData.getImageUrl());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
secondActivity.positioncallBack(myListData.getID());

        //        Toast.makeText(view.getContext(),"click on item: "+myListData.getTITLE(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
