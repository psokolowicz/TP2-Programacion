package com.example.joacopaulinc.trabajopractico2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActividadJuego extends AppCompatActivity {

    ImageButton[] ArrBotones;
    Boolean[] ArrImagen;
    //True es refachera
    //False es love
    String Ganador;
    int trues;
    int falses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ActJue", "");
        Log.d("ActJue", "A punto de agregar el contenido");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_juego);

        Log.d("ActJue", "Despues de agregar el contenido");


        //Instanciamos los arreys
        ArrBotones = new ImageButton[9];
        ArrImagen = new Boolean[9];

        //Llenamos ArrBotones con los botones de la actividad
        ArrBotones[0] = (ImageButton) findViewById(R.id.btn1);
        ArrBotones[1] = (ImageButton) findViewById(R.id.btn2);
        ArrBotones[2] = (ImageButton) findViewById(R.id.btn3);
        ArrBotones[3] = (ImageButton) findViewById(R.id.btn4);
        ArrBotones[4] = (ImageButton) findViewById(R.id.btn5);
        ArrBotones[5] = (ImageButton) findViewById(R.id.btn6);
        ArrBotones[6] = (ImageButton) findViewById(R.id.btn7);
        ArrBotones[7] = (ImageButton) findViewById(R.id.btn8);
        ArrBotones[8] = (ImageButton) findViewById(R.id.btn9);

        Log.d("ActJue", "Despues de instanciar el ArrBotones y ArrImagen y Llenar el ArrBotones");

        randomizar();
        while(Gano())
        {
            randomizar();
        }

        Log.d("ActJue", "Despues de randomizar las imagenes");


    }

    public void GanaPorMi(View vista)
    {
        //no anda cuando
        while(!Gano())
        {
            List<Integer> posFalses = new ArrayList<Integer>();
            List<Integer> posTrues = new ArrayList<Integer>();

            for (int i = 0; i <= 8; i++)
            {
                if (!ArrImagen[i])
                {
                    posFalses.add(i);
                }
                else
                {
                    posTrues.add(i);
                }
            }

            if (posFalses.size() > posTrues.size())
            {
                for (int i = 0; i <= posTrues.size(); i++)
                {
                    InvertirCostados(i);
                }
            }
            else
            {
                for (int i = 0; i <= posFalses.size(); i++)
                {
                    InvertirCostados(i);
                }
            }

        }
    }


    public void randomizar()
    {
        //Colocamos al azar las dos fotos en los botones
        Random generadorDeAzar;
        generadorDeAzar = new Random();

        for (int i = 0; i<=8; i++)
        {
            Integer random;
            random = generadorDeAzar.nextInt(2);
            if(random ==1)
            {
                ArrBotones[i].setImageResource(R.drawable.love);
                ArrImagen[i] = false;
            }
            else
            {
                ArrBotones[i].setImageResource(R.drawable.refachera);
                ArrImagen[i] = true;

            }
        }
    }
    public boolean Gano()
    {
        trues = 0;
        falses = 0;
        Ganador="";

        for (int i = 0; i <= 8; i++)
        {
            if (ArrImagen[i])
            {
                trues++;
            }
            else
            {
                falses++;
            }
        }
        if (trues == 9)
        {
            Ganador="refachera";
            return true;
        }
        else
        {
            if(falses == 9)
            {
                Ganador="love";
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    //Resto despues de las modificiaciones de los botones
    //Para no tener que bajar a prograr (Mucho scroll para mi)
    public void Resto()
    {
        if(Gano())
        {
            Bundle PaqueteDeDatos;
            PaqueteDeDatos = new Bundle();
            PaqueteDeDatos.putString("Cual imagen", Ganador);

            Intent ActividadDestino;
            ActividadDestino= new Intent(ActividadJuego.this,ActividadGanaste.class);
            ActividadDestino.putExtras(PaqueteDeDatos);

            startActivity(ActividadDestino);
        }
    }

    public void Accion(View VistaPresionada)
    {
        ImageButton BotonPresionado;
        BotonPresionado = (ImageButton)VistaPresionada;


        String TagBotonPresionado;
        TagBotonPresionado = BotonPresionado.getTag().toString();



        int NumeroBotonPresionado;
        NumeroBotonPresionado = Integer.parseInt(TagBotonPresionado);


        InvertirCostados(NumeroBotonPresionado);

    }
    public void InvertirImageButton(int botonACambiar, boolean cualfoto)
    {
        if (cualfoto)
        {

            ArrBotones[botonACambiar].setImageResource(R.drawable.love);
            ArrImagen[botonACambiar]=false;
        }
        else
        {

            ArrBotones[botonACambiar].setImageResource(R.drawable.refachera);
            ArrImagen[botonACambiar]=true;
        }
    }

    //True es refachera
    //False es love

    public void InvertirCostados(int num)
    {
        switch (num)
        {
            case 0:

                if (ArrImagen[0])
                {
                    InvertirImageButton(0, true);
                }
                else
                {
                    InvertirImageButton(0, false);
                }



                if (ArrImagen[1])
                {
                    InvertirImageButton(1, true);
                }
                else
                {
                    InvertirImageButton(1, false);
                }



                if (ArrImagen[3])
                {
                    InvertirImageButton(3, true);
                }
                else
                {
                    InvertirImageButton(3, false);
                }


                if (ArrImagen[4])
                {
                    InvertirImageButton(4, true);
                }
                else
                {
                    InvertirImageButton(4, false);
                }

                break;


            case 1:
                if (ArrImagen[0])
                {
                    InvertirImageButton(0, true);
                }
                else
                {
                    InvertirImageButton(0, false);
                }


                if (ArrImagen[1])
                {
                    InvertirImageButton(1, true);
                }
                else
                {
                    InvertirImageButton(1, false);
                }


                if (ArrImagen[2])
                {
                    InvertirImageButton(2, true);
                }
                else
                {
                    InvertirImageButton(2, false);
                }


                if (ArrImagen[4])
                {
                    InvertirImageButton(4, true);
                }
                else
                {
                    InvertirImageButton(4, false);
                }

                break;


            case 2:
                if (ArrImagen[1])
                {
                    InvertirImageButton(1, true);
                }
                else
                {
                    InvertirImageButton(1, false);
                }


                if (ArrImagen[2])
                {
                    InvertirImageButton(2, true);
                }
                else
                {
                    InvertirImageButton(2, false);
                }


                if (ArrImagen[4])
                {
                    InvertirImageButton(4, true);
                }
                else
                {
                    InvertirImageButton(4, false);
                }


                if (ArrImagen[5])
                {
                    InvertirImageButton(5, true);
                }
                else
                {
                    InvertirImageButton(5, false);
                }


                break;


            case 3:
                if (ArrImagen[0])
                {
                    InvertirImageButton(0, true);
                }
                else
                {
                    InvertirImageButton(0, false);
                }


                if (ArrImagen[3])
                {
                    InvertirImageButton(3, true);
                }
                else
                {
                    InvertirImageButton(3, false);
                }


                if (ArrImagen[4])
                {
                    InvertirImageButton(4, true);
                }
                else
                {
                    InvertirImageButton(4, false);
                }


                if (ArrImagen[6])
                {
                    InvertirImageButton(6, true);
                }
                else
                {
                    InvertirImageButton(6, false);
                }
                break;


            case 4:
                if (ArrImagen[1])
                {
                    InvertirImageButton(1, true);
                }
                else
                {
                    InvertirImageButton(1, false);
                }


                if (ArrImagen[3])
                {
                    InvertirImageButton(3, true);
                }
                else
                {
                    InvertirImageButton(3, false);
                }


                if (ArrImagen[4])
                {
                    InvertirImageButton(4, true);
                }
                else
                {
                    InvertirImageButton(4, false);
                }


                if (ArrImagen[5])
                {
                    InvertirImageButton(5, true);
                }
                else
                {
                    InvertirImageButton(5, false);
                }

                if (ArrImagen[7])
                {
                    InvertirImageButton(7, true);
                }
                else
                {
                    InvertirImageButton(7, false);
                }
                break;


            case 5:
                if (ArrImagen[2])
                {
                    InvertirImageButton(2, true);
                }
                else
                {
                    InvertirImageButton(2, false);
                }


                if (ArrImagen[4])
                {
                    InvertirImageButton(4, true);
                }
                else
                {
                    InvertirImageButton(4, false);
                }


                if (ArrImagen[5])
                {
                    InvertirImageButton(5, true);
                }
                else
                {
                    InvertirImageButton(5, false);
                }


                if (ArrImagen[8])
                {
                    InvertirImageButton(8, true);
                }
                else
                {
                    InvertirImageButton(8, false);
                }


                break;

            case 6:
                if (ArrImagen[3])
                {
                    InvertirImageButton(3, true);
                }
                else
                {
                    InvertirImageButton(3, false);
                }


                if (ArrImagen[4])
                {
                    InvertirImageButton(4, true);
                }
                else
                {
                    InvertirImageButton(4, false);
                }


                if (ArrImagen[6])
                {
                    InvertirImageButton(6, true);
                }
                else
                {
                    InvertirImageButton(6, false);
                }


                if (ArrImagen[7])
                {
                    InvertirImageButton(7, true);
                }
                else
                {
                    InvertirImageButton(7, false);
                }

                break;

            case 7:
                if (ArrImagen[4])
                {
                    InvertirImageButton(4, true);
                }
                else
                {
                    InvertirImageButton(4, false);
                }


                if (ArrImagen[6])
                {
                    InvertirImageButton(6, true);
                }
                else
                {
                    InvertirImageButton(6, false);
                }


                if (ArrImagen[7])
                {
                    InvertirImageButton(7, true);
                }
                else
                {
                    InvertirImageButton(7, false);
                }


                if (ArrImagen[8])
                {
                    InvertirImageButton(8, true);
                }
                else
                {
                    InvertirImageButton(8, false);
                }

                break;


            case 8:
                if (ArrImagen[4])
                {
                    InvertirImageButton(4, true);
                }
                else
                {
                    InvertirImageButton(4, false);
                }


                if (ArrImagen[5])
                {
                    InvertirImageButton(5, true);
                }
                else
                {
                    InvertirImageButton(5, false);
                }


                if (ArrImagen[7])
                {
                    InvertirImageButton(7, true);
                }
                else
                {
                    InvertirImageButton(7, false);
                }


                if (ArrImagen[8])
                {
                    InvertirImageButton(8, true);
                }
                else
                {
                    InvertirImageButton(8, false);
                }
                break;


            default:
                break;

        }
        //Luego de las modificacion en los botones todas la funcionalidades
        Resto();
    }

}
