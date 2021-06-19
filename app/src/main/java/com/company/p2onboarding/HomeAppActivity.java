package com.company.p2onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeAppActivity extends AppCompatActivity {
    private Button mButtonSingOut;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        mAuth  = FirebaseAuth.getInstance();
        mButtonSingOut = (Button) findViewById(R.id.btnSignOut);

        mButtonSingOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(HomeAppActivity.this,MainActivity2.class));
                finish();
            }
        });
    }
}