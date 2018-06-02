package com.example.rizi.mtcwoodwizard.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rizi.mtcwoodwizard.R;




public class ViewHolderNormalSearch extends RecyclerView.ViewHolder {


    TextView name,description;

    public ViewHolderNormalSearch(View ItemView) {
        super(ItemView);
        name= (TextView) itemView.findViewById(R.id.tvName);
        description=(TextView) itemView.findViewById(R.id.tvDescription);

    }
}
