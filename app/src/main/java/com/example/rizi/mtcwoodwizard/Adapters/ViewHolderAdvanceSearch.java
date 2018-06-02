package com.example.rizi.mtcwoodwizard.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rizi.mtcwoodwizard.R;

/**
 * Created by Rizi on 31/08/2017.
 */

public class ViewHolderAdvanceSearch extends RecyclerView.ViewHolder {
    TextView title;

    public ViewHolderAdvanceSearch(View ItemView) {
        super(ItemView);
        title= (TextView) itemView.findViewById(R.id.tvNameAdvance);


    }

}


