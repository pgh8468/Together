package com.example.together_park;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.widget.ZoomControlView;

public class PostingActivity extends Activity implements OnMapReadyCallback{

    private MapView mapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        mapView = findViewById(R.id.map_view);
        //ZoomControlView_zoom =findViewById(R.id.ZoomControlView_zoom);
        mapView.getMapAsync(this);

        mapView.getMapAsync(naverMap -> {
            ZoomControlView ZoomControlView_zoom = findViewById(R.id.ZoomControlView_zoom);
            ZoomControlView_zoom.setMap(naverMap);
        });

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

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Marker marker = new Marker(); //선문대 마크
        Marker marker1 = new Marker();//천안역 마크
        Marker marker2 = new Marker();//아산역
        Marker marker3 = new Marker();//트라팰리스 탕정면사무소
        Marker marker4 = new Marker();//천안터미널 버스정류장으로
        marker.setPosition(new LatLng(36.799218, 127.074920));
        marker.setOnClickListener(overlay -> { //마크 클릭시 출발지 목적지 선택 가능하도록.
            Toast.makeText(getApplicationContext(), "선문대학교클릭",Toast.LENGTH_SHORT).show();
            return true;
        });
        marker1.setPosition(new LatLng(36.809299, 127.146593));
        marker1.setOnClickListener(overlay -> { //마크 클릭시 출발지 목적지 선택 가능하도록.
            Toast.makeText(getApplicationContext(), "천안역클릭",Toast.LENGTH_SHORT).show();
            return true;
        });
        marker2.setPosition(new LatLng(36.792171, 127.104496));
        marker2.setOnClickListener(overlay -> { //마크 클릭시 출발지 목적지 선택 가능하도록.
            Toast.makeText(getApplicationContext(), "아산역클릭",Toast.LENGTH_SHORT).show();
            return true;
        });
        marker3.setPosition(new LatLng(36.798487, 127.060894));
        marker3.setOnClickListener(overlay -> { //마크 클릭시 출발지 목적지 선택 가능하도록.
            Toast.makeText(getApplicationContext(), "트라팰리스클릭",Toast.LENGTH_SHORT).show();
            return true;
        });
        marker4.setPosition(new LatLng(36.820818, 127.156362));
        marker4.setOnClickListener(overlay -> { //마크 클릭시 출발지 목적지 선택 가능하도록.
            Toast.makeText(getApplicationContext(), "천안터미널클릭",Toast.LENGTH_SHORT).show();
            return true;
        });
        marker.setMap(naverMap);
        marker1.setMap(naverMap);
        marker2.setMap(naverMap);
        marker3.setMap(naverMap);
        marker4.setMap(naverMap);

        //줌 최대 최소 설정.
        CameraPosition cameraPosition = naverMap.getCameraPosition();
        naverMap.setCameraPosition(cameraPosition);
        naverMap.setMinZoom(5.0);
        naverMap.setMaxZoom(18.0);

        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(true);//현재위치 버튼
        uiSettings.setZoomControlEnabled(true);//줌 확대 축소 버튼

        naverMap.setNightModeEnabled(true);//밤되면 지도 바탕이 어두워지도록.
        naverMap.setLightness(0.1f);//지도 밝기
    }
}
