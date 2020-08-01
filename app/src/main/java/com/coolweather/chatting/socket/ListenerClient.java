package com.coolweather.chatting.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import static com.coolweather.chatting.socket.NetServer.clients;

public class ListenerClient extends Thread {
        BufferedWriter out = null;
        BufferedReader in = null;
        Socket client;          //用于记录连接上的客户端
    public ListenerClient(Socket client) {
        this.client = client;
        this.run();
    }

    @Override
    public void run() {
        super.run();
        String msg = "";
        System.out.println(client.toString());
        while(true){
            try {
                out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),"UTF-8"));
                in = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
                msg = in.readLine();
                sendMsg(msg);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            if(msg != null && msg.trim() != ""){
                println(msg);
            }
        }
    }
    public void println(String s) {
        if (s != null) {
            s = "服务端打印消息：" + s;
            System.out.println(s + "\n");
        }
    }
    public synchronized void sendMsg(String msg){           //发送所有信息给

        try {
            for (int i = 0; i < NetServer.clients.size(); i++) {
                Socket client = NetServer.clients.get(i);
                out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),"UTF-8"));
                out.write(msg);
                out.flush();
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
