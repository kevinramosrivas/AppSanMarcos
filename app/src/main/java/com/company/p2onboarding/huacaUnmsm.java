package com.company.p2onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Locale;

public class huacaUnmsm extends AppCompatActivity implements TextToSpeech.OnInitListener {
    ViewFlipper v_flipper;
    private TextToSpeech tts;
    private TextView textViewHuaca;
    private ImageButton btnHuacaPlay;
    private ImageButton btnHuacaStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huaca_unmsm);

        int[] images = {R.drawable.huaca_unmsm1,R.drawable.huaca_unmsm2,R.drawable.huaca_unmsm3};

        v_flipper = findViewById(R.id.flipper_huaca);

        for(int image:images){
            flipperImages(image);
        }

        tts = new TextToSpeech(this,this);
        btnHuacaPlay = (ImageButton) findViewById(R.id.HuacaAVozPlay);
        btnHuacaStop = (ImageButton) findViewById(R.id.HuacaAVozStop);
        textViewHuaca = (TextView) findViewById(R.id.cuerpoHuaca);
        btnHuacaPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
                Toast.makeText(huacaUnmsm.this, "Espere un momento por favor", Toast.LENGTH_SHORT).show();
            }


        });
        btnHuacaStop.setOnClickListener(new View.OnClickListener() {
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
        String textoHuaca = textViewHuaca.getText().toString();
        tts.speak(textoHuaca,TextToSpeech.QUEUE_FLUSH,null);
    }


}