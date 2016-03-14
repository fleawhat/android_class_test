package com.example.sampleui.simpleui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class DrinkMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);
    }
    public void add(View view)//按鈕onClick設定add，下面設定add的功能：click是+1的輸入
    {
        Button button = (Button) view;
        int number = Integer.parseInt(button.getText().toString());//string轉成int(轉成非object)
        number ++;//number = number + 1
        button.setText(String.valueOf(number));//轉回object

    }
    public void cancel(View view)//設定返回鍵：結束這個activity，回到main activity
    {
        finish();
    }

    public void done(View view)//設定完成鍵：回傳order done資訊，結束activity
    {
        Intent data = new Intent();
        data.putExtra("result","order done");
        setResult(RESULT_OK, data);
        finish();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("debug", "drink menu onDestroy");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("debug", "drink menu onStop");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("debug", "drink menu onStart");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("debug", "drink menu onPause");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("debug", "drink menu onResume");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("debug", "drink menu onRestart");
    }
}
