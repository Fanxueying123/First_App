package com.example.myapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.myapplication.Fragments.FourFragment;
import com.example.myapplication.Fragments.OneFragment;
import com.example.myapplication.Fragments.ThreeFragment;
import com.example.myapplication.Fragments.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CallBackValue,BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar bottomNavigationBar;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationBar= (BottomNavigationBar) findViewById(R.id.bt_nav_bar);
        init();
        Intent intent=getIntent();
        Toast.makeText(this,"欢迎 "+intent.getStringExtra("user")+" 使用",Toast.LENGTH_SHORT).show();
        int number=intent.getIntExtra("backValue",0);
        if(number==1){
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.main_container,fragments.get(3));
            bottomNavigationBar.selectTab(3);
            ft.commit();
        }
    }

    private void init() {
        fragments=new ArrayList<>();
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
        fragments.add(new FourFragment());

        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.nav_circle_selector,"动态"))
                .addItem(new BottomNavigationItem(R.drawable.nav_find_selector,"发现"))
                .addItem(new BottomNavigationItem(R.drawable.nav_me_selector,"我的"))
                .addItem(new BottomNavigationItem(R.drawable.nav_message_selector,"信息"))
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                .setFirstSelectedPosition(1)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.main_container,fragments.get(1));
        ft.commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.main_container,fragments.get(position));
        ft.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void sendMessageValue(String strValue) {
        if(strValue=="two")
            bottomNavigationBar.selectTab(2);
    }
}
