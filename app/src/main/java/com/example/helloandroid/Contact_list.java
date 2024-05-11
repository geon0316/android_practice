package com.example.helloandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Contact_list extends Activity {

    private DBManager dbManager;

    ContactAdapter contactAdapter;

    ListView contactList;

    TextView emptyText;


    private void updateListView() {
        Cursor newCursor = dbManager.getAllContacts();
        if (contactAdapter != null) {
            contactAdapter.changeCursor(newCursor); // 어댑터에 새로운 커서 설정
            contactAdapter.notifyDataSetChanged(); // 리스트뷰 갱신
        }
        else
        {
            contactAdapter = new ContactAdapter(this, newCursor);
            contactList.setAdapter(contactAdapter);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateListView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);

        // DB 매니저 인스턴스 생성
        dbManager = new DBManager(this);

        // 리스트뷰 할당
        contactList = (ListView)findViewById(R.id.contact_list);
        // 리스트뷰가 비어있을 떄 텅 비었음 메시지 출력
        emptyText = (TextView)findViewById(R.id.txt_empty);
        contactList.setEmptyView(emptyText);

        // DB의 데이터를 커서 객체로 가져옴
        Cursor contactCursor = dbManager.getAllContacts();

        // 리스트뷰에 나타낼 커스텀 어댑터 인스턴스 선언
        contactAdapter = new ContactAdapter(this, contactCursor);

        // 커스텀 어댑터 인스턴스를 리스트뷰에 set
        contactList.setAdapter(contactAdapter);



        // 뒤로가기 버튼 할당
        Button backBtn = (Button)findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메인창 인텐트 선언 및 이동
                Intent MainIntent = new Intent(getApplicationContext(), main_layout.class);
                startActivity(MainIntent);
            }
        });

        // 주소록 추가 버튼
        Button addContactBtn = (Button) findViewById(R.id.contact_add_btn);
        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 주소록 추가 인텐트 선언 및 이동
                Intent AddContactIntent = new Intent(getApplicationContext(), AddContact.class);
                startActivity(AddContactIntent);
            }
        });

        // 리스트 뷰에서 아이템 선택시 처리
        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // 선택한 주소록의 화면으로 전환을 위함
                Intent SelectContactIntent = new Intent(getApplicationContext(), selectContent.class);

                // 선택한 주소록의 db key값을 같이 전달
                SelectContactIntent.putExtra("databaseKey", id);

                startActivity(SelectContactIntent);
            }
        });
    }


}
