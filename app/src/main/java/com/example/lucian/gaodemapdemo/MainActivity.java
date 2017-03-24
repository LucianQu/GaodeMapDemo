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
    public  List<WellLocationBean> m_wLBeanList = new ArrayList<>() ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        m_Btn1View = (TextView) findViewById(R.id.btnText1) ;
        m_Btn1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent() ;
                intent.setClass(MainActivity.this, ShowMapActivity.class) ;

                m_wLBeanList.clear();
                for (int i = 0; i < 400; i++) {

                    if(i <= 100) {
                        WellLocationBean wLBean = new WellLocationBean();
                        wLBean.setM_wellNo("奎管处" + i);
                        wLBean.setM_wellName("屈陆胜");
                        wLBean.setM_wellUser("" + i);
                        wLBean.setM_wellX(i * 0.01 + 44.42);
                        wLBean.setM_wellY(84.9);
                        m_wLBeanList.add(wLBean);
                    }
                    if(i <= 200 && i >100) {
                        WellLocationBean wLBean = new WellLocationBean();
                        wLBean.setM_wellNo("奎管处" + i);
                        wLBean.setM_wellName("苏景台");
                        wLBean.setM_wellUser("" + i);
                        wLBean.setM_wellX(44.42);
                        wLBean.setM_wellY((i-100) * 0.01 + 84.9);
                        m_wLBeanList.add(wLBean);
                    }

                    if(i <= 300 && i >200) {
                        WellLocationBean wLBean = new WellLocationBean();
                        wLBean.setM_wellNo("奎管处" + i);
                        wLBean.setM_wellName("崔健");
                        wLBean.setM_wellUser("" + i);
                        wLBean.setM_wellX(1 +44.42);
                        wLBean.setM_wellY((i-200) * 0.01 + 84.9);
                        m_wLBeanList.add(wLBean);
                    }

                    if(i <= 400 && i > 300) {
                        WellLocationBean wLBean = new WellLocationBean();
                        wLBean.setM_wellNo("奎管处" + i);
                        wLBean.setM_wellName("张威");
                        wLBean.setM_wellUser("" + i);
                        wLBean.setM_wellX((i-300) * 0.01 + 44.42);
                        wLBean.setM_wellY(1+ 84.9);
                        m_wLBeanList.add(wLBean);
                    }
                }
                WellLocationBean wLBean = new WellLocationBean();
                wLBean.setM_wellNo("北京奥特美克科技股份有限公司");
                wLBean.setM_wellName("研发中心_平台组");
                wLBean.setM_wellUser("屈陆胜");
                wLBean.setM_wellX(0.5 + 44.42);
                wLBean.setM_wellY(0.5+ 84.9);
                m_wLBeanList.add(wLBean);

                intent.putParcelableArrayListExtra("wellLocatBeanList",
                        (ArrayList<? extends Parcelable>) m_wLBeanList);
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
