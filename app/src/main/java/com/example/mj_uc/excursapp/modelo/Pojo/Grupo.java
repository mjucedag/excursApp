package com.example.mj_uc.excursapp.modelo.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Grupo implements Parcelable {

    private int id;
    private String nombre;

    public Grupo() {
    }

    public Grupo(int id, String nombre) {
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