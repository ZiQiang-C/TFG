package com.example.degabriel;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degabriel.adapter.catalogoAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class catalogo extends AppCompatActivity implements catalogoAdapter.onItemClickListener{
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<String> documentId=new ArrayList<>();

    private ImageView catalogoMenu, catalogCart, catalogUser;
    private RecyclerView recy;
    private catalogoAdapter adapter;
    private List<Map<String, Object>> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        catalogoMenu =findViewById(R.id.catalogoMenu);
        catalogCart =findViewById(R.id.catalogoCart);
        catalogUser =findViewById(R.id.catalogoPerfil);

        recy=findViewById(R.id.catalogoRcy);
        recy.setLayoutManager(new LinearLayoutManager(this));

        dataList = new ArrayList<>();
        adapter = new catalogoAdapter(dataList);
        recy.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
        //obtenerCatalogo();
        readDataFromFirestore();



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

    private void readDataFromFirestore() {
        db.collection("Bolsos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // 处理每个文档的数据
                                 documentId .add(document.getId());
                                Map<String, Object> data = document.getData();



                                // 添加数据到列表
                                dataList.add(data);
                            }
                            // 更新UI显示适配器的数据
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                            Toast.makeText(catalogo.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        String ID=documentId.get(position);

        envio(ID);
    }
    public void envio(String ID) {
        Intent intent = new Intent(this, detalleArticulo.class);
        intent.putExtra("ID",ID);
        startActivity(intent);

    }
}