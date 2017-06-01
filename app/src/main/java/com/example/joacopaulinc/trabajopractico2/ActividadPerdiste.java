package com.example.joacopaulinc.trabajopractico2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ActividadPerdiste extends AppCompatActivity {

    private SQLite DBaccess;
    private SQLiteDatabase database;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_perdiste);

        Bundle PaqueteDeDatosRecibidos;
        PaqueteDeDatosRecibidos = this.getIntent().getExtras();

        int Record = PaqueteDeDatosRecibidos.getInt("record");
        int partidas = PaqueteDeDatosRecibidos.getInt("partidas");
        int movimientosActuales = PaqueteDeDatosRecibidos.getInt("movimientosActuales");
        nombre = PaqueteDeDatosRecibidos.getString("nombre");

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

    public void BorrarHistorial(View vista)
    {
        try
        {
            if (baseDeDatosAbierta())
            {
                borrarHistorial(nombre);

                database.close();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error, pasese a constru", Toast.LENGTH_SHORT);
        }
    }

    public Boolean baseDeDatosAbierta()
    {
        Boolean respuesta;
        DBaccess = new SQLite(this, "jugadores", null, 1);
        database = DBaccess.getWritableDatabase();

        if (database != null)
            respuesta = true;
        else
            respuesta = false;

        return respuesta;
    }

    private void borrarHistorial (String nombrePlayer)
    {
        database.delete("judadores", "nombre = " + nombrePlayer, null);
        VolverAHome(null);

    }
}
