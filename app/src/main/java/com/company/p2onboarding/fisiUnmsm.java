package com.company.p2onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class fisiUnmsm extends AppCompatActivity implements TextToSpeech.OnInitListener {
    ViewFlipper v_flipper;
    private TextToSpeech tts;
    private TextView textViewFisi;
    private ImageButton btnFisiPlay;
    private ImageButton btnFisiStop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisi_unmsm);

        int images[] = {R.drawable.fisi_unmsm1,R.drawable.fisi_unmsm2,R.drawable.fisi_unmsm3};

        v_flipper = findViewById(R.id.v_flipper);

        for(int image:images){
            flipperImages(image);
        }

        tts = new TextToSpeech(this,this);
        btnFisiPlay = (ImageButton) findViewById(R.id.FisiAVozPlay);
        btnFisiStop = (ImageButton) findViewById(R.id.FisiAVozStop);
        textViewFisi = (TextView) findViewById(R.id.cuerpoFisi);
        btnFisiPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
                Toast.makeText(fisiUnmsm.this, "Espere un momento por favor", Toast.LENGTH_SHORT).show();
            }


        });
        btnFisiStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        String textoFisi = textViewFisi.getText().toString();
        tts.speak(textoFisi,TextToSpeech.QUEUE_FLUSH,null);
    }




}