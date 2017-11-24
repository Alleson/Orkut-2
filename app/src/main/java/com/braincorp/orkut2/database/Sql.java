package com.braincorp.orkut2.database;

public class Sql {

    private Sql() { }

    public static final String ALL_OTHER_USERS = String.format("SELECT * FROM %1$s WHERE %2$s <> ",
            UserDao.TABLE_NAME, Column.ID) + "%d";

}
