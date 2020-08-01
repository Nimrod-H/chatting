package com.coolweather.chatting.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NetServer {
    static List<Socket> clients = new ArrayList<>();
    static BufferedWriter out = null;
    static BufferedReader msg = null;
    public static void main(String[] args) throws IOException {
        ServerSocket socketServer = null;
        Socket client = null;

        try {
            socketServer = new ServerSocket(10005);      //创建本地服务器端口
            System.out.println("服务器已启动");
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("local host:" + addr);
            while (true) {
                client = socketServer.accept();   //接受一个请求
                System.out.println("服务器已连接");
                clients.add(client);
                System.out.println("连接成功，客户端请求服务端的详细信息：" + client.toString());
                new ListenerClient(client);
            }

        }catch (Exception e){
            System.out.println("启动服务器失败，端口：" + 10005);
            e.printStackTrace();
        }finally {
           client.close();
           socketServer.close();
        }
    }
    public static  void manageConnection(final Socket socket){

        new Thread(){
            @Override
            public void run() {

                try {
                     out =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
                     msg =new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));//对缓冲区数据读取
                    StringBuffer info = new StringBuffer();
                    System.out.println("收到信息为：" + info.toString());
                    info.append("I`m fine ! 你好！");  //回应的数据
                    info.append(msg.readLine());
                    System.out.println(info.toString());
                    sleep(1000);
                    out.write(info.toString());
                    out.flush();
                    info.delete(0,info.length());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        out.close();
                        msg.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            }


        }.start();
    }
    public synchronized void sendMsg(String msg){           //发送所有信息给

        try {
            for (int i = 0; i < clients.size(); i++) {
                Socket client = clients.get(i);
                out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
                out.write(msg);
                out.flush();
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void println(String s) {
        if (s != null) {
            s = "服务端打印消息：" + s;
            System.out.println(s + "\n");
        }
    }

}
