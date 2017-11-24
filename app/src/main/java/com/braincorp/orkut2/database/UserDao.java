package com.braincorp.orkut2.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.braincorp.orkut2.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao extends Dao {

    public static final String TABLE_NAME = "USERS";
    private static final String WHERE_CLAUSE = String.format("%1$s = ?", Column.ID);
    private static final String[] COLUMNS = null;
    private static final String GROUP_BY = null;
    private static final String HAVING = null;
    private static final String LIMIT = "1";

    @SuppressLint("StaticFieldLeak")
    private static UserDao instance;

    public static UserDao getInstance(Context context) {
        if (instance == null)
            instance = new UserDao(context);
        return instance;
    }

    private UserDao(Context context) {
        super(context);
    }

    public boolean delete(User user) {
        if (!writer.isOpen())
            openDatabase();
        String[] whereArgs = new String[] {String.valueOf(user.getId())};
        boolean success = writer.delete(TABLE_NAME, WHERE_CLAUSE, whereArgs) > 0;
        writer.close();
        return success;
    }

    public long insert(User user) {
        if (!writer.isOpen())
            openDatabase();
        final String nullColumnHack = null;
        ContentValues values = new ContentValues();
        fillFields(user, values);
        return writer.insert(TABLE_NAME, nullColumnHack, values);
    }

    public List<User> select() {
        if (!reader.isOpen())
            openDatabase();
        final String selection = null;
        final String[] selectionArgs = null;
        String orderBy = String.format("%1$s ASC", Column.ID);
        Cursor cursor = reader.query(TABLE_NAME, COLUMNS, selection, selectionArgs, GROUP_BY, HAVING, orderBy);
        List<User> users = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            users = queryUsers(cursor);
        }
        cursor.close();
        reader.close();
        return users;
    }

    public List<User> select(String sql) {
        if (!reader.isOpen())
            openDatabase();
        List<User> users = new ArrayList<>();
        Cursor cursor = reader.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            users = queryUsers(cursor);
        }
        cursor.close();
        reader.close();
        return users;
    }

    public User select(int id) {
        if (!reader.isOpen())
            openDatabase();
        String selection = String.format("%1$s = ?", Column.ID);
        String[] selectionArgs = new String[] {String.valueOf(id)};
        final String orderBy = null;
        Cursor cursor = reader.query(TABLE_NAME, COLUMNS, selection, selectionArgs,
                                     GROUP_BY, HAVING, orderBy, LIMIT);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return buildEntity(cursor, id);
        } else {
            cursor.close();
            reader.close();
            return null;
        }
    }

    public User select(String userName, String password) {
        if (!reader.isOpen())
            openDatabase();
        String selection = String.format("%1$s = ? AND %2$s = ?",
                Column.USER_NAME,
                Column.PASSWORD);
        String[] selectionArgs = new String[] {userName, password};
        final String orderBy = null;
        Cursor cursor = reader.query(TABLE_NAME, COLUMNS, selection, selectionArgs,
                GROUP_BY, HAVING, orderBy, LIMIT);
        if (cursor.getCount() > 0) {
            return buildEntity(cursor);
        } else {
            reader.close();
            return null;
        }
    }

    public boolean update(User user) {
        if (!writer.isOpen())
            openDatabase();
        ContentValues values = new ContentValues();
        fillFields(user, values);
        String[] whereArgs = new String[] {String.valueOf(user.getId())};
        boolean success = writer.update(TABLE_NAME, values, WHERE_CLAUSE, whereArgs) > 0;
        writer.close();
        return success;
    }

    private User buildEntity(Cursor cursor) {
        cursor.moveToFirst();

        int id = cursor.getInt(cursor.getColumnIndex(Column.ID));
        String userName = cursor.getString(cursor.getColumnIndex(Column.USER_NAME));
        String password = cursor.getString(cursor.getColumnIndex(Column.PASSWORD));
        String fullName = cursor.getString(cursor.getColumnIndex(Column.FULL_NAME));
        long dateInMillis = cursor.getLong(cursor.getColumnIndex(Column.DATE_OF_BIRTH));
        Date dateOfBirth = new Date(dateInMillis);

        User.Builder userBuilder = new User.Builder().setId(id)
                .setUserName(userName)
                .setPassword(password)
                .setFullName(fullName)
                .setDateOfBirth(dateOfBirth);
        cursor.close();
        reader.close();
        return userBuilder.build();
    }

    private User buildEntity(Cursor cursor, int id) {
        String userName = cursor.getString(cursor.getColumnIndex(Column.USER_NAME));
        String password = cursor.getString(cursor.getColumnIndex(Column.PASSWORD));
        String fullName = cursor.getString(cursor.getColumnIndex(Column.FULL_NAME));
        long dateInMillis = cursor.getLong(cursor.getColumnIndex(Column.DATE_OF_BIRTH));
        Date dateOfBirth = new Date(dateInMillis);

        User.Builder userBuilder = new User.Builder().setId(id)
                .setUserName(userName)
                .setPassword(password)
                .setFullName(fullName)
                .setDateOfBirth(dateOfBirth);
        return userBuilder.build();
    }

    private void fillFields(User user, ContentValues values) {
        values.put(Column.USER_NAME, user.getUserName());
        values.put(Column.PASSWORD, user.getPassword());
        values.put(Column.FULL_NAME, user.getFullName());
        if (user.getDateOfBirth() != null)
            values.put(Column.DATE_OF_BIRTH, user.getDateOfBirth().getTime());
    }

    private List<User> queryUsers(Cursor cursor) {
        List<User> users = new ArrayList<>();
        do {
            int id = cursor.getInt(cursor.getColumnIndex(Column.ID));
            User user = buildEntity(cursor, id);
            users.add(user);
        } while (cursor.moveToNext());
        return users;
    }

}
