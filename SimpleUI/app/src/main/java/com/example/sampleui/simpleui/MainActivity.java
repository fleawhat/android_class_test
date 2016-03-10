package com.example.sampleui.simpleui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity { //繼承自Activity類別
    TextView textView;
    EditText editText;
    CheckBox hideCheckBox;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //覆寫Activity類別的onCreate方法
        super.onCreate(savedInstanceState); // 一定要加入呼叫父類別onCreate方法的敘述
        setContentView(R.layout.activity_main); // 指定這個元件使用的畫面配置資源


        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);
        hideCheckBox = (CheckBox)findViewById(R.id.checkBox);

        sp = getSharedPreferences("setting", Context.MODE_PRIVATE);//取得setting內容
        editor = sp.edit();//在白紙上寫字

        editText.setText(sp.getString("editText", ""));//如果找不到editText，就會輸入空白字串

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                editor.putString("editText", editText.getText().toString());//輸入字串給editor
                editor.apply();//儲存

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    submit(v);
                    return true;
                }

                return false;
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    submit(v);
                    return true;
                }
                return false;
            }
        });

        hideCheckBox =  (CheckBox)findViewById(R.id.checkBox);

        hideCheckBox.setChecked(sp.getBoolean("hideCheckBox", false));//儲存上個checkbox的狀態

        hideCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("hideCheckBox", hideCheckBox.isChecked());
                editor.apply();
            }
        });
    }
    public void submit(View view)
    {
        //Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show();
        //textView.setText("Test");
        String text = editText.getText().toString();
        if (hideCheckBox.isChecked()) {
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();

            textView.setText("******");
            editText.setText("******");
            return;
        }
        editText.setText("");
        textView.setText(text);
    }

}
