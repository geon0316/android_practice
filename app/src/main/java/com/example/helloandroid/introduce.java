package com.example.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class introduce extends Activity {

    // 텍스트 키워드 정보
    TextView chicken;
    TextView cpp;
    TextView entj;
    TextView ansan;
    TextView tonkatsu;
    TextView car;
    TextView birth;
    TextView naviworks;

    TextView bgh;


    Button backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce);

        // 배건형 텍스트
        bgh = (TextView)findViewById(R.id.bghh);
        bgh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bghIntent = new Intent(getApplicationContext(), chicken.class);
                startActivity(bghIntent);
            }
        });

        // 키워드 정보 버튼 셋
        chicken = (TextView) findViewById(R.id.chicken);
        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chickenIntent = new Intent(getApplicationContext(), chicken.class);
                startActivity(chickenIntent);
            }
        });
        cpp = (TextView) findViewById(R.id.cpp);
        cpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cppIntent = new Intent(getApplicationContext(), cpp.class);
                startActivity(cppIntent);
            }
        });

        entj = (TextView) findViewById(R.id.entj);
        entj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent entjIntent = new Intent(getApplicationContext(), entj.class);
                startActivity(entjIntent);
            }
        });
        ansan = (TextView) findViewById(R.id.ansan);
        ansan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ansanIntent = new Intent(getApplicationContext(), ansan.class);
                startActivity(ansanIntent);
            }
        });

        tonkatsu = (TextView) findViewById(R.id.tonkatsu);
        tonkatsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tonkatsuIntent = new Intent(getApplicationContext(), tonkatsu.class);
                startActivity(tonkatsuIntent);
            }
        });

        car = (TextView) findViewById(R.id.car);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tonkatsuIntent = new Intent(getApplicationContext(), tonkatsu.class);
                startActivity(tonkatsuIntent);
            }
        });
        birth = (TextView) findViewById(R.id.birth);
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent birthIntent = new Intent(getApplicationContext(), birth.class);
                startActivity(birthIntent);
            }
        });

        naviworks = (TextView) findViewById(R.id.naviworks);
        naviworks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent naviworksIntent = new Intent(getApplicationContext(), naviworks.class);
                startActivity(naviworksIntent);
            }
        });
        
        // 뒤로가기 버튼 할당
        backBtn = (Button) findViewById(R.id.goto_home_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 뒤로가기 버튼 클릭시 메인창으로 이동
                // Intent MainIntent = new Intent(getApplicationContext(), main_layout.class);
                // startActivity(MainIntent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
