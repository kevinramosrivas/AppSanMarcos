package com.company.p2onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrarseActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasena;
    private EditText contrasenaConfirmacion;
    DatabaseReference mDatabase;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        correo = findViewById(R.id.correoI);
        contrasena = findViewById(R.id.contrasenaI);
        contrasenaConfirmacion = findViewById(R.id.contrasenaConfirmacion);
        mDialog = new ProgressDialog(this);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    public void registrarUsuario(View view){
        if(contrasena.getText().toString().equals(contrasenaConfirmacion.getText().toString())){
            mAuth.createUserWithEmailAndPassword(correo.getText().toString(),contrasena.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Map<String,Object> map = new HashMap<>();
                                map.put("correo",correo.getText().toString().trim());
                                map.put("contrasena",contrasena.getText().toString());
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "signInWithCustomToken:success");
                                String id = mAuth.getCurrentUser().getUid();
                                mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task2) {
                                        if(task2.isSuccessful()){
                                            mDialog.setMessage("Espere un momento por favor");
                                            mDialog.setCanceledOnTouchOutside(false);
                                            mDialog.show();
                                            Toast.makeText(getApplicationContext(),"Usuario creado",Toast.LENGTH_LONG).show();
                                            Intent i = new Intent(getApplicationContext(),IniciarSesionActivity.class);
                                            startActivity(i);
                                        }
                                        else{
                                            Toast.makeText(RegistrarseActivity.this,"no se crearon los datos correctamente",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                                //FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                                Toast.makeText(getApplicationContext(),"Autentificacion fallida.",Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
        }
        else{
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }
    }
}