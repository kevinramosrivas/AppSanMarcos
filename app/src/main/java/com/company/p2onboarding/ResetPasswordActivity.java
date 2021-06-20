package com.company.p2onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText mEditTextEmail;
    private Button mButtonResetPassword;
    private String email= "";

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mButtonResetPassword = (Button) findViewById(R.id.btnResetPassword);
        //instanciamos el objeto de tipo FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
        //intanciamos el el mDialog
        mDialog = new ProgressDialog(this);

        mButtonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEditTextEmail.getText().toString();
                if(!email.isEmpty()){
                    mDialog.setMessage("Espere un momento");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    resetPassword();


                }
                else{
                    Toast.makeText(ResetPasswordActivity.this, "Debe ingresar el Email", Toast.LENGTH_LONG).show();
                }
                
            }
        });

    }
    private void resetPassword(){
        //le indicamos en que idioma queremos que llegue el correo de
        //restablecer contraseña
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ResetPasswordActivity.this, "Se ha enviado un correo para restablecer tu contraseña", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ResetPasswordActivity.this,"No se pudo enviar el correo para restablecer su contraseña",Toast.LENGTH_LONG).show();
                }
                mDialog.dismiss();
            }
        });

    }
}