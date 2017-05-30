package com.example.joacopaulinc.trabajopractico2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActividadChanta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_chanta);
    }

    public void VolverAHome(View vista)
    {
        Intent ActividadDestino;
        ActividadDestino= new Intent(ActividadChanta.this,ActividadPrincipal.class);
        startActivity(ActividadDestino);
    }
}
