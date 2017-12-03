package com.example.mj_uc.excursapp.modelo.Pojo;


import java.util.List;

//TODO: parcelable y crear metodos
public class ObjectJson {

    private List<Actividad> actividad;
    private List<Grupo> grupo;
    private List<Profesor> profesor;

    public List<Actividad> getActividad() {
        return actividad;
    }

    public void setActividad(List<Actividad> actividad) {
        this.actividad = actividad;
    }

    public List<Grupo> getGrupo() {
        return grupo;
    }

    public void setGrupo(List<Grupo> grupo) {
        this.grupo = grupo;
    }

    public List<Profesor> getProfesor() {
        return profesor;
    }

    public void setProfesor(List<Profesor> profesor) {
        this.profesor = profesor;
    }

}
