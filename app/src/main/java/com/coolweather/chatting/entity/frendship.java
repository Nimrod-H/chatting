package com.coolweather.chatting.entity;

import org.litepal.crud.LitePalSupport;

public class frendship extends LitePalSupport {
        private int frendID;
        private String frendName;
        private String frendNickName;
        private int frendStatus;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    private int UserId;
    public int getFrendID() {
        return frendID;
    }

    public void setFrendID(int frendID) {
        this.frendID = frendID;
    }

    public String getFrendName() {
        return frendName;
    }

    public void setFrendName(String frendName) {
        this.frendName = frendName;
    }

    public String getFrendNickName() {
        return frendNickName;
    }

    public void setFrendNickName(String frendNickName) {
        this.frendNickName = frendNickName;
    }

    public int getFrendStatus() {
        return frendStatus;
    }

    public void setFrendStatus(int frendStatus) {
        this.frendStatus = frendStatus;
    }
}
