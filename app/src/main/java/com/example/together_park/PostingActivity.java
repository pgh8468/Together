package com.example.together_park;

import android.app.Activity;
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
import com.naver.maps.map.overlay.Marker;

public class PostingActivity extends Activity implements OnMapReadyCallback{

    private MapView mapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        mapView = findViewById(R.id.map_view);
        mapView.getMapAsync(this);

        Button Button_save = findViewById(R.id.Button_save);
        Button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostingActivity.this,MainActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "게시 되었습니다.", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Marker marker = new Marker();
        marker.setPosition(new LatLng(36.799218, 127.074920));
        marker.setMap(naverMap);
    }

}
