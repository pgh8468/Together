package com.example.together_park;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.MarkerIcons;
import com.naver.maps.map.widget.ZoomControlView;

public class PostingActivity extends Activity implements OnMapReadyCallback {

    private MapView mapView;
    NaverMap naverMap;
    Marker marker = new Marker();//학교
    Marker marker1 = new Marker();//천안역 마크
    Marker marker2 = new Marker();//아산역
    Marker marker3 = new Marker();//트라팰리스 탕정면사무소
    Marker marker4 = new Marker();//천안터미널 버스정류장으로

    CameraUpdate cameraUpdate;

    RadioGroup RadioGroup_departure;
    RadioButton RadioButton_departuresunmoon;
    RadioButton RadioButton_departureasan;
    RadioButton RadioButton_departurecheonan;
    RadioButton RadioButton_departureterminal;
    RadioButton RadioButton_departuretral;
    RadioGroup RadioGroup_arrival;
    RadioButton RadioButton_arrivalsunmoon;
    RadioButton RadioButton_arrivalasan;
    RadioButton RadioButton_arrivalcheonan;
    RadioButton RadioButton_arrivalterminal;
    RadioButton RadioButton_arrivaltra;

    View Title; //제목 필드값
    String departure; // 출발지 필드값
    String arrival; //도착지 필드값
    int person; //제한인원 필드값

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        Title = findViewById(R.id.TextInputEditText);
        RadioGroup_departure = findViewById(R.id.RadioGroup_departure);
        RadioButton_departuresunmoon = findViewById(R.id.RadioButton_departuresunmoon);
        RadioButton_departureasan = findViewById(R.id.RadioButton_departuretasan);
        RadioButton_departurecheonan = findViewById(R.id.RadioButton_departurecheonan);
        RadioButton_departureterminal = findViewById(R.id.RadioButton_departureterminal);
        RadioButton_departuretral = findViewById(R.id.RadioButton_departuretra);

        RadioGroup_arrival = findViewById(R.id.RadioGroup_arrival);
        RadioButton_arrivalsunmoon = findViewById(R.id.RadioButton_arrivalsunmoon);
        RadioButton_arrivalasan = findViewById(R.id.RadioButton_arrivalasan);
        RadioButton_arrivalcheonan = findViewById(R.id.RadioButton_arrivalcheonan);
        RadioButton_arrivalterminal = findViewById(R.id.RadioButton_arrivalterminal);
        RadioButton_arrivaltra = findViewById(R.id.RadioButton_arrivaltra);

        RadioGroup_departure.setOnCheckedChangeListener(dRadioCheck);
        RadioGroup_arrival.setOnCheckedChangeListener(aRadioCheck);

        mapView = findViewById(R.id.map_view);
        //ZoomControlView_zoom =findViewById(R.id.ZoomControlView_zoom);
        mapView.getMapAsync(this);

        mapView.getMapAsync(naverMap -> {
            ZoomControlView ZoomControlView_zoom = findViewById(R.id.ZoomControlView_zoom);
            ZoomControlView_zoom.setMap(naverMap);
        });

        Button Button_save = findViewById(R.id.Button_save);
        //RadioButton selected

