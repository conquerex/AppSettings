package com.jongkook.android.basiclist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        listView = (ListView)findViewById(R.id.listView);

        ArrayList<String> datas = new ArrayList<>();
        for(int i = 0;i<30;i++){
            datas.add("data : "+i);
        }
        adapter = new CustomAdapter(this, datas);
        listView.setAdapter(adapter);
    }
}

class CustomAdapter extends BaseAdapter{

    Context context;        // 컨텍스트
    ArrayList datas;        // 데이터 배열
    LayoutInflater inflater; // xml 파일을 인스턴스화해서 메모리에 올려준다

    public CustomAdapter(Context context, ArrayList datas){
        this.context = context;
        this.datas = datas;
        // 시스템에서 xml을 개체화 시켜주는 인플레이터를 가져온다.
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { // 자식뷰들의 개수를 리턴
        return datas.size();
    }

    @Override
    public Object getItem(int position) {   // 자식뷰를 리턴
                          // 자식뷰의 순서 - 1부터 시작
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {   // 자식뷰의 아이디값을 리턴
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //

        Log.i("GETVIEW","------- position ::: " + position);
        Log.i("VIEWGROUP","------- parent ::: " + parent.getId());

        if(convertView == null)
            Log.i("GETVIEW","------- position ::: " + position);
            Log.i("VIEWGROUP","------- parent ::: " + parent.getId());
            convertView = inflater.inflate(R.layout.activity_custom_list_item,null);

        TextView tv = (TextView)convertView.findViewById(R.id.text1);
        tv.setText(datas.get(position).toString());

        return convertView;
    }
}