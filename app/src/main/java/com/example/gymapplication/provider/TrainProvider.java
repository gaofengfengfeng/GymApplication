package com.example.gymapplication.provider;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.Objects;

@SuppressLint("Registered")
public class TrainProvider extends ContentProvider {

    // 常量
    private static final String PROVIDER_NAME = "com.example.gymapplication.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/trainers");
    public static final String ID = "_id";
    public static final String TRAINER_NAME = "trainer_name";
    private static final int TRAINERS = 1;
    private static final int TRAINER_ID = 2;
    private static UriMatcher URI_MATCHER = null;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(PROVIDER_NAME, "/trainers", TRAINERS);
        URI_MATCHER.addURI(PROVIDER_NAME, "/trainers/#", TRAINER_ID);
    }

    // 数据库实例
    SQLiteDatabase trainDB;
    static final String DATABASE_NAME = "Train";
    static final String DATABASE_TABLE = "trainers";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE = "create table " + DATABASE_TABLE +
            " (_id integer primary key autoincrement, "
            + "trainer_name text not null);";

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        trainDB = dbHelper.getWritableDatabase();
        return trainDB != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
        sqlBuilder.setTables(DATABASE_TABLE);
        //---如果要获取制定的图书信息--
        if (URI_MATCHER.match(uri) == TRAINER_ID) {
            sqlBuilder.appendWhere(ID + " = " + uri.getPathSegments().get(1));
        }
        if (sortOrder == null || Objects.equals(sortOrder, "")) {
            sortOrder = TRAINER_NAME;
        }
        Cursor c = sqlBuilder.query(
                trainDB,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        //---注册一个观察者来监视Uri的变化---
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            //---获取所有的教练---
            case TRAINERS:
                return "vnd.android.cursor.dir/vnd.com.example.gymapplication.provider.trainers";
            //---获取指定的教练---
            case TRAINER_ID:
                return "vnd.android.cursor.item/vnd.com.example.gymapplication.provider.trainers";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //---添加一个教练---
        long rowID = trainDB.insert(DATABASE_TABLE, "", values);
        //---如果添加成功的话---
        if (rowID > 0) {
            Uri tempUri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(tempUri, null);
            return tempUri;
        }
        //---添加不成功---
        throw new SQLException("Failed to insert row into " + uri);
    }

    /**
     * 删除一个教练
     *
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Integer count;
        switch (URI_MATCHER.match(uri)) {
            case TRAINERS:
                count = trainDB.delete(DATABASE_TABLE, selection, selectionArgs);
                break;
            case TRAINER_ID:
                String id = uri.getPathSegments().get(1);
                count = trainDB.delete(DATABASE_TABLE, ID + "=" + id + (
                        !TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("unsuppoted Uri:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count;
        switch (URI_MATCHER.match(uri)) {
            case TRAINERS:
                count = trainDB.update(
                        DATABASE_TABLE,
                        values,
                        selection,
                        selectionArgs);
                break;
            case TRAINER_ID:
                count = trainDB.update(
                        DATABASE_TABLE,
                        values,
                        ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" +
                                        selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    /**
     * 本示例中Content Provider使用SQLite数据库存储图书数据。
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            Log.w("Provider database", "Upgrading database from version " +
                    oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    }
}
