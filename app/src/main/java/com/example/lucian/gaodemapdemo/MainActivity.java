package com.example.lucian.gaodemapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by qulus on 2017/3/23 0023.
 */

public class MainActivity extends AppCompatActivity {
    private TextView m_Btn1View ;
    private TextView m_Btn2View ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        m_Btn1View = (TextView) findViewById(R.id.btnText1) ;
        m_Btn2View = (TextView) findViewById(R.id.btnText2) ;

        m_Btn1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent() ;
                intent.setClass(MainActivity.this, ShowMapActivity.class) ;
                String[] strArr = new String[7] ;
                strArr[0] = "奎管处1号井" ;
                strArr[1] = "奎管处1号井" ;
                strArr[2] = "奎管处1号井" ;
                strArr[3] = "奎管处1号井" ;
                strArr[4] = "奎管处1号井" ;
                double locationX = 44.46033333333333 ;
                double locationY = 84.91884444444445 ;
                intent.putExtra("wellLocaInfo", strArr) ;
                intent.putExtra("wellLocaX", locationX) ;
                intent.putExtra("wellLocaY", locationY) ;
                startActivity(intent);
            }
        });
        m_Btn2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent() ;
                intent.setClass(MainActivity.this, ShowMapActivity.class) ;
                String[] strArr = new String[7] ;
                strArr[0] = "奎管处2号井" ;
                strArr[1] = "奎管处2号井" ;
                strArr[2] = "奎管处2号井" ;
                strArr[3] = "奎管处2号井" ;
                strArr[4] = "奎管处2号井" ;
                double locationX = 55.46033333333333 ;
                double locationY = 66.91884444444445 ;
                intent.putExtra("wellLocaInfo", strArr) ;
                intent.putExtra("wellLocaX", locationX) ;
                intent.putExtra("wellLocaY", locationY) ;
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
