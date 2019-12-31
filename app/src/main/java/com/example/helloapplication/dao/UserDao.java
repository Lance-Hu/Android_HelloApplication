package com.example.helloapplication.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.helloapplication.bean.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/7/21.
 */
public class UserDao {

    private SQLiteDatabase db;

    public UserDao(SQLiteDatabase db) {
        this.db = db;
    }

    public boolean insertUser(User user) {
        if (user == null) {
            Log.e("my sqlLite", "user is null");
            return false;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("age", user.getAge());

        /**
         * insert:表名 null 数据
         * contentValues 类似于map
         */
        long insertResult = db.insert("user", null, contentValues);
        if (insertResult == -1) {
            Log.e("my sqlLite", "insert failure");
            return false;
        }
        Log.i("my sqlLite", "insert success");
        return true;
    }

    public boolean delete(User user) {
        if (user == null) {
            Log.e("my sqlLiet", "user is null");
            return false;
        }

        String id = user.getId() + "";
        String[] deleteIdArr = new String[]{id};

        /**
         *表名    删除的条件
         */
        int deleteResult = db.delete("user", "id=?",
                deleteIdArr);
        if (deleteResult == 0) {
            Log.e("my sqlLite", "delete failure");
            return false;
        }
        return true;
    }

    public boolean update(User user) {
        if (user == null) {
            Log.e("my sqlLiet", "user is null");
            return false;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("age", user.getAge());

        String id = user.getId() + "";
        String[] updateIdArr = new String[]{id};

        int updateResult = db.update("user", contentValues, "id=?", updateIdArr);
        if (updateResult == 0) {
            Log.e("my sqlLite", "update failure");
            return false;
        }
        return true;
    }

    public User queryByName(User user) {
        if (user == null) {
            Log.e("my sqlLite", "user is null");
            return null;
        }
        String name = user.getName();
        String[] queryNameArr = new String[]{name};

        User result = new User();
        /**
         * cursor是返回数据集的指针
         * Cursor支持在查询的数据集合中以各种方式移动
         */

        /**
         * 要查询的表。必填
         * 要查询的列的数组，不填就查询所有列
         * 查询条件，必填
         *查询条件对应的值
         * groupby 分组语句
         * having 分组条件
         * orderBy 排序方式
         *limit 分页查询时的限制条件
         */
        Cursor cursor = db.query("user", null, "name=?",
                queryNameArr, null, null, null, null);

        while (cursor.moveToNext()) {
            result.setId(cursor.getInt(0));
            result.setAge(cursor.getInt(2));
        }
        return result;
    }

    public List<User> queryAll() {
        List<User> userList = new ArrayList<>();
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setAge(cursor.getInt(2));
            /**
             * Log 的i（info）等方法的第二个参数
             * 要传递的是打印的值，这个值不能是空
             */
           // Log.i("sqllite", cursor.getString(3) + "");
            userList.add(user);
        }
        return userList;
    }
}
