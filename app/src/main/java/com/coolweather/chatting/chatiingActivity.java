package com.coolweather.chatting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.coolweather.chatting.adapters.MsgAdapter;

import java.util.ArrayList;
import java.util.List;

public class chatiingActivity extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecycleView;
    private MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_layout);
        initMsgs();
        msgRecycleView = findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecycleView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecycleView.setAdapter(adapter);
    }
    public  void initMsgs(){
        Msg msg1 = new Msg("你好啊！",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("你好！",Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg4 = new Msg("你好！",Msg.TYPE_SEND);
        msgList.add(msg4);
        msgList.add(msg4);
        Msg msg3 = new Msg("我叫陈琛琛！",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
        Msg msg5 = new Msg("我叫呼呼呼！",Msg.TYPE_RECEIVED);
        msgList.add(msg5);
        msgList.add(new Msg("哈哈哈哈哈哈哈",Msg.TYPE_RECEIVED));
    }
}
