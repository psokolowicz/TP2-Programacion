package com.example.joacopaulinc.trabajopractico2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ActividadGanaste extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_ganaste);

        Bundle PaqueteDeDatosRecibidos;
        PaqueteDeDatosRecibidos = this.getIntent().getExtras();

        String CualFoto = PaqueteDeDatosRecibidos.getString("Cual imagen");
        ImageView Visor = (ImageView) findViewById(R.id.imagenganadora);

        if (CualFoto.equals("refachera"))
        {
            Visor.setImageResource(R.drawable.refachera);
        }
        else
        {
            Visor.setImageResource(R.drawable.love);
        }


    }

    public void VolverAHome(View vista)
    {
        Intent ActividadDestino;
        ActividadDestino= new Intent(ActividadGanaste.this,ActividadPrincipal.class);
        startActivity(ActividadDestino);
    }

}
