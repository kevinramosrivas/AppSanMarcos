package com.company.p2onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class menuGeneral extends AppCompatActivity {

    private ImageButton btnPersonajes;
    private ImageButton btnCronologia;
    private ImageButton btnAutoridades;
    private ImageButton btnCultura;
    private ImageButton btnSedes;
    private ImageButton btnLugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_general);

        btnPersonajes = (ImageButton)findViewById(R.id.ilustresImageButton);
        btnCronologia = (ImageButton)findViewById(R.id.cronologiaImageButton);
        btnAutoridades = (ImageButton)findViewById(R.id.autoridadesImageButton);
        btnCultura = (ImageButton)findViewById(R.id.culturaImageButton);
        btnSedes = (ImageButton)findViewById(R.id.sedesImageButton);
        btnLugares = (ImageButton)findViewById(R.id.lugaresImageButton);

        btnPersonajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_g1 = new Intent(menuGeneral.this,ilustres_activity.class);
                startActivity(intent_g1);
            }
        });

        btnSedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_g5 = new Intent(menuGeneral.this,sedes_activity.class);
                startActivity(intent_g5);
            }
        });

        btnCultura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inteng_g4 = new Intent(menuGeneral.this,cultura_activity.class);
                startActivity(inteng_g4);
            }
        });


    }
}