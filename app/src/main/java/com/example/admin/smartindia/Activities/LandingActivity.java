package com.example.admin.smartindia.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.preference.RingtonePreference;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import android.support.v7.app.AlertDialog;
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
import com.example.admin.smartindia.Utilities.SharedPrefUtil;
import com.example.admin.smartindia.Utilities.UtilMethods;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
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
    private static final int RC_NFC = 1234;
    private MaterialDialog progressDialog;
    private   final String TAG  = LandingActivity.this.getClass().getName();
    private SharedPrefUtil sharedPrefUtil;
    private Socket socket;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPrefUtil = new SharedPrefUtil(LandingActivity.this);


       // initialiseMenuSocket();

      /*  socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                socket.emit(Constants.EVENT_REGISTER_USER,sharedPrefUtil.getLoggedInUser().getUserId());
            }
        });

        socket.on(Constants.EVENT_NOTIFY, new Emitter.Listener() {
            @Override
            public void call(Object... args){
                Log.d(TAG,"hiii");
                JSONObject jsonObject = (JSONObject) args[0];
                try{
                    if(jsonObject.getString("id").equals(sharedPrefUtil.getLoggedInUser().getUserId())){
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    final Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    ringtone.play();
                    new MaterialDialog.Builder(LandingActivity.this)
                            .content("Doctor is Calling you.....")
                            .cancelable(false)
                            .title("Your Turn....")
                            .positiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.cancel();

                                    ringtone.stop();
                                }
                            })
                            .show();
                }
            }catch (JSONException e){e.printStackTrace();}
            }

        });
*/
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
        profileButton.setOnClickListener(this);


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

        //fetchDataFromServer();

    }

   /* private void fetchDataFromServer() {
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
    }*/

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
                Intent intent2=new Intent(LandingActivity.this,AllergyActivity.class);
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
            case R.id.profile_button:
                Intent intent=new Intent(LandingActivity.this,RegisterActivity.class);
                startActivity(intent);
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

    @Override
    protected void onResume() {
        super.onResume();

        if (socket==null){
//            initialiseMenuSocket();
        }

        if (socket!=null && !socket.connected()){
            socket.connect();
        }

        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(getIntent().getAction()) ){

            processNfcTag(getIntent());
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
     //   Log.d(TAG,"On Pause is called");
    }

    void processNfcTag(Intent intent){
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Log.d(TAG,sharedPrefUtil.getLoggedInUser().getUserId());

        if(socket==null) {
            initialiseMenuSocket();
        }

        if (socket!=null && !socket.connected()){
            socket.connect();
        }



    }





    void initialiseMenuSocket(){

        try{
            socket = IO.socket(Constants.SOCKET_SERVER);
            socket.connect();
            Log.d(TAG,"Connecting to Menu Socket Server");
        }catch (URISyntaxException e){
            e.printStackTrace();
        }

        if (!socket.connected()){
            socket.connect();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }



    void showSwitchOnNfcDialog(){

        new AlertDialog.Builder(LandingActivity.this)
                .setMessage("NFC Is Not Enabled. In Order To Enjoy Our Services Enable Your Wifi..")
                .setCancelable(false)
                .setPositiveButton("ENABLE NFC", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
                            startActivityForResult(intent,RC_NFC);
                        } else {
                            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                            startActivityForResult(intent,RC_NFC);
                        }
                    }
                })
                .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LandingActivity.this.finish();
                    }
                })
                .create().show();
    }

}