        //저장버튼 눌렀을때 제목, 도착지 목적지, 제한인원버튼값을 넘겨줘야함.
        Button_save.setOnClickListener(new View.OnClickListener() { //저장 버튼 눌렀을 때 할 동작
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostingActivity.this, Posting_masterActivity.class);
                intent.putExtra("departure", "dRadioCheck"); // 출발지 값 넘기기
                intent.putExtra("arrive", "aRadioCheck"); // 도착지 값 넘기기
                intent.putExtra("제목", "Title");
                //intent.putExtra()


                startActivity(intent);

                Toast.makeText(getApplicationContext(), "게시 되었습니다.", Toast.LENGTH_SHORT).show(); //게시판으로 넘어갔을때 게시되었습니다. 토스트메세지 띄어줌
            }
        });

    }

    RadioGroup.OnCheckedChangeListener aRadioCheck = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
          if (i == R.id.RadioButton_arrivalsunmoon){
              marker.setIcon(MarkerIcons.BLACK);
              marker.setIconTintColor(Color.YELLOW);

              if (RadioButton_departuresunmoon.isChecked()){
                 marker.setIconTintColor(Color.RED);
              }

              marker1.setIconTintColor(Color.GREEN);
              marker2.setIconTintColor(Color.GREEN);
              marker3.setIconTintColor(Color.GREEN);
              marker4.setIconTintColor(Color.GREEN);
          } else if (i == R.id.RadioButton_arrivalasan) {
              marker2.setIcon(MarkerIcons.BLACK);
              marker2.setIconTintColor(Color.YELLOW);

              marker1.setIconTintColor(Color.GREEN);
              marker.setIconTintColor(Color.GREEN);
              marker3.setIconTintColor(Color.GREEN);
              marker4.setIconTintColor(Color.GREEN);
          } else if (i == R.id.RadioButton_arrivalcheonan) {
              marker1.setIcon(MarkerIcons.BLACK);
              marker1.setIconTintColor(Color.YELLOW);

              marker.setIconTintColor(Color.GREEN);
              marker2.setIconTintColor(Color.GREEN);
              marker3.setIconTintColor(Color.GREEN);
              marker4.setIconTintColor(Color.GREEN);
          } else if (i == R.id.RadioButton_arrivalterminal) {
              marker4.setIcon(MarkerIcons.BLACK);
              marker4.setIconTintColor(Color.YELLOW);

              marker1.setIconTintColor(Color.GREEN);
              marker2.setIconTintColor(Color.GREEN);
              marker3.setIconTintColor(Color.GREEN);
              marker.setIconTintColor(Color.GREEN);
          } else if (i == R.id.RadioButton_arrivaltra) {
              marker3.setIcon(MarkerIcons.BLACK);
              marker3.setIconTintColor(Color.YELLOW);

              marker1.setIconTintColor(Color.GREEN);
              marker2.setIconTintColor(Color.GREEN);
              marker.setIconTintColor(Color.GREEN);
              marker4.setIconTintColor(Color.GREEN);
          }
        }
    };

    RadioGroup.OnCheckedChangeListener dRadioCheck = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) { //버튼 클릭에 따라 출발지 마커 색 변경
            if (checkedId == R.id.RadioButton_departuresunmoon) {
                marker.setIcon(MarkerIcons.BLACK);
                marker.setIconTintColor(Color.RED);

                marker1.setIconTintColor(Color.GREEN);
                marker2.setIconTintColor(Color.GREEN);
                marker3.setIconTintColor(Color.GREEN);
                marker4.setIconTintColor(Color.GREEN);

            } else if (checkedId == R.id.RadioButton_departuretasan) {
                marker2.setIcon(MarkerIcons.BLACK);
                marker2.setIconTintColor(Color.RED);

                marker1.setIconTintColor(Color.GREEN);
                marker.setIconTintColor(Color.GREEN);
                marker3.setIconTintColor(Color.GREEN);
                marker4.setIconTintColor(Color.GREEN);
            } else if (checkedId == R.id.RadioButton_departurecheonan) {
                marker1.setIcon(MarkerIcons.BLACK);
                marker1.setIconTintColor(Color.RED);

                marker.setIconTintColor(Color.GREEN);
                marker2.setIconTintColor(Color.GREEN);
                marker3.setIconTintColor(Color.GREEN);
                marker4.setIconTintColor(Color.GREEN);
            } else if (checkedId == R.id.RadioButton_departureterminal) {
                marker4.setIcon(MarkerIcons.BLACK);
                marker4.setIconTintColor(Color.RED);

                marker1.setIconTintColor(Color.GREEN);
                marker2.setIconTintColor(Color.GREEN);
                marker3.setIconTintColor(Color.GREEN);
                marker.setIconTintColor(Color.GREEN);
            } else if (checkedId == R.id.RadioButton_departuretra) {
                marker3.setIcon(MarkerIcons.BLACK);
                marker3.setIconTintColor(Color.RED);

                marker1.setIconTintColor(Color.GREEN);
                marker2.setIconTintColor(Color.GREEN);
                marker.setIconTintColor(Color.GREEN);
                marker4.setIconTintColor(Color.GREEN);
            }
        }

    };

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
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

        cameraUpdate = CameraUpdate.scrollTo(new LatLng(36.799218, 127.074920))
                .animate(CameraAnimation.Easing);
        naverMap.moveCamera(cameraUpdate);

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
