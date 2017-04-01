package com.example.admin.smartindia.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.smartindia.R;
import com.example.admin.smartindia.Utilities.SharedPrefUtil;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private SharedPrefUtil sharedPrefUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton= (Button) findViewById(R.id.login_button);
        sharedPrefUtil=new SharedPrefUtil(LoginActivity.this);

        if(sharedPrefUtil.isLoggedIn()){
            Intent intent=new Intent(LoginActivity.this,LandingActivity.class);
            startActivity(intent);
            this.finish();
        }
        else{
            Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
    }
}
