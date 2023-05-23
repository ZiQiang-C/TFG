package com.example.degabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class welcomeActivity extends AppCompatActivity {
    private ImageView imageView;
    private int[] imageResources = {R.drawable.finaldune1, R.drawable.f2, R.drawable.f3};
    private int currentImageIndex = 0;
    private int time_sleepfirst=1000;
    private int time_sleepAll=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_app);

        imageView = findViewById(R.id.welcomeImage);


        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                showNextImage();
            }
        };
        handler.postDelayed(runnable, time_sleepfirst);
    }
    private void showNextImage() {
        if (currentImageIndex < imageResources.length) {
            // 显示下一张图片
            // monitor sigue image
            imageView.setImageResource(imageResources[currentImageIndex]);
            currentImageIndex++;

            // 继续计时器
            // contar tiempo
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showNextImage();
                }
            }, time_sleepAll);
        } else {
            // 所有图片显示完毕，进行跳转
            // cuando todos images verificar , va otro pagina (pagina principal)
            Intent intent = new Intent(welcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}





