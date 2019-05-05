package com.example.webview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        button=(Button) findViewById(R.id.button);
        edit=(EditText) findViewById(R.id.edit);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button:
                Intent intent=new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(edit.getText().toString()));
                startActivity(Intent.createChooser(intent, "请选择浏览器"));
                break;
        }
    }
}
