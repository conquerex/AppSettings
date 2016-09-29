package com.jongkook.android.basiclist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jongkook on 2016. 9. 29..
 */
public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView textTitle;
        CardView cardView;

        public ViewHolder(View itemView){
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.image);
            textTitle = (TextView)itemView.findViewById(R.id.title);
            cardView = (CardView)itemView.findViewById(R.id.cardItem);

        }
    }

    ArrayList<RecyclerData> datas;
    int itemLayout;
    Context context;

    public RecyclerCardAdapter(ArrayList<RecyclerData> datas, int itemLayout, Context context){
        this.datas = datas;
        this.itemLayout = itemLayout;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final RecyclerData data = datas.get(position);
        holder.img.setBackgroundResource(data.image);
        
        holder.img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"이미지가 클릭됨!!",Toast.LENGTH_SHORT).show();
            }
        });

        Log.i("Exception"," ::::::: "+data.title);
        holder.textTitle.setText(data.title);
        holder.cardView.setTag(data);


        setAnimation(holder.cardView, position);
    }

    int lastPosition = -1;
    public void setAnimation(View view, int position){
        if(position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
