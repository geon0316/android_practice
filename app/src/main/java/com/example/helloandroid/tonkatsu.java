package com.example.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tonkatsu extends Activity {

    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tontatsu);

        backBtn = (Button) findViewById(R.id.goto_introduce);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 저자 소개 탭으로 뒤로가기
                finish();
            }
        });
    }
}
