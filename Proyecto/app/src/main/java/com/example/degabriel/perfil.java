package com.example.degabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class perfil extends AppCompatActivity {
    private ImageView perMenu,perCart,perPerfil;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText nombre, apellidos, correo, direccion, telefono;
    Button guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        mAuth=FirebaseAuth.getInstance();
        asignarElementos();
        obtenerUsuario();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarDatos();
            }
        });
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
    public void asignarElementos(){
        nombre=findViewById(R.id.PerfilNombre);
        apellidos=findViewById(R.id.PerfilApellidos);
        correo=findViewById(R.id.PerfilCorreo);
        direccion=findViewById(R.id.PerfilDireccion);
        telefono=findViewById(R.id.PerfilTelefono);
        guardar=findViewById(R.id.PerfilBoton);
        perMenu=findViewById(R.id.perfilMenu);
        perCart=findViewById(R.id.perfilCart);
        perPerfil=findViewById(R.id.perfilPerfil);
    }
    public void obtenerUsuario(){
        //Toast.makeText(this, mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
        correo.setText(mAuth.getCurrentUser().getEmail());
        correo.setEnabled(false);
        String uid = mAuth.getCurrentUser().getUid();
        db.collection("Usuarios").document(uid)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // El documento existe, se ha obtenido con éxito
                        String nombreObtenido=documentSnapshot.getString("Nombre");
                        String apellidosObtenido=documentSnapshot.getString("Apellidos");
                        String direccionObtenido=documentSnapshot.getString("Direccion");
                        String telefonoObtenido=documentSnapshot.getString("Telefono");
                        nombre.setText(nombreObtenido);
                        apellidos.setText(apellidosObtenido);
                        direccion.setText(direccionObtenido);
                        telefono.setText(telefonoObtenido);
                        // Acceder a los datos del documento
                        // ...
                    } else {
                        // El documento no existe
                    }
                })
                .addOnFailureListener(e -> {
                    // Error al obtener el documento
                });
    }
    public void cambiarDatos(){
        String uid = mAuth.getCurrentUser().getUid();
        Map<String, Object> userData = new HashMap<>();
        userData.put("Apellidos", apellidos.getText().toString());
        userData.put("Direccion", direccion.getText().toString());
        userData.put("Nombre", nombre.getText().toString());
        userData.put("Telefono", telefono.getText().toString());
        db.collection("Usuarios").document(uid)
                .set(userData)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this,"Se ha modificado el usuario", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Manejar errores aquí
                    Toast.makeText(this,"No se ha modificado el usuario", Toast.LENGTH_SHORT).show();
                });
    }
}