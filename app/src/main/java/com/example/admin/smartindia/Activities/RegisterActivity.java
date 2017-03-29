package com.example.admin.smartindia.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.smartindia.Models.User;
import com.example.admin.smartindia.R;
import com.example.admin.smartindia.Utilities.Constants;
import com.example.admin.smartindia.Utilities.SharedPrefUtil;
import com.example.admin.smartindia.Utilities.UtilMethods;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

public class RegisterActivity extends Activity implements Constants{

    private EditText userName;
    private EditText userAdhaar;
    private EditText userDob;
    private EditText userBloodGroup;
    private EditText userEmail;
    private EditText userPhone;
    private EditText userAddress;
    private Button registerButton;
    private Calendar calendar;
    private ImageView dobCalender;
    private SharedPrefUtil sharedPrefUtil;
    private MaterialDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName= (EditText) findViewById(R.id.reg_full_name);
        userAdhaar= (EditText) findViewById(R.id.reg_aadhar);
        userDob= (EditText) findViewById(R.id.reg_dob);
        userBloodGroup= (EditText) findViewById(R.id.reg_blood_group);
        userEmail= (EditText) findViewById(R.id.reg_mail);
        userPhone= (EditText) findViewById(R.id.reg_phone);
        userAddress= (EditText) findViewById(R.id.reg_address);
        registerButton= (Button) findViewById(R.id.register_button);
        dobCalender= (ImageView) findViewById(R.id.dob_calender);

        sharedPrefUtil=new SharedPrefUtil(RegisterActivity.this);

        dobCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar=Calendar.getInstance();
                DatePickerDialog datePickerDialog=DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String date=dayOfMonth+"-"+monthOfYear+"-"+year;
                        userDob.setText(date);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show(getFragmentManager(),"user_date_of_birth");
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //registerUserOnServer();
            }
        });

    }

   /* private void registerUserOnServer() {
        showProgressDialog("Registering You On Server Please Wait....");
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
                        UtilMethods.ToastL(RegisterActivity.this,"Sorry Unable To Connect To Server");
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result=response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    if (jsonObject.getBoolean("status")){
                        sharedPrefUtil.startRegisterSession(new User(userAddress.getText().toString(),
                                userPhone.getText().toString(),userEmail.getText().toString(),
                                userBloodGroup.getText().toString(),userDob.getText().toString(),
                                userAdhaar.getText().toString(),userName.getText().toString()));
                        Intent intent=new Intent(RegisterActivity.this,LandingActivity.class);
                        startActivity(intent);
                        RegisterActivity.this.finish();
                    }
                    else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideProgressDialog();
                                UtilMethods.ToastL(RegisterActivity.this,"Sorry Unable To Connect To Server");
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    public void showProgressDialog(String msg){
        progressDialog=new MaterialDialog.Builder(RegisterActivity.this)
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
