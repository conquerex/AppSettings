package com.example.jongkook.firebase_database;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    // DatabaseReference rootRef;
    DatabaseReference userRef;

    ArrayList<Map<String,User>> datas = new ArrayList<>();
    EditText etUid;
    EditText etName;
    EditText etEmail;
    Button btn;
    Button btnOpen;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        // Database Connection
        database = FirebaseDatabase.getInstance();

        etUid = (EditText) findViewById(R.id.etId);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btn = (Button) findViewById(R.id.btnAdd);
        btnOpen = (Button) findViewById(R.id.btnOpen);
        listView = (ListView)findViewById(R.id.listView);

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BbsActivity.class);
                startActivity(intent);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = etUid.getText().toString().trim(); // trim() : 문자열 앞뒤 공백 제거 메소드
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();

                if(!"".equals(uid) && !"".equals(name) && !"".equals(email)){
                    writeNewUser(uid,name,email);
                    etUid.setText("");
                    etName.setText("");
                    etEmail.setText("");
                }else{
                    Toast.makeText(MainActivity.this,"아이디, 이름, 이메일을 입력하세요",Toast.LENGTH_LONG).show();
                }
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        ListAdapter adapter = new ListAdapter();
        listView.setAdapter(adapter);

        // rootRef = database.getReference();
        // 참조포인트
        userRef = database.getReference("users");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot users) {
                Log.e("FireBase","snapshot="+users.getValue());

                datas = new ArrayList<>();
                for(DataSnapshot userData : users.getChildren()){
                    try {
                        Map<String, User> data = new HashMap<>();
                        String userId = userData.getKey();
                        User user = userData.getValue(User.class);
                        data.put(userId, user);
                        datas.add(data);
                    }catch(Exception e){
                        // 데이터 구조가 달라서 매핑이 안될경우 예외처리
                        e.printStackTrace();
                    }
                }
                listView.deferNotifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);
        userRef.child(userId).setValue(user);

       /*
        *  root / users / michael / name : 누구
        *                         / email : 어디
        *               / javafa  / name : 누구
        *                         / email : 어디
        */
    }


    class ListAdapter extends BaseAdapter{

        LayoutInflater inflater;

        public ListAdapter() {
            inflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null){
                view = inflater.inflate(R.layout.item, null);
            }
            TextView tvId = (TextView)view.findViewById(R.id.tvId);
            TextView tvName = (TextView)view.findViewById(R.id.tvName);
            TextView tvEmail = (TextView)view.findViewById(R.id.tvEmail);

            Map<String, User> data = datas.get(i);
            String uid = data.keySet().iterator().next();

            User user = data.get(uid);

            tvId.setText(uid);
            tvName.setText(user.username);
            tvEmail.setText(user.email);
            return view;
        }
    }
}