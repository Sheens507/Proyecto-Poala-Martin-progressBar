package com.example.proyectoprogressbar;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class consejos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consejos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener referencia del WebView desde el layout XML
        WebView webView = findViewById(R.id.ConsejosView);

        // Habilitar JavaScript en el WebView (opcional, dependiendo de la página web)
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Configurar un WebViewClient para manejar errores de carga de página
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                // Mostrar un mensaje de error al usuario
                Toast.makeText(consejos.this, "Error al cargar la página web: " + description, Toast.LENGTH_SHORT).show();
            }
        });

        // Cargar la URL deseada en el WebView
        webView.loadUrl("https://www.fundacionaquae.org/wiki/ahorrar-agua-ahora-escasea/");
    }
}