package com.coolweather.chatting.socket;

import com.coolweather.chatting.entity.Msg;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientManager {
        private static Map<String, Socket> clientList = new HashMap<>();
         private static ServerThread serverThread = null;
        private static class ServerThread implements Runnable {
            private int port = 10010;
            private boolean isExit = false;             //该变量是用于记录当前线程的服务器是否开始
            private ServerSocket server;

            public ServerThread() {                  //在初始化该类时就把服务器开启，避免在主线程中开启线程，或者多一个线程开启线程引起后续操作增加
                try {
                    server = new ServerSocket(port);
                    System.out.println("启动服务器！");
                } catch (IOException e) {
                    System.out.println("启动服务器失败，原因是：" + e.toString());
                }
            }

            @Override
            public void run() {
                try {
                    while (!isExit) {         //进入等待状态
                        System.out.println("等待用户进行连接！");
                        final Socket socket = server.accept();
                        //获取手机连接的端口号以及地址
                        final String address = socket.getRemoteSocketAddress().toString();
                        System.out.println("连接成功，连接的手机为 ：" + address);
                        //开启一个新的线程用来处理客户端放入储存的map中
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    synchronized (this) {
                                        clientList.put(address, socket);
                                    }
                                    //定义输入流
                                    InputStream inputStream = socket.getInputStream();      //用输入流的形式来处理发来的信息
                                    while(true) {
                                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                                        Msg msg  = (Msg)objectInputStream.readObject();
                                        if(msg != null) {
                                            System.out.println("客户端:"+msg.getLocalSocketAddress()+"发来的消息为：" + msg.getContent());
                                            msg.setType(Msg.TYPE_RECEIVED);
                                            sendMsgAll(msg);
                                        }else {
                                            break;
                                        }
                                    }
                                  //  byte[] buffer = new byte[1024];
                                  //  int len;
                                  //  while ((len = inputStream.read(buffer)) != -1) {
                                  //      String text = new String(buffer, 0, len);
                                  //      System.out.println("用户："+ address+"发来的信息为：" + text);
                                   //     sendMsgAll(text);
                                   // }
                                } catch (Exception e) {
                                    System.out.println("错误信息为：" + e.getMessage());
                                } finally {
                                    synchronized (this) {
                                        System.out.println("关闭链接" + address);
                                        clientList.remove(address);
                                    }
                                }
                            }
                        }).start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void Stop() {
                isExit = true;   //关闭服务器的标志
                if (server != null) {
                    try {
                        server.close();
                        System.out.println("已关闭Server！");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
            public static ServerThread startServer(){
                System.out.println("开启服务");
                if (serverThread != null){
                    showDown();
                }
                serverThread = new ServerThread();
                new Thread(serverThread).start();
                System.out.println("开启服务成功");
                return serverThread;
            }
            // 关闭所有server socket 和 清空Map
            public static void showDown(){
                for (Socket socket : clientList.values()) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                serverThread.Stop();
                clientList.clear();
            }
            // 群发的方法
            public static boolean sendMsgAll(Msg msg){
                try {

                    for (Socket socket : clientList.values()) {
                        if(!msg.getLocalSocketAddress().equals(socket.getRemoteSocketAddress().toString())){
                            OutputStream outputStream = socket.getOutputStream();
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                            objectOutputStream.writeObject(msg);
                            objectOutputStream.flush();
                            System.out.println(msg.getLocalSocketAddress());
                            System.out.println(socket.getRemoteSocketAddress());
                            System.out.println("发送信息给主机："+socket.getRemoteSocketAddress()+msg.getContent());
                        }
                    }
                    return true;
                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }
        }

