package com.example.degabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private TextView registra;
    private EditText correo,contrasena;
    private Button iniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        correo=findViewById(R.id.loginCorreo);
        contrasena=findViewById(R.id.loginContra);
        iniciar=findViewById(R.id.loginBt);
        registra=findViewById(R.id.loginRegistrarse);

        // cuando el correo y contrasena que hay cambia pagina

        iniciar.setOnClickListener(view -> {
            if(correo.getText().length()>0 && contrasena.getText().length()>0){
                Intent intent = new Intent(MainActivity.this, menu.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "error correo o contrasena", Toast.LENGTH_SHORT).show();
            }
        });

        registra.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, registro.class);
            startActivity(intent);
        });
    }
}