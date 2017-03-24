package com.example.admin.smartindia.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import com.afollestad.materialdialogs.MaterialDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.smartindia.Models.UserCurrentMedicalData;
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

public class LandingActivity extends AppCompatActivity implements View.OnClickListener,Constants {

    private Toolbar toolbar;
    private LinearLayout historyButton;
    private LinearLayout alergiesButton;
    private LinearLayout mediclaimStatusButton;
    private Button pendingButton;
    private Button completedButton;
    private LinearLayout profileButton;
    private ImageView arrowIcon;
    private LinearLayout bottomSheetButtonsLayout;
    private RelativeLayout mediclaimOptionsContainer;
    private LinearLayout swipe_down;
    private LinearLayout swipe_up;
    private LinearLayout mediclaimButtons;
    private BottomSheetBehavior sheetBehavior;
    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback;
    private MaterialDialog progressDialog;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new LandingAdapter(this,getData());
        recyclerView.setAdapter(adapter);*/

        historyButton = (LinearLayout) findViewById(R.id.history_button);
        alergiesButton = (LinearLayout) findViewById(R.id.alergic_button);
        mediclaimStatusButton = (LinearLayout) findViewById(R.id.mediclaim_status_button);
        pendingButton = (Button) findViewById(R.id.pending_button);
        completedButton = (Button) findViewById(R.id.completed_button);
        profileButton = (LinearLayout) findViewById(R.id.profile_button);
        arrowIcon= (ImageView) findViewById(R.id.arrow_image_View);
        swipe_down = (LinearLayout) findViewById(R.id.bottom_sheet_swipe_down_layout);
        swipe_up = (LinearLayout) findViewById(R.id.bottom_sheet_swipe_up_layout);
        mediclaimButtons= (LinearLayout) findViewById(R.id.mediclaim_option_buttons);
        bottomSheetButtonsLayout = (LinearLayout) findViewById(R.id.bottom_sheet_buttons_layout);
        mediclaimOptionsContainer = (RelativeLayout) findViewById(R.id.mediclaim_option_buttons_container);
        sheetBehavior=BottomSheetBehavior.from(findViewById(R.id.bottom_sheet));

        historyButton.setOnClickListener(this);
        alergiesButton.setOnClickListener(this);
        mediclaimStatusButton.setOnClickListener(this);
        pendingButton.setOnClickListener(this);
        completedButton.setOnClickListener(this);
        mediclaimOptionsContainer.setOnClickListener(this);
        arrowIcon.setOnClickListener(this);


        bottomSheetCallback=new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                if(slideOffset<0.5){ //Log.d("qwerty","onSlide"+slideOffset);
                    if(mediclaimOptionsContainer.getVisibility()==View.VISIBLE){
                        mediclaimButtons.setVisibility(View.GONE);
                        mediclaimOptionsContainer.setVisibility(View.GONE);
                        bottomSheetButtonsLayout.animate()
                                .alpha(1.0f);

                    }
                }
                if(slideOffset>0.95){
                    arrowIcon.setImageResource(R.drawable.collapse_arrow);
                }

                if(slideOffset<0.1){
                    arrowIcon.setImageResource(R.drawable.expand_arrow);
                }
            }
        };
        sheetBehavior.setBottomSheetCallback(bottomSheetCallback);

        fetchDataFromServer();

    }

    private void fetchDataFromServer() {
        String url=BASE_URL;

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
                        UtilMethods.ToastS(LandingActivity.this,"Sorry Unable To Connect to Server");
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result=response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    setCurrentData(jsonArray.getJSONObject(0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setCurrentData(JSONObject jsonObject) throws JSONException {
        //set Current Data Here
        TextView doctorName= (TextView) findViewById(R.id.current_treatment_doctor_name);
        TextView hospitlaName= (TextView) findViewById(R.id.current_treatment_hospital_name);
        TextView morningMedicine= (TextView) findViewById(R.id.current_treatment_morning_med_1);
        TextView noonMedicine= (TextView) findViewById(R.id.current_treatment_noon_med_1);
        TextView nightMedicine= (TextView) findViewById(R.id.current_treatment_night_med_1);
        TextView food= (TextView) findViewById(R.id.current_treatment_food_1);

        doctorName.setText(jsonObject.getString("doctorName"));
        hospitlaName.setText(jsonObject.getString("hospitalName"));
        morningMedicine.setText(jsonObject.getString("morningMed"));
        noonMedicine.setText(jsonObject.getString("noonMed"));
        nightMedicine.setText(jsonObject.getString("nightMed"));
        food.setText(jsonObject.getString("food"));
    }

    private List<UserCurrentMedicalData> getData() {
        ArrayList list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(new UserCurrentMedicalData("time" + (i + 1), "Med" + (i + 1)));
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
            case R.id.mediclaim_status_button:
                mediclaimOptionsContainer.setVisibility(View.VISIBLE);
                mediclaimButtons.animate().translationY(-Resources.getSystem()
                                          .getDisplayMetrics().heightPixels/2+80)
                                          .setDuration(500)
                                          .setListener(new AnimatorListenerAdapter() {
                                              @Override
                                              public void onAnimationStart(Animator animation) {
                                                  super.onAnimationStart(animation);
                                                  bottomSheetButtonsLayout.animate()
                                                          .alpha(0.1f)
                                                          .setDuration(500);
                                                  mediclaimButtons.setVisibility(View.VISIBLE);
                                              }
                                          });
                break;
            case R.id.pending_button:
                Intent intent3=new Intent(LandingActivity.this,PendingMediclaimActivity.class);
                startActivity(intent3);
                break;
            case R.id.completed_button:
                Intent intent4=new Intent(LandingActivity.this, CompletedMediclaimActivity.class);
                startActivity(intent4);
                break;
            case R.id.mediclaim_option_buttons_container:
                    mediclaimOptionsContainer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            mediclaimButtons.animate()
                                    .translationY(Resources.getSystem()
                                            .getDisplayMetrics().heightPixels/2)
                                    .setDuration(500)
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationStart(Animator animation) {
                                            bottomSheetButtonsLayout.animate()
                                                    .alpha(1.0f)
                                                    .setDuration(500);
                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            mediclaimOptionsContainer.setVisibility(View.GONE);
                                        }
                                    });
                        }
                    });
                break;
            case R.id.arrow_image_View:
                if(sheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                else{
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
        }
    }

    public void showProgressDialog(String msg){
        progressDialog=new MaterialDialog.Builder(LandingActivity.this)
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
