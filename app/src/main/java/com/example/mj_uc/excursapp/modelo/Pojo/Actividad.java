package com.example.mj_uc.excursapp.modelo.Pojo;

import android.support.annotation.NonNull;

import java.util.List;

public class Actividad {

    private int id;
    private String img,titulo,lugar,direccion,fechasalida,horasalida,horallegada,descripcion;
    private List<Integer> idprofesor;
    private List<Integer> idgrupo;

    public Actividad() {
    }

    public Actividad(int id, String img, String titulo, String lugar, String direccion, String fechasalida, String horasalida, String horallegada, String descripcion, List<Integer> idprofesor, List<Integer> idgrupo) {
        this.id = id;
        this.img = img;
        this.titulo = titulo;
        this.lugar = lugar;
        this.direccion = direccion;
        this.fechasalida = fechasalida;
        this.horasalida = horasalida;
        this.horallegada = horallegada;
        this.descripcion = descripcion;
        this.idprofesor = idprofesor;
        this.idgrupo = idgrupo;
    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getLugar() {
        return lugar;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFechasalida() {
        return fechasalida;
    }

    public String getHorasalida() {
        return horasalida;
    }

    public String getHorallegada() {
        return horallegada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Integer> getProfesores() {
        return idprofesor;
    }

    public List<Integer> getGrupos() {
        return idgrupo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechasalida(String fechasalida) {
        this.fechasalida = fechasalida;
    }

    public void setHorasalida(String horasalida) {
        this.horasalida = horasalida;
    }

    public void setHorallegada(String horallegada) {
        this.horallegada = horallegada;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setProfesores(List<Integer> idprofesor) {
        this.idprofesor = idprofesor;
    }

    public void setGrupos(List<Integer> grupos) {
        this.idgrupo = idgrupo;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", titulo='" + titulo + '\'' +
                ", lugar='" + lugar + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechasalida='" + fechasalida + '\'' +
                ", horasalida='" + horasalida + '\'' +
                ", horallegada='" + horallegada + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", profesores=" + idprofesor +
                ", grupos=" + idgrupo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actividad that = (Actividad) o;

        if (id != that.id) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;
        if (!titulo.equals(that.titulo)) return false;
        if (!lugar.equals(that.lugar)) return false;
        if (!direccion.equals(that.direccion)) return false;
        if (!fechasalida.equals(that.fechasalida)) return false;
        if (!horasalida.equals(that.horasalida)) return false;
        if (!horallegada.equals(that.horallegada)) return false;
        if (!descripcion.equals(that.descripcion)) return false;
        if (!idprofesor.equals(that.idprofesor)) return false;
        return idgrupo.equals(that.idgrupo);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + titulo.hashCode();
        result = 31 * result + lugar.hashCode();
        result = 31 * result + direccion.hashCode();
        result = 31 * result + fechasalida.hashCode();
        result = 31 * result + horasalida.hashCode();
        result = 31 * result + horallegada.hashCode();
        result = 31 * result + descripcion.hashCode();
        result = 31 * result + idprofesor.hashCode();
        result = 31 * result + idgrupo.hashCode();
        return result;
    }
}
