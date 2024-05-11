package com.example.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class selectContent extends Activity {

    private DBManager dbManager;

    // 인텐트로 넘겨받은 db 키값 정보
    long dbKey;

    // 유틸 함수 인스턴스 선언
    utilFunc func;

    // 인텐트 선언
    Intent intent;

    // 이름 입력칸 할당
    EditText nameInfo;

    // 전화번호 입력칸 할당
    EditText phoneInfo;

    // 이메일 입력칸 할당
    EditText emailInfo;

    // 회사 입력칸 할당
    EditText companyInfo;

    // 직함 입력칸 할당
    EditText levelInfo;

    RatingBar ratingBar;
    // 레이팅바 임시 변경값
    float tmpRating;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_contact);

        // 주소록 목록 인텐트 선언
        Intent ContactListIntent = new Intent(getApplicationContext(), Contact_list.class);

        // 유틸 함수 인스턴스 할당
        func = new utilFunc();

        // 레이팅바 할당
        ratingBar = (RatingBar)findViewById(R.id.contact_select_rating);
        tmpRating = 0.0f;

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tmpRating = rating;
            }
        });

        // 버튼 할당

        // 뒤로가기
        Button backBtn = (Button)findViewById(R.id.goto_list_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
                // 연락처 목록으로 돌아감
                startActivity(ContactListIntent);
            }
        });

        // 저장
        Button saveBtn = (Button)findViewById(R.id.contact_add_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 수정받은 정보를 통해 db의 데이터 업데이트

                // 저장 로직 처리
                Log.v("디버깅용", "저장 버튼 클릭");
                // 1. 입력값에 공백 여부 확인
                // 2. 입력값 유효 여부 확인
                // 3. 유효시 값 업데이트
                // 4. 연락처 목록으로 돌아감

                // 1. 입력값 공백 여부 확인
                boolean isEmpty = func.inputValueEmpty(nameInfo.getText().toString(), phoneInfo.getText().toString(),
                        emailInfo.getText().toString(), companyInfo.getText().toString(), levelInfo.getText().toString());

                // 입력값에 하나라도 공백이 존재함
                if (isEmpty == true)
                {
                    Toast inpToast = Toast.makeText(getApplicationContext(), "모든 값을 입력 해주세요", Toast.LENGTH_SHORT);
                    inpToast.show();
                }
                else
                {
                    // 2. 전화번호 유효 여부 확인
                    boolean isValid = func.validUserInfo(phoneInfo.getText().toString());

                    // 입력 받은 정보 (전화번호) 가 유효함
                    if (isValid == true)
                    {
                        // 3. 값 업데이트
                        int updateResult = dbManager.updateContact(dbKey, nameInfo.getText().toString(), phoneInfo.getText().toString(),
                                emailInfo.getText().toString(), companyInfo.getText().toString(), levelInfo.getText().toString(), tmpRating);

                        // 업데이트 관련 처리 결과 토스트 알림

                        // 4. 주소록 목록 인텐트 이동
                        startActivity(ContactListIntent);
                    }

                    // 유효 하지 않을때는 따로 이동하지 않음
                    else
                    {
                        Toast phoneToast = Toast.makeText(getApplicationContext(), "전화번호를 올바르게 입력 해주세요", Toast.LENGTH_SHORT);
                        phoneToast.show();
                    }
                }
            }
        });

        // 공유
        Button shareBtn = (Button)findViewById(R.id.contact_share_btn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 공유 처리
                Log.d("공유 버튼 처리", "공유 버튼 클릭 처리");
            }
        });

        // 삭제
        Button cancelBtn = (Button)findViewById(R.id.contact_delete_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 해당 연락처 정보 삭제

                // 삭제 연산 진행
                int deleteResult = dbManager.deleteContact(dbKey);

                if (deleteResult > 0)
                {
                    // 삭제 처리 결과 관련 토스트 알림
                    Toast delToast = Toast.makeText(getApplicationContext(), "연락처 정보가 삭제됐습니다", Toast.LENGTH_SHORT);
                    delToast.show();
                }
                else
                {
                    // 따로 토스트 알림 처리 X
                }
                startActivity(ContactListIntent);
            }
        });



        // 화면에 보여줄 데이터값

        // 이름 입력칸 할당
        nameInfo = (EditText) findViewById(R.id.name_info);

        // 전화번호 입력칸 할당
        phoneInfo = (EditText) findViewById(R.id.phoneNum_info);

        // 이메일 입력칸 할당
        emailInfo = (EditText) findViewById(R.id.email_info);

        // 회사 입력칸 할당
        companyInfo = (EditText) findViewById(R.id.company_info);

        // 직함 입력칸 할당
        levelInfo = (EditText) findViewById(R.id.level_info);

        // DBManager 인스턴스 할당
        dbManager = new DBManager(this);

        // 인텐트에서 넘겨 받기
        intent = getIntent();

        // 넘겨받은 인텐트 정보로 화면 구성하는 함수 호출
        setIntentInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 인텐트에서 넘겨 받기
        intent = getIntent();

        // 넘겨받은 인텐트 정보로 화면 구성하는 함수 호출
        setIntentInfo();
    }
    
    // 넘겨 받은 인텐트 정보로 화면에 정보 나타내기
    public void setIntentInfo()
    {
        // 넘겨받은 DB의 키값
        dbKey = intent.getLongExtra("databaseKey",-1L);

        // 키값을 통해 커서 객체 받아오기
        Cursor contactData = dbManager.getContact(dbKey);

        // 받아온 커서 객체를 통해 화면에 값 전시하기
        if (contactData != null) {
            try {
                // 커서를 첫 번째 행으로 이동
                if (contactData.moveToFirst()) {
                    // 정보별로 DB상의 인덱스 가져옴
                    int nameIndex = contactData.getColumnIndex("name");
                    int phoneIndex = contactData.getColumnIndex("phone_number");
                    int emailIndex = contactData.getColumnIndex("email");
                    int companyIndex = contactData.getColumnIndex("company");
                    int levelIndex = contactData.getColumnIndex("level");
                    int ratingIndex = contactData.getColumnIndex("rating");

                    // 각 인덱스를 사용하여 데이터 추출
                    String name = contactData.getString(nameIndex);
                    String phone = contactData.getString(phoneIndex);
                    String email = contactData.getString(emailIndex);
                    String company = contactData.getString(companyIndex);
                    String level = contactData.getString(levelIndex);
                    float rating = contactData.getFloat(ratingIndex);

                    // 화면에 가져온 값을 적용
                    nameInfo.setText(name);
                    phoneInfo.setText(phone);
                    emailInfo.setText(email);
                    companyInfo.setText(company);
                    levelInfo.setText(level);
                    ratingBar.setRating(rating);
                }
                else
                {
                    // 커서가 비어 있음 (데이터 없음)
                    // 화면에 기본값을 적용
                    nameInfo.setText("name");
                    phoneInfo.setText("phone");
                    emailInfo.setText("email");
                    companyInfo.setText("company");
                    levelInfo.setText("level");
                    ratingBar.setRating(0.0f);
                }
            }
            catch (Exception e)
            {
                // 예외 처리 로그
                Log.e("커서객체 접근 오류", "알 수 없는 오류", e);
            }

        }

        else
        {
            // 커서가 null인 경우의 처리
            // 화면에 기본값을 적용
            nameInfo.setText("name");
            phoneInfo.setText("phone");
            emailInfo.setText("email");
            companyInfo.setText("company");
            levelInfo.setText("level");
            ratingBar.setRating(0.0f);

        }



/*
        // 받아온 커서 객체를 통해 화면에 값 전시하기
        if (contactData != null)
        {
            // 정보별로 DB상의 인덱스 가져옴
            int nameIndex = contactData.getColumnIndex("name");
            int phoneIndex = contactData.getColumnIndex("phone_number");
            int emailIndex = contactData.getColumnIndex("email");
            int companyIndex = contactData.getColumnIndex("company");
            int levelIndex = contactData.getColumnIndex("level");

            // 커서 객체에서 데이터를 추출함
            String name = contactData.getString(nameIndex);
            String phone = contactData.getString(phoneIndex);
            String email = contactData.getString(emailIndex);
            String company = contactData.getString(companyIndex);
            String level = contactData.getString(levelIndex);

            // 화면에 가져온 값을 적용
            nameInfo.setText(name);
            phoneInfo.setText(phone);
            emailInfo.setText(email);
            companyInfo.setText(company);
            levelInfo.setText(level);
        }

        // 사용자가 리스트뷰에서 선택한 아이템의 ID값이 유효하지 않음
        else
        {

        }
*/
        // 커서 객체 닫기
        contactData.close();
    }
}
