package com.example.admin.smartindia.Activities;

import android.app.Activity;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.admin.smartindia.Adapters.PendingMediclaimAdapter;
import com.example.admin.smartindia.Models.PendingMediclaimData;
import com.example.admin.smartindia.R;

import java.util.ArrayList;
import java.util.List;

public class PendingMediclaimActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PendingMediclaimAdapter adapter;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_mediclaim);

        coordinatorLayout= (CoordinatorLayout) findViewById(R.id.pending_mediclaim_coordinator_layout);
        recyclerView= (RecyclerView) findViewById(R.id.pending_mediclaim_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter=new PendingMediclaimAdapter(this,getData(),coordinatorLayout);
        recyclerView.setAdapter(adapter);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Pending MediClaims");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private List<PendingMediclaimData> getData() {
        ArrayList<PendingMediclaimData> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add(new PendingMediclaimData("Hospital Name","18-03-2017","20000"));
        }
        return list;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
