package com.company.p2onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class estadioUnmsm extends AppCompatActivity implements TextToSpeech.OnInitListener {
    ViewFlipper v_flipper;
    private TextToSpeech tts;
    private Button btnEstadio;
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
        btnEstadio = (Button) findViewById(R.id.estadioAVoz);

        btnEstadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
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

    }
    private void speakOut() {
    }
}