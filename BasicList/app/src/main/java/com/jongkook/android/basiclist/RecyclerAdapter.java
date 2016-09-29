package com.jongkook.android.basiclist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jongkook on 2016. 9. 28..
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<RecyclerData> datas;
    int itemLayout;

    public RecyclerAdapter(ArrayList<RecyclerData> datas, int itemLayout){
        this.datas = datas;
        this.itemLayout = itemLayout;
    }

    // view를 만들어서 홀더에 저장하는 역할
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new ViewHolder(view);
    }

    // 일반 ListAdapter의 getView를 대체하는 함수
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerData data = datas.get(position);
        holder.img.setBackgroundResource(data.image);
        holder.text1.setText(data.title);
        holder.text2.setText(data.name);
        holder.itemView.setTag(data);

    }

    //
    @Override
    public int getItemCount() {
        return datas.size();
    }

    // 데이터를 재사용해주는 객체
    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView text1;
        TextView text2;

        public ViewHolder(View itemView){
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.imageView);
            text1 = (TextView)itemView.findViewById(R.id.title);
            text2 = (TextView)itemView.findViewById(R.id.name);
        }
    }
}
