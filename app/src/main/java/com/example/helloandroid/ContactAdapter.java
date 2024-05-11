package com.example.helloandroid;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ContactAdapter extends CursorAdapter {

    public ContactAdapter(Context context, Cursor cursor)
    {
        super(context, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        // 인자 내부의 레이아웃은 리스트뷰의 원소를 나타내는 레이아웃임
        return LayoutInflater.from(context).inflate(R.layout.contact_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // 컬럼 인덱스 찾기
        int nameIndex = cursor.getColumnIndex("name");

        // 뷰 찾기
        TextView textViewName = view.findViewById(R.id.contact_view_name);

        // 데이터 설정
        textViewName.setText(cursor.getString(nameIndex));

    }
}
