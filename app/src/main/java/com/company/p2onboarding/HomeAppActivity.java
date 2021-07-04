package com.company.p2onboarding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class HomeAppActivity extends AppCompatActivity {
    private Button mButtonSingOut;
    private FirebaseAuth mAuth;
    private ImageButton btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        mAuth  = FirebaseAuth.getInstance();
        mButtonSingOut = (Button) findViewById(R.id.btnSignOut);
        btnScan = (ImageButton)findViewById(R.id.qrScanImageButton);

        mButtonSingOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(HomeAppActivity.this,IniciarSesionActivity.class));
                finish();
            }
        });

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrador = new IntentIntegrator(HomeAppActivity.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("Apunte a un un Qr dentro de la UNMSM");
                integrador.setCameraId(0);
                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);
                integrador.setOrientationLocked(false);
                integrador.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result =IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null ){
            if(result.getContents() == null){

            }
            else{
                Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
                String idPoint = result.getContents();
                switch (idPoint){
                    case "fisiUnmsm":
                        Intent intent1 = new Intent(HomeAppActivity.this,fisiUnmsm.class);
                        startActivity(intent1);
                        break;
                    case "bibliotecaUnmsm":
                        Intent intent2 = new Intent(HomeAppActivity.this,bibliotecaUnmsm.class);
                        startActivity(intent2);
                        break;
                    case "estadioUnmsm":
                        Intent intent3 = new Intent(HomeAppActivity.this,estadioUnmsm.class);
                        startActivity(intent3);
                        break;
                }

            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}