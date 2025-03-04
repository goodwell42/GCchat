package com.goodwell42.gcchat.aty;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.view.WindowManager;
import android.widget.Toast;

import com.goodwell42.gcchat.R;
import com.goodwell42.gcchat.adapter.AdapterMainViewPager;
import com.goodwell42.gcchat.util.ImageManager;
import com.goodwell42.gcchat.view.LayoutChats;
import com.goodwell42.gcchat.view.LayoutContacts;
import com.goodwell42.gcchat.view.LayoutMoments;

import java.util.ArrayList;
import java.util.List;

public class AtyMain extends AppCompatActivity {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<TabLayout.Tab> tabList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.aty_main);

        initViews();

        // 显示欢迎用户名
        Intent intent = getIntent();
        String userName = intent.getStringExtra("param1");
        Toast.makeText(this, "Halo, my Lord. " + userName,
                Toast.LENGTH_SHORT).show();
        //String data = getResources().getString(R.string.user_name);
        //String disData = String.format(data, userName);
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.vp_main);
        tabLayout = (TabLayout) findViewById(R.id.tl_main);

        tabList = new ArrayList<>();

        AdapterMainViewPager adapter = new AdapterMainViewPager(getSupportFragmentManager());

        adapter.addFragment(new LayoutChats());
        adapter.addFragment(new LayoutContacts());
        adapter.addFragment(new LayoutMoments());

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        tabList.add(tabLayout.getTabAt(0));
        tabList.add(tabLayout.getTabAt(1));
        tabList.add(tabLayout.getTabAt(2));
        tabList.get(0).setIcon(R.drawable.msgselected).setText("Chats");
        tabList.get(1).setIcon(R.drawable.contactsunselected).setText("Contacts");
        tabList.get(2).setIcon(R.drawable.momentunselected).setText("Moments");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabList.get(tab.getPosition()).setIcon(ImageManager.imageID[tab.getPosition() + 3]);
                tabLayout.setTabTextColors(
                        ContextCompat.getColor(AtyMain.this, R.color.colorBlack),
                        ContextCompat.getColor(AtyMain.this, R.color.colorBlue)
                );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabList.get(tab.getPosition()).setIcon(ImageManager.imageID[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public static void actionStart(Context context, String data1) {
        Intent intent = new Intent(context, AtyMain.class);
        intent.putExtra("param1", data1);
        context.startActivity(intent);
    }
}
