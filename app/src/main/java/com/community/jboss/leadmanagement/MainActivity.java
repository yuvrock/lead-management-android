package com.community.jboss.leadmanagement;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.community.jboss.leadmanagement.fragment.AboutUsFragment;
import com.community.jboss.leadmanagement.fragment.ContactsFragment;
import com.community.jboss.leadmanagement.fragment.GroupsFragment;
import com.community.jboss.leadmanagement.fragment.HomeFragment;
import com.community.jboss.leadmanagement.fragment.PrivacyPolicyFragment;
import com.community.jboss.leadmanagement.fragment.SettingsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    private final int ID = 512;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionManager permissionManager = new PermissionManager(this,this);
        if(!permissionManager.permissionStatus(Manifest.permission.READ_PHONE_STATE)){
            permissionManager.requestPermission(ID,Manifest.permission.READ_PHONE_STATE);
        }

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        ButterKnife.bind(this);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        ButterKnife.bind(this);
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame,homeFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_gallery) {
            ContactsFragment contactsFragment = new ContactsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame,contactsFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_slideshow) {
            GroupsFragment groupsFragment = new GroupsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame,groupsFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_manage) {
            SettingsFragment settingsFragment = new SettingsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame,settingsFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_share) {
            AboutUsFragment aboutUsFragment = new AboutUsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame,aboutUsFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_send) {
            PrivacyPolicyFragment privacyPolicyFragment = new PrivacyPolicyFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame,privacyPolicyFragment)
                    .addToBackStack(null)
                    .commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
