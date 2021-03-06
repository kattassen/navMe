package com.example.simfur.navme;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class TabsActivity extends FragmentActivity
        implements ActionBar.TabListener, RouteListFragment.OnFragmentInteractionListener {

    private ViewPager viewPager;
    private final String[] tabs = {"Route List", "Navigate"};
    private TabsPagerAdapter mAdapter;

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        ActionBar actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
    }

    public void onFragmentInteraction(String filename){
        /* Switch to route tab and send string with file name */
        getActionBar().setSelectedNavigationItem(1);
        ((NavFragment)(mAdapter.getFragment(1))).routeSelected(this, filename);
    }
}
