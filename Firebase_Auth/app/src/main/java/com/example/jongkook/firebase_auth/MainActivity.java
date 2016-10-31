package com.example.jongkook.firebase_auth;

import android.support.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ArrayList<Map<String,User>> datas = new ArrayList<>();

    FirebaseDatabase database;
    // DatabaseReference rootRef;
    DatabaseReference userRef;

    public static final String TAG = "Activity";

    Button btnSignup;
    Button btnSignin;
    Button btnSignout;
    EditText etEmail;
    EditText etPassword;
    TextView tvResult;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();

        btnSignup = (Button)findViewById(R.id.btnSignup);
        btnSignin = (Button)findViewById(R.id.btnSignin);
        btnSignout = (Button)findViewById(R.id.btnSignout);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);
        tvResult = (TextView)findViewById(R.id.tvResult);
        listView = (ListView)findViewById(R.id.listView);

        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();

            }
        });

        // 5. 들어가기
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String pw    = etPassword.getText().toString().trim();
                if(!"".equals(email) && !"".equals(pw)){
                    inUser(email, pw);
                }else {
                    Toast.makeText(MainActivity.this,"공백없이 입력하시오",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 4. 신규 계정 생성
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String pw    = etPassword.getText().toString().trim();
                if(!"".equals(email) && !"".equals(pw)){
                    addUser(email, pw);
                }else {
                    Toast.makeText(MainActivity.this,"공백없이 입력하시오",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 1. 인증객체 가져오기
        mAuth = FirebaseAuth.getInstance();

        // 2. 리스너 설정
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        // 6. 데이터 불러오기

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

    // 3. 리스너 해제 및 재등록 처리
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void inUser(String email, String pw){
        mAuth.signInWithEmailAndPassword(email, pw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "로그인에 실패하였습니다 ㅠㅠ",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "로그인에 성공하였습니다 ^^",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void addUser(String email, String pw){
        mAuth.createUserWithEmailAndPassword(email, pw)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "사용자 등록에 실패하였습니다 ㅠㅠ",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "사용자 등록에 성공하였습니다 ^^",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            })
        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.w(TAG,"---- addOnSuccessListener ---- " );
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG,"---- addOnFailureListener ---- " + e.getMessage());
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


    class ListAdapter extends BaseAdapter {

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