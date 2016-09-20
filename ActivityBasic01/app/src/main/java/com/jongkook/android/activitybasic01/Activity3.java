package com.jongkook.android.activitybasic01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by jongkook on 2016. 9. 20..
 * 수동으로 Activity 만들기
 *
 * 1. Activity 클래스 상속
 * 2. onCreate 메서드를 오버라이드
 * 3. onCreate 메서드 안에서 레이아웃.xml을 Set
 * 4.
 *
 */
public class Activity3 extends Activity {

    private static final String TAG = "Activity3";

    public void openActivity4(View v){
        // Activity를 호출하는 로직
        // 1. 인텐트를 생성          컨텍스트 , 호출할 액티비티 클래스
        Intent intent = new Intent(this, Activity4.class);
        // 2. 생성된 인텐트를 시스템에 넘겨서 실행
        startActivity(intent);
    }

    public void openActivity5(View v){
        // Activity를 호출하는 로직
        // 1. 인텐트를 생성          컨텍스트 , 호출할 액티비티 클래스
        Intent intent = new Intent(this, Activity5.class);
        // 2. 생성된 인텐트를 시스템에 넘겨서 실행
        startActivity(intent);
    }

    @Override // 1. 액티비티 생성시에 호출되는 함수
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
    }

    @Override // 2. 화면에 나타나기 바로 전에 호출되는 함수
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "called onStart");
    }

    @Override // 3. 이 때 사용자가 화면에 입력할 수 있게 된다
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "called onResume");
    }

    // 3.5. Activity가 동작하고 있는 중

    @Override // 4. 화면에서 사라졌을 때 < Activity가 나를 일부만 가리고 있을
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "called onPause");
    }

    @Override // 5. 화면에서 사라졌을 때
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "called onStop");
    }

    @Override // 5.5. Stop 되었던 Activity가 다시 호출될 때 > onStart
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "called onRestart");
    }

    @Override // 6. 앱이 종료되면 (메모리에서 해제되면)
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "called onDestroy");
    }

}
