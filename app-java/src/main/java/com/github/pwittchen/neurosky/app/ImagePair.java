package com.github.pwittchen.neurosky.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ImagePair extends AppCompatActivity {

    private Long startTime; //初始時間
    private Chronometer timer; //已經過時間
    private Handler handler = new Handler();


    private ArrayList<String> colorNames = new ArrayList<>();
    private ArrayList<Integer> colorValues = new ArrayList<>();
    private ArrayList<TextView> button = new ArrayList<>();

    private Random random;

    private TextView colorTextView;

    private int red;
    private int blue;
    private int green;


    private TextView ImageButtonA;
    private TextView ImageButtonB;
    private TextView ImageButtonC;

    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pair);
        //設定隱藏標題
        getSupportActionBar().hide();
        timer = (Chronometer) findViewById(R.id.timer);
        //取得目前時間
        startTime = System.currentTimeMillis();
        //設定定時要執行的方法
        handler.removeCallbacks(updateTimer);
        //設定Delay的時間
        handler.postDelayed(updateTimer, 10);
        random = new Random();
        populateBothArraylists();
        getRandomColor();
        deter();
        setupViewsAndListeners();

    }

    private void setupViewsAndListeners(){

        ImageButtonA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //Get color of drawable.
                String TagA = (String) colorTextView.getTag();

                if(ImageButtonA.getText() == TagA){
                    count++;
                    getRandomColor();
                    deter();
                    checkEnd();
                }
            }
        });
        ImageButtonB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String TagB = (String) colorTextView.getTag();

                if(ImageButtonB.getText() == TagB){
                    count++;
                    getRandomColor();
                    deter();
                    checkEnd();
                }
            }
        });
        ImageButtonC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String TagC = (String) colorTextView.getTag();

                if(ImageButtonC.getText() == TagC){
                    count++;
                    getRandomColor();
                    deter();
                    checkEnd();
                }
            }
        });
    }

    private void getRandomColor(){



//      Add a bit more randomness
        Collections.shuffle(colorNames);
        Collections.shuffle(colorValues);
        Collections.shuffle(button);

        //using a random number to get a random color from the arrayList
        String colorChosen1 = colorNames.get(0);
        String colorChosen2 = colorNames.get(1);
        String colorChosen3 = colorNames.get(2);

        //set this random color to be the text that is shown at the bottom
        colorTextView.setText(colorChosen1);
        button.get(0).setText(colorChosen1);
        button.get(1).setText(colorChosen2);
        button.get(2).setText(colorChosen3);

        //chose an actual color to be randomly generated for the text
        colorTextView.setTextColor(colorValues.get(0));
        button.get(0).setTextColor(colorValues.get(0));
        button.get(1).setTextColor(colorValues.get(1));
        button.get(2).setTextColor(colorValues.get(2));
    }

    private void checkEnd(){
        if(count == 5){
            handler.removeCallbacks(updateTimer);
            //頁面跳轉
            Intent intent = new Intent();
            intent.setClass(ImagePair.this, Home.class);
            intent.putExtra("time",startTime);
            startActivity(intent);
            finish();
        }
    }

    private void deter(){
        int col = colorTextView.getCurrentTextColor();
        if (col == -571050){
            colorTextView.setTag("紅色");
            Log.d("MyTagGoesHere", "我在紅色裡面");
        }
        else if (col == -5973084){
            colorTextView.setTag("綠色");
            Log.d("MyTagGoesHere", "我在綠色裡面");
        }
        else{
            colorTextView.setTag("藍色");
            Log.d("MyTagGoesHere", "我在藍色裡面");
        }
    }


    private void populateBothArraylists(){
        //question
        colorTextView = (TextView) findViewById(R.id.question);

        //ABC選項
        ImageButtonA = (TextView) findViewById(R.id.optionA);
        ImageButtonB = (TextView)findViewById(R.id.optionB);
        ImageButtonC = (TextView) findViewById(R.id.optionC);

        //add color names to the ArrayList
        colorNames.add("紅色");
        colorNames.add("綠色");
        colorNames.add("藍色");

        red = R.color.colorRed;
        green = R.color.colorGreen;
        blue = R.color.colorBlue;

        //Add color values to the arraylist [-571050, -5973084, -9328385]

        colorValues.add(ContextCompat.getColor(this,red));
        colorValues.add(ContextCompat.getColor(this,green));
        colorValues.add(ContextCompat.getColor(this,blue));


        button.add(ImageButtonA);
        button.add(ImageButtonB);
        button.add(ImageButtonC);



    }





    //固定要執行的方法
    private Runnable updateTimer = new Runnable() {
        public void run() {
            final TextView time = (Chronometer) findViewById(R.id.timer);
            Long spentTime = System.currentTimeMillis() - startTime;
            //計算目前已過小時數
            Long hour = (spentTime/1000)/3600;
            //計算目前已過分鐘數
            Long minius = ((spentTime/1000)/60) % 60;
            //計算目前已過秒數
            Long seconds = (spentTime/1000) % 60;
            String formattedTime = String.format("%02d:%02d:%02d",hour, minius, seconds);
            time.setText(formattedTime);
            handler.postDelayed(this, 1000);
        }
    };
}