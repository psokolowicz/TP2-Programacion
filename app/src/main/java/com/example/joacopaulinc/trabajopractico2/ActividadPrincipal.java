package com.example.joacopaulinc.trabajopractico2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class ActividadPrincipal extends AppCompatActivity {

    EditText txtCaptcha;
    int num1;
    int num2;
    private SQLite DBaccess;
    private SQLiteDatabase database;

    int cantPartidas;
    int record;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);


        txtCaptcha = (EditText) findViewById(R.id.Captcha);
        generarCaptcha();

    }

    public void generarCaptcha()
    {
        Random random = new Random();
        num1 = random.nextInt(10);
        num2 = random.nextInt(10);

        if (num1 == 0 || num2 == 0)
            //Recurcion baby
            generarCaptcha();

        String hint = "¿" + num1 + " + " + num2 + "?";
        txtCaptcha.setHint(hint);
    }
	
	public boolean checkeaCaptcha(int res)
    {
        if (res == (num1 + num2) )
			return true;
		return false;
    }


    public void Juego(View vista)
    {
        EditText txtJugador  = (EditText) findViewById(R.id.txtJugador);
        String Jugador =  txtJugador.getText().toString().trim();
        String captchaStr = txtCaptcha.getText().toString();
        int Suma = 0;

        if (Jugador.isEmpty() || captchaStr.isEmpty())
        {
            Toast.makeText(this, "Verifique sus datos", Toast.LENGTH_SHORT).show();
            generarCaptcha();
        }
        else
        {
            Suma = Integer.parseInt(txtCaptcha.getText().toString().trim());

            if(!checkeaCaptcha(Suma))
            {

                generarCaptcha();
                txtCaptcha.setText("");
                Toast.makeText(this, "Vuelva a poner un captcha por favor, gracias, los queremos", Toast.LENGTH_LONG).show();
            }
            else
            {
                try
                {
                    if (baseDeDatosAbierta())
                    {
                        if (exiteJugador(Jugador))
                        {
                            sumarPartida(Jugador);
                        }
                        else
                        {
                            guardaJugador(Jugador);
                        }

                        cantPartidas = obtenerPartidas(Jugador);
                        record = obtenerRecord(Jugador);

                        database.close();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(this, "Error, pasese a constru", Toast.LENGTH_SHORT);
                }

                Bundle paqueteJugador = new Bundle();
                paqueteJugador.putString("nombreJugador", Jugador);
                paqueteJugador.putInt("cantidadPartidas", cantPartidas);
                paqueteJugador.putInt("record", record);

                //Vamos a la actividad del punto 2 sin mandar ningun dato
                Intent ActividadDestino;
                ActividadDestino= new Intent(ActividadPrincipal.this,ActividadJuego.class);
                ActividadDestino.putExtras(paqueteJugador);
                Log.d("ActPrin", "Antes de actividad");
                startActivity(ActividadDestino);
                //No creo que llegue
                Log.d("ActPrin", "despues de actividad");
            }
        }

    }

    public void guardaJugador(String nombre)
    {
        ContentValues nuevoRegistro;
        nuevoRegistro = new ContentValues();

        nuevoRegistro.put("nombre", nombre);
        nuevoRegistro.put("partidas", 1);
        nuevoRegistro.put("record", 2147483647);

        database.insert("jugadores", null, nuevoRegistro);

    }


    public boolean exiteJugador(String nombrePlayer)
    {
        Cursor conjuntoDeRegistros;
        conjuntoDeRegistros = database.rawQuery("select nombre from jugadores;", null);
        String nombreJugadorActual = "";
        if (conjuntoDeRegistros.moveToFirst() == true)
        {
            do
            {
                nombreJugadorActual = conjuntoDeRegistros.getString(0);
                if (nombreJugadorActual.equals(nombrePlayer))
                    return true;
            }
            while (conjuntoDeRegistros.moveToNext() == true);

        }

        return false;
    }

    private void sumarPartida(String nombrePlayer)
    {
        Cursor conjuntoDeRegistros;
        conjuntoDeRegistros = database.rawQuery("select partidas from jugadores WHERE nombre = '" + nombrePlayer + "';" , null);
        int partidasActual = 0;
        if (conjuntoDeRegistros.moveToFirst() == true)
        {
            do
            {
                partidasActual = conjuntoDeRegistros.getInt(0);

            }
            while (conjuntoDeRegistros.moveToNext() == true);

        }

        partidasActual++;

        ContentValues nuevoRegistro;
        nuevoRegistro = new ContentValues();

        nuevoRegistro.put("partidas", partidasActual);

        database.update("jugadores", nuevoRegistro, "nombre = '" + nombrePlayer + "'", null);

    }

    private int obtenerPartidas (String nombrePlayer)
    {
        Cursor conjuntoDeRegistros;
        conjuntoDeRegistros = database.rawQuery("select partidas from jugadores WHERE nombre = '" + nombrePlayer + "';" , null);
        int partidasActual = 0;
        if (conjuntoDeRegistros.moveToFirst() == true)
        {
            do
            {
                partidasActual = conjuntoDeRegistros.getInt(0);

            }
            while (conjuntoDeRegistros.moveToNext() == true);

        }
        return partidasActual;

    }

    private int obtenerRecord (String nombrePlayer)
    {
        Cursor conjuntoDeRegistros;
        conjuntoDeRegistros = database.rawQuery("select record from jugadores WHERE nombre = '" + nombrePlayer + "';" , null);
        int recordEste = 0;
        if (conjuntoDeRegistros.moveToFirst() == true)
        {
            do
            {
                recordEste = conjuntoDeRegistros.getInt(0);

            }
            while (conjuntoDeRegistros.moveToNext() == true);

        }
        return recordEste;

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
}
