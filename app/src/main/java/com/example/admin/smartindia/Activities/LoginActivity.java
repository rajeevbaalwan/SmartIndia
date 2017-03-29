package com.example.admin.smartindia.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.smartindia.Models.User;
import com.example.admin.smartindia.R;
import com.example.admin.smartindia.Utilities.SharedPrefUtil;

public class LoginActivity extends AppCompatActivity {

    private SharedPrefUtil sharedPrefUtil;
    private Button loginButton;
    private Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPrefUtil=new SharedPrefUtil(LoginActivity.this);
        loginButton= (Button) findViewById(R.id.login_button);
        signUpButton= (Button) findViewById(R.id.sign_up_button);

        if(sharedPrefUtil.isLoggedIn()){
            Intent intent=new Intent(LoginActivity.this,LandingActivity.class);
            startActivity(intent);
            this.finish();
        }

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefUtil.startRegisterSession(new User("abfrt","abcd","abcd","abcd","abcd","abcd","abcd","abcd"));
                Intent intent = new Intent(LoginActivity.this,LandingActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
    }
}