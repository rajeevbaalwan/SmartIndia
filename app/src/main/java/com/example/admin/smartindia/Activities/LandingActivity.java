package com.example.admin.smartindia.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.admin.smartindia.Adapters.LandingAdapter;
import com.example.admin.smartindia.Models.UserCurrentMedicalData;
import com.example.admin.smartindia.R;

import java.util.ArrayList;
import java.util.List;

public class LandingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LandingAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new LandingAdapter(this,getData());
        recyclerView.setAdapter(adapter);
    }

    private List<UserCurrentMedicalData> getData() {
        ArrayList list= new ArrayList<>();
        for(int i=0;i<20;i++){
            list.add(new UserCurrentMedicalData("item"+(i+1),"Food"+(i+1)));
        }
        return list;
    }


}
