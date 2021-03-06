package com.example.admin.smartindia.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.smartindia.Models.User;
import com.example.admin.smartindia.R;
import com.example.admin.smartindia.Utilities.SharedPrefUtil;

public class ProfileActivity extends AppCompatActivity {

    private TextView userName;
    private TextView userAdhaar;
    private TextView userPhone;
    private TextView userEmail;
    private TextView userDob;
    private TextView userBloodGroup;
    private TextView userAddress;
    private FloatingActionButton emergencyButton;
    private User user;
    private SharedPrefUtil sharedPrefUtil;
    private Toolbar toolbar;
    private ImageView profileImageEditButton;
    private ImageView profileImage;
    private int RESULT_LOAD_IMAGE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName= (TextView) findViewById(R.id.reg_full_name);
        userAdhaar= (TextView) findViewById(R.id.reg_aadhar);
        userPhone= (TextView) findViewById(R.id.reg_phone);
        userEmail= (TextView) findViewById(R.id.reg_mail);
        userDob= (TextView) findViewById(R.id.reg_dob);
        userBloodGroup= (TextView) findViewById(R.id.reg_blood_group);
        userAddress= (TextView) findViewById(R.id.reg_address);
        profileImage= (ImageView) findViewById(R.id.register_image);
        emergencyButton= (FloatingActionButton) findViewById(R.id.emergency_floating_button);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);

        sharedPrefUtil=new SharedPrefUtil(ProfileActivity.this);
        user=sharedPrefUtil.getLoggedInUser();

        userName.setText(user.getUserName());
        userAdhaar.setText(user.getUserAadhar());
        userPhone.setText(user.getUserPhone());
        userEmail.setText(user.getUserEmail());
        userDob.setText(user.getUserDob());
        userBloodGroup.setText(user.getUserBloodGroup());
        userAddress.setText(user.getUserAddress());

        emergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileActivity.this,EmergencyActivity.class);
                startActivity(intent);
            }
        });

        /*profileImageEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,RESULT_LOAD_IMAGE);
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data!=null){
            Uri selectedImage=data.getData();
            String[] filePathColumn= {MediaStore.Images.Media.DATA};

            Cursor cursor=getContentResolver().query(selectedImage,filePathColumn ,null,null,null);
            cursor.moveToFirst();

            int columnIndex=cursor.getColumnIndex(filePathColumn[0]);
            String picturePath=cursor.getString(columnIndex);

            profileImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home)
            this.finish();
        return  true;
    }
}
