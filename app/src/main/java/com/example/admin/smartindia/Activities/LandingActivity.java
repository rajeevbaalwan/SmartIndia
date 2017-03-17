package com.example.admin.smartindia.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.admin.smartindia.Adapters.LandingAdapter;
import com.example.admin.smartindia.Models.UserCurrentMedicalData;
import com.example.admin.smartindia.R;

import java.util.ArrayList;
import java.util.List;

public class LandingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LandingAdapter adapter;
    private Button history;
    private Button alergic;
    private Button mediclaimStatus;
    private Button pending;
    private Button completed;
    private LinearLayout hiddenLayout;
    private Animation animShow;

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

        history= (Button) findViewById(R.id.history_button);
        alergic= (Button) findViewById(R.id.alergic_button);
        mediclaimStatus= (Button) findViewById(R.id.mediclaim_status);
        pending= (Button) findViewById(R.id.pending_button);
        completed= (Button) findViewById(R.id.completed_button);
        hiddenLayout= (LinearLayout) findViewById(R.id.Landing_hidden_linear_layout);

        animShow= AnimationUtils.loadAnimation(this,R.anim.view_show);

        mediclaimStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hiddenLayout.setVisibility(View.VISIBLE);
                hiddenLayout.startAnimation(animShow);
            }
        });
    }

    private List<UserCurrentMedicalData> getData() {
        ArrayList list= new ArrayList<>();
        for(int i=0;i<8;i++){
            list.add(new UserCurrentMedicalData("time"+(i+1),"Med"+(i+1)));
        }
        return list;
    }


}
