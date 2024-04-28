package com.example.proyectoprogressbar;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;


import androidx.appcompat.app.AppCompatActivity;

public class TimerB extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;
    private Button tresMinButton;
    private Button cincoMinButton;
    private int totalTimeInSeconds = 0;
    private int elapsedTimeInSeconds = 0;
    private final int intervalInMillis = 1000; // Intervalo de actualización en milisegundos
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_b);

        // Obtener referencias de las vistas
        progressBar = findViewById(R.id.pro_bar);
        progressText = findViewById(R.id.pro_text);
        tresMinButton = findViewById(R.id.tres_min);
        cincoMinButton = findViewById(R.id.cinco_min);

        // Configurar el listener del botón de 3 minutos
        tresMinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimerDuration(3 * 60);
                resetTimer();
            }
        });

        // Configurar el listener del botón de 5 minutos
        cincoMinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimerDuration(5 * 60);
                resetTimer();
            }
        });
    }

    // Método para establecer la duración del temporizador en segundos
    private void setTimerDuration(int seconds) {
        totalTimeInSeconds = seconds;
    }

    // Método para iniciar el temporizador
    public void startTimer(View view) {
        if (totalTimeInSeconds > 0) {
            elapsedTimeInSeconds = 0;
            handler.postDelayed(timerRunnable, intervalInMillis);
        } else {
            // Si no se ha seleccionado una duración válida, mostrar un mensaje de error
            Toast.makeText(this, "Please select a timer duration.", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para detener el temporizador
    public void stopTimer(View view) {
        handler.removeCallbacks(timerRunnable);
    }

    // Método para resetear el temporizador
    private void resetTimer() {
        elapsedTimeInSeconds = 0;
        progressBar.setProgress(0);
        progressText.setText("00:00");
        handler.removeCallbacks(timerRunnable);
    }

    // Runnable para actualizar el temporizador
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if (elapsedTimeInSeconds < totalTimeInSeconds) {
                int progress = (int) (((float) elapsedTimeInSeconds / totalTimeInSeconds) * 100);
                progressBar.setProgress(progress);
                progressText.setText(formatTime(elapsedTimeInSeconds));
                elapsedTimeInSeconds++;
                handler.postDelayed(this, intervalInMillis);
            } else {
                playAlarmSound();
                handler.removeCallbacks(this);
            }
        }
    };

    // Método para reproducir el sonido de la alarma durante 5 segundos
    private void playAlarmSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.alarm_sound);
        mediaPlayer.start();

        // Detener la reproducción después de 5 segundos
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
            }
        }, 5000); // 5000 milisegundos = 5 segundos
    }



    // Método para formatear el tiempo en formato "mm:ss"
    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }
}
