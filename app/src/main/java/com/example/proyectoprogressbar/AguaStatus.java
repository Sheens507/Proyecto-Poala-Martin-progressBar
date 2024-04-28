package com.example.proyectoprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AguaStatus extends AppCompatActivity {


    private Button cienML, quinientosML, milML;
    private TextView txtper, excesoB;
    private ProgressBar Prog;
    int cantidadTotalML = 0;
    private int excesoTotalML = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agua_status);

        cienML = findViewById(R.id.cienml);
        quinientosML = findViewById(R.id.quinientosml);
        milML = findViewById(R.id.unlitro);
        txtper = findViewById(R.id.txtper);
        Prog = findViewById(R.id.Prog);
        excesoB = findViewById(R.id.excesoB);

        cienML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumarMililitros(100);
            }
        });

        quinientosML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumarMililitros(500);
            }
        });

        milML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumarMililitros(1000);
            }
        });
    }
    private void sumarMililitros(int cantidad) {
        cantidadTotalML += cantidad; // Incrementamos la cantidad total de mililitros
        if (cantidadTotalML > 100000) { // Limitamos la cantidad total a 100 litros (100000 ml)
            // Calculamos el exceso de agua
            int excesoActual = cantidadTotalML - 100000;
            excesoTotalML += excesoActual; // Sumamos el exceso actual al exceso total
            // Establecemos la cantidad total en 100 litros
            cantidadTotalML = 100000;
        }
        txtper.setText(String.valueOf(cantidadTotalML) + " ml"); // Actualizamos el TextView con la cantidad total

        // Mostramos el exceso de agua en el TextView correspondiente
        excesoB.setText(String.valueOf(excesoTotalML) + " ml en exceso");

        // Establecemos la visibilidad del TextView basándonos en el exceso total
        if (excesoTotalML > 0) {
            excesoB.setVisibility(View.VISIBLE); // Hacer visible el TextView
        } else {
            excesoB.setVisibility(View.INVISIBLE); // Ocultar el TextView
        }

        // Calculamos el porcentaje y actualizamos la ProgressBar
        float porcentaje = ((float) cantidadTotalML / 100000) * 100;
        Prog.setProgress((int) porcentaje);
    }


}
