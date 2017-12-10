package com.example.mj_uc.excursapp.presentador;


import android.content.Intent;
import android.os.Bundle;

import com.example.mj_uc.excursapp.apirest.APIConnection;
import com.example.mj_uc.excursapp.apirest.WebRequest;
import com.example.mj_uc.excursapp.apirest.WebResponse;
import com.example.mj_uc.excursapp.contrato.ContratoConsultaGrupo;
import com.example.mj_uc.excursapp.contrato.ContratoVerActividad;
import com.example.mj_uc.excursapp.modelo.Album;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.example.mj_uc.excursapp.modelo.Pojo.Profesor;
import com.example.mj_uc.excursapp.vista.ConsultaGrupo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class PresentadorConsultaGrupo implements ContratoConsultaGrupo.Presentador, WebResponse {

    public static final int QUERY_GROUP = 1;

    private ContratoConsultaGrupo.Vista vista;
    private ConsultaGrupo consultaGrupoActivity;
    private Integer typeResponseService;

    @Override
    public void setVista(ContratoConsultaGrupo.Vista vista) {
        this.vista = vista;
    }


    @Override
    public void onResponseService(String response) {

        switch (typeResponseService){
            case QUERY_GROUP:
                onQueryGroupResponseService(response);
                break;

        }
    }

    private void onQueryGroupResponseService(String response) {

        List<Actividad> actividadesWithGroups = new ArrayList<>();
        List<Integer> idGroups = new ArrayList<>();

        Intent intent = consultaGrupoActivity.getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            String ids = b.getString("IDS_QUERY");

            List<String> idGroupsString = Arrays.asList(ids.split("#"));

            for(String s : idGroupsString) {
                idGroups.add(Integer.valueOf(s));
            }

            ObjectJson objectJsons = new Gson().fromJson(response, ObjectJson.class);

            for (Actividad actividad : objectJsons.getActividad()){

                //Disjoint: Returns true if the two specified collections have no elements in common.
                if(!Collections.disjoint(idGroups, actividad.getGrupos())){
                    actividadesWithGroups.add(actividad);
                }
            }

            //Here we have all the Actividades to show
            List<Album> albumOrdenadoFecha = new ArrayList<>();

            //leer el objeto para convertirlo en los objetos Album (titulo, profesor, imagen)que es lo que mostramos (albumList)

            for (Actividad actividad : actividadesWithGroups) {

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

            consultaGrupoActivity.getAlbumList().addAll(albumOrdenadoFecha);

            consultaGrupoActivity.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void doQueryConsult(){
        consultaGrupoActivity = (ConsultaGrupo) vista;

        typeResponseService = PresentadorConsultaGrupo.QUERY_GROUP;
        APIConnection.getConnection("https://apirest-mjuceda.c9users.io/db", WebRequest.GETRequest, this);
    }

    public String getNombreProfesor(ObjectJson objectJsons, Integer idProfesor) {
        for (Profesor profesor : objectJsons.getProfesor()) {
            if (profesor.getId() == idProfesor) {
                return profesor.getNombre();
            }
        }
        return "";
    }
}
