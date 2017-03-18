package com.example.admin.smartindia.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.admin.smartindia.Adapters.CompletedMediclaimAdapter;
import com.example.admin.smartindia.Models.CompletedMediclaimData;
import com.example.admin.smartindia.R;

import java.util.ArrayList;
import java.util.List;

public class CompletedMediclaimActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CompletedMediclaimAdapter adapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_mediclaim);

        recyclerView= (RecyclerView) findViewById(R.id.completed_mediclaim_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter=new CompletedMediclaimAdapter(this,getData());
        recyclerView.setAdapter(adapter);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Completed MediClaims");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private List<CompletedMediclaimData> getData() {
        ArrayList<CompletedMediclaimData> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add(new CompletedMediclaimData("Hospital Name","Date","20000"));
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
