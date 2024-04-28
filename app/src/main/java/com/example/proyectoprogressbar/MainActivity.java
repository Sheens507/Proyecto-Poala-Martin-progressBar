package com.example.proyectoprogressbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        // Crear un Handler y usar postDelayed para retrasar la visibilidad del ProgressBar
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(ProgressBar.VISIBLE);
                // Llamar a la clase HomeActivity usando un Intent
                Intent intent = new Intent(MainActivity.this, HOME.class);
                startActivity(intent);
                // Finalizar esta actividad para que no se pueda volver atrás a ella
                finish();
            }
        }, 1500); // 1500 milisegundos = 1.5 segundos
    }
}

