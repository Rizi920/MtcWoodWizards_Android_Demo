package com.example.rizi.mtcwoodwizard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rizi.mtcwoodwizard.Models.ModelCategories;
import com.example.rizi.mtcwoodwizard.Models.ModelCompanyInfo;
import com.example.rizi.mtcwoodwizard.R;

import java.util.ArrayList;

/**
 * Created by Rizi on 31/08/2017.
 */

public class AdapterAdvanceSearch extends RecyclerView.Adapter<ViewHolderAdvanceSearch>{

    Context context;
    ArrayList<ModelCategories> categories;

    public AdapterAdvanceSearch(Context c, ArrayList<ModelCategories> m){
        this.context=c;
        this.categories=m;
    }

    @Override
    public ViewHolderAdvanceSearch onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.advance_search_list_items,parent,false);
        return new ViewHolderAdvanceSearch(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderAdvanceSearch holder, int position) {

        holder.title.setText(categories.get(position).getTitle());



    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
