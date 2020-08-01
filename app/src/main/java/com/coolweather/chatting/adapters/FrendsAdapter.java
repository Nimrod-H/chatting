package com.coolweather.chatting.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.coolweather.chatting.Msg;
import com.coolweather.chatting.R;

import java.util.List;

public class FrendsAdapter extends ArrayAdapter<Msg> {
    private final  int Imageid;             //自定义子项的id
    public FrendsAdapter(Context context, int resource, List<Msg> objects) {
        super(context, resource,objects);
        Imageid = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            Msg msg =getItem(position);         //获取当前实例
            View view = LayoutInflater.from(getContext()).inflate(Imageid,parent,false);    //装载view
            ImageView imageView = view.findViewById(R.id.frends_face);
            TextView  textView = view.findViewById(R.id.frends_name);
            textView.setText(msg.getContent());
            return view;                    //返回设置完的view
    }
}
