package com.example.joacopaulinc.trabajopractico2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ActividadPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
    }

    public void Juego(View vista)
    {
        //Vamos a la actividad del punto 2 sin mandar ningun dato
        Intent ActividadDestino;
        ActividadDestino= new Intent(ActividadPrincipal.this,ActividadJuego.class);
        Log.d("ActPrin", "Antes de actividad");
        startActivity(ActividadDestino);
        //No creo que llegue
        Log.d("ActPrin", "despues de actividad");
    }

}
