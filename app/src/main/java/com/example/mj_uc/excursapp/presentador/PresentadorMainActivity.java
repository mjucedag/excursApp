package com.example.mj_uc.excursapp.presentador;

import com.example.mj_uc.excursapp.MainActivity;
import com.example.mj_uc.excursapp.apirest.APIConnection;
import com.example.mj_uc.excursapp.apirest.WebRequest;
import com.example.mj_uc.excursapp.apirest.WebResponse;
import com.example.mj_uc.excursapp.contrato.ContratoMainActivity;
import com.example.mj_uc.excursapp.modelo.Album;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.example.mj_uc.excursapp.modelo.Pojo.Profesor;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Presentador main activity.
 */
public class PresentadorMainActivity implements ContratoMainActivity.Presentador, WebResponse{

    private ContratoMainActivity.Vista vista;

    @Override
    public void setVista(ContratoMainActivity.Vista vista) {
        this.vista = vista;
    }

    @Override
    public void prepareAlbums() {

        APIConnection.getConnection("https://apirest-mjuceda.c9users.io/db",  WebRequest.GETRequest, this);
    }

    @Override
    public void onResponseService(String s) {
        MainActivity mainActivity = (MainActivity) vista;
        ObjectJson objectJsons = new Gson().fromJson(s, ObjectJson.class);
        List<Album> albumOrdenadoFecha = new ArrayList<>();

        //leer el objeto para convertirlo en los objetos Album (titulo, profesor, imagen)que es lo que mostramos (albumList)

        for (Actividad actividad : objectJsons.getActividad()) {

            Integer id = actividad.getId();
            String titulo = actividad.getTitulo();
            String imagen = actividad.getImg();
            //Nos hace falta recuperar el NOMBRE del profesor, no el ID. pero en actividad solo tenemos el ID
            Integer idProfesor = actividad.getProfesores().get(0);//controla el null
            // metodo (IDPROFESOR), TE DEVUELVA EL NOMBRE DE ESE PROFESOR
            String nombreProfesor = getNombreProfesor(objectJsons, idProfesor);
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
