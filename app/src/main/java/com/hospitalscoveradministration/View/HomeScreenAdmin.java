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
import android.view.View;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.hospitalscoveradministration.Model.User;
import com.hospitalscoveradministration.R;

public class HomeScreenAdmin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public TextView hospital_name_nav;
    public TextView hospital_email_nav;

    public static User currentUser=null;

    void subscribeToTopic(String hospitalID) {
        FirebaseMessaging.getInstance().subscribeToTopic(hospitalID);
    }
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
        View hView =  navigationView.getHeaderView(0);
        View hView2 =  navigationView.getHeaderView(1);
        hospital_name_nav = (TextView)hView.findViewById(R.id.hospital_name_nav);

        hospital_email_nav=(TextView)hView.findViewById(R.id.hospital_email_nav);

        ReservationsFragment reservationsFragment = new ReservationsFragment();
        addFragment(reservationsFragment, reservationsFragment.reservationsTag);





        if(currentUser==null)
            currentUser = (User) getIntent().getSerializableExtra("user");

        subscribeToTopic(currentUser.getData().id);
        hospital_name_nav.setText(currentUser.getData().name);
        hospital_email_nav.setText(currentUser.getData().name + "@gmail.com");

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

       if (id == R.id.resources) {
            ResourcesFragment resourcesFragment = new ResourcesFragment();
            addFragment(resourcesFragment, resourcesFragment.resourcesTag);
        } else if (id == R.id.reservations) {
            ReservationsFragment reservationsFragment = new ReservationsFragment();
            addFragment(reservationsFragment, reservationsFragment.reservationsTag);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.navigationFragment, fragment)
                .commit();
    }

    private void popFragment() {
        getSupportFragmentManager().popBackStackImmediate();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            finish();
    }


}
