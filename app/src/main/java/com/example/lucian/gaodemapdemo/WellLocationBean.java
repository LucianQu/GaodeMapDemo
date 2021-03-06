package com.example.lucian.gaodemapdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by QLS on 3/23/2017.
 */

public class WellLocationBean implements Parcelable {
    private static final String TAG = "WellLocationBean" ;

    private String m_wellNo ;//水井编码
    private String m_wellName  ;//水井名称
    private String m_wellUser  ;//用户
    private double m_wellX ; //
    private double m_wellY ; //

    public String getM_wellNo() {
        return m_wellNo;
    }

    public void setM_wellNo(String m_wellNo) {
        this.m_wellNo = m_wellNo;
    }

    public String getM_wellName() {
        return m_wellName;
    }

    public void setM_wellName(String m_wellName) {
        this.m_wellName = m_wellName;
    }

    public String getM_wellUser() {
        return m_wellUser;
    }

    public void setM_wellUser(String m_wellUser) {
        this.m_wellUser = m_wellUser;
    }

    public double getM_wellX() {
        return m_wellX;
    }

    public void setM_wellX(double m_wellX) {
        this.m_wellX = m_wellX;
    }

    public double getM_wellY() {
        return m_wellY;
    }

    public void setM_wellY(double m_wellY) {
        this.m_wellY = m_wellY;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(m_wellNo);
        parcel.writeString(m_wellName);
        parcel.writeString(m_wellUser);
        parcel.writeDouble(m_wellX);
        parcel.writeDouble(m_wellY);
    }

    public static final Parcelable.Creator<WellLocationBean> CREATOR =
            new Creator<WellLocationBean>() {
                @Override
                public WellLocationBean createFromParcel(Parcel parcel) {
                    WellLocationBean mWellLocationBean = new WellLocationBean() ;
                    mWellLocationBean.m_wellNo = parcel.readString();
                    mWellLocationBean.m_wellName = parcel.readString() ;
                    mWellLocationBean.m_wellUser = parcel.readString() ;
                    mWellLocationBean.m_wellX = parcel.readDouble() ;
                    mWellLocationBean.m_wellY = parcel.readDouble() ;
                    return mWellLocationBean;
                }

                @Override
                public WellLocationBean[] newArray(int i) {
                    return new WellLocationBean[i];
                }
            };

}
