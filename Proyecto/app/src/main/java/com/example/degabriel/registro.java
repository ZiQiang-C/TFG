package com.example.degabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class registro extends AppCompatActivity {
    private TextView iniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        iniciar=findViewById(R.id.regIniciar);


        iniciar.setOnClickListener(view -> {
            Intent intent = new Intent(registro.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}