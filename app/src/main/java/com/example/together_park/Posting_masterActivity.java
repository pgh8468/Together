package com.example.together_park;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.naver.maps.map.MapView;

public class Posting_masterActivity extends AppCompatActivity {

    private TextView TextView_Title;
    private TextView TextView_arrival;
    private TextView TextView_departure;
    private TextView TextView_person;
    Button Button_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting_master);

        TextView_Title = findViewById(R.id.TextView_Title);
        TextView_arrival = findViewById(R.id.TextView_arrival);
        TextView_departure = findViewById(R.id.TextView_departure);
        TextView_person = findViewById(R.id.TextView_person);
        Button_call = findViewById(R.id.Button_call);

        Intent intent = getIntent();
        Intent dialintent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-5573-8468"));

        String Title = intent.getStringExtra("Title");
        String arrival = intent.getStringExtra("arrival");
        String departure = intent.getStringExtra("departure");
        String person = intent.getStringExtra("person");

        TextView_departure.setText(departure);
        TextView_Title.setText(Title);
        TextView_arrival.setText(arrival);
        TextView_person.setText(person);

        //콜벤 전화버튼 누르면 전화화면으로 전환
        Button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialintent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-5573-8468"));
                startActivity(dialintent);
            }
        });

        //startActivity(dialintent);
    }
}
