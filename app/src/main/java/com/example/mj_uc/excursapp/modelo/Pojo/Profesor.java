package com.example.mj_uc.excursapp.modelo.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The type Profesor.
 */
public class Profesor implements Parcelable {

    private int id;
    private String nombre;

    /**
     * Instantiates a new Profesor.
     */
    public Profesor() {
    }

    /**
     * Instantiates a new Profesor.
     *
     * @param id     the id
     * @param nombre the nombre
     */
    public Profesor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
     * Gets nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
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
     * Sets nombre.
     *
     * @param nombre the nombre
     */
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
    }

    /**
     * Instantiates a new Profesor.
     *
     * @param in the in
     */
    protected Profesor(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
    }

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<Profesor> CREATOR = new Parcelable.Creator<Profesor>() {
        @Override
        public Profesor createFromParcel(Parcel source) {
            return new Profesor(source);
        }

        @Override
        public Profesor[] newArray(int size) {
            return new Profesor[size];
        }
    };
}
