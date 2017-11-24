package com.braincorp.orkut2.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Date;

public class User implements Parcelable {

    public static final Creator CREATOR = new Creator() {
        @NonNull
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @NonNull
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private int id;
    private String userName;
    private String password;
    private String fullName;
    private Date dateOfBirth;

    private User() { }

    private User(Parcel in) {
        id = in.readInt();
        userName = in.readString();
        password = in.readString();
        fullName = in.readString();
        dateOfBirth = new Date(in.readLong());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    void setUserName(String userName) {
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

    void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "User {"+
                "id = '" + id + '\'' +
                ", userName = '" + userName + '\'' +
                ", password = '" + password + '\'' +
                ", fullName = '" + fullName + '\'' +
                ", dateOfBirth = '" + dateOfBirth.getTime() + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(userName);
        parcel.writeString(password);
        parcel.writeString(fullName);
        parcel.writeLong(dateOfBirth.getTime());
    }

    public static class Builder {

        private User user;

        public Builder() {
            user = new User();
        }

        public Builder setId(int id) {
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
