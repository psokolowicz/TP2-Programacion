package com.example.joacopaulinc.trabajopractico2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ActividadPrincipal extends AppCompatActivity {

    EditText txtCaptcha;
    int num1;
    int num2;
	
	

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

        int Suma = Integer.parseInt(txtCaptcha.getText().toString().trim());

        if(!checkeaCaptcha(Suma))
        {
            generarCaptcha();
            txtCaptcha.setText("");
            Toast.makeText(this, "Vuelva a poner un captcha por favor, gracias, los queremos", Toast.LENGTH_LONG).show();
        }
		else
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

}
