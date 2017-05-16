package com.example.joacopaulinc.trabajopractico2;

/**
 * Created by 42374778 on 16/5/2017.
 */

public class Jugador
{
    public String Nombre;
    public int Partidas;
    public int Record;

    public Jugador(String nombre, int partidas, int record) {
        Nombre = nombre;
        Partidas = partidas;
        Record = record;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getPartidas() {
        return Partidas;
    }

    public void setPartidas(int partidas) {
        Partidas = partidas;
    }

    public int getRecord() {
        return Record;
    }

    public void setRecord(int record) {
        Record = record;
    }
}
