package com.example.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class contact_share extends Activity {

    // 맨 위 유저 정보
    TextView userName;
    // 유저 휴대폰
    TextView userPhone;
    // 유저 이메일
    TextView userEmail;
    // 유저 회사
    TextView userCompany;

    Intent intent;

    Button share;

    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_share);

        // 유저 정보 할당
        userName = (TextView) findViewById(R.id.select_name);

        userPhone = (TextView) findViewById(R.id.userPhone);

        userEmail = (TextView) findViewById(R.id.userEmail);

        userCompany = (TextView) findViewById(R.id.userCompany);

        // 공유/취소 버튼 할당
        share = (Button)findViewById(R.id.share_go);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast processToast = Toast.makeText(getApplicationContext(), "공유 완료!", Toast.LENGTH_LONG);
                processToast.show();

                finish();
            }
        });

        cancel = (Button)findViewById(R.id.share_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다시 선택한 연락처 정보로 이동
                finish();
            }
        });

        // 인텐트 정보 가져오기
        intent = getIntent();

        // 인텐트로 넘겨 받은 정보를 통해 화면의 글자 구성
        userName.setText(intent.getStringExtra("userName"));

        userPhone.setText(intent.getStringExtra("userPhone"));

        userEmail.setText(intent.getStringExtra("userEmail"));

        userCompany.setText(intent.getStringExtra("userCompany"));

    }

    @Override
    protected void onResume(){
        super.onResume();

        // 넘겨 받은 유저 정보를 통해 팝업의 정보 구성
        // 인텐트 정보 가져오기
        intent = getIntent();

        // 인텐트로 넘겨 받은 정보를 통해 화면의 글자 구성
        userName.setText(intent.getStringExtra("userName"));

        userPhone.setText(intent.getStringExtra("userPhone"));

        userEmail.setText(intent.getStringExtra("userEmail"));

        userCompany.setText(intent.getStringExtra("userCompany"));
    }
}

