package com.example.helloapplication.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2018/7/21.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    private static final String name = "lizishule.db";
    private static final int version = 4;

    public MyOpenHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建表
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                "user(id INTEGER primary key autoincrement," +
                "name varchar(32),age INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.i("my sqlLite", newVersion + "\t" + oldVersion);
            /**
             * 这里为了直观的看到效果，我们做一个删除数据的操作
             * 真正的开发实践中，是不会这么做的
             * 一般情况我们会在这里去做数据库表的增加与删除
             * 原有数据库的字段等的修改与增加或者删除
             */

            sqLiteDatabase.execSQL("ALTER table user add phone varchar(14)");
        }
    }
}
