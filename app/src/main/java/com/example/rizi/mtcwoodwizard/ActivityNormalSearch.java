package com.example.rizi.mtcwoodwizard;

import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rizi.mtcwoodwizard.AdvanceSearch.ActivityAdvanceSearch;
import com.example.rizi.mtcwoodwizard.fragments.FragmentAlphabatical;
import com.example.rizi.mtcwoodwizard.fragments.FragmentTextSearch;

import co.ceryle.segmentedbutton.SegmentedButtonGroup;

public class ActivityNormalSearch extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTrans=fragmentManager.beginTransaction();
        FragmentTextSearch ft=new FragmentTextSearch();
        fragmentTrans.replace(R.id.fragPlace,ft);
        fragmentTrans.commit();

        SegmentedButtonGroup sbg=(SegmentedButtonGroup)findViewById(R.id.sbgSellerDetails);
        sbg.setOnClickedButtonPosition(new SegmentedButtonGroup.OnClickedButtonPosition() {
            @Override
            public void onClickedButtonPosition(int position) {
                if(position==0){
                    FragmentManager fragmentManager= getFragmentManager();
                    FragmentTransaction fragmentTrans=fragmentManager.beginTransaction();
                    FragmentTextSearch ft=new FragmentTextSearch();
                    fragmentTrans.replace(R.id.fragPlace,ft);
                    fragmentTrans.commit();


                }else{
                    FragmentManager fragmentManager= getFragmentManager();
                    FragmentTransaction fragmentTrans=fragmentManager.beginTransaction();
                    FragmentAlphabatical ft2=new FragmentAlphabatical();
                    fragmentTrans.replace(R.id.fragPlace,ft2);
                    fragmentTrans.commit();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Toast.makeText(this, "No instructions for HOME implementation", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_normal_search) {

        } else if (id == R.id.nav_advance_search) {
           Intent i= new Intent(this, ActivityAdvanceSearch.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}