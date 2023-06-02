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
import android.widget.Toast;

import com.example.degabriel.adapter.catalogoAdapter;
import com.example.degabriel.adapter.expressionAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class expression extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private expressionAdapter adapter1;

    private expressionAdapter adapter2;
    private ImageView expMenu,expCart,expPerfil;
    RecyclerView recy1,recy2;
    private List<String> ImagenesAll =new ArrayList<>();
    private List<String> Imagenes1= new ArrayList<>();
    private List<String> Imagenes2= new ArrayList<>();
    private String http="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expression);

        expMenu=findViewById(R.id.expMenu);
        expCart=findViewById(R.id.expCart);
        expPerfil=findViewById(R.id.expPerfil);

        recy1=findViewById(R.id.expressionRecy1);
        recy1.setLayoutManager(new LinearLayoutManager(this));
        recy2=findViewById(R.id.expressionRcy2);
        recy2.setLayoutManager(new LinearLayoutManager(this));

        adapter1 = new expressionAdapter(Imagenes1);
        recy1.setAdapter(adapter1);
        adapter2 = new expressionAdapter(Imagenes2);
        recy2.setAdapter(adapter2);

        readDataFromFirestore();

        adapter1.setOnItemClickListener(new expressionAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 处理 RecyclerView1 的点击事件
                http=Imagenes1.get(position);
                envio(http);

            }
        });
        adapter2.setOnItemClickListener(new expressionAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 处理 RecyclerView1 的点击事件
                http=Imagenes2.get(position);
                envio(http);

            }
        });


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
    private void readDataFromFirestore() {
        db.collection("Expression")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // 处理每个文档的数据

                                Map<String, Object> data = document.getData();

                                ImagenesAll = (List<String>) data.get("Imagen");
                                if (ImagenesAll != null && ImagenesAll.size() > 0) {
                                    for(int i=0;i<ImagenesAll.size();i++){
                                        if(i%2==0){
                                            Imagenes1.add(ImagenesAll.get(i));
                                        }else{
                                            Imagenes2.add(ImagenesAll.get(i));
                                        }
                                    }

                                }
                                // 添加数据到列表

                            }
                            // 更新UI显示适配器的数据
                            adapter1.notifyDataSetChanged();
                            adapter2.notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                            Toast.makeText(expression.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void envio(String ID) {
        Intent intent = new Intent(this, fotosArticulo.class);
        intent.putExtra("http",ID);
        startActivity(intent);

    }
}