package com.coolweather.chatting.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.coolweather.chatting.R;
import com.coolweather.chatting.adapters.fragmentAdapter;
import com.coolweather.chatting.socket.ClientManager;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{
    private RadioButton rb_message;
    private ViewPager vpager;
    private RadioGroup rg_tab_bar;
    private fragmentAdapter mFragmentAdapter;
    private RadioButton rb_my;
    private RadioButton rb_main;
    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        LitePal.getDatabase();
        mFragmentAdapter = new fragmentAdapter(getSupportFragmentManager());
        initView();
        rg_tab_bar.setOnCheckedChangeListener(this);
        vpager.setOnPageChangeListener(this);

    }
    public void initView(){
        rb_main = findViewById(R.id.button_message);
        rb_message = findViewById(R.id.button_frends);
        rb_my = findViewById(R.id.button_my);
        vpager = findViewById(R.id.vpager);
        vpager.setAdapter(mFragmentAdapter);
        rg_tab_bar = findViewById(R.id.rb_button);
        rb_main.setChecked(true);
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.button_frends:
            vpager.setCurrentItem(PAGE_TWO);
                Toast.makeText(this, "frendsbuttom", Toast.LENGTH_LONG).show();
            break;
            case R.id.button_message:
                vpager.setCurrentItem(PAGE_ONE);
                Toast.makeText(this, "messagesbuttom", Toast.LENGTH_LONG).show();
                break;
            case R.id.button_my:
                vpager.setCurrentItem(PAGE_THREE);
                Toast.makeText(this, "mybuttom", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case PAGE_ONE:
                rb_main.setChecked(true);
                break;
            case PAGE_TWO:
                rb_message.setChecked(true);
                break;
            case PAGE_THREE:
                rb_my.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2) {
            switch (vpager.getCurrentItem()) {

            }
        }
    }
}
