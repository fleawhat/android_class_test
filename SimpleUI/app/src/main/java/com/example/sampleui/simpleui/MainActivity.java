package com.example.sampleui.simpleui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity { //繼承自Activity類別

    private static final int REQUEST_CODE_MENU_ACTIVITY = 0;

    TextView textView;
    EditText editText;
    CheckBox hideCheckBox;
    ListView listView;
    Spinner spinner;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //覆寫Activity類別的onCreate方法
        super.onCreate(savedInstanceState); // 一定要加入呼叫父類別onCreate方法的敘述
        setContentView(R.layout.activity_main); // 指定這個元件使用的畫面配置資源


        textView = (TextView) findViewById(R.id.textView);//跟xml中的元件做連結
        editText = (EditText) findViewById(R.id.editText);
        hideCheckBox = (CheckBox) findViewById(R.id.checkBox);
        listView = (ListView) findViewById(R.id.listView);
        spinner = (Spinner) findViewById(R.id.spinner);


        //SharePreferences的功能：儲存應用程式的設定值，方便下次應用程式被啟動時，載入偏好設定，讓應用程式自動回復到前一次設定值

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

        hideCheckBox = (CheckBox) findViewById(R.id.checkBox);

        hideCheckBox.setChecked(sp.getBoolean("hideCheckBox", false));//儲存上個checkbox的狀態

        hideCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("hideCheckBox", hideCheckBox.isChecked());
                editor.apply();
            }
        });

        setListView();//呼叫listview
        setSpinner();//呼叫spinner
    }

    private void setListView()
    {
        String[] data = Utils.readFile(this, "history.txt").split("\n");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }

    private void setSpinner()
    {
        String[] data = getResources().getStringArray(R.array.storeInfo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data);
        spinner.setAdapter(adapter);
    }

    public void submit(View view)
    {
        //Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show();
        //textView.setText("Test");
        String text = editText.getText().toString();
        Utils.writeFile(this, "history.txt", text + '\n');

        if (hideCheckBox.isChecked()) {
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();

            textView.setText("******");
            editText.setText("******");
            return;
        }
        editText.setText("");
        textView.setText(text);
    }

    public void goToMenu(View view)
    {
        Intent intent = new Intent();//按alt+enter可以import紅字
        intent.setClass(this, DrinkMenuActivity.class);

        //startActivity(intent);
        startActivityForResult(intent, REQUEST_CODE_MENU_ACTIVITY);//要求系統回傳結果
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("debug", "main activity result");
        
        if(requestCode == REQUEST_CODE_MENU_ACTIVITY)
        {
            if(resultCode == RESULT_OK)
            {
                textView.setText(data.getStringExtra("result"));
            }
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("debug", "main menu onDestroy");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("debug", "main menu onStop");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("debug", "main menu onStart");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("debug", "drink menu onPause");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("debug", "main menu onResume");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("debug", "main menu onRestart");
    }

}




