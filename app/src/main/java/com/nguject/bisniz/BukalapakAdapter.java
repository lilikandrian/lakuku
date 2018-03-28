package com.nguject.bisniz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by User on 09/06/2017.
 */

public class BukalapakAdapter extends RecyclerView.Adapter<BukalapakAdapter.ViewHolder>{

    Context context;
    ArrayList<HashMap<String,String>> listItem;

    public BukalapakAdapter(BusinessListActivity activity,ArrayList<HashMap<String,String>> listItem){

        this.context=activity;
        this.listItem=listItem;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_business_list,null);
        return new ViewHolder(view);
    }


    public void onBindViewHolder (ViewHolder holder,int position){
        /*Glide.with(context)
                .load("https://api.bukalapak.com/v2/products.json?keywords=laptop"+listItem.get(position).get("images"))
                .crossFade()
                .placeholder(R.drawable.lipat_united_norm)
                .into(holder.img);
           */
        holder.itemName.setText(listItem.get(position).get("iNames"));
    }

    public int getItemCount(){
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemName;
        ImageView img;

        public ViewHolder (View itemView){

            super(itemView);
            //itemName=(TextView) itemView.findViewById(R.id.itemName);
            //img=(ImageView) itemView.findViewById(R.id.itemImg);

        }
    }

}