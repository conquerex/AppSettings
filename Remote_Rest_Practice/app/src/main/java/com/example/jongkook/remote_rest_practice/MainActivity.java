package com.example.jongkook.remote_rest_practice;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    // MapView 참고 http://seuny.tistory.com/14
    public void getWeather(View view)
    {
        EditText tvLon = (EditText) findViewById(R.id.lon);
        String strLon = tvLon.getText().toString();
        int lon = Integer.parseInt(strLon);

        EditText tvLat = (EditText) findViewById(R.id.lat);
        String strLat = tvLat.getText().toString();
        int lat = Integer.parseInt(strLat);


        // 날씨를 읽어오는 API 호출
        OpenWeatherAPITask t= new OpenWeatherAPITask();
        try {
            // 임의의 값을 입력 : 30, 30
            Weather w = t.execute(30,30).get();

            // System.out.println("Temp :"+w.getTemprature());

            TextView tem = (TextView)findViewById(R.id.tem);
            String temperature = String.valueOf(w.getTemprature());

            tem.setText(temperature);
            //w.getTemprature());


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
