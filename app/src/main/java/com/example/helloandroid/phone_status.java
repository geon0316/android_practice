package com.example.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class phone_status extends Activity {

    // 홈 화면 인텐트
    Intent HomeIntent;

    // 뒤로가기 버튼
    Button gotoHomeBtn;

    // 화면에 보여질 텍스트 정보

    // 안드로이드 버전 정보
    TextView androidVersion;
    // 와이파이 정보
    TextView wifiInfo;
    // LTE 정보
    TextView lteInfo;
    // 내부 저장소 정보
    TextView storageInfo;
    // 설치 앱 정보
    TextView appDataInfo;

    // 유틸 함수 내장 인스턴스
    utilFunc util;

    // 유틸 함수를 통해 리턴받는 정보
    String androidVerstr;
    boolean wifiInfoflag;
    boolean lteInfoflag;
    double[] storageInfodbl;
    int installappint;
    int runappint;

    @Override
    protected void onResume() {
        super.onResume();

        // 휴대폰 정보 얻기
        getPhoneInfo();
        // 휴대폰 정보 화면에 전시
        setPhoneInfoView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_status);

        util = new utilFunc();

        // 홈 인텐트
        HomeIntent = new Intent(getApplicationContext(), main_layout.class);

        // 뒤로가기 버튼
        gotoHomeBtn = (Button)findViewById(R.id.goto_home_btn);
        gotoHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 뒤로가기
                startActivity(HomeIntent);
            }
        });

        // 텍스트뷰 정보 할당
        androidVersion = (TextView) findViewById(R.id.androidVersion);
        wifiInfo = (TextView) findViewById(R.id.wifiInfo);
        lteInfo = (TextView) findViewById(R.id.lteInfo);
        storageInfo = (TextView) findViewById(R.id.storageInfo);
        appDataInfo = (TextView) findViewById(R.id.appDataInfo);

        // 휴대폰 정보 얻어오기
        getPhoneInfo();

        // 텍스트뷰에 정보 업데이트
        setPhoneInfoView();
    }
    
    public void getPhoneInfo()
    {
        // 휴대폰 정보 불러오기

        // 안드로이드 버전 할당
        androidVerstr = util.getAndroidVersion();

        // 와이파이 정보 할당
        wifiInfoflag = util.isWifiEnabled(this);

        // LTE 정보 할당
        lteInfoflag = util.isMobileDataEnabled(this);

        // 내부 저장소 정보 할당 (총사용량/사용중)
        storageInfodbl = util.getStorageInfo();

        // 설치 앱 정보 할당
        // 설치된 앱 개수
        installappint = util.getInstalledAppCount(this);

        // 동작중인 앱 개수
        runappint = util.getRecentAppCount(this);
    }
    
    public void setPhoneInfoView()
    {
        // 텍스트뷰 정보 업데이트
        
        // 안드로이드 버전
        androidVersion.setText(androidVerstr);
        
        // 와이파이 정보
        String wifi;
        if (wifiInfoflag == true)
        {
            wifi = "ON";
        }
        else
        {
            wifi = "OFF";
        }
        wifiInfo.setText(wifi);
        
        // LTE 정보
        String lte;
        if (lteInfoflag == true)
        {
            lte = "ON";
        }
        else
        {
            lte = "OFF";
        }
        lteInfo.setText(lte);

        // 저장 공간 정보
        double maxStorage = storageInfodbl[0];
        double leftStorage = storageInfodbl[1];
        String storageinfo = String.format("전체: %.2f 남은용량: %.2f", maxStorage, leftStorage);
        storageInfo.setText(storageinfo);

        // 설치한 앱 수 정보
        String appinfo = String.format("설치 앱 수:%d 동작 앱 수: %d", installappint, runappint);
        appDataInfo.setText(appinfo);
    }
}
