package com.example.degabriel;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.degabriel.adapter.catalogoAdapter;
import com.example.degabriel.adapter.noticiasAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class noticias extends AppCompatActivity implements noticiasAdapter.onItemClickListener{
    private List<String> documentId=new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView noticiaMenu,noticiaCart,noticiaPerfil;
    private List<Map<String, Object>> dataList= new ArrayList<>();;
    private List<String> http = new ArrayList<>();
    private noticiasAdapter adapter;
    private RecyclerView recy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        noticiaMenu=findViewById(R.id.noticiaMenu);
        noticiaCart=findViewById(R.id.noticiasCart);
        noticiaPerfil=findViewById(R.id.noticiasPerfil);
        recy =findViewById(R.id.noticiasRcy);
        recy.setLayoutManager(new LinearLayoutManager(this));


        adapter = new noticiasAdapter(dataList);
        recy.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        readDataFromFirestore();


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
    private void readDataFromFirestore() {
        db.collection("Noticias")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // 处理每个文档的数据
                                documentId .add(document.getId());
                                Map<String, Object> data = document.getData();

                                http.add((String)data.get("url"));

                                // 添加数据到列表
                                dataList.add(data);

                            }

                            // 更新UI显示适配器的数据
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                            Toast.makeText(noticias.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        String HTTP=http.get(position);

        envia(HTTP);
    }
    public void envia(String http){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(http));
        startActivity(intent);
    }
}