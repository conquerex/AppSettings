package com.example.jongkook.remote_rest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnGetList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetList = (Button)findViewById(R.id.btnGetList);
        btnGetList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        String result = "";
        boolean firstCheck = false;
        for (int i=0;i<20;i++){
            if(firstCheck){
                result = result + ",";
            }else{
                firstCheck = true;
            }
            result = result +"{\"key\":\"value+"+i+"\"}";
        }

    }

    ArrayList<String> datas = new ArrayList<>();
    class CustomAdapter extends BaseAdapter{
        LayoutInflater inflater;
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
            if(view == null)
                view = inflater.inflate(R.layout.list_item,null);

            return view;
        }
    }

    private void getNaver(){
        new AsyncTask<Void,Void, String>(){
            ProgressDialog progress;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress = new ProgressDialog(MainActivity.this);
                progress.setTitle("다운로드");
                progress.setMessage("download");
                progress.setProgressStyle((ProgressDialog.STYLE_SPINNER));
                progress.setCancelable(false);
                progress.show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                String result = null;
                try{
                    result = Remote.getData("http://192.168.0.151:8080");
                    Log.i("doInBackground","----- "+result);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                StringBuffer sb = new StringBuffer();
                try{
                    // 전체 문자열을 json 오브젝트로 변환
                    JSONObject json = new JSONObject(s);
                    // 특정 키를 가진 단일값을 꺼낸다
                    // JSONObject topObject = json.getJSONObject("realtimeStationArrival");
                    // 특정키를 가진 배열 형태의 값을 꺼낸다
                    JSONArray rows = json.getJSONArray("realtimeArrivalList");
                    int rowsCount = rows.length();
                    for(int i = 0;i < rowsCount;i++){
                        // 배열을 반복문을 돌면서 배열의 index로 값을 꺼낸다
                        JSONObject result = (JSONObject)rows.get(i);
                        // 최종적으로 각열의 컬럼으ㅏㅣ 키이름에 해당하는 값을 꺼낸다
                        String trainName = result.getString("trainLineNm");
                        sb.append(trainName + "\n");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                // textView.setText(sb.toString());
                progress.dismiss();
            }
        }.execute();
    }

}
