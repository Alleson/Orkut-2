package com.braincorp.orkut2.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.braincorp.orkut2.BuildConfig.DB_NAME;
import static com.braincorp.orkut2.BuildConfig.DB_PATH;

class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private SQLiteDatabase database;
    private final Context context;

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        createDatabase();
        openDatabase();
    }

    private void createDatabase() {
        boolean dbExists = checkDatabase();
        if (!dbExists) {
            getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDatabase() {
        SQLiteDatabase db = null;
        try {
            String path = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        if (db != null)
            db.close();
        return db != null;
    }

    private void copyDatabase() throws IOException {
        InputStream input = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream output = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024 * 50];
        int length;
        while ((length = input.read(buffer)) > 0)
            output.write(buffer, 0, length);

        output.flush();
        output.close();
        input.close();
    }

    private void openDatabase() throws SQLException {
        String path = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

}