package com.example.mj_uc.excursapp.presentador;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.mj_uc.excursapp.Tools.Tools;
import com.example.mj_uc.excursapp.apirest.WebRequest;
import com.example.mj_uc.excursapp.contrato.ContratoVerActividad;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.Grupo;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.example.mj_uc.excursapp.modelo.Pojo.Profesor;
import com.example.mj_uc.excursapp.vista.VerActividad;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PresentadorVerActividad implements ContratoVerActividad.Presentador {

    private ContratoVerActividad.Vista vista;


    @Override
    public void setVista(ContratoVerActividad.Vista vista) {
        this.vista = vista;
    }

    @Override
    public void prepareView() {

        final VerActividad verActividad = (VerActividad) vista;

        Intent intent = verActividad.getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            final Integer idActividad = b.getInt("ID_ACTIVIDAD");

            AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
                @Override
                protected String doInBackground(String... args) {
                    WebRequest webreq = new WebRequest();
                    String jsonStr = webreq.makeWebServiceCall("https://apirest-mjuceda.c9users.io/db", WebRequest.GETRequest);
                    return jsonStr;
                }
                @Override
                protected void onPostExecute(String s) {
                    populateFields(s, verActividad, idActividad);
                }
            };
            task.execute();
        }
    }

    private void populateFields(String jsonResponse, VerActividad verActividad, Integer idActividad) {
        ObjectJson objectJsons =  new Gson().fromJson(jsonResponse, ObjectJson.class);

        List<Integer> idprofesor = new ArrayList<>();
        List<Integer> idGrupos = new ArrayList<>();

        //Recogemos nuestra actividad
        Actividad actividades = new Actividad();
        for (Actividad actividad : objectJsons.getActividad()) {

            if (idActividad == actividad.getId()) {
                actividades = actividad;
                break;
            }
        }

        //Rellenamos los campos con nuestra actividad
        verActividad.getTitulo().setText(actividades.getTitulo());
        verActividad.getDescripcion().setText(actividades.getDescripcion());
        idprofesor.addAll(actividades.getProfesores());
        idGrupos.addAll(actividades.getGrupos());
        verActividad.getCampoLugar().setText(actividades.getLugar());
        verActividad.getCampoDireccion().setText(actividades.getDireccion());
        verActividad.getCampoFecha().setText(actividades.getFechasalida());
        verActividad.getHoraSalida().setText("Desde las " + actividades.getHorasalida() + " hasta las " + actividades.getHorallegada());
        String nomFoto = actividades.getImg();
        nomFoto = Tools.removeExtension(nomFoto);
        Log.v("foto", nomFoto);
        Context context = verActividad.getFotoAct().getContext();
        int id = context.getResources().getIdentifier(nomFoto, "drawable", context.getPackageName());
        verActividad.getFotoAct().setImageResource(id);

        //Rellenamos los nombres de profesores
        List<Profesor> NOMBREPROFESOR = objectJsons.getProfesor();
        for (Profesor profesor : NOMBREPROFESOR) {
            for (int i = 0; i < idprofesor.size(); i++) {
                if (profesor.getId() == idprofesor.get(i)) {
                    verActividad.getCampoProfesor().setText(verActividad.getCampoProfesor().getText() + " " + profesor.getNombre() + "\t");
                }
            }
        }

        //Rellanamos los nombre de los grupos
        List<Grupo> NOMBREGRUPO = objectJsons.getGrupo();
        for (Grupo grupo : NOMBREGRUPO) {
            for (int i = 0; i < idGrupos.size(); i++) {
                if (grupo.getId() == idGrupos.get(i)) {
                    verActividad.getCampoGrupos().setText(verActividad.getCampoGrupos().getText() + " , " + grupo.getNombre());
                }
            }
        }
    }
}
