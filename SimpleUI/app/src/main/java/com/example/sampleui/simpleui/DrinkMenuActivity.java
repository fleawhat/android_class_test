package com.example.sampleui.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DrinkMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);
    }
    public void add(View view)
    {
        Button button = (Button) view;
        int number = Integer.parseInt(button.getText().toString());//string轉成int(轉成非object)
        number ++;//number = number + 1
        button.setText(String.valueOf(number));//轉回object

    }
    public void cancel(View view)//結束這個activity，回到main activity
    {
        finish();
    }
}
