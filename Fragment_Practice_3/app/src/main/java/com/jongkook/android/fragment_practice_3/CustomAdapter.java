package com.jongkook.android.fragment_practice_3;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItems = new ArrayList<>();
    Context context;


    // ListViewAdapter의 생성자
    public CustomAdapter(){

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItems.size();
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItems.get(position);
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_custom_list, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView textView1 = (TextView)convertView.findViewById(R.id.textView);
        TextView textView2 = (TextView)convertView.findViewById(R.id.textView2);
        Log.i("TV",">>>>>> " + textView1.toString() + " <<<<");

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItems.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        textView1.setText(listViewItem.getTitle());
        textView2.setText(listViewItem.getContents());

        return convertView;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String title, String contents){
        ListViewItem item = new ListViewItem();
        item.setTitle(title);
        item.setContents(contents);

        listViewItems.add(item);
    }

}
