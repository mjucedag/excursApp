package com.example.mj_uc.excursapp.modelo.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The type Grupo.
 */
public class Grupo implements Parcelable {

    private int id;
    private String nombre;

    /**
     * Instantiates a new Grupo.
     */
    public Grupo() {
    }

    /**
     * Instantiates a new Grupo.
     *
     * @param id     the id
     * @param nombre the nombre
     */
    public Grupo(int id, String nombre) {
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
        return "Grupo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grupo grupo = (Grupo) o;

        if (id != grupo.id) return false;
        return nombre.equals(grupo.nombre);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nombre.hashCode();
        return result;
    }


    /**
     * Instantiates a new Grupo.
     *
     * @param in the in
     */
    protected Grupo(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
    }

    /**
     * The constant CREATOR.
     */
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Grupo> CREATOR = new Parcelable.Creator<Grupo>() {
        @Override
        public Grupo createFromParcel(Parcel in) {
            return new Grupo(in);
        }

        @Override
        public Grupo[] newArray(int size) {
            return new Grupo[size];
        }
    };
}