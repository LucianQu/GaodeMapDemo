package com.example.lucian.gaodemapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qulus on 2017/3/23 0023.
 */

public class MainActivity extends AppCompatActivity {
    private TextView m_Btn1View ;
    private TextView m_Btn2View ;

    public static List<WellLocationBean> m_wellLocatBeanList = new ArrayList<>() ;
    public WellLocationBean m_wellLocatBean ;

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


                m_wellLocatBean = new WellLocationBean() ;
                /*Parcel parcel = Parcel.obtain() ;
                WellLocationBean bean = WellLocationBean.CREATOR.createFromParcel(parcel);*/

                for (int i = 0; i < 10; i++) {
                    m_wellLocatBean.setM_wellName("" + i);
                    m_wellLocatBean.setM_wellAttr("" + i);
                    m_wellLocatBean.setM_wellAdmin("" + i);
                    m_wellLocatBean.setM_wellX("" + i * 1000);
                    m_wellLocatBean.setM_wellY("" + i * 1000);
                    m_wellLocatBean.setM_markerX(i* 0.000001 + 55.46033333333333);
                    m_wellLocatBean.setM_markerY(i* 0.000001 + 66.91884444444445);
                    m_wellLocatBeanList.add(m_wellLocatBean);
                }
                //parcel.recycle();

                intent.putParcelableArrayListExtra("wellLocatBeanList",
                        (ArrayList<? extends Parcelable>) m_wellLocatBeanList);
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
