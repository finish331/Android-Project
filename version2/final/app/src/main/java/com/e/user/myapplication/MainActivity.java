package com.e.user.myapplication;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final int MENU_MONTH1 = Menu.FIRST,
            MENU_MONTH2 = Menu.FIRST + 1,
            MENU_MONTH3 = Menu.FIRST + 2,
            MENU_PLAYGAME = Menu.FIRST + 3;

    public int type = 1;
    private android.app.FragmentManager fragmentManager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public Bundle getMyData() {
        Bundle hm = new Bundle();
        hm.putInt("type",type);
        return hm;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,MENU_MONTH1,1,"1-2月").setIcon(android.R.drawable.ic_dialog_info);
        menu.add(0,MENU_MONTH2,1,"3-4月").setIcon(android.R.drawable.ic_dialog_info);
        menu.add(0,MENU_MONTH3,1,"1~4月一起兌").setIcon(android.R.drawable.ic_dialog_info);
        menu.add(0,MENU_PLAYGAME,2,"玩個小遊戲").setIcon(android.R.drawable.ic_menu_close_clear_cancel);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case MENU_MONTH1:
                type = 0;
                return true;
            case MENU_MONTH2:
                type = 1;
                return true;
            case MENU_MONTH3:
                type = 2;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position){
            Fragment fragment = null;

            switch (position){
                case  0:
                    fragment = new first();
                    break;
                case 1:
                    fragment = new second();
                    break;
                case 2:
                    fragment = new third();
                    break;
            }
            return  fragment;
        }
        @Override
        public int getCount() {
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "兌換發票";
                case 1:
                    return "3~4月中獎紀錄";
                case 2:
                    return "1~2月中獎紀錄";
                default:
                    return null;
            }
        }
    }
}
