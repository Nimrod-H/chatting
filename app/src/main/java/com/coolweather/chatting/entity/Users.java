package com.coolweather.chatting.entity;

import org.litepal.crud.LitePalSupport;

public class Users extends LitePalSupport {
            private int userId;
            private int userName;
            private String passWord;
            private int tellPhoneNum;
            private String address;
            private String sex;
            private int age;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserName() {
        return userName;
    }

    public void setUserName(int userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getTellPhoneNum() {
        return tellPhoneNum;
    }

    public void setTellPhoneNum(int tellPhoneNum) {
        this.tellPhoneNum = tellPhoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
