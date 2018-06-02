package com.example.rizi.mtcwoodwizard.AdvanceSearch;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.rizi.mtcwoodwizard.ActivityNormalSearch;
import com.example.rizi.mtcwoodwizard.Adapters.AdapterAdvanceSearch;
import com.example.rizi.mtcwoodwizard.Models.ModelCategories;
import com.example.rizi.mtcwoodwizard.R;
import com.example.rizi.mtcwoodwizard.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ActivityAdvanceSearch extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ArrayList<ModelCategories> categories = new ArrayList<>();
    protected RecyclerView mRecyclerView;
    AdapterAdvanceSearch myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView=(RecyclerView)findViewById(R.id.rvCategories);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        fillData();
        myAdapter=new AdapterAdvanceSearch(this,categories);
        mRecyclerView.setAdapter(myAdapter);
        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(this, R.drawable.horizontal_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        mRecyclerView.addItemDecoration(horizontalDecoration);



        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i=new Intent(ActivityAdvanceSearch.this,ActivitySubCategories.class);
                        i.putExtra("id",  categories.get(position).getId());
                        i.putExtra("title",categories.get(position).getTitle());
                        startActivity(i);



                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Toast.makeText(ActivityAdvanceSearch.this, "Long Pressed "+position, Toast.LENGTH_SHORT).show();
                    }
                })
        );


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

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


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
            Intent i= new Intent(this, ActivityNormalSearch.class);
            startActivity(i);
        } else if (id == R.id.nav_advance_search) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void fillData(){
        categories.clear();
        ModelCategories model= new ModelCategories();
        try {
            JSONObject Categories_obj = new JSONObject(loadJSONFromAsset());
            JSONArray categories_jArry = Categories_obj.getJSONArray("categories");

            for (int i = 0; i < categories_jArry.length(); i++) {

                JSONObject jo_inside = categories_jArry.getJSONObject(i);
                String title=jo_inside.getString("title");
                String id=jo_inside.getString("id");
                model = new ModelCategories();
                model.setTitle(title);
                model.setId(id);
                categories.add(model);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }




    }
    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = this.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
