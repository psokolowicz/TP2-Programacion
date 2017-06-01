package com.example.joacopaulinc.trabajopractico2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActividadGanaste extends AppCompatActivity {


    private SQLite DBaccess;
    private SQLiteDatabase database;

    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_ganaste);

        Bundle PaqueteDeDatosRecibidos;
        PaqueteDeDatosRecibidos = this.getIntent().getExtras();

        String CualFoto = PaqueteDeDatosRecibidos.getString("Cual imagen");
        int nuevoRecord = PaqueteDeDatosRecibidos.getInt("nuevoRecord");
        int partidas = PaqueteDeDatosRecibidos.getInt("partidas");
        nombre = PaqueteDeDatosRecibidos.getString("nombre");


        ImageView Visor = (ImageView) findViewById(R.id.imagenganadora);

        if (CualFoto.equals("refachera"))
        {
            Visor.setImageResource(R.drawable.refachera);
        }
        else
        {
            Visor.setImageResource(R.drawable.love);
        }

        TextView record = (TextView) findViewById(R.id.record);
        record.setText("Tu nuevo record es: " + nuevoRecord + " movimientos");

        TextView cantPartidas = (TextView) findViewById(R.id.partidasRegistradas);
        cantPartidas.setText("Cantidad de partidas: " + partidas);

    }



    public void VolverAHome(View vista)
    {
        Intent ActividadDestino;
        ActividadDestino= new Intent(ActividadGanaste.this,ActividadPrincipal.class);
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
