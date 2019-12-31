package com.example.helloapplication.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.helloapplication.dao.MyOpenHelper;


/**
 * 自定义的ContentProvider需要继承ContentProvider类
 * <p>
 * 涉及到一个URI类准确的说，它代表要操作的数据，包括两部分信息：
 * 1、ContentProvider
 * 2、ContentProvider中的什么数据
 */
public class UserProvider extends ContentProvider {
    private MyOpenHelper myOpenHelper;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    //匹配码
    private static final int USER = 1;
    private static final int USER_ID = 2;

    static {
        MATCHER.addURI("com.example.helloapplication.provider.userProvider",
                "user", USER);
        MATCHER.addURI("com.example.helloapplication.provider.userProvider",
                "user/#", USER_ID);
    }

    /**
     * 会在创建ContentProvider时调用
     */
    @Override
    public boolean onCreate() {
        myOpenHelper = new MyOpenHelper(this.getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();

        switch (MATCHER.match(uri)) {
            case USER:
                return db.query("user", strings, s, strings1, null, null, s1);
            case USER_ID:
                long id = ContentUris.parseId(uri);
                String where = "id=" + id;
                if (null != s && !"".equals(s.trim())) {
                    where += "and " + s;
                }
                return db.query("user",
                        strings, where, strings1, null, null, s1);
            default:
                throw new IllegalArgumentException(
                        "this is unkown uri:" + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        switch (MATCHER.match(uri)) {
            case USER:
                long id = db.insert("user", null, contentValues);
                Uri insertUri = ContentUris.withAppendedId(uri, id);
                db.close();
                return insertUri;
            case USER_ID:
                break;
            default:
                throw new IllegalArgumentException(
                        "this is unkown uri:" + uri);
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        switch (MATCHER.match(uri)) {
            case USER:
                return db.update("user", contentValues, s, strings);
            case USER_ID:
                long id = ContentUris.parseId(uri);
                String where = "id=" + id;
                if (null != s && !"".equals(s.trim())) {
                    where += "and " + s;
                }
                return db.update("user", contentValues, where, strings);
            default:
                throw new IllegalArgumentException(
                        "this is unkown uri:" + uri);
        }
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        switch (MATCHER.match(uri)) {
            case USER:
                return db.delete("user", s, strings);
            case USER_ID:
                long id = ContentUris.parseId(uri);
                String where = "id=" + id;
                if (null != s && !"".equals(s.trim())) {
                    where += "and " + s;
                }
                return db.delete("user", where, strings);
            default:
                throw new IllegalArgumentException(
                        "this is unkown uri:" + uri);
        }
    }

    /**
     * 传进来一个URI
     * 返回值是生成一个字符串MIMETYPE
     * vnd.android.cursor.item/
     * vnd.android.cursor.dir/
     * <p>
     * 加上在清单文件中的配置，等于是做了一个标示
     * 可以让其他应用找到
     */
    @Override
    public String getType(Uri uri) {
        switch (MATCHER.match(uri)) {
            case USER:
                return "vnd.android.cursor.item/user";
            case USER_ID:
                return "vnd.android.cursor.item/user/";
            default:
                throw new IllegalArgumentException(
                        "this is unkown uri:" + uri);
        }
    }
}
