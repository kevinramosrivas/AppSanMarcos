package com.company.p2onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IniciarSesionActivity extends AppCompatActivity {
    private EditText correo;
    private EditText contrasena;
    private FirebaseAuth mAuth;
    private Button mButtonLogin;
    private Button mButtonResetPassword;
    private Button mbuttonsendtoregister;
    private String correoString;
    private String contrasenaString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        correo =  findViewById(R.id.correoI);
        contrasena = findViewById(R.id.contrasenaI);
        mAuth = FirebaseAuth.getInstance();
        mButtonLogin = (Button) findViewById(R.id.iniciarSesionBtn);
        mButtonResetPassword = (Button) findViewById(R.id.btnSendToResestPassword);
        mbuttonsendtoregister= (Button) findViewById(R.id.sendtoregister);
        mButtonLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                correoString = correo.getText().toString().trim();
                contrasenaString = contrasena.getText().toString();
                if(!correoString.isEmpty() && !contrasenaString.isEmpty()){
                    loginUser();
                }
                else{
                    Toast.makeText(IniciarSesionActivity.this,"Complete los campos",Toast.LENGTH_LONG).show();
                }
            }
        });
        mButtonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IniciarSesionActivity.this,ResetPasswordActivity.class));
            }
        });
        mbuttonsendtoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IniciarSesionActivity.this,RegistrarseActivity.class));
            }
        });
    }
    private void loginUser(){
        mAuth.signInWithEmailAndPassword(correoString,contrasenaString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(IniciarSesionActivity.this,HomeAppActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(IniciarSesionActivity.this,"No se pudo iniciar la sesion",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    //ESTE CODIGO ES DE UNA ANTERIOR IMPLEMENTACION XD NO LO BORREN TODAVIA
    /*
    @Override

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    public void iniciarSesion(View view){
        mAuth.signInWithEmailAndPassword(correo.getText().toString(),contrasena.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            //
                            Toast.makeText(getApplicationContext(),"Inicio exitoso",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),HomeAppActivity.class);
                            startActivity(i);
                            //
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }
    */
}