package com.example.together_park;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.naver.maps.geometry.LatLng;
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
    //private NaverMap naverMap;

    private Marker marker = new Marker();//학교
    private Marker marker1 = new Marker();//천안역 마크
    private Marker marker2 = new Marker();//아산역
    private Marker marker3 = new Marker();//트라팰리스 탕정면사무소
    private Marker marker4 = new Marker();//천안터미널 버스정류장으로

    private RadioGroup RadioGroup_departure;
    private RadioButton RadioButton_departuresunmoon;
    private RadioButton RadioButton_departureasan;
    private RadioButton RadioButton_departurecheonan;
    private RadioButton RadioButton_departureterminal;
    private RadioButton RadioButton_departuretral;
    private RadioGroup RadioGroup_arrival;
    private RadioButton RadioButton_arrivalsunmoon;
    private RadioButton RadioButton_arrivalasan;
    private RadioButton RadioButton_arrivalcheonan;
    private RadioButton RadioButton_arrivalterminal;
    private RadioButton RadioButton_arrivaltra;
    private RadioGroup RadioGroup_person;
    private RadioButton RadioButton_one;
    private RadioButton RadioButton_two;
    private RadioButton RadioButton_three;

    private TextInputEditText TextInputEditText; //제목
    private String Title; //제목 입력하면 여기에
    private String departure; // 출발지 필드값
    private String arrival; //도착지 필드값
    String person; //제한인원 필드값

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

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

        RadioGroup_person = findViewById(R.id.RadioGroup_person);
        RadioButton_one = findViewById(R.id.RadioButton_one);
        RadioButton_two = findViewById(R.id.RadioButton_two);
        RadioButton_three = findViewById(R.id.RadioButton_three);

        RadioGroup_departure.setOnCheckedChangeListener(dRadioCheck);
        RadioGroup_arrival.setOnCheckedChangeListener(aRadioCheck);
        RadioGroup_person.setOnCheckedChangeListener(pRadioCheck);

        TextInputEditText = findViewById(R.id.TextInputEditText);

        mapView = findViewById(R.id.map_view);
        mapView.getMapAsync(this);

        Button Button_save = findViewById(R.id.Button_save);

        //저장버튼 눌렀을때 제목, 도착지 목적지, 제한인원버튼값을 넘겨줘야함.
        Button_save.setOnClickListener(new View.OnClickListener() { //저장 버튼 눌렀을 때 할 동작
            @Override
            public void onClick(View v) {
                Title = TextInputEditText.getText().toString(); //버튼을 눌렀을때 데이터가 들어간채로 넘어갈수잇게
                Intent intent = new Intent(PostingActivity.this, Posting_masterActivity.class);

                intent.putExtra("Title", Title);
                intent.putExtra("arrival", arrival);
                intent.putExtra("departure", departure);
                intent.putExtra("person", person);

                startActivity(intent); //액티비티 이동

                Toast.makeText(getApplicationContext(), "게시 되었습니다.", Toast.LENGTH_SHORT).show(); //게시판으로 넘어갔을때 게시되었습니다. 토스트메세지 띄어줌
                finish();
            }
        });

    }

    RadioGroup.OnCheckedChangeListener aRadioCheck = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {

            if (i == R.id.RadioButton_arrivalsunmoon){
                marker.setIcon(MarkerIcons.BLACK);
                marker.setIconTintColor(Color.YELLOW);

                marker1.setIconTintColor(Color.GREEN);
                marker2.setIconTintColor(Color.GREEN);
                marker3.setIconTintColor(Color.GREEN);
                marker4.setIconTintColor(Color.GREEN);

                arrival = ((RadioButton)findViewById(R.id.RadioButton_arrivalsunmoon)).getText().toString();
            } else if (i == R.id.RadioButton_arrivalasan) {
                marker2.setIcon(MarkerIcons.BLACK);
                marker2.setIconTintColor(Color.YELLOW);

                marker1.setIconTintColor(Color.GREEN);
                marker.setIconTintColor(Color.GREEN);
                marker3.setIconTintColor(Color.GREEN);
                marker4.setIconTintColor(Color.GREEN);

                arrival = ((RadioButton)findViewById(R.id.RadioButton_arrivalasan)).getText().toString();
            } else if (i == R.id.RadioButton_arrivalcheonan) {
                marker1.setIcon(MarkerIcons.BLACK);
                marker1.setIconTintColor(Color.YELLOW);

                marker.setIconTintColor(Color.GREEN);
                marker2.setIconTintColor(Color.GREEN);
                marker3.setIconTintColor(Color.GREEN);
                marker4.setIconTintColor(Color.GREEN);

                arrival = ((RadioButton)findViewById(R.id.RadioButton_arrivalcheonan)).getText().toString();
            } else if (i == R.id.RadioButton_arrivalterminal) {
                marker4.setIcon(MarkerIcons.BLACK);
                marker4.setIconTintColor(Color.YELLOW);

                marker1.setIconTintColor(Color.GREEN);
                marker2.setIconTintColor(Color.GREEN);
                marker3.setIconTintColor(Color.GREEN);
                marker.setIconTintColor(Color.GREEN);

                arrival = ((RadioButton)findViewById(R.id.RadioButton_arrivalterminal)).getText().toString();
            } else if (i == R.id.RadioButton_arrivaltra) {
                marker3.setIcon(MarkerIcons.BLACK);
                marker3.setIconTintColor(Color.YELLOW);

                marker1.setIconTintColor(Color.GREEN);
                marker2.setIconTintColor(Color.GREEN);
                marker.setIconTintColor(Color.GREEN);
                marker4.setIconTintColor(Color.GREEN);

                arrival = ((RadioButton)findViewById(R.id.RadioButton_arrivaltra)).getText().toString();
            }
        }
    };

    RadioGroup.OnCheckedChangeListener dRadioCheck;{
        dRadioCheck = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {//버튼 클릭에 따라 출발지 마커 색 변경
                if (checkedId == R.id.RadioButton_departuresunmoon) {
                    marker.setIcon(MarkerIcons.BLACK);
                    marker.setIconTintColor(Color.RED);

                    marker1.setIconTintColor(Color.GREEN);
                    marker2.setIconTintColor(Color.GREEN);
                    marker3.setIconTintColor(Color.GREEN);
                    marker4.setIconTintColor(Color.GREEN);

                    departure = ((RadioButton) findViewById(R.id.RadioButton_departuresunmoon)).getText().toString();
                } else if (checkedId == R.id.RadioButton_departuretasan) {
                    marker2.setIcon(MarkerIcons.BLACK);
                    marker2.setIconTintColor(Color.RED);

                    marker1.setIconTintColor(Color.GREEN);
                    marker.setIconTintColor(Color.GREEN);
                    marker3.setIconTintColor(Color.GREEN);
                    marker4.setIconTintColor(Color.GREEN);

                    departure = ((RadioButton) findViewById(R.id.RadioButton_departuretasan)).getText().toString();
                } else if (checkedId == R.id.RadioButton_departurecheonan) {
                    marker1.setIcon(MarkerIcons.BLACK);
                    marker1.setIconTintColor(Color.RED);

                    marker.setIconTintColor(Color.GREEN);
                    marker2.setIconTintColor(Color.GREEN);
                    marker3.setIconTintColor(Color.GREEN);
                    marker4.setIconTintColor(Color.GREEN);

                    departure = ((RadioButton) findViewById(R.id.RadioButton_departurecheonan)).getText().toString();
                } else if (checkedId == R.id.RadioButton_departureterminal) {
                    marker4.setIcon(MarkerIcons.BLACK);
                    marker4.setIconTintColor(Color.RED);

                    marker1.setIconTintColor(Color.GREEN);
                    marker2.setIconTintColor(Color.GREEN);
                    marker3.setIconTintColor(Color.GREEN);
                    marker.setIconTintColor(Color.GREEN);

                    departure = ((RadioButton) findViewById(R.id.RadioButton_departureterminal)).getText().toString();
                } else if (checkedId == R.id.RadioButton_departuretra) {
                    marker3.setIcon(MarkerIcons.BLACK);
                    marker3.setIconTintColor(Color.RED);

                    marker1.setIconTintColor(Color.GREEN);
                    marker2.setIconTintColor(Color.GREEN);
                    marker.setIconTintColor(Color.GREEN);
                    marker4.setIconTintColor(Color.GREEN);

                    departure = ((RadioButton) findViewById(R.id.RadioButton_departuretra)).getText().toString();
                }
            }
        };
    }

    RadioGroup.OnCheckedChangeListener pRadioCheck = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.RadioButton_one) {
                person = ((RadioButton) findViewById(R.id.RadioButton_one)).getText().toString();
            } else if (i == R.id.RadioButton_two) {
                person = ((RadioButton) findViewById(R.id.RadioButton_two)).getText().toString();
            } else if (i == R.id.RadioButton_three) {
                person = ((RadioButton) findViewById(R.id.RadioButton_three)).getText().toString();
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

        naverMap.setLightness(0.1f);//지도 밝기
    }
}
