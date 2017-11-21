package com.braincorp.orkut2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

abstract class Dao {

    private final DatabaseHelper helper;

    SQLiteDatabase reader;
    SQLiteDatabase writer;
    private Context context;

    Dao(Context context) {
        this.context = context;
        helper = new DatabaseHelper(context);
        openDatabase();
    }

    void openDatabase() {
        reader = helper.getReadableDatabase();
        writer = helper.getWritableDatabase();
    }

}
