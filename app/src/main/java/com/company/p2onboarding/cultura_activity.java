package com.company.p2onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class cultura_activity extends AppCompatActivity {

    ViewFlipper v_flipper;
    TextView textoImagenes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultura);

        int[] images = {R.drawable.cultura_1,R.drawable.cultura_2,R.drawable.cultura_3,R.drawable.cultura_4,R.drawable.cultura_5,R.drawable.cultura_6,R.drawable.cultura_7,R.drawable.cultura_8,R.drawable.cultura_9,R.drawable.cultura_10};
        String[] text = {"Bienvenida de cachimbos","Examen Medico","Comedor Universitario","Feria Vocacional","Almuerzo Especial","Presentaciones UNMSM","Banda musical Tuna","Teatro Ella Dunbar","Clubes de Deporte","Grupo Sikuris"};
        v_flipper = (ViewFlipper)findViewById(R.id.flipper_cultura);
        for(int image:images){
            flipperImages(image);
        }

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

}