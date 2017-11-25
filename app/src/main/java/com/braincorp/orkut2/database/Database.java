package com.braincorp.orkut2.database;

import com.braincorp.orkut2.model.User;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database instance;

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    private List<User> users;

    private Database() {
        users = new ArrayList<>(50);
    }

    public List<User> getUsers() {
        return users;
    }

    public int getUserIndex(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(user))
                return i;
        }
        return -1;
    }

    public void insert(User user) {
        users.add(user);
    }

    public void delete(User user) {
        users.remove(user);
    }

    public List<User> select(User user, boolean excludeCurrentUser) {
        List<User> result = users;
        if (excludeCurrentUser)
            users.remove(user);
        return result;
    }

    public User select(String userName, String password) {
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

}
