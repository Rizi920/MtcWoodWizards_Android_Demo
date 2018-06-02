package com.example.rizi.mtcwoodwizard.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rizi.mtcwoodwizard.ActivitySellerDetails;
import com.example.rizi.mtcwoodwizard.Adapters.AdapterNormalSearch;
import com.example.rizi.mtcwoodwizard.Models.ModelCompanyInfo;
import com.example.rizi.mtcwoodwizard.R;
import com.example.rizi.mtcwoodwizard.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class FragmentAlphabatical extends Fragment {
    ArrayList<ModelCompanyInfo> companyInfo = new ArrayList<>();
    protected RecyclerView mRecyclerView;
    AdapterNormalSearch myAdapter;


    public FragmentAlphabatical() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView=(RecyclerView) getView().findViewById(R.id.rvAlphabatical);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fillData();
        if (companyInfo.size() > 0) {
            Collections.sort(companyInfo, new Comparator<ModelCompanyInfo>() {
                @Override
                public int compare(final ModelCompanyInfo object1, final ModelCompanyInfo object2) {
                    return object1.getName().compareTo(object2.getName());
                }
            });
        }
        myAdapter=new AdapterNormalSearch(getActivity(),companyInfo);
        mRecyclerView.setAdapter(myAdapter);
        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.horizontal_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        mRecyclerView.addItemDecoration(horizontalDecoration);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i=new Intent(getActivity(),ActivitySellerDetails.class);
                        i.putExtra("title",companyInfo.get(position).getName());
                        i.putExtra("desc",companyInfo.get(position).getDescription());
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Toast.makeText(getActivity(), "Long Pressed", Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_alphabatical, container, false);

        return v;

    }



    public void fillData(){
        companyInfo.clear();
        ModelCompanyInfo model= new ModelCompanyInfo();
        try {
            JSONObject Categories_obj = new JSONObject(loadJSONFromAsset());
            JSONArray categories_jArry = Categories_obj.getJSONArray("categories");

            for (int i = 0; i < categories_jArry.length(); i++) {

                JSONObject jo_inside = categories_jArry.getJSONObject(i);
                JSONArray child_Array = jo_inside.getJSONArray("childern");

                for (int j = 0; j < child_Array.length(); j++) {

                    JSONObject child2 = child_Array.getJSONObject(j);


                    JSONArray companyInfoArray = child2.getJSONArray("Items");


                    for (int k = 0; k < companyInfoArray.length(); k++) {
                        JSONObject comp_info = companyInfoArray.getJSONObject(k);

                        Log.d("Details-->", comp_info.getString("title"));
                        String Main_title = comp_info.getString("title");
                        int sellerId=comp_info.getInt("id");
                        String description = comp_info.getString("description");
                        model = new ModelCompanyInfo();
                        model.setName(Main_title);
                        model.setId(sellerId);
                        model.setDescription(description);
                        companyInfo.add(model);


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
            InputStream is = getActivity().getAssets().open("data.json");
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
