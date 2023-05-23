package com.example.degabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class menu extends AppCompatActivity {
    private Button noticias,catalogo,expression,manfiesto;
    private ImageView menuMenu,menuCart,menuPerfil;
    private String url = "https://www.degabriel-official.com/en/manifest/"; //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuMenu=findViewById(R.id.menuMenu);
        menuCart=findViewById(R.id.menuCart);
        menuPerfil=findViewById(R.id.menuPerfil);
        noticias=findViewById(R.id.menuNoticias);
        catalogo=findViewById(R.id.menuCatalogo);
        expression=findViewById(R.id.menuExpression);
        manfiesto=findViewById(R.id.menuManifiesto);

        menuMenu.setOnClickListener(view -> {
            Intent intent = new Intent(menu.this, menu.class);
            startActivity(intent);
            finish();
        });

        menuCart.setOnClickListener(view -> {
            Intent intent = new Intent(menu.this, carrito.class);
            startActivity(intent);
            finish();
        });

        menuPerfil.setOnClickListener(view -> {
            Intent intent = new Intent(menu.this, perfil.class);
            startActivity(intent);
            finish();
        });

        noticias.setOnClickListener(view -> {
            Intent intent = new Intent(menu.this, noticias.class);
            startActivity(intent);
            finish();
        });

        catalogo.setOnClickListener(view -> {
            Intent intent = new Intent(menu.this, catalogo.class);
            startActivity(intent);
            finish();
        });

        expression.setOnClickListener(view -> {
            Intent intent = new Intent(menu.this, expression.class);
            startActivity(intent);
            finish();
        });

        manfiesto.setOnClickListener(view -> {
            openWebsite();
        });
    }
    private void openWebsite() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}