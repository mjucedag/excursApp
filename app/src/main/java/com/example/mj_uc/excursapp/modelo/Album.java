package com.example.mj_uc.excursapp.modelo;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Album.
 */
public class Album implements Comparable<Album>{
    private int id;
    private String titulo;
    private String profesor;
    private String imagen;
    private String fecha;

    /**
     * Instantiates a new Album.
     */
    public Album(){

    }

    /**
     * Instantiates a new Album.
     *
     * @param id       the id
     * @param titulo   the titulo
     * @param profesor the profesor
     * @param imagen   the imagen
     * @param fecha    the fecha
     */
    public Album(int id, String titulo, String profesor, String imagen, String fecha){
        this.id= id;
        this.titulo=titulo;
        this.profesor= profesor;
        this.imagen=imagen;
        this.fecha = fecha;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets titulo.
     *
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Gets profesor.
     *
     * @return the profesor
     */
    public String getProfesor() {
        return profesor;
    }

    /**
     * Gets imagen.
     *
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Gets fecha.
     *
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets titulo.
     *
     * @param titulo the titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Sets profesor.
     *
     * @param profesor the profesor
     */
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    /**
     * Sets imagen.
     *
     * @param imagen the imagen
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Sets fecha.
     *
     * @param fecha the fecha
     */
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
