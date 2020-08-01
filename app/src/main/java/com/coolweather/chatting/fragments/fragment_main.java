package com.coolweather.chatting.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coolweather.chatting.MainActivity;
import com.coolweather.chatting.Msg;
import com.coolweather.chatting.R;
import com.coolweather.chatting.adapters.MessagewAdapter;
import com.coolweather.chatting.chatiingActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

public class fragment_main extends Fragment {
    //在此处设定对各种组件的初始化等操作
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    ImageView messageImageView;
    TextView messageTime;
    TextView messageName;
    TextView messageContent;
    List<Msg> msgList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.main_layout,container,false);
        recyclerView = view.findViewById(R.id.message_recycle_view);
        messageImageView = view.findViewById(R.id.message_face);
        messageName = view.findViewById(R.id.message_name);
        messageContent = view.findViewById(R.id.message_content);
        messageTime = view.findViewById(R.id.message_time);
        for(int i  = 0;i<50;i++){
            msgList.add(new Msg("好友"+ i +"发来了信息",Msg.TYPE_SEND,new Date(),1,2,Msg.noReceived,"1234456"));
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        MessagewAdapter messagewAdapter = new MessagewAdapter(msgList);
        recyclerView.setAdapter(messagewAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;

    }
}
