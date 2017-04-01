package com.example.admin.smartindia.Activities;

import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.smartindia.Adapters.EmergencyAdapter;
import com.example.admin.smartindia.Models.EmergencyHospitalData;
import com.example.admin.smartindia.R;

import java.util.ArrayList;
import java.util.List;

public class EmergencyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmergencyAdapter adapter;
    private Toolbar toolbar;
    private MaterialDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        showProgressDialog("Fetching Data");
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               hideProgressDialog();
                toolbar= (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);

                recyclerView= (RecyclerView) findViewById(R.id.emergency_activity_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(EmergencyActivity.this));

                adapter=new EmergencyAdapter(getData(),EmergencyActivity.this);
                recyclerView.setAdapter(adapter);
            }
        },3000);


    }

    private List<EmergencyHospitalData> getData() {
        ArrayList<EmergencyHospitalData> list=new ArrayList<>();
        String[] hospitalNames;
        String[] hospitalAddresses;
        String[] hospitalSpecialists;
        String[] hospitalOpenHours;
        String[] hospitalHelplines;

        Resources resources=getResources();
        hospitalNames=resources.getStringArray(R.array.hospital_names);
        hospitalAddresses=resources.getStringArray(R.array.hospital_address);
        hospitalSpecialists=resources.getStringArray(R.array.hospital_specialist);
        hospitalOpenHours=resources.getStringArray(R.array.hospital_open_hours);
        hospitalHelplines=resources.getStringArray(R.array.hospital_helpline);

        for(int i=0;i<8;i++){
            list.add(new EmergencyHospitalData(hospitalNames[i],hospitalAddresses[i],hospitalSpecialists[i],
                    hospitalOpenHours[i],hospitalHelplines[i]));
        }
        return list;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            this.finish();
        }
        return true;
    }

    public void showProgressDialog(String msg){
        progressDialog=new MaterialDialog.Builder(EmergencyActivity.this)
                .progress(true,100)
                .content(msg)
                .cancelable(false)
                .build();
        progressDialog.show();
    }

    public void hideProgressDialog(){
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.cancel();
        }
    }
}
