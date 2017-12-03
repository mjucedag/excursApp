package com.example.mj_uc.excursapp.modelo.Pojo;

import android.support.annotation.NonNull;

public class Profesor {

    private int id;
    private String nombre;

    public Profesor() {
    }

    public Profesor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profesor profesor = (Profesor) o;

        if (id != profesor.id) return false;
        return nombre.equals(profesor.nombre);
    }

    @Override
    public int hashCode() { //crea un número pseudoaleatorio para un objeto
        int result = id;
        result = 31 * result + nombre.hashCode();
        return result;
    }

}
