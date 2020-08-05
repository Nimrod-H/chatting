package com.coolweather.chatting.socket;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyClass {
    public static void main(String[] args) throws IOException, ParseException {
            ClientManager.startServer();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HHmm");
        Date nowTime = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Date lastTime = simpleDateFormat.parse("2020-08-03 2230");
        System.out.println(nowTime);
        System.out.println(lastTime);
        long between = (nowTime.getTime() - lastTime.getTime())/1000;
        System.out.println((int)between/60);
    }

}
