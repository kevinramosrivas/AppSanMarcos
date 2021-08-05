package com.company.p2onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AcercaDe extends AppCompatActivity {
    private Button btnDonar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        btnDonar = (Button)findViewById(R.id.boton_donar);
        btnDonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://paypal.me/VisitaSanMarcos?locale.x=es_XC");
                Intent intentd = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intentd);
            }
        });

    }
}