package com.coolweather.chatting.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.coolweather.chatting.fragments.fragment_main;
import com.coolweather.chatting.fragments.fragment_message;
import com.coolweather.chatting.fragments.fragment_my;

//此处是碎片的构造器
public class fragmentAdapter extends FragmentPagerAdapter {
    @NonNull
    private final int PAGER_COUNT = 3;
    private fragment_main fragment_main = null;
    private fragment_my  fragment_my = null;
    private fragment_message fragment_message = null;

    ///构造器初始化
    public fragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragment_message = new fragment_message();
        fragment_my = new fragment_my();
        fragment_main = new fragment_main();
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment= fragment_main;
                break;
            case 1:
                fragment = fragment_message;
                break;
            case 2:
                fragment = fragment_my;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
