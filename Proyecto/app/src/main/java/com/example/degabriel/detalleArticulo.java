package com.example.degabriel;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.degabriel.adapter.catalogoAdapter;
import com.example.degabriel.adapter.detalleArticuloAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class detalleArticulo extends AppCompatActivity  {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView detalleMenu, detalleCart, detalleUser,detalleArticulo;
    private TextView detalleNombre,detalledescrpcion,detalleprecio;

    private RecyclerView recy;
    private detalleArticuloAdapter adapter;
    private List<String> Imagenes= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_articulo);

        detalleMenu=findViewById(R.id.detalleMenu);
        detalleCart=findViewById(R.id.detalleCart);
        detalleUser=findViewById(R.id.detallePerfil);
        detalleNombre=findViewById(R.id.detalleArticuloNombre);
        detalledescrpcion=findViewById(R.id.detalleArticuloDescripcion);
        detalleprecio=findViewById(R.id.detalleArticuloPrecio);
        detalleArticulo=findViewById(R.id.detalleArticulo);
        recy=findViewById(R.id.detalleArticuloRecy);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recy.setLayoutManager(layoutManager);



        String ID=getIntent().getStringExtra("ID");
        readDataFromFirestore(ID);


        adapter = new detalleArticuloAdapter(Imagenes);
        recy.setAdapter(adapter);



        detalleMenu.setOnClickListener(view -> {
            Intent intent = new Intent(detalleArticulo.this, menu.class);
            startActivity(intent);
            finish();
        });

        detalleCart.setOnClickListener(view -> {
            Intent intent = new Intent(detalleArticulo.this, carrito.class);
            startActivity(intent);
            finish();
        });

        detalleUser.setOnClickListener(view -> {
            Intent intent = new Intent(detalleArticulo.this, perfil.class);
            startActivity(intent);
            finish();
        });
    }
    private void readDataFromFirestore(String ID) {
        db.collection("Bolsos").document(ID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Map<String, Object> data = document.getData();
                                String name =(String)data.get("Nombre");
                                String descrip =(String)data.get("Descripcion");
                                Long precio = (Long) data.get("Precio");


                                detalleNombre.setText(name);
                                detalledescrpcion.setText(descrip);
                                detalleprecio.setText(""+precio);

                                List<String> imageUrls = (List<String>) data.get("Imagen");
                                if (imageUrls != null && imageUrls.size() > 0) {

                                    Imagenes.addAll(imageUrls);


                                    // Obtenga la URL de la primera imagen del arreglo
                                    String firstImageUrl = imageUrls.get(0);

                                    // Utilice Glide para cargar la imagen en el ImageView
                                    Glide.with(detalleArticulo.this)
                                            .load(firstImageUrl)
                                            .into(detalleArticulo);

                                }
                                Toast.makeText(detalleArticulo.this, "Log in correcto", Toast.LENGTH_SHORT).show();
                                adapter.notifyDataSetChanged();

                            } else {
                                Toast.makeText(detalleArticulo.this, "Log in mal", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }

}