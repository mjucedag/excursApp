package com.example.mj_uc.excursapp.modelo;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Album implements Comparable<Album>{
    private int id;
    private String titulo;
    private String profesor;
    private String imagen;
    private String fecha;

    public Album(){

    }
    public Album(int id, String titulo, String profesor, String imagen, String fecha){
        this.id= id;
        this.titulo=titulo;
        this.profesor= profesor;
        this.imagen=imagen;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }

    public String getProfesor() {
        return profesor;
    }

    public String getImagen() {
        return imagen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public int compareTo(@NonNull Album o) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date converterDate = new Date();
        Date converterDateToCompare = new Date();
        try {
            converterDate = dateFormat.parse(getFecha());
            converterDateToCompare = dateFormat.parse(o.getFecha());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return converterDate.compareTo(converterDateToCompare);
    }
}
