package com.github.pwittchen.neurosky.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Personal extends AppCompatActivity {

    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        //設定隱藏標題
        getSupportActionBar().hide();



    }

}

    /*//頁面跳轉  點選確定->回到主頁面
    Button button1 = findViewById(R.id.sure);
        button1.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Intent intent=new Intent();
        intent.setClass(personal.this, home.class);
        startActivity(intent);
        }
        });

        //switch開關
        aSwitch=(Switch)findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
@Override
public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked == true){
        Toast.makeText(getBaseContext(),"On",Toast.LENGTH_SHORT).show();
        }else{
        Toast.makeText(getBaseContext(),"OFF",Toast.LENGTH_SHORT).show();
        }

        }
        });*/
