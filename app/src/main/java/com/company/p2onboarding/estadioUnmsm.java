package com.company.p2onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class estadioUnmsm extends AppCompatActivity implements TextToSpeech.OnInitListener {
    ViewFlipper v_flipper;
    private TextToSpeech tts;
    private TextView textViewEstadio;
    private ImageButton btnEstadioPlay;
    private ImageButton btnEstadioStop;
    private FloatingActionButton btnRetroceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadio_unmsm);
        int images[] = {R.drawable.estadio_unmsm1,R.drawable.estadio_unmsm2,R.drawable.estadio_unmsm3};

        v_flipper = findViewById(R.id.v_flipper);

        for(int image:images){
            flipperImages(image);
        }
        tts = new TextToSpeech(this,this);

        btnEstadioPlay = (ImageButton) findViewById(R.id.estadioAVozPlay);
        btnEstadioStop = (ImageButton) findViewById(R.id.estadioAVozPause);
        textViewEstadio = (TextView) findViewById(R.id.cuerpo_estadio);
        btnRetroceder  =(FloatingActionButton) findViewById(R.id.btnRetrocederEstadio);
        btnEstadioPlay.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            speakOut();
                Toast.makeText(estadioUnmsm.this, "Espere un momento por favor", Toast.LENGTH_SHORT).show();
        }

        });

        btnEstadioStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.stop();
            }
        });

        btnRetroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(estadioUnmsm.this,HomeAppActivity.class);
                startActivity(intent);
                finish();
                tts.stop();
            }
        });


    }
    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);
        v_flipper.setInAnimation(this,android.R.anim.slide_out_right);
        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);

    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            int result = tts.setLanguage(Locale.getDefault());
            if(result==TextToSpeech.LANG_NOT_SUPPORTED
                    || result ==TextToSpeech.LANG_MISSING_DATA){
                Log.e("TTS","Este lenguaje no es soportado");
            }
        }
        else{
            Log.e("TTS","inicializacion del lenguaje falla");
        }

    }
    private void speakOut() {
        String textoEstadio = textViewEstadio.getText().toString();
        tts.speak(textoEstadio,TextToSpeech.QUEUE_FLUSH,null);
    }
}