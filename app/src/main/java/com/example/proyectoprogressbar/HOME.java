package com.example.proyectoprogressbar;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HOME extends AppCompatActivity {

    private Button oms, bano, consumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Aquí agregamos el código para iniciar la actividad "OMS" cuando se hace clic en el botón "oms"
        oms = findViewById(R.id.OMS_agua);
        bano = findViewById(R.id.BanoB);
        consumo = findViewById(R.id.ConsumoB);
        oms.setOnClickListener(v -> {
            Intent intent = new Intent(HOME.this, OMS.class);
            startActivity(intent);
        });
        bano.setOnClickListener(v -> {
            Intent intent = new Intent(HOME.this, TimerB.class);
            startActivity(intent);
        });
        consumo.setOnClickListener(v -> {
            Intent intent = new Intent(HOME.this, AguaStatus.class);
            startActivity(intent);
        });
    }
}