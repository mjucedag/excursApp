package com.example.mj_uc.excursapp.modelo.Pojo;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Object json.
 */
public class ObjectJson implements Parcelable {

    private List<Actividad> actividad;
    private List<Grupo> grupo;
    private List<Profesor> profesor;

    /**
     * Gets actividad.
     *
     * @return the actividad
     */
    public List<Actividad> getActividad() {
        return actividad;
    }

    /**
     * Sets actividad.
     *
     * @param actividad the actividad
     */
    public void setActividad(List<Actividad> actividad) {
        this.actividad = actividad;
    }

    /**
     * Gets grupo.
     *
     * @return the grupo
     */
    public List<Grupo> getGrupo() {
        return grupo;
    }

    /**
     * Sets grupo.
     *
     * @param grupo the grupo
     */
    public void setGrupo(List<Grupo> grupo) {
        this.grupo = grupo;
    }

    /**
     * Gets profesor.
     *
     * @return the profesor
     */
    public List<Profesor> getProfesor() {
        return profesor;
    }

    /**
     * Sets profesor.
     *
     * @param profesor the profesor
     */
    public void setProfesor(List<Profesor> profesor) {
        this.profesor = profesor;
    }

    /**
     * Instantiates a new Object json.
     */
    public ObjectJson(){};


    /**
     * Instantiates a new Object json.
     *
     * @param in the in
     */
    protected ObjectJson(Parcel in) {
        if (in.readByte() == 0x01) {
            actividad = new ArrayList<Actividad>();
            in.readList(actividad, Actividad.class.getClassLoader());
        } else {
            actividad = null;
        }
        if (in.readByte() == 0x01) {
            grupo = new ArrayList<Grupo>();
            in.readList(grupo, Grupo.class.getClassLoader());
        } else {
            grupo = null;
        }
        if (in.readByte() == 0x01) {
            profesor = new ArrayList<Profesor>();
            in.readList(profesor, Profesor.class.getClassLoader());
        } else {
            profesor = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (actividad == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(actividad);
        }
        if (grupo == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(grupo);
        }
        if (profesor == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(profesor);
        }
    }

    /**
     * The constant CREATOR.
     */
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ObjectJson> CREATOR = new Parcelable.Creator<ObjectJson>() {
        @Override
        public ObjectJson createFromParcel(Parcel in) {
            return new ObjectJson(in);
        }

        @Override
        public ObjectJson[] newArray(int size) {
            return new ObjectJson[size];
        }
    };
}
