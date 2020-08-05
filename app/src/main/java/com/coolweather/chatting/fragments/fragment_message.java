package com.coolweather.chatting.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.coolweather.chatting.entity.Msg;
import com.coolweather.chatting.R;
import com.coolweather.chatting.adapters.FrendsAdapter;
import com.coolweather.chatting.activity.chatiingActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class fragment_message extends Fragment {
    //在此处设定对各种组件的初始化等操作
    private ListView listView;
    private FrendsAdapter myAdapter;
    private List<Msg> msgList;
    private RelativeLayout relativeLayout;
    private Msg msg;
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.frends_laout,container,false);
        msgList = new ArrayList<Msg>();
        listView = view.findViewById(R.id.frends_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getActivity(),chatiingActivity.class);
                    startActivity(intent);
            }
        });
        for(int i = 1;i<50;i++) {
            msg = new Msg("朋友"+i,Msg.TYPE_RECEIVED,new Date(),1,2,Msg.noReceived,"123456456");
            msgList.add(msg);
        }
        myAdapter = new FrendsAdapter(getContext(),R.layout.frends_item,msgList);
        listView.setAdapter(myAdapter);

        return view;
    }
}
