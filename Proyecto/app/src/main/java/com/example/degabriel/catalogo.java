package com.example.degabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class catalogo extends AppCompatActivity {
    private ImageView catalogoMenu, catalogCart, catalogUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        catalogoMenu =findViewById(R.id.carritoMenu);
        catalogCart =findViewById(R.id.carritoCart);
        catalogUser =findViewById(R.id.carritoUser);

        catalogoMenu.setOnClickListener(view -> {
            Intent intent = new Intent(catalogo.this, menu.class);
            startActivity(intent);
            finish();
        });

        catalogCart.setOnClickListener(view -> {
            Intent intent = new Intent(catalogo.this, carrito.class);
            startActivity(intent);
            finish();
        });

        catalogUser.setOnClickListener(view -> {
            Intent intent = new Intent(catalogo.this, perfil.class);
            startActivity(intent);
            finish();
        });
    }
}