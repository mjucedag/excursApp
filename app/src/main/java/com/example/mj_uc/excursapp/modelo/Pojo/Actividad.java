package com.example.mj_uc.excursapp.modelo.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Actividad.
 */
public class Actividad implements Parcelable {

    private int id;
    private String img;
    private String titulo;
    private String lugar;
    private String direccion;
    private String fechasalida;
    private String horasalida;
    private String horallegada;
    private String descripcion;
    private List<Integer> idprofesor;
    private List<Integer> idgrupo;

    /**
     * Instantiates a new Actividad.
     */
    public Actividad() {
    }

    /**
     * Instantiates a new Actividad.
     *
     * @param id          the id
     * @param img         the img
     * @param titulo      the titulo
     * @param lugar       the lugar
     * @param direccion   the direccion
     * @param fechasalida the fechasalida
     * @param horasalida  the horasalida
     * @param horallegada the horallegada
     * @param descripcion the descripcion
     * @param idprofesor  the idprofesor
     * @param idgrupo     the idgrupo
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets img.
     *
     * @return the img
     */
    public String getImg() {
        return img;
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
     * Gets lugar.
     *
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Gets direccion.
     *
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Gets fechasalida.
     *
     * @return the fechasalida
     */
    public String getFechasalida() {
        return fechasalida;
    }

    /**
     * Gets horasalida.
     *
     * @return the horasalida
     */
    public String getHorasalida() {
        return horasalida;
    }

    /**
     * Gets horallegada.
     *
     * @return the horallegada
     */
    public String getHorallegada() {
        return horallegada;
    }

    /**
     * Gets descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Gets profesores.
     *
     * @return the profesores
     */
    public List<Integer> getProfesores() {
        return idprofesor;
    }

    /**
     * Gets grupos.
     *
     * @return the grupos
     */
    public List<Integer> getGrupos() {
        return idgrupo;
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
     * Sets img.
     *
     * @param img the img
     */
    public void setImg(String img) {
        this.img = img;
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
     * Sets lugar.
     *
     * @param lugar the lugar
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * Sets direccion.
     *
     * @param direccion the direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Sets fechasalida.
     *
     * @param fechasalida the fechasalida
     */
    public void setFechasalida(String fechasalida) {
        this.fechasalida = fechasalida;
    }

    /**
     * Sets horasalida.
     *
     * @param horasalida the horasalida
     */
    public void setHorasalida(String horasalida) {
        this.horasalida = horasalida;
    }

    /**
     * Sets horallegada.
     *
     * @param horallegada the horallegada
     */
    public void setHorallegada(String horallegada) {
        this.horallegada = horallegada;
    }

    /**
     * Sets descripcion.
     *
     * @param descripcion the descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Sets profesores.
     *
     * @param idprofesor the idprofesor
     */
    public void setProfesores(List<Integer> idprofesor) {
        this.idprofesor = idprofesor;
    }

    /**
     * Sets grupos.
     *
     * @param grupos the grupos
     */
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

    /**
     * Instantiates a new Actividad.
     *
     * @param in the in
     */
    protected Actividad(Parcel in) {
        id = in.readInt();
        img = in.readString();
        titulo = in.readString();
        lugar = in.readString();
        direccion = in.readString();
        fechasalida = in.readString();
        horasalida = in.readString();
        horallegada = in.readString();
        descripcion = in.readString();
        if (in.readByte() == 0x01) {
            idprofesor = new ArrayList<Integer>();
            in.readList(idprofesor, Integer.class.getClassLoader());
        } else {
            idprofesor = null;
        }
        if (in.readByte() == 0x01) {
            idgrupo = new ArrayList<Integer>();
            in.readList(idgrupo, Integer.class.getClassLoader());
        } else {
            idgrupo = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(img);
        dest.writeString(titulo);
        dest.writeString(lugar);
        dest.writeString(direccion);
        dest.writeString(fechasalida);
        dest.writeString(horasalida);
        dest.writeString(horallegada);
        dest.writeString(descripcion);
        if (idprofesor == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(idprofesor);
        }
        if (idgrupo == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(idgrupo);
        }
    }

    /**
     * The constant CREATOR.
     */
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Actividad> CREATOR = new Parcelable.Creator<Actividad>() {
        @Override
        public Actividad createFromParcel(Parcel in) {
            return new Actividad(in);
        }

        @Override
        public Actividad[] newArray(int size) {
            return new Actividad[size];
        }
    };
}