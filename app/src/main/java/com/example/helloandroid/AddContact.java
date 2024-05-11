package com.example.helloandroid;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class AddContact extends Activity {

    // DB 매니저 인스턴스
    private DBManager dbManager;

    utilFunc func;

    // 레이팅바
    RatingBar ratingBar;
    // 레이팅바 임시 변경값
    float tmpRating;

    @Override
    protected void onResume() {
        super.onResume();
        tmpRating = 0.0f;
        ratingBar.setNumStars(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        // 유틸 함수 인스턴스 생성
        func = new utilFunc();

        // DB 매니저 인스턴스 생성
        dbManager = new DBManager(this);

        //!!!! 저장 눌렀을 때 DB에 저장하는 로직부터 진행하면 됨
        
        
        // 사용자 정보 입력 //

        // 이름 입력칸 할당
        EditText nameInput = (EditText) findViewById(R.id.name_input);

        // 전화번호 입력칸 할당
        EditText phoneInput = (EditText) findViewById(R.id.phoneNum_input);

        // 이메일 입력칸 할당
        EditText emailInput = (EditText) findViewById(R.id.email_input);

        // 회사 입력칸 할당
        EditText companyInput = (EditText) findViewById(R.id.company_input);

        // 직함 입력칸 할당
        EditText levelInput = (EditText) findViewById(R.id.level_input);

        // 레이팅바 할당
        ratingBar = (RatingBar)findViewById(R.id.contact_rating);
        tmpRating = 0.0f;

        // 주소록 목록 인텐트 선언
        Intent ContactListIntent = new Intent(getApplicationContext(), Contact_list.class);

        // 뒤로가기 버튼 할당
        Button backBtn = (Button) findViewById(R.id.goto_list_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 저장 로직 처리

                // 1. 연락처 목록으로 돌아감

                // 연락처 목록 인텐트 이동
                startActivity(ContactListIntent);
            }
        });

        // 레이팅바 설정


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tmpRating = rating;
            }
        });

        // 저장 버튼 할당
        Button saveBtn = (Button) findViewById(R.id.contact_add_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 저장 로직 처리
                Log.v("디버깅용", "저장 버튼 클릭");
                // 1. 입력값 공백 확인
                // 2. 입력값 유효 여부 확인
                // 3. 유효시 값 저장
                // 4. 연락처 목록으로 돌아감

                // 1. 입력한 값중 공백이 있는지확인
                boolean isEmpty =  func.inputValueEmpty(nameInput.getText().toString(), phoneInput.getText().toString(),
                        emailInput.getText().toString(), companyInput.getText().toString(), levelInput.getText().toString());
                
                // 입력 받은 값중에 공백이 존재
                if (isEmpty == true)
                {
                    // 재입력을 안내하는 토스트 알림 띄움
                    Toast inpToast = Toast.makeText(getApplicationContext(), "모든 값을 입력 해주세요", Toast.LENGTH_SHORT);
                    inpToast.show();
                }
                // 입력 받은 값중에 공백이 존재하지 않음
                else
                {
                    // 2. 입력값 유효 여부 확인 (전화번호)
                    boolean isValid = func.validUserInfo(phoneInput.getText().toString());

                    // 입력 받은 정보 (전화번호)가 유효

                    if (isValid == true)
                    {
                        // 3. 값 저장
                        dbManager.addContact(nameInput.getText().toString(), phoneInput.getText().toString(),
                                emailInput.getText().toString(), companyInput.getText().toString(), levelInput.getText().toString(), tmpRating);

                        // 4. 주소록 목록 인텐트 이동
                        startActivity(ContactListIntent);
                    }
                    // 입력 받은 정보 (전화번호)가 유효하지 않음
                    else
                    {
                        // 휴대폰 번호 재입력을 안내하는 토스트 알림 띄움
                        Toast phoneToast = Toast.makeText(getApplicationContext(), "전화번호를 올바르게 입력 해주세요", Toast.LENGTH_SHORT);
                        phoneToast.show();
                    }
                }
            }
        });

        // 취소 버튼 할당
        Button cancelBtn = (Button) findViewById(R.id.contact_add_cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 취소 로직 처리
                // 1. 연락처 목록으로 돌아감

                // 주소록 목록 인텐트 이동
                startActivity(ContactListIntent);
            }
        });


    }
}