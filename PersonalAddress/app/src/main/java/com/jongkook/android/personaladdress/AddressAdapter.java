package com.jongkook.android.personaladdress;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jongkook on 2016. 10. 5..
 */
public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder>{
    ArrayList<AddressData> datas;
    int itemLayout;
    Context context;

    public AddressAdapter(ArrayList<AddressData> datas, int itemLayout, Context context){
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        AddressData data = datas.get(position);
        holder.text1.setText(data.name);
        holder.text2.setText(data.phoneNum);
        holder.itemView.setTag(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text1;
        TextView text2;

        public ViewHolder(View itemView){
            super(itemView);

            text1 = (TextView)itemView.findViewById(R.id.name);
            text2 = (TextView)itemView.findViewById(R.id.phoneNum);
        }
    }
}
