package com.example.mj_uc.excursapp.presentador;

import com.example.mj_uc.excursapp.MainActivity;
import com.example.mj_uc.excursapp.contrato.ContratoMainActivity;
import com.example.mj_uc.excursapp.Tools.JsonTool;
import com.example.mj_uc.excursapp.modelo.Album;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.example.mj_uc.excursapp.modelo.Pojo.Profesor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PresentadorMainActivity implements ContratoMainActivity.Presentador {

    private ContratoMainActivity.Vista vista;

    @Override
    public void setVista(ContratoMainActivity.Vista vista) {
        this.vista = vista;
    }

    @Override
    public void prepareAlbums() {

        MainActivity mainActivity = (MainActivity) vista;

        ObjectJson objectJsons = JsonTool.readFromFile(mainActivity);
        List<Album> albumOrdenadoFecha = new ArrayList<>();

        //leer el objeto para convertirlo en los objetos Album (titulo, profesor, imagen)que es lo que mostramos (albumList)

        for (Actividad actividad : objectJsons.getActividad()) {

            Integer id = actividad.getId();
            String titulo = actividad.getTitulo();
            String imagen = actividad.getImg();
            //Nos hace falta recuperar el NOMBRE del profesor, no el ID. pero en actividad solo tenemos el ID
            Integer idProfesor = actividad.getProfesores().get(0);//controla el null
            // metodo (IDPROFESOR), TE DEVUELVA EL NOMBRE DE ESE PROFESOR
            String nombreProfesor = this.getNombreProfesor(objectJsons, idProfesor);
            //entonces, cuando ya tengas el nombre, ya puedes crearte tu objeto ALBUM
            String fecha = actividad.getFechasalida();
            albumOrdenadoFecha.add(new Album(id, titulo, nombreProfesor, imagen, fecha));
        }
        //ordenar mis fotos por fecha
        Collections.sort(albumOrdenadoFecha);
        mainActivity.getAlbumList().addAll(albumOrdenadoFecha);


        mainActivity.getAdapter().notifyDataSetChanged();
    }

    @Override
    public String getNombreProfesor(ObjectJson objectJsons, Integer idProfesor) {
        for (Profesor profesor : objectJsons.getProfesor()) {
            if (profesor.getId() == idProfesor) {
                return profesor.getNombre();
            }
        }
        return "";
    }
}
