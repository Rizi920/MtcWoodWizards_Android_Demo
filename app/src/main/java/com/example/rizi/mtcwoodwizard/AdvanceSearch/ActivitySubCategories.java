package com.example.rizi.mtcwoodwizard.AdvanceSearch;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rizi.mtcwoodwizard.ActivitySellerDetails;
import com.example.rizi.mtcwoodwizard.Adapters.AdapterAdvanceSearch;
import com.example.rizi.mtcwoodwizard.Adapters.AdapterNormalSearch;
import com.example.rizi.mtcwoodwizard.Models.ModelCategories;
import com.example.rizi.mtcwoodwizard.Models.ModelCompanyInfo;
import com.example.rizi.mtcwoodwizard.R;
import com.example.rizi.mtcwoodwizard.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ActivitySubCategories extends AppCompatActivity {

    TextView titleSubCat;
    ImageView im;
    AdapterNormalSearch myAdapternormalSearch;
    ArrayList<ModelCategories> categories = new ArrayList<>();
    protected RecyclerView mRecyclerView;
    AdapterAdvanceSearch myAdapter;
    String id;
    ArrayList<ModelCompanyInfo> companyInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categories);

        id=getIntent().getStringExtra("id");
        String title=getIntent().getStringExtra("title");

        titleSubCat=(TextView) findViewById(R.id.tvTitleDetails);
        titleSubCat.setText(title);

        im=(ImageView)findViewById(R.id.ivNavBack);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mRecyclerView=(RecyclerView)findViewById(R.id.rvSubCategories);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LocateSubCAtegories();
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

                        if(categories.get(position).getIsParent().equalsIgnoreCase("true")) {

                            titleSubCat.setText(categories.get(position).getTitle());
                            LocateGrandChildCategories(categories.get(position).getId());
                            myAdapter=new AdapterAdvanceSearch(ActivitySubCategories.this,categories);
                            mRecyclerView.setAdapter(myAdapter);

                        }else if(categories.get(position).getIsParent().equalsIgnoreCase("false")){

                            titleSubCat.setText(categories.get(position).getTitle());
                            LocateProfiles(categories.get(position).getId());
                            myAdapternormalSearch=new AdapterNormalSearch(ActivitySubCategories.this,companyInfo);
                            mRecyclerView.setAdapter(myAdapternormalSearch);


                        }else if(categories.get(position).getIsParent().equalsIgnoreCase("rizi")){
                            Intent i=new Intent(ActivitySubCategories.this,ActivitySellerDetails.class);
                            i.putExtra("title",companyInfo.get(position).getName());
                            i.putExtra("desc",companyInfo.get(position).getDescription());
                            startActivity(i);

                        }


                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Toast.makeText(ActivitySubCategories.this, "Long Pressed! "+position, Toast.LENGTH_SHORT).show();
                    }
                })
        );



    }

    public void LocateSubCAtegories(){
        categories.clear();
        ModelCategories model= new ModelCategories();
        try {
            JSONObject Categories_obj = new JSONObject(loadJSONFromAsset());
            JSONArray categories_jArry = Categories_obj.getJSONArray("categories");

            for (int i = 0; i < categories_jArry.length(); i++) {

                JSONObject jo_inside = categories_jArry.getJSONObject(i);
                String idjson=jo_inside.getString("id");

                if(id.equalsIgnoreCase(idjson)){
                    JSONArray child_Array = jo_inside.getJSONArray("childern");
                    for (int j = 0; j < child_Array.length(); j++) {
                        JSONObject subcat=child_Array.getJSONObject(j);

                        String subID=subcat.getString("id");
                        String subTitle=subcat.getString("title");
                        String isParent=subcat.getString("isParent");

                        model = new ModelCategories();
                        model.setTitle(subTitle);
                        model.setId(subID);
                        model.setIsParent(isParent);
                        categories.add(model);

                    }

                }


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void LocateGrandChildCategories(String grandid){

        categories.clear();
        ModelCategories model= new ModelCategories();

        try {
            JSONObject Categories_obj = new JSONObject(loadJSONFromAsset());
            JSONArray categories_jArry = Categories_obj.getJSONArray("categories");

            for (int i = 0; i < categories_jArry.length(); i++) {

                JSONObject jo_inside = categories_jArry.getJSONObject(i);
                    JSONArray child_Array = jo_inside.getJSONArray("childern");

                    for (int j = 0; j < child_Array.length(); j++) {
                        JSONObject subcat=child_Array.getJSONObject(j);
                        String subID=subcat.getString("id");

                        if(grandid.equalsIgnoreCase(subID)) {
                            JSONArray child2Array = subcat.getJSONArray("childern");

                            for(int k=0;k<child2Array.length();k++){
                                JSONObject grandchild=child2Array.getJSONObject(k);

                                String subID2=grandchild.getString("id");
                                String subTitle=grandchild.getString("title");
                                String isParent=grandchild.getString("isParent");

                        model = new ModelCategories();
                        model.setTitle(subTitle);
                        model.setId(subID2);
                        model.setIsParent(isParent);
                        categories.add(model);
                            }
                        }
                    }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }




    }


    public void LocateProfiles(String grandId){
        companyInfo.clear();
        ModelCompanyInfo modelCompanyInfo= new ModelCompanyInfo();
        categories.clear();
        ModelCategories model= new ModelCategories();

        try {
            JSONObject Categories_obj = new JSONObject(loadJSONFromAsset());
            JSONArray categories_jArry = Categories_obj.getJSONArray("categories");

            for (int i = 0; i < categories_jArry.length(); i++) {

                JSONObject jo_inside = categories_jArry.getJSONObject(i);
                JSONArray child_Array = jo_inside.getJSONArray("childern");

                for (int j = 0; j < child_Array.length(); j++) {
                    JSONObject subcat=child_Array.getJSONObject(j);
                    String subID=subcat.getString("id");

                    if(grandId.equalsIgnoreCase(subID)) {
                        JSONArray child2Array = subcat.getJSONArray("Items");

                        for(int k=0;k<child2Array.length();k++){
                            JSONObject grandchild=child2Array.getJSONObject(k);

                            String subID2=grandchild.getString("id");
                            String subTitle=grandchild.getString("title");
                            String desc=grandchild.getString("description");

                            model=new ModelCategories();
                            model.setIsParent("rizi");
                            categories.add(model);


                            modelCompanyInfo = new ModelCompanyInfo();
                            modelCompanyInfo.setId(Integer.parseInt(subID2));
                            modelCompanyInfo.setName(subTitle);
                            modelCompanyInfo.setDescription(desc);
                            companyInfo.add(modelCompanyInfo);
                        }
                    }




                }




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
