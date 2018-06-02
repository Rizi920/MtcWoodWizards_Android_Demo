package com.example.rizi.mtcwoodwizard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rizi.mtcwoodwizard.Models.ModelCompanyInfo;
import com.example.rizi.mtcwoodwizard.R;

import java.util.ArrayList;



public class AdapterNormalSearch extends RecyclerView.Adapter<ViewHolderNormalSearch> {

    Context context;
    ArrayList<ModelCompanyInfo> companyInfo;

    public AdapterNormalSearch(Context c, ArrayList<ModelCompanyInfo> m){
        this.context=c;
        this.companyInfo=m;
    }

    @Override
    public ViewHolderNormalSearch onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.normal_search_list_items,parent,false);
        return new ViewHolderNormalSearch(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderNormalSearch holder, int position) {

        holder.name.setText(companyInfo.get(position).getName());
        holder.description.setText(companyInfo.get(position).getDescription());


    }

    @Override
    public int getItemCount() {
        return companyInfo.size();
    }
}
