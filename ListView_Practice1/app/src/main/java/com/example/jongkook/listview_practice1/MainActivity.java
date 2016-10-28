package com.example.jongkook.listview_practice1;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    ArrayList<String> arGeneral = new ArrayList<>();
    String[] arGeneral = {"나성범", "김태군", "해커", "스튜어트", "원종현"};
//    ArrayAdapter adapter;
    ListView listView;
    ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list);

//        arGeneral.add("손종국");
//        arGeneral.add("구자욱");
//        arGeneral.add("나성범");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arGeneral);
//        adapter = ArrayAdapter.createFromResource(this, R.array.country,
//                android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        // 레이아웃 속성 추가
        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);
        listView.setDivider(new ColorDrawable(Color.YELLOW));
        listView.setDividerHeight(5);

        // 클릭 추가
        listView.setOnItemClickListener(listener);
    }

    AdapterView.OnItemClickListener listener
            = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String mes;
            mes = "Select item = " + arGeneral[i];
            Toast.makeText(MainActivity.this, mes, Toast.LENGTH_SHORT).show();
        }
    };
}
