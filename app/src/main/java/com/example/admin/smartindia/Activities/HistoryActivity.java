package com.example.admin.smartindia.Activities;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.smartindia.Adapters.HistoryAdapter;
import com.example.admin.smartindia.Models.UserMedicalHistoryData;
import com.example.admin.smartindia.R;
import com.example.admin.smartindia.Utilities.Constants;
import com.example.admin.smartindia.Utilities.SharedPrefUtil;
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

public class HistoryActivity extends AppCompatActivity implements Constants{

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RelativeLayout medicineTextviewContainer;
    private TextView medicineTextview;
    private CoordinatorLayout coordinatorLayout;
    private HistoryAdapter historyAdapter;
    private MaterialDialog progressDialog;
    private SharedPrefUtil sharedPrefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        medicineTextviewContainer= (RelativeLayout) findViewById(R.id.history_medicine_textview_container);
        medicineTextview= (TextView) findViewById(R.id.history_medicine_textview);
        coordinatorLayout= (CoordinatorLayout) findViewById(R.id.history_activity_coordinater_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Medical History");
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
        sharedPrefUtil = new SharedPrefUtil(HistoryActivity.this);
        recyclerView= (RecyclerView) findViewById(R.id.history_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        historyAdapter=new HistoryAdapter(new ArrayList<UserMedicalHistoryData>(),this,
                medicineTextviewContainer,medicineTextview,coordinatorLayout);
        recyclerView.setAdapter(historyAdapter);

        fetchDataFromServer();
    }

    private void fetchDataFromServer() {
        showProgressDialog("Fetching Your Medical History.....");
        String url=BASE_URL+"checkhistory?uniqueid="+sharedPrefUtil.getLoggedInUser().getUserId();

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
                        UtilMethods.ToastL(HistoryActivity.this,"Sorry Unable To Connect To Server.");
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String result=response.body().string();
                try {
                    final JSONArray jsonArray=new JSONArray(result);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                               // UtilMethods.ToastL(HistoryActivity.this,result);
                                historyAdapter.changeList(getData(jsonArray));
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
    }

    private List<UserMedicalHistoryData> getData(JSONArray results) throws JSONException {
        ArrayList<UserMedicalHistoryData> list=new ArrayList<>();
        for(int i=0;i<results.length();i++){
            JSONObject object=results.optJSONObject(i);

            String doctorName=object.getString("docname").replaceAll("^\\s+|\\s+$","");
            String hospitalName=object.getString("hospname").replaceAll("^\\s+|\\s+$","");
            String issue=object.getString("issue").replaceAll("^\\s+|\\s+$","");
            String date=object.getString("date").replaceAll("^\\s+|\\s+$","");
            String medicines=object.getString("medicine").replaceAll("^\\s+|\\s+$","");
            list.add(new UserMedicalHistoryData(hospitalName,doctorName,issue,medicines,date));
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
        progressDialog=new MaterialDialog.Builder(HistoryActivity.this)
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
