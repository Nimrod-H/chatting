package com.coolweather.chatting.entity;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.Date;

public class Msg  extends LitePalSupport implements Serializable  {
    private static final long serialVersionUID = -5809782578272943999L;
    public static final int TYPE_RECEIVED = 0;              //信息类型，1为发送，0为接受
    public static final int TYPE_SEND = 1;
    public static final boolean Received = true;            //信息是否已经被接收
    public static final boolean noReceived = false;
    public static final int msgType_private = 0;            //信息类型是私人还是群发
    public static final int msgType_public  = 1;
    private  String content;                                //消息内容
    private  int type;                                      //消息类型
    private Date date;                                      //消息发送日期
    private int sendID;                                     //消息发送者ID
    private int receiveID;                                  //消息接收者ID
    private int msgType;                                    //消息发送类型或者接受类型
    private boolean isReceived;                             //消息是否被接受
    private String localSocketAddress;                      //发送的主机位置

    public Msg(String content, int type, Date date, int sendID, int receiveID, boolean isReceived,String localSocketAddress) {
        this.content = content;
        this.type = type;
        this.date = date;
        this.sendID = sendID;
        this.receiveID = receiveID;
        this.isReceived = isReceived;
        this.localSocketAddress = localSocketAddress;
    }

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
    public void setType(int type) {
        this.type = type;
    }

    public Msg() {
    }

    public String getLocalSocketAddress() {
        return localSocketAddress;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSendID(int sendID) {
        this.sendID = sendID;
    }

    public void setReceiveID(int receiveID) {
        this.receiveID = receiveID;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public boolean isReceived() {
        return isReceived;
    }

    public void setReceived(boolean received) {
        isReceived = received;
    }

    public void setLocalSocketAddress(String localSocketAddress) {
        this.localSocketAddress = localSocketAddress;
    }



    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
