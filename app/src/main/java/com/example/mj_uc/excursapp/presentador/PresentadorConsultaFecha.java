package com.example.mj_uc.excursapp.presentador;


import android.content.Intent;
import android.os.Bundle;

import com.example.mj_uc.excursapp.apirest.APIConnection;
import com.example.mj_uc.excursapp.apirest.WebRequest;
import com.example.mj_uc.excursapp.apirest.WebResponse;
import com.example.mj_uc.excursapp.contrato.ContratoConsultaFecha;
import com.example.mj_uc.excursapp.contrato.ContratoConsultaGrupo;
import com.example.mj_uc.excursapp.modelo.Album;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.Grupo;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.example.mj_uc.excursapp.tools.Tools;
import com.example.mj_uc.excursapp.vista.ConsultaFecha;
import com.example.mj_uc.excursapp.vista.ConsultaGrupo;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * The type Presentador consulta fecha.
 */
public class PresentadorConsultaFecha implements ContratoConsultaFecha.Presentador, WebResponse {

    private ContratoConsultaFecha.Vista vista;
    private ConsultaFecha consultaFechaActivity;

    @Override
    public void setVista(ContratoConsultaFecha.Vista vista) {
        this.vista = vista;
    }


    @Override
    public void onResponseService(String response) {

        List<Actividad> actividadesWithDate = new ArrayList<>();
        ObjectJson objectJsons = new Gson().fromJson(response, ObjectJson.class);

        Intent intent = consultaFechaActivity.getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            String dates = b.getString("IDS_QUERY");

            List<String> listDate = Arrays.asList(dates.split("#"));

            addActividadesWithTheDatesSelected(actividadesWithDate, listDate, objectJsons);
            List<Album> albumOrdenadoFecha = Tools.getAlbumsOfActividades(actividadesWithDate, objectJsons);

            consultaFechaActivity.getAlbumList().addAll(albumOrdenadoFecha);

            consultaFechaActivity.getAdapter().notifyDataSetChanged();
        }
    }

    private void addActividadesWithTheDatesSelected(List<Actividad> actividadesWithDate, List<String> listDate, ObjectJson objectJsons) {

        //Check which actividad has our date
        for (Actividad actividad : objectJsons.getActividad()) {

            try {

                //Necessary apply format in order to compare with selected dates.
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date date = formatter.parse(actividad.getFechasalida());
                String dateFormatted = formatter.format(date);

                if (listDate.contains(dateFormatted)) {
                    actividadesWithDate.add(actividad);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void doQueryConsult() {
        consultaFechaActivity = (ConsultaFecha) vista;

        APIConnection.getConnection("https://apirest-mjuceda.c9users.io/db", WebRequest.GETRequest, this);
    }
}
