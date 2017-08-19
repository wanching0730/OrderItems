package com.wanching.orderitems;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WanChing on 19/8/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "product";


    private static final String SQL_CREATE_ENTRIES=
            "CREATE TABLE " + DbContract.DbEntry.TABLE_NAME
                    + "(" + DbContract.DbEntry.COLUMN_ID + " INTEGER PRIMARY KEY," + DbContract.DbEntry.COLUMN_PRODUCTNAME
                    + " TEXT," + DbContract.DbEntry.COLUMN_QUANTITY + " INTEGER" + ")";


    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DbContract.DbEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
