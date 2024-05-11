package com.example.helloandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager {

    private DBHelper dbHelper;

    public DBManager(Context context)
    {
        dbHelper = DBHelper.getInstance(context);
    }

    // 모든 인스턴스 반환
    public Cursor getAllContacts()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Contact", null);
    }

    // 요소 추가
    public void addContact(String name, String phone, String email, String company, String level, float rating)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone_number", phone);
        values.put("email", email);
        values.put("company", company);
        values.put("level", level);
        values.put("rating", rating);
        db.insert("Contact", null, values);
    }

    // 요소 업데이트
    public int updateContact(Long id, String name, String phone, String email, String company, String level, float rating)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone_number", phone);
        values.put("email", email);
        values.put("company", company);
        values.put("level", level);
        values.put("rating", rating);

        // 업데이트 조건
        String selection = "_id = ?";
        String[] selectionArgs = { String.valueOf(id) };

        // 업데이트 실행
        return db.update("Contact", values, selection, selectionArgs);
    }

    // id 값으로 요소 삭제
    public int deleteContact(long id)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // 삭제 조건
        String selection = "_id = ?";
        String[] selectionArgs = { String.valueOf(id) };

        // 삭제 실행
        return db.delete("Contact", selection, selectionArgs);
    }

    // id값으로 데이터 조회
    public Cursor getContact(long id)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // 반환될 커서 객체
        Cursor cursor = null;

        try
        {
            cursor = db.rawQuery("SELECT * FROM Contact WHERE _id = ?", new String[] {String.valueOf(id)});
            if (cursor != null && cursor.moveToFirst())
            {
                // 데이터가 성공적으로 조회됨
                return cursor;
            }
            else
            {
                // 데이터가 없는 경우 처리
                Log.d("데이터베이스", "해당하는 ID의 데이터 정보를 찾을 수 없음: " + id);
            }
        }
        catch (Exception e)
        {
            // 예외 처리 로그
            Log.e("데이터베이스", "알 수 없는 오류", e);
        }
        finally {
            // 필요한 경우 여기서 커서를 닫을 수 있음
            // cursor.close(); <- 반환하기 전에 닫으면 안 됨
        }

        return null; // 오류 또는 데이터 없음
    }


}
