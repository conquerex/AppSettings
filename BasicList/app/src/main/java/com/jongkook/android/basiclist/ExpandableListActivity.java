package com.jongkook.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class ExpandableListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);

        ExpandableListView listView = (ExpandableListView)findViewById(R.id.expandableListView);


        // 어답터에 넘겨 줄 데이터 정의
        ArrayList<ExpandData> datas = new ArrayList<>();
        ExpandData data = new ExpandData();
        data.cityName = "서울";
        data.guNames.add("강동");
        data.guNames.add("강남");
        data.guNames.add("마포");
        data.guNames.add("서초");
        data.guNames.add("구로");
        datas.add(data);

        data = new ExpandData();
        data.cityName = "광주";
        data.guNames.add("광산");
        data.guNames.add("중구");
        data.guNames.add("북구");
        datas.add(data);

        data = new ExpandData();
        data.cityName = "부산";
        data.guNames.add("해운대");
        data.guNames.add("남구");
        data.guNames.add("사하");
        data.guNames.add("영도");
        data.guNames.add("중구");
        data.guNames.add("수영");
        datas.add(data);

        ExpandableAdapter ea = new ExpandableAdapter(
                this,
                R.layout.expand_parent_item,
                R.layout.expand_child_item,
                datas
        );

        listView.setAdapter(ea);
    }
}
