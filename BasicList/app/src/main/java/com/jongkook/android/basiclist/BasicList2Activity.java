package com.jongkook.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicList2Activity extends AppCompatActivity {

    ArrayList<Map<String,String>> datas;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_list2);
        listView = (ListView)findViewById(R.id.listView);

        setDatas();
    }

    private void setDatas(){
        datas = new ArrayList<>();
        for(int i=0;i<26;i++){
            Map<String, String> map = new HashMap<>();
            map.put("number",i+"");
            map.put("char",(char)(65+i)+"");
            map.put("char2",(char)(97+i)+"");
            datas.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(
                this,                               // 1. context
                datas,                              // 2. data
                R.layout.activity_basic_list2_item, // 3. custom layout
                new String[]{"number","char","char2"},        // 4. datas에 들어가있는 맵의 키값들
                new int[]{R.id.text1,R.id.text2,R.id.text3} // 5. 커스텀 레이아웃의 view 아이디들
        );

        listView.setAdapter(adapter);

    }

}