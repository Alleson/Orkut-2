package com.braincorp.orkut2.model;

import java.util.Date;

public class User {

    private long id;
    private String userName;
    private String password;
    private String fullName;
    private Date dateOfBirth;

    private User() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static class Builder {

        private User user;

        public Builder() {
            user = new User();
        }

        public Builder setId(long id) {
            user.setId(id);
            return this;
        }

        public Builder setUserName(String userName) {
            user.setUserName(userName);
            return this;
        }

        public Builder setPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder setFullName(String fullName) {
            user.setFullName(fullName);
            return this;
        }

        public Builder setDateOfBirth(Date dateOfBirth) {
            user.setDateOfBirth(dateOfBirth);
            return this;
        }

        public User build() {
            return user;
        }

    }

}
