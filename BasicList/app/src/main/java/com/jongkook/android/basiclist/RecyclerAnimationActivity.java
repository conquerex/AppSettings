package com.jongkook.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAnimationActivity extends AppCompatActivity {

    // 1. 리사이클러뷰를 메인레이아웃에 만든다 (O)
    // 2. 리아이클러뷰에 들어갈 아이템 레이아웃을 만든다 (O)
    // ** 들어갈 데이터는 정의되어 있고
    // 3. Adapter를 만든다. (O)
    // 4. 리사이클러뷰에 아답터를 세팅 (O)
    // 5. 리사이클러뷰에 레이아웃 매니저를 지정 (O)

    public static ArrayList<RecyclerData> datas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_animation);

        datas = new ArrayList<>();

        for(int i = 0 ; i < 100 ; i++){
            RecyclerData data = new RecyclerData();

            data.title = i + " Hello";
            data.name = "Adele";
            data.image = R.mipmap.adele;
            datas.add(data);
        }

        RecyclerView listView2 = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerAnimationAdapter adapter = new RecyclerAnimationAdapter(datas,
                R.layout.recycler_animation_item, this);
        listView2.setAdapter(adapter);
        listView2.setLayoutManager(new LinearLayoutManager(this));

    }
}
