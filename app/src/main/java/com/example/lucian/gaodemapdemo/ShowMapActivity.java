package com.example.lucian.gaodemapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ShowMapActivity extends AppCompatActivity implements AMap.OnMarkerClickListener,
        AMap.OnInfoWindowClickListener,AMap.InfoWindowAdapter,AMap.OnMapClickListener{

    private MapView m_mapView ;
    private AMap m_aMap ;
    private MarkerOptions m_markerOptions ;
    private View m_markerInfoWindow = null;

    private List<MarkerOptions> m_markerOptionsList = new ArrayList<>();
    public static List<WellLocationBean> m_wellLocatBeanList = new ArrayList<>();
    public WellLocationBean m_wellLocatBean ;

    private Marker m_marker = null ;
    private double m_markerX;
    private double m_markerY;

    private String m_wellName = "" ;
    private String m_wellAttr = "" ;
    private String m_wellAdmin = "" ;
    private String m_wellX = "" ;
    private String m_wellY = "" ;

    /**
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_show_map) ;
        Intent intent = getIntent() ;
        Bundle bundle = intent.getExtras();
      /*  String[] strArr = bundle.getStringArray("wellLocaInfo") ;
        m_markerX = bundle.getDouble("wellLocaX") ;
        m_markerY = bundle.getDouble("wellLocaY") ;*/

        m_wellLocatBeanList = bundle.getParcelableArrayList("wellLocatBeanList");

        m_mapView = (MapView) findViewById(R.id.map) ;//初始化地图控件
        m_mapView.onCreate(savedInstanceState) ;//必须要写
        m_aMap = m_mapView.getMap() ;

        m_aMap.moveCamera(CameraUpdateFactory.newLatLngZoom
                (new LatLng(m_wellLocatBeanList.get(0).getM_markerX(),
                        m_wellLocatBeanList.get(0).getM_markerY()),0));//初始化调用

        m_aMap.getUiSettings().setMyLocationButtonEnabled(false);
        m_aMap.setMyLocationEnabled(false);
        RemoveMarkerList();
        WellLocatBeanList2MarkerOptionsList(m_wellLocatBeanList,m_markerOptionsList);
        AddMarkerList(m_markerOptionsList) ;


        m_aMap.setOnMarkerClickListener(markerClickListener);
        m_aMap.setInfoWindowAdapter(infoWindowAdapter);
        m_aMap.setOnInfoWindowClickListener(infoWindowListener);

    }

    public void WellLocatBeanList2MarkerOptionsList(List<WellLocationBean> wellBeanList,
                                                    List<MarkerOptions> markerOptionsList) {
        for (int i = 0; i < wellBeanList.size(); i++) {
            m_wellLocatBean = wellBeanList.get(i) ;
            WellInfoWindowSet(m_wellLocatBean.getM_wellName(),m_wellLocatBean.getM_wellAttr(),
                    m_wellLocatBean.getM_wellAdmin(),m_wellLocatBean.getM_wellX(),
                    m_wellLocatBean.getM_wellY());

            markerOptionsList.add(CustomMarker(m_wellLocatBean.getM_wellName(),m_wellLocatBean.getM_wellAttr(),
                    R.drawable.well_64_5,m_wellLocatBean.getM_markerX(),
                    m_wellLocatBean.getM_markerY()));
        }
    }


    public void AddMarkerList(List<MarkerOptions> markerOptionsList) {

        for (int i = 0; i < m_wellLocatBeanList.size(); i++) {
            MarkerOptions marker = markerOptionsList.get(i) ;
            m_marker = m_aMap.addMarker(marker) ;
        }

    }

    public void RemoveMarkerList() {
        List<Marker> saveMarkerList = m_aMap.getMapScreenMarkers() ;
        if(saveMarkerList == null || saveMarkerList.size() <= 0)
            return;
        for (Marker marker : saveMarkerList) {
            marker.remove();
        }
    }


    /**
     *自定义Marker窗口
     */
    AMap.InfoWindowAdapter infoWindowAdapter = new AMap.InfoWindowAdapter() {
        @Override
        public View getInfoWindow(Marker marker) {
            m_markerInfoWindow = ShowMapActivity.this.getLayoutInflater().
                    inflate(R.layout.custom_info,null) ;//自定义视图layout
            Render(marker, m_markerInfoWindow) ;
            return m_markerInfoWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    };
    /**
     *机井窗口信息设置
     */
    public  void WellInfoWindowSet(String wellName, String wellAttr, String wellAdmin,
                                   String wellX, String wellY) {
        m_wellName = wellName ;
        m_wellAttr = wellAttr ;
        m_wellAdmin = wellAdmin ;
        m_wellX = wellX ;
        m_wellY = wellY ;
    }
    /**
     *自定义窗口内容
     */
    public void Render(Marker marker, View view) {
        //如果想修改自定义Infow中内容,请通过view找到它,并修改
        TextView wellName = (TextView) view.findViewById(R.id.markerWellName) ;
        TextView wellAttr = (TextView) view.findViewById(R.id.markerWellAttr) ;
        TextView wellAdmin = (TextView) view.findViewById(R.id.markerWellAdmin) ;
        TextView wellX = (TextView) view.findViewById(R.id.markerWellLocalX) ;
        TextView wellY = (TextView) view.findViewById(R.id.markerWellLocalY) ;

        wellName.setText(m_wellName);
        wellAttr.setText(m_wellAttr);
        wellAdmin.setText(m_wellAdmin);
        wellX.setText(m_wellX);
        wellY.setText(m_wellY);
    }

    AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener(){
        @Override
        public boolean onMarkerClick(Marker marker) {
            return false ;
        }
    };

    AMap.OnInfoWindowClickListener infoWindowListener = new AMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
        }
    };

    public MarkerOptions CustomMarker(String title, String snippet, int icon, double v, double v1) {
        MarkerOptions markerOptions = new MarkerOptions() ;
        markerOptions.position(new LatLng(v,v1)) ;//在地图上标记位置的经纬度值
        markerOptions.title(title) ;//点标记的标题
        markerOptions.snippet(snippet) ;//点标记的内容
        markerOptions.perspective(true) ;
        markerOptions.visible(true);//点标记是否可见
        markerOptions.draggable(true) ;//点标记是否可拖拽
        markerOptions.setFlat(true) ;//设置Marker贴地显示,可以双指下拉地图查看效果
        markerOptions.icon(BitmapDescriptorFactory.fromResource(icon)) ;

       return markerOptions;
    }
    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        m_mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        m_mapView.onPause();
    }
    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        m_mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        m_mapView.onDestroy();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {}

    @Override
    public View getInfoWindow(Marker marker) { return null;}

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onMapClick(LatLng latLng) {}

    @Override
    public boolean onMarkerClick(Marker marker) { return false;}
}
