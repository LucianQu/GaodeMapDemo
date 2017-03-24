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
        AMap.OnInfoWindowClickListener,AMap.InfoWindowAdapter,AMap.OnMapClickListener {

    private MapView m_mapView;
    private AMap m_aMap;
    private View m_markerInfoWindow = null;

    public  WellLocationBean m_wLBean;

    private ArrayList<MarkerOptions> m_mOptionsList = new ArrayList<>();
    public  List<WellLocationBean> m_wLBeanList = new ArrayList<>();
    private ArrayList<Marker> m_markerList;

    private String m_wellNo = "";
    private String m_wellName = "";
    private String m_wellUser = "";

    /**
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        m_wLBeanList = bundle.getParcelableArrayList("wellLocatBeanList");

        m_mapView = (MapView) findViewById(R.id.map);//初始化地图控件
        m_mapView.onCreate(savedInstanceState);//必须要写
        m_aMap = m_mapView.getMap();

        m_aMap.getUiSettings().setMyLocationButtonEnabled(false);
        m_aMap.setMyLocationEnabled(false);

        removeMarkerList();
        addMarkerList(WellLocatBeanList2MarkerOptionsList(m_wLBeanList, m_mOptionsList), m_wLBeanList.size() - 1);

        m_aMap.setOnMarkerClickListener(markerClickListener);
        m_aMap.setInfoWindowAdapter(infoWindowAdapter);
        m_aMap.setOnInfoWindowClickListener(infoWindowListener);
    }

    public ArrayList<MarkerOptions> WellLocatBeanList2MarkerOptionsList(List<WellLocationBean> wellBeanList,
                                                                        ArrayList<MarkerOptions> markerOptionsList) {
        for (int i = 0; i < wellBeanList.size(); i++) {
            m_wLBean = wellBeanList.get(i);
            WellInfoWindowSet(m_wLBean.getM_wellNo(), m_wLBean.getM_wellName(),
                    m_wLBean.getM_wellUser());

            markerOptionsList.add(CustomMarker(m_wLBean.getM_wellNo(), m_wLBean.getM_wellName(),
                    R.drawable.well_64_5, m_wLBean.getM_wellX(),m_wLBean.getM_wellY()));
        }
        return markerOptionsList;
    }

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
        //markerOptions.setInfoWindowOffset(30,0) ;
        return markerOptions;
    }

    public void addMarkerList(ArrayList<MarkerOptions> markerOptionsList , int defultMarkPosition) {

        m_markerList = m_aMap.addMarkers(markerOptionsList,true) ;

        setDefultMarkDisplayInfo(defultMarkPosition) ;
    }

    public void setDefultMarkDisplayInfo (int position) {
        if(position < 0)
            return;
        WellInfoWindowSet(m_markerList.get(position).getTitle(),m_markerList.get(position).getSnippet(),
                m_wLBeanList.get(position).getM_wellUser());
        m_markerList.get(position).showInfoWindow();
    }

    public void removeMarkerList() {
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
            Render(m_markerInfoWindow) ;
            return m_markerInfoWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    };
    /**
     *自定义窗口内容
     */
    public void Render(View view) {
        //如果想修改自定义Infow中内容,请通过view找到它,并修改
        TextView wellNo = (TextView) view.findViewById(R.id.markerWellNo) ;
        TextView wellName = (TextView) view.findViewById(R.id.markerWellName) ;
        TextView wellUser = (TextView) view.findViewById(R.id.markerWellUser) ;

        wellNo.setText(m_wellNo);
        wellName.setText(m_wellName);
        wellUser.setText(m_wellUser);
    }

    /**
     *机井窗口信息设置
     */
    public  void WellInfoWindowSet(String wellNo, String wellName, String wellUser) {
        m_wellNo = wellNo ;
        m_wellName = wellName ;
        m_wellUser = wellUser ;
    }

    AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener(){
        @Override
        public boolean onMarkerClick(Marker marker) {
            WellInfoWindowSet(marker.getTitle(),marker.getSnippet(),getWellUser(marker)) ;
            return false ;
        }
    };

    private String getWellUser(Marker marker){
        boolean isExist = false ;
        int markerPosition = 0;
        String userName ;
        for (int i = 0; i < m_markerList.size(); i++) {
            if(marker.equals(m_markerList.get(i))) {
                isExist = true ;
                markerPosition = i ;
            }
        }
        if(isExist) {
            userName = m_wLBeanList.get(markerPosition).getM_wellUser() ;
        }else {
            userName = "" ;
        }
        return userName ;
    }

    AMap.OnInfoWindowClickListener infoWindowListener = new AMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
        }
    };


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
