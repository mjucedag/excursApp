package com.example.mj_uc.excursapp.presentador;


import android.content.Intent;
import android.os.Bundle;

import com.example.mj_uc.excursapp.apirest.APIConnection;
import com.example.mj_uc.excursapp.apirest.WebRequest;
import com.example.mj_uc.excursapp.apirest.WebResponse;
import com.example.mj_uc.excursapp.contrato.ContratoConsultaGrupo;
import com.example.mj_uc.excursapp.modelo.Album;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.Grupo;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.example.mj_uc.excursapp.tools.Tools;
import com.example.mj_uc.excursapp.vista.ConsultaGrupo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * The type Presentador consulta grupo.
 */
public class PresentadorConsultaGrupo implements ContratoConsultaGrupo.Presentador, WebResponse {

    private ContratoConsultaGrupo.Vista vista;
    private ConsultaGrupo consultaGrupoActivity;

    @Override
    public void setVista(ContratoConsultaGrupo.Vista vista) {
        this.vista = vista;
    }


    @Override
    public void onResponseService(String response) {

        List<Actividad> actividadesWithGroups = new ArrayList<>();
        List<Integer> idGroups = new ArrayList<>();

        ObjectJson objectJsons = new Gson().fromJson(response, ObjectJson.class);

        Intent intent = consultaGrupoActivity.getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            String nameGroups = b.getString("IDS_QUERY");

            List<String> nameGroupsString = Arrays.asList(nameGroups.split("#"));

            //Get Id Groups
            for (String groupName : nameGroupsString) {
                idGroups.add(getIdGroup(objectJsons, groupName));
            }

            addActividadesWithTheGroupsSelected(actividadesWithGroups, idGroups, objectJsons);

            List<Album> albumOrdenadoFecha = Tools.getAlbumsOfActividades(actividadesWithGroups, objectJsons);

            consultaGrupoActivity.getAlbumList().addAll(albumOrdenadoFecha);

            consultaGrupoActivity.getAdapter().notifyDataSetChanged();
        }
    }

    /**
     * Add actividades with the groups selected.
     *
     * @param actividadesWithGroups the actividades with groups
     * @param idGroups              the id groups
     * @param objectJsons           the object jsons
     */
    private void addActividadesWithTheGroupsSelected(List<Actividad> actividadesWithGroups, List<Integer> idGroups, ObjectJson objectJsons) {
        //Check which actividad has our Id
        for (Actividad actividad : objectJsons.getActividad()) {

            //Disjoint: Returns true if the two specified collections have no elements in common.
            if (!Collections.disjoint(idGroups, actividad.getGrupos())) {
                actividadesWithGroups.add(actividad);
            }
        }
    }

    @Override
    public void doQueryConsult() {
        consultaGrupoActivity = (ConsultaGrupo) vista;

        APIConnection.getConnection("https://apirest-mjuceda.c9users.io/db", WebRequest.GETRequest, this);
    }


    /**
     * Gets id group.
     *
     * @param objectJsons the object jsons
     * @param nameGroup   the name group
     * @return the id group
     */
    public Integer getIdGroup(ObjectJson objectJsons, String nameGroup) {
        for (Grupo grupo : objectJsons.getGrupo()) {
            if (nameGroup.equalsIgnoreCase(grupo.getNombre())) {
                return grupo.getId();
            }
        }
        return null;
    }
}
