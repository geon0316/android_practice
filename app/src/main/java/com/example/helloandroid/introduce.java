package com.example.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class introduce extends Activity {

    Button backBtn; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce);
        
        // 뒤로가기 버튼 할당
        backBtn = (Button) findViewById(R.id.goto_home_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 뒤로가기 버튼 클릭시 메인창으로 이동
                Intent MainIntent = new Intent(getApplicationContext(), main_layout.class);
                startActivity(MainIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
