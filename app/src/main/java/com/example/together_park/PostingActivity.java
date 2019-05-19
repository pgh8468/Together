package com.example.together_park;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.widget.ZoomControlView;

public class PostingActivity extends Activity implements OnMapReadyCallback{

    public NaverMap naverMap;
    public UiSettings uiSettings = naverMap.getUiSettings();
    private ZoomControlView ZoomControlView_zoom;
    private MapView mapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        ZoomControlView_zoom =findViewById(R.id.ZoomControlView_zoom);
        mapView = findViewById(R.id.map_view);
        mapView.getMapAsync(this);

        Button Button_save = findViewById(R.id.Button_save);

        //저장버튼 눌렀을때 제목, 도착지 목적지, 제한인원버튼값을 넘겨줘야함.
        Button_save.setOnClickListener(new View.OnClickListener() { //저장 버튼 눌렀을 때 할 동작
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostingActivity.this,MainActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "게시 되었습니다.", Toast.LENGTH_SHORT).show(); //게시판으로 넘어갔을때 게시되었습니다. 토스트메세지 띄어줌
            }
        });
    }

//    네이버 지도에서 현위치 찾는 메소드
//    public LocationButtonView(Context context){
//        public NaverMap getMap
//    }


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) { //현위치 잡는 법을 못해서 마크가 제대로 떴는지 모르겠음. 선문대는 확인함.
        Marker marker = new Marker(); //선문대 마크
        Marker marker1 = new Marker();//천안역 마크
        Marker marker2 = new Marker();//아산역
        Marker marker3 = new Marker();//트라팰리스 탕정면사무소
        Marker marker4 = new Marker();//천안터미널 버스정류장으로
        marker.setPosition(new LatLng(36.799218, 127.074920));
        marker1.setPosition(new LatLng(36.809299, 127.146593));
        marker2.setPosition(new LatLng(36.792171, 127.104496));
        marker3.setPosition(new LatLng(36.798487, 127.060894));
        marker4.setPosition(new LatLng(36.820818, 127.156362));
        marker.setMap(naverMap);
    }

    public ZoomControlView(@NonNull Context context){
     return ZoomControlView_zoom;
    }
}
