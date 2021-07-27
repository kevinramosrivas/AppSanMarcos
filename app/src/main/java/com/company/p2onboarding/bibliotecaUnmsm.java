package com.company.p2onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import android.widget.TextView;
import java.util.Locale;

public class bibliotecaUnmsm extends AppCompatActivity implements TextToSpeech.OnInitListener{
    ViewFlipper v_flipper;
    private TextToSpeech tts;
    private TextView textViewBiblioteca;
    private ImageButton btnBibliotecaPlay;
    private ImageButton btnBibliotecaStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblioteca_unmsm);
        int images[] = {R.drawable.bibliotecacentral_unmsm1,R.drawable.bibliotecacentral_unmsm2,R.drawable.bibliotecacentral_unmsm3};
        v_flipper = findViewById(R.id.v_flipperBiblioteca);
        for(int image:images){
            flipperImages(image);
        }


        tts = new TextToSpeech(this,this);
        btnBibliotecaPlay = (ImageButton) findViewById(R.id.bibliotecaAVozPlay);
        btnBibliotecaStop = (ImageButton) findViewById(R.id.bibliotecaAVozStop);
        textViewBiblioteca = (TextView) findViewById(R.id.textViewBiblioteca);
        btnBibliotecaPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }


        });
        btnBibliotecaStop.setOnClickListener(new View.OnClickListener() {
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
            else{
                speakOut();
            }
        }
        else{
            Log.e("TTS","inicializacion del lenguaje falla");
        }
    }
    private void speakOut() {
        String textoEstadio = textViewBiblioteca.getText().toString();
        tts.speak(textoEstadio,TextToSpeech.QUEUE_FLUSH,null);
    }
}