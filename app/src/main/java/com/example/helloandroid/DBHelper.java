package com.example.helloandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    // DB 인스턴스
    private static DBHelper instance;

    // 싱글톤을 위한 인스턴스 getter
    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext(), "contactDB.db", null, 1);
        }
        return instance;
    }



    // 생성자
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE if not exists Contact ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT,"
                + "phone_number TEXT,"
                + "email TEXT,"
                + "company TEXT,"
                + "level TEXT,"
                + "rating FLOAT);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE if exists Contact";

        db.execSQL(sql);
        onCreate(db);
    }
}
