package com.example.admin.smartindia.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.admin.smartindia.Adapters.HistoryAdapter;
import com.example.admin.smartindia.Models.UserMedicalHistoryData;
import com.example.admin.smartindia.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Medical History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView= (RecyclerView) findViewById(R.id.history_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        historyAdapter=new HistoryAdapter(getData(),this);
        recyclerView.setAdapter(historyAdapter);
    }

    private List<UserMedicalHistoryData> getData() {
        ArrayList<UserMedicalHistoryData> list=new ArrayList<>();

        for(int i=0;i<10;i++){
            list.add(new UserMedicalHistoryData("Hospital name","Doctor name","Issue","Medicines","Date"));
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
