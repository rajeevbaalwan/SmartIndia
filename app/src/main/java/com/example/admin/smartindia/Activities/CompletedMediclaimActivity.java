package com.example.admin.smartindia.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.smartindia.Adapters.CompletedMediclaimAdapter;
import com.example.admin.smartindia.Models.CompletedMediclaimData;
import com.example.admin.smartindia.Models.PendingMediclaimData;
import com.example.admin.smartindia.R;
import com.example.admin.smartindia.Utilities.Constants;
import com.example.admin.smartindia.Utilities.UtilMethods;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompletedMediclaimActivity extends AppCompatActivity implements Constants{

    private RecyclerView recyclerView;
    private CompletedMediclaimAdapter adapter;
    private Toolbar toolbar;
    private MaterialDialog progressDialog;
    private FloatingActionButton emergencyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_mediclaim);

        recyclerView= (RecyclerView) findViewById(R.id.completed_mediclaim_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        emergencyButton= (FloatingActionButton) findViewById(R.id.emergency_floating_button);

        adapter=new CompletedMediclaimAdapter(this,new ArrayList<CompletedMediclaimData>());
        recyclerView.setAdapter(adapter);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Completed MediClaims");
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);

        emergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CompletedMediclaimActivity.this,EmergencyActivity.class);
                startActivity(intent);
            }
        });

        //fetchDataFromServer();
    }

   /* private void fetchDataFromServer() {
        showProgressDialog("Fetching Your Completed Mediclaims....");
        String url=BASE_URL+"";

        OkHttpClient okHttpClient=new OkHttpClient();

        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        UtilMethods.ToastL(CompletedMediclaimActivity.this,"Sorry Unable To Connect To Server");
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result=response.body().string();
                try {
                    final JSONObject jsonObject=new JSONObject(result);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                adapter.changeList(getData(jsonObject.getJSONArray("results")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                    }
                });
            }
        });
    }*/

    private List<CompletedMediclaimData> getData(JSONArray results) throws JSONException {
        ArrayList<CompletedMediclaimData> list=new ArrayList<>();

        for(int i=0;i<results.length();i++){
            JSONObject object=results.getJSONObject(i);

            String hospitalName=object.getString("hospitalName");
            String date=object.getString("date");
            String amount=object.getString("amount");
            list.add(new CompletedMediclaimData(hospitalName,date,amount));
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

    public void showProgressDialog(String msg){
        progressDialog=new MaterialDialog.Builder(CompletedMediclaimActivity.this)
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