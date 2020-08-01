package com.coolweather.chatting;

import java.io.Serializable;
import java.util.Date;

public class Msg implements Serializable {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;
    public static final boolean Received = true;
    public static final boolean noReceived = false;
    public static final int msgType_private = 0;
    public static final int msgType_public  = 1;
    private  String content;
    private  int type;

    public void setType(int type) {
        this.type = type;
    }

    private Date date;

    public String getLocalSocketAddress() {
        return localSocketAddress;
    }

    private int sendID;
    private int receiveID;
    private int msgType;
    private boolean isReceived;
    private String localSocketAddress;
    public Date getDate() {
        return date;
    }

    public int getSendID() {
        return sendID;
    }

    public int getReceiveID() {
        return receiveID;
    }

    public int getMsgType() {
        return msgType;
    }

    public boolean getIsReceived() {
        return isReceived;
    }

    public Msg(String content, int type, Date date, int sendID, int receiveID, boolean isReceived,String localSocketAddress) {
        this.content = content;
        this.type = type;
        this.date = date;
        this.sendID = sendID;
        this.receiveID = receiveID;
        this.isReceived = isReceived;
        this.localSocketAddress = localSocketAddress;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
