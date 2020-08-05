package com.coolweather.chatting.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.coolweather.chatting.entity.Msg;
import com.coolweather.chatting.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MsgAdapter  extends RecyclerView.Adapter<MsgAdapter.ViewHolder1>{
    private List<Msg> msgList;
    static  class ViewHolder1 extends RecyclerView.ViewHolder{
        RelativeLayout leftLayout;
        RelativeLayout rightLayout;
        TextView leftText;
        TextView rightText;
        TextView timeText;
        ImageView leftImage;
        ImageView rightImage;
        CardView timeCardView;
        public ViewHolder1(@NonNull View view) {
            super(view);
            timeText =view.findViewById(R.id.time_text);
            leftImage = view.findViewById(R.id.left_image);
            leftLayout = view.findViewById(R.id.left_layout);
            leftText = view.findViewById(R.id.left_text);
            rightLayout = view.findViewById(R.id.right_layout);
            rightText = view.findViewById(R.id.right_text);
            rightImage = view.findViewById(R.id.right_image);
            timeCardView = view.findViewById(R.id.timeCardView);
        }
    }

    public MsgAdapter(List<Msg> msgList) {
        this.msgList = msgList;
    }


    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);

        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position) {
        Msg msg = msgList.get(position);

        Calendar calendar = Calendar.getInstance();
        int mDay  = calendar.get(Calendar.DAY_OF_MONTH);
        int mHour  = calendar.get(Calendar.HOUR);
        int mMin = calendar.get(Calendar.MINUTE);
        int apm = calendar.get(Calendar.AM_PM);
        int intervalTime = 0;
        if(apm == 0){
        holder.timeText.setText("上午"+Integer.toString(mHour)+":"+Integer.toString(mMin) );
        }
        if(apm == 1){
            holder.timeText.setText("下午"+Integer.toString(mHour)+":"+Integer.toString(mMin) );
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm" );

        if(position >= 1) {
            Msg lastMsg = msgList.get(position - 1);

            try {
                Date nowTime = simpleDateFormat.parse(simpleDateFormat.format(msg.getDate()));              //先把日期格式化，再转成对应的Date进行运算
                Date lastTime = simpleDateFormat.parse(simpleDateFormat.format(lastMsg.getDate()));         //simpleDateFormat可以把Date数据转为想要的样子让后再进行操作
                long nowDateTime = nowTime.getTime();
                long lastDateTime = lastTime.getTime();
                intervalTime = (int)(nowDateTime - lastDateTime) / (1000 * 60);
                holder.timeCardView.setVisibility(View.VISIBLE);
                if (intervalTime < 5  ){
                    holder.timeCardView.setVisibility(View.GONE);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(msg.getType() == Msg.TYPE_RECEIVED){
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftText.setText(msg.getContent());

        }else if(msg.getType() == Msg.TYPE_SEND){
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightText.setText(msg.getContent());

        }
    }



    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
