package com.example.rizi.mtcwoodwizard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rizi.mtcwoodwizard.Models.ModelCompanyInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ActivitySellerDetails extends AppCompatActivity {

    TextView TVtitle,desc1,windowTitle;
    ImageView im;
    ArrayList<ModelCompanyInfo> companyInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_details);

        TVtitle=(TextView)findViewById(R.id.tvSellerName);
        windowTitle=(TextView)findViewById(R.id.tvTitleDetails);
        desc1=(TextView)findViewById(R.id.tvSummaryDetails);
        desc1.setMovementMethod(new ScrollingMovementMethod());
        im=(ImageView) findViewById(R.id.ivNavBack);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String title=getIntent().getStringExtra("title");
        String desc=getIntent().getStringExtra("desc");
        TVtitle.setText(title);
        windowTitle.setText(title);
        desc1.setText(desc);


    }


}
