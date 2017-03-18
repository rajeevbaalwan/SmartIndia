package com.example.admin.smartindia.Activities;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admin.smartindia.Adapters.CompletedMediclaimAdapter;
import com.example.admin.smartindia.Adapters.LandingAdapter;
import com.example.admin.smartindia.Models.UserCurrentMedicalData;
import com.example.admin.smartindia.R;

import java.util.ArrayList;
import java.util.List;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LandingAdapter adapter;
    private Button history;
    private Button alergies;
    private Button mediclaimStatus;
    private Button pending;
    private Button completed;
    private LinearLayout hiddenLayout;
    private Animation animShow;
    private LinearLayout swipe_down;
    private LinearLayout swipe_up;
    private Animation slide_up;
    private Animation slide_down;
    private NestedScrollView swipeLayout;
    private int swipeFlag=0;
    private int startY, endY;

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
        alergies= (Button) findViewById(R.id.alergic_button);
        mediclaimStatus= (Button) findViewById(R.id.mediclaim_status);
        pending= (Button) findViewById(R.id.pending_button);
        completed= (Button) findViewById(R.id.completed_button);
        hiddenLayout= (LinearLayout) findViewById(R.id.landing_hidden_linear_layout);
        swipe_down= (LinearLayout) findViewById(R.id.hidden_swipe_down_layout);
        swipe_up= (LinearLayout) findViewById(R.id.hidden_swipe_up_layout);
        swipeLayout= (NestedScrollView) findViewById(R.id.bottom_sheet_swipe_layout);

        animShow= AnimationUtils.loadAnimation(this,R.anim.view_show);
        slide_up=AnimationUtils.loadAnimation(this,R.anim.slide_up);
        slide_down=AnimationUtils.loadAnimation(this,R.anim.slide_down);

        history.setOnClickListener(this);
        alergies.setOnClickListener(this);
        mediclaimStatus.setOnClickListener(this);
        pending.setOnClickListener(this);
        completed.setOnClickListener(this);

          /*  swipeLayout.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int eventAction = motionEvent.getAction();
                    switch (eventAction) {
                        case MotionEvent.ACTION_DOWN:
                            startY = (int) motionEvent.getY();
                            break;
                        case MotionEvent.ACTION_UP:
                            endY = (int) motionEvent.getY();
                    }
                    if(swipeFlag==0){
                       if(startY>endY){
                           swipeLayout.startAnimation(slide_up);
                           swipe_up.setVisibility(View.INVISIBLE);
                           swipe_down.setVisibility(View.VISIBLE);
                           swipeFlag=1;
                       }
                    }
                    else{
                        if(startY<endY){
                            swipeLayout.startAnimation(slide_down);
                            swipe_up.setVisibility(View.VISIBLE);
                            swipe_down.setVisibility(View.INVISIBLE);
                            swipeFlag=0;
                        }
                    }
                    return true;
                }
            });*/
    }

    private List<UserCurrentMedicalData> getData() {
        ArrayList list= new ArrayList<>();
        for(int i=0;i<8;i++){
            list.add(new UserCurrentMedicalData("time"+(i+1),"Med"+(i+1)));
        }
        return list;
    }


    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.history_button:
                Intent intent1=new Intent(LandingActivity.this,HistoryActivity.class);
                startActivity(intent1);
                break;
            case R.id.alergic_button:
                Intent intent2=new Intent(LandingActivity.this,AlergyActivity.class);
                startActivity(intent2);
                break;
            case R.id.mediclaim_status:
                hiddenLayout.setVisibility(View.VISIBLE);
                hiddenLayout.startAnimation(animShow);
                break;
            case R.id.pending_button:
                Intent intent3=new Intent(LandingActivity.this,PendingMediclaimActivity.class);
                startActivity(intent3);
                break;
            case R.id.completed_button:
                Intent intent4=new Intent(LandingActivity.this, CompletedMediclaimActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
