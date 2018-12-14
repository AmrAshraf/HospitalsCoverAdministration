package com.hospitalscoveradministration.View;

import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;

import com.hospitalscoveradministration.R;

public class HomeScreenAdmin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ReservationsFragment reservationsFragment=new ReservationsFragment();
        addFragment(reservationsFragment,reservationsFragment.reservationsTag);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        }
        popFragment();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.basic_data) {
            BasicDataFragment  basicDataFragment=new BasicDataFragment();
            addFragment(basicDataFragment,basicDataFragment.basicDataTag);
        } else if (id == R.id.resources) {
            ResourcesFragment resourcesFragment=new ResourcesFragment();
            addFragment(resourcesFragment,resourcesFragment.resourcesTag);
        } else if (id == R.id.reservations) {
            ReservationsFragment reservationsFragment=new ReservationsFragment();
            addFragment(reservationsFragment,reservationsFragment.reservationsTag);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addFragment(Fragment fragment, String tag)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.navigationFragment,fragment)
        .commit();
    }
    private void popFragment()
    {
        getSupportFragmentManager().popBackStackImmediate();
        if(getSupportFragmentManager().getBackStackEntryCount()==0)
            finish();
    }


}
