package com.example.helloapplication.resolver;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.helloapplication.bean.User;

import java.util.ArrayList;
import java.util.List;

public class UserResolver {
    private ContentResolver resolver;
    private Uri uri;

    public UserResolver(Context context){
        resolver = context.getContentResolver();
        // 相当于域名
        uri = Uri.parse("content://com.example.helloapplication.provider.userProvider/user");
    }

    public void insert() {
        ContentValues values = new ContentValues();
        values.put("name", "Lance");
        values.put("age", 23);
        resolver.insert(uri, values);
    }

    public void delete(User user) {
        resolver.delete(ContentUris.withAppendedId(uri, user.getId()), null, null);
    }

    public void update(User user) {
        ContentValues values = new ContentValues();
        values.put("age", user.getAge() + 10);
        resolver.update(ContentUris.withAppendedId(uri, user.getId()), values, null, null);
    }

    public List<User> query() {
        List<User> userList = new ArrayList<>();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setAge(cursor.getInt(2));
        }
        return userList;
    }
}
