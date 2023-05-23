package com.example.degabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class perfil extends AppCompatActivity {
    private ImageView perMenu,perCart,perPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        perMenu=findViewById(R.id.perfilMenu);
        perCart=findViewById(R.id.perfilCart);
        perPerfil=findViewById(R.id.perfilPerfil);

        perMenu.setOnClickListener(view -> {
            Intent intent = new Intent(perfil.this, menu.class);
            startActivity(intent);
            finish();
        });

        perCart.setOnClickListener(view -> {
            Intent intent = new Intent(perfil.this, carrito.class);
            startActivity(intent);
            finish();
        });

        perPerfil.setOnClickListener(view -> {
            Intent intent = new Intent(perfil.this, perfil.class);
            startActivity(intent);
            finish();
        });
    }
}