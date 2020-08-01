package com.coolweather.chatting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coolweather.chatting.adapters.MsgAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class chatiingActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecycleView;
    private MsgAdapter adapter;
    private  String getMsg = "";
    private Handler handler=null;
    private String outMsg = "";
    Socket client = null;
    BufferedReader msg;
    BufferedWriter out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_layout);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        if(ContextCompat.checkSelfPermission(chatiingActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Aaaaaaaaaaaaaaaaaaaaaa",Toast.LENGTH_LONG).show();
        }

        msgRecycleView = findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecycleView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecycleView.setAdapter(adapter);
        inputText = findViewById(R.id.chatting_editText);
        send = findViewById(R.id.message_send);
        new Thread(){
            @Override
            public void run() {
                try {
                    client = new Socket("192.168.31.100", 10010);
                    InputStream inputStream = client.getInputStream();

                    while( true) {
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                        Msg getMsg = null;
                        if((getMsg = (Msg) objectInputStream.readObject())!= null){
                            Message message = Message.obtain();
                            message.what = 1;
                            message.obj = getMsg;
                            handler.sendMessage(message);
                        }else{
                            break;
                        }

                    }

                    //int len;
                    //while ((len = inputStream.read(buffer)) != -1) {
                     //   String data = new String(buffer, 0, len);
                     //   //发送到主线程中收到的数据
                     //   Message message = Message.obtain();
                     //   message.what = 1;
                     //   message.obj = data;
                     //   handler.sendMessage(message);
                    //}
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }}.start();
        send.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onClick(View view) {
                msgRecycleView.scrollToPosition(msgList.size() - 1);

                    if((outMsg = inputText.getText().toString()).length() >0){
                    final Msg sendMsg = new Msg(outMsg, Msg.TYPE_SEND, new Date(), 1, 2, Msg.noReceived, client.getLocalSocketAddress().toString());
                    msgList.add(sendMsg);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    msgRecycleView.scrollToPosition(msgList.size() - 1);
                    inputText.setText("");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                OutputStream outputStream = client.getOutputStream();
                                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                                objectOutputStream.writeObject(sendMsg);
                                objectOutputStream.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    handler = new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.what == 1) {
                                Msg getMsg = (Msg) msg.obj;
                                msgList.add(getMsg);
                                adapter.notifyItemInserted(msgList.size() - 1);
                                msgRecycleView.scrollToPosition(msgList.size() - 1);
                            }
                        }
//                        Msg msg1 = new Msg(getMsg,Msg.TYPE_RECEIVED);
//                        msgList.add(msg1);
//                        adapter.notifyItemInserted(msgList.size()-1);
//                        msgRecycleView.scrollToPosition(msgList.size()-1);
                    };
                } else{
                    Toast.makeText(getBaseContext(),"发送信息不可为空！",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public void sendMsg(String outMsg){
        try {
            out.write(outMsg);
            out.flush();
            client.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
