package com.example.admin.smartindia.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.admin.smartindia.R;

public class AllergyActivity extends AppCompatActivity {

    private TextView allergicReaction;
    private String allergy;
    private FloatingActionButton emergencyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy);
        emergencyButton= (FloatingActionButton) findViewById(R.id.emergency_floating_button);
        allergicReaction= (TextView) findViewById(R.id.allergic_reaction);
        allergy=getIntent().getStringExtra("allergy");
        allergicReaction.setText(allergy);

        emergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AllergyActivity.this,EmergencyActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home)
            this.finish();
        return true;
    }
}
