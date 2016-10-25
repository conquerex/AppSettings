package com.example.jongkook.remote_httpurlconnection_cookie;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    EditText etId;
    EditText etPassword;
    Button btnSignid;
    TextView tvResult;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getApplicationContext().getSharedPreferences("cookie", 0);
        editor = sp.edit();
        setContentView(R.layout.activity_main);

        etId = (EditText)findViewById(R.id.editText);
        etPassword = (EditText)findViewById(R.id.editText3);
        tvResult = (TextView)findViewById(R.id.tvResult);
        btnSignid = (Button)findViewById(R.id.button);
        btnSignid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        String keyID = "USERID";
        String keyPWD = "USERPWD";

        tvResult.setText(sp.getString(keyID,"")+" ;;;;;; "+sp.getString(keyPWD,""));
    }

    private void signIn(){

        Map userInfo = new HashMap();
        userInfo.put("user_id",etId.getText().toString());
        userInfo.put("user_pwd",etPassword.getText().toString());

        new AsyncTask<Map, Void, String>(){
            ProgressDialog progress;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress = new ProgressDialog(MainActivity.this);
                progress.setTitle("다운로드");
                progress.setMessage("downloading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setCancelable(false);
                progress.show();
            }

            @Override
            protected String doInBackground(Map... params) {
                String result = "";;
                String url = "http://192.168.0.151:8080/setCookies.jsp";
                try{
                    result = Remote.postData(url, params[0], "POST");
                    Log.i("result","--------- "+result);
                }catch (Exception e){
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                // tvResult.setText(result);

                List<HttpCookie> cookies = Remote.cookieManager.getCookieStore().getCookies();
                StringBuffer cookieString = new StringBuffer();
                for(HttpCookie cookie : cookies){
                    cookieString.append(cookie.getName()+"-"+cookie.getValue()+"\n");
                    editor.putString(cookie.getName(),cookie.getValue());
                    // 삭제 editor.remove("키") , 전체삭제 editor.clear()
                }
                editor.commit();

                tvResult.setText("Cookie : "+cookieString.toString());

                progress.dismiss();
            }

        }.execute(userInfo);
    }
}
