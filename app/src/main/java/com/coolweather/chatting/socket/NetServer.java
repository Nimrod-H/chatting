package com.coolweather.babyscot.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(10000);      //创建本地服务器端口
        System.out.println("服务器已启动");
        Socket client  = socket.accept();   //接受一个请求
        PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream(),"UTF-8"),true);
        BufferedReader msg = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
        StringBuffer info = new StringBuffer();
        info.append("I`m fine ! 你好！");  //回应的数据
        info.append(msg.readLine());
        System.out.println(info);
        out.print(info);
        out.close();
        msg.close();
        client.close();
        socket.close();
    }
}
