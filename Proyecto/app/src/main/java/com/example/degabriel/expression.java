package com.example.degabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class expression extends AppCompatActivity {
    private ImageView expMenu,expCart,expPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expression);

        expMenu=findViewById(R.id.expMenu);
        expCart=findViewById(R.id.expCart);
        expPerfil=findViewById(R.id.expPerfil);

        expMenu.setOnClickListener(view -> {
            Intent intent = new Intent(expression.this, menu.class);
            startActivity(intent);
            finish();
        });

        expCart.setOnClickListener(view -> {
            Intent intent = new Intent(expression.this, carrito.class);
            startActivity(intent);
            finish();
        });

        expPerfil.setOnClickListener(view -> {
            Intent intent = new Intent(expression.this, perfil.class);
            startActivity(intent);
            finish();
        });
    }
}