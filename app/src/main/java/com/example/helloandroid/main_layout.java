package com.example.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class main_layout extends Activity {

    TextView text_ContactList;  // 주소록 관리
    TextView text_Internet;  // 인터넷
    TextView text_Phone;  // 폰 상태
    TextView text_Introduce;  // 저자 소개

    // DB 매니저 인스턴스
    // private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // DB 매니저 인스턴스 생성
        // dbManager = new DBManager(this);

        // 텍스트 바인딩

        text_ContactList = (TextView) findViewById(R.id.address_Manage);
        text_Internet = (TextView) findViewById(R.id.internet);
        text_Phone = (TextView) findViewById(R.id.phone_State);
        text_Introduce = (TextView) findViewById(R.id.author);

        text_ContactList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 주소록 관리
                Intent ContactIntent = new Intent(getApplicationContext(), Contact_list.class);
                startActivity(ContactIntent);
            }
        });
        text_Internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 인터넷
                Intent InternetIntent = new Intent(getApplicationContext(), webView.class);
                startActivity(InternetIntent);
            }
        });
        text_Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 폰 상태
                Intent PhoneStatusIntent = new Intent(getApplicationContext(), phone_status.class);
                startActivity(PhoneStatusIntent);
            }
        });
        text_Introduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 저자 소개
                Intent IntroduceIntent = new Intent(getApplicationContext(), introduce.class);
                startActivity(IntroduceIntent);
            }
        });
    }
}
