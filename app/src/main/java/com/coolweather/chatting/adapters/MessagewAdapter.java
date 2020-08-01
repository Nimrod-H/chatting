package com.coolweather.chatting.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coolweather.chatting.Msg;
import com.coolweather.chatting.R;

import java.util.List;

public class MessagewAdapter extends RecyclerView.Adapter<MessagewAdapter.ViewHolder> {
    private List<Msg> msgList;

    public MessagewAdapter(List<Msg> msgList) {
        this.msgList = msgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messge_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Msg msg = msgList.get(position);
            if(position < 10) {
                holder.messageTime.setText("上午10:0" + position);
            }else{
                holder.messageTime.setText("上午10:" + position);
            }
            holder.messageContent.setText(msg.getContent());
            holder.messageName.setText("朋友"+position);
            if(position/2 == 0){
                holder.messageImageView.setImageResource(R.drawable.girl);
            }

    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        ImageView messageImageView;
        TextView messageTime;
        TextView messageName;
        TextView messageContent;
        public ViewHolder(@NonNull View view) {
            super(view);
            relativeLayout = view.findViewById(R.id.message_recycle_view);
            messageImageView = view.findViewById(R.id.message_face);
            messageName = view.findViewById(R.id.message_name);
            messageContent = view.findViewById(R.id.message_content);
            messageTime = view.findViewById(R.id.message_time);
        }
    }



}
