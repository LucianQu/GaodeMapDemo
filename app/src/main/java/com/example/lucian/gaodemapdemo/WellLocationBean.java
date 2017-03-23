package com.example.lucian.gaodemapdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by QLS on 3/23/2017.
 */

public class WellLocationBean implements Parcelable {
    private static final String TAG = "WellLocationBean" ;
    private String m_wellName ;//水井名称
    private String m_wellAttr  ;//水井管理处
    private String m_wellAdmin  ;//水井管理者
    private String m_wellX ;//水井X坐标名称
    private String m_wellY ;//水井Y坐标名称
    private double m_markerX ;//水井X坐标
    private double m_markerY ;//水井Y坐标

    public double getM_markerX() {
        return m_markerX;
    }

    public void setM_markerX(double m_markerX) {
        this.m_markerX = m_markerX;
    }

    public double getM_markerY() {
        return m_markerY;
    }

    public void setM_markerY(double m_markerY) {
        this.m_markerY = m_markerY;
    }

    public String getM_wellAdmin() {
        return m_wellAdmin;
    }

    public void setM_wellAdmin(String m_wellAdmin) {
        this.m_wellAdmin = m_wellAdmin;
    }

    public String getM_wellAttr() {
        return m_wellAttr;
    }

    public void setM_wellAttr(String m_wellAttr) {
        this.m_wellAttr = m_wellAttr;
    }

    public String getM_wellName() {
        return m_wellName;
    }

    public void setM_wellName(String m_wellName) {
        this.m_wellName = m_wellName;
    }

    public String getM_wellX() {
        return m_wellX;
    }

    public void setM_wellX(String m_wellX) {
        this.m_wellX = m_wellX;
    }

    public String getM_wellY() {
        return m_wellY;
    }

    public void setM_wellY(String m_wellY) {
        this.m_wellY = m_wellY;
    }

    @Override
    public String toString() {
        return "WellLocationBean{" +
                "m_markerX=" + m_markerX +
                ", m_wellName='" + m_wellName + '\'' +
                ", m_wellAttr='" + m_wellAttr + '\'' +
                ", m_wellAdmin='" + m_wellAdmin + '\'' +
                ", m_wellX='" + m_wellX + '\'' +
                ", m_wellY='" + m_wellY + '\'' +
                ", m_markerY=" + m_markerY +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(m_wellName);
        parcel.writeString(m_wellAdmin);
        parcel.writeString(m_wellAttr);
        parcel.writeString(m_wellX);
        parcel.writeString(m_wellY);
        parcel.writeDouble(m_markerX);
        parcel.writeDouble(m_markerY);
    }

    public static final Parcelable.Creator<WellLocationBean> CREATOR =
            new Creator<WellLocationBean>() {
                @Override
                public WellLocationBean createFromParcel(Parcel parcel) {
                    WellLocationBean mWellLocationBean = new WellLocationBean() ;
                    mWellLocationBean.m_wellName = parcel.readString();
                    mWellLocationBean.m_wellAdmin = parcel.readString() ;
                    mWellLocationBean.m_wellAttr = parcel.readString() ;
                    mWellLocationBean.m_wellX = parcel.readString() ;
                    mWellLocationBean.m_wellY = parcel.readString() ;
                    mWellLocationBean.m_markerX = parcel.readDouble() ;
                    mWellLocationBean.m_markerY = parcel.readDouble() ;
                    return mWellLocationBean;
                }

                @Override
                public WellLocationBean[] newArray(int i) {
                    return new WellLocationBean[i];
                }
            };

}
