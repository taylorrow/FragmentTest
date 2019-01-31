package com.midigame.fragmenttest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.midigame.fragmenttest.fragment_1.FragmentOne;
import com.midigame.fragmenttest.fragment_2.FragmentTwo;

public class MainActivity extends AppCompatActivity {

    public static MainActivity instance;
    private TabLayout allTabs;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private boolean isTabPosition = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        getAllWidgets();
        bindWidgetsWithAnEvent();
        setupTabLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupTabSelected();
    }

    private void getAllWidgets() {
        allTabs = (TabLayout) findViewById(R.id.tabs);
    }

    private void setupTabLayout() {
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        allTabs.addTab(allTabs.newTab().setText("TAB 1"), true);
        allTabs.addTab(allTabs.newTab().setText("TAB 2"));
    }

    private void setupTabSelected() {
        if (isTabPosition) {
            allTabs.getTabAt(0).select();
        } else {
            allTabs.getTabAt(1).select();
        }
    }

    private void bindWidgetsWithAnEvent() {
        allTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setCurrentTabFragment(int tabPosition) {
        switch (tabPosition) {
            case 0:
                isTabPosition = true;
                replaceFragment(fragmentOne);
                break;
            case 1:
                isTabPosition = false;
                replaceFragment(fragmentTwo);
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("tabPosition", isTabPosition);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isTabPosition = savedInstanceState.getBoolean("tabPosition");
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();

    }
}
