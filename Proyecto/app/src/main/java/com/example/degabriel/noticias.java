package com.example.degabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class noticias extends AppCompatActivity {
    private ImageView noticiaMenu,noticiaCart,noticiaPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        noticiaMenu=findViewById(R.id.noticiaMenu);
        noticiaCart=findViewById(R.id.noticiasCart);
        noticiaPerfil=findViewById(R.id.noticiasPerfil);

        noticiaMenu.setOnClickListener(view -> {
            Intent intent = new Intent(noticias.this, menu.class);
            startActivity(intent);
            finish();
        });

        noticiaCart.setOnClickListener(view -> {
            Intent intent = new Intent(noticias.this, carrito.class);
            startActivity(intent);
            finish();
        });

        noticiaPerfil.setOnClickListener(view -> {
            Intent intent = new Intent(noticias.this, perfil.class);
            startActivity(intent);
            finish();
        });

    }
}