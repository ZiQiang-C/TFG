package com.example.degabriel;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class carrito extends AppCompatActivity {
    private FirebaseFirestore db;
    private ImageView carritoMenu, carritoCart, carritoUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        carritoMenu =findViewById(R.id.carritoMenu);
        carritoCart =findViewById(R.id.carritoCart);
        carritoUser =findViewById(R.id.carritoUser);


        carritoMenu.setOnClickListener(view -> {
            Intent intent = new Intent(carrito.this, menu.class);
            startActivity(intent);
            finish();
        });

        carritoCart.setOnClickListener(view -> {
            Intent intent = new Intent(carrito.this, carrito.class);
            startActivity(intent);
            finish();
        });

        carritoUser.setOnClickListener(view -> {
            Intent intent = new Intent(carrito.this, perfil.class);
            startActivity(intent);
            finish();
        });
    }

}