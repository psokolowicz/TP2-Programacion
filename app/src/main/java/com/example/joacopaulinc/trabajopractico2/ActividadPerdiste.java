package com.example.joacopaulinc.trabajopractico2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActividadPerdiste extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_perdiste);

        Bundle PaqueteDeDatosRecibidos;
        PaqueteDeDatosRecibidos = this.getIntent().getExtras();

        int Record = PaqueteDeDatosRecibidos.getInt("record");
        int partidas = PaqueteDeDatosRecibidos.getInt("partidas");
        int movimientosActuales = PaqueteDeDatosRecibidos.getInt("movimientosActuales");
        String nombre = PaqueteDeDatosRecibidos.getString("nombre");

        TextView record = (TextView) findViewById(R.id.record);
        record.setText("Tu record es: " + record + " movimientos");

        TextView movAct = (TextView) findViewById(R.id.movActuales);
        movAct.setText("En esta partida hiciste: " + movAct + " movimientos");

        TextView cantPartidas = (TextView) findViewById(R.id.partidasRegistradas);
        cantPartidas.setText("Cantidad de partidas: " + partidas);

    }

    public void VolverAHome(View vista)
    {
        Intent ActividadDestino;
        ActividadDestino= new Intent(ActividadPerdiste.this,ActividadPrincipal.class);
        startActivity(ActividadDestino);
    }
}
