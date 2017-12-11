package com.example.mj_uc.excursapp.presentador;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.mj_uc.excursapp.MainActivity;
import com.example.mj_uc.excursapp.apirest.APIConnection;
import com.example.mj_uc.excursapp.apirest.WebRequest;
import com.example.mj_uc.excursapp.apirest.WebResponse;
import com.example.mj_uc.excursapp.contrato.ContratoCreateActivity;
import com.example.mj_uc.excursapp.contrato.ContratoEditarActividad;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.Grupo;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.example.mj_uc.excursapp.modelo.Pojo.Profesor;
import com.example.mj_uc.excursapp.tools.Tools;
import com.example.mj_uc.excursapp.vista.CreateActivity;
import com.example.mj_uc.excursapp.vista.EditarActividad;
import com.example.mj_uc.excursapp.vista.VistaImagenes;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Presentador editar activity.
 */
public class PresentadorEditarActividad implements ContratoEditarActividad.Presentador, WebResponse {

    private Integer idActividad;

    /**
     * The constant PREPARE_VIEW.
     */
    public static final int PREPARE_VIEW = 1;
    /**
     * The constant CREATE_ACTIVITY.
     */
    public static final int CREATE_ACTIVITY = 2;

    private ContratoEditarActividad.Vista vista;
    private EditarActividad editarActividad;

    private Integer typeResponseService;

    @Override
    public void setVista(ContratoEditarActividad.Vista vista) {
        this.vista = vista;
    }


    @Override
    public void saveActivity() {

        Actividad actividad = editarActividad.getActividades();

        actividad.setTitulo(editarActividad.getTituloAct().getText().toString());
        actividad.setDescripcion(editarActividad.getDescripcion().getText().toString());
        actividad.setDireccion(editarActividad.getDireccion().getText().toString());
        actividad.setLugar(editarActividad.getLugarActividad().getText().toString());
        actividad.setFechasalida(editarActividad.getFechaSalida().getText().toString().substring(19));
        actividad.setHorasalida(editarActividad.getHoraSalida().getText().toString());
        actividad.setHorallegada(editarActividad.getHoraLlegada().getText().toString());
        actividad.setGrupos(devIdGrupo());
        actividad.setProfesores(devIdProfesor());
        // Generamos el JSON
        String jsonString = new Gson().toJson(actividad);
        Toast.makeText(editarActividad, "...", Toast.LENGTH_SHORT).show();

        typeResponseService = PresentadorEditarActividad.CREATE_ACTIVITY;
        APIConnection.getConnection("https://apirest-mjuceda.c9users.io/actividad", WebRequest.PUTRequest, this, jsonString);
    }

    @Override
    public void goToVistaImagenes() {

        Intent i = new Intent(editarActividad, VistaImagenes.class);
        i.putExtra("tituloAct", editarActividad.getTituloAct().getText().toString());
        i.putExtra("lugar", editarActividad.getLugarActividad().getText().toString());
        i.putExtra("direccion", editarActividad.getDireccion().getText().toString());
        i.putExtra("profesores", editarActividad.getProfesores().getText().toString());
        i.putExtra("grupos", editarActividad.getGrupos().getText().toString());
        i.putExtra("fecha", editarActividad.getFechaSalida().getText().toString());
        i.putExtra("horaSalida", editarActividad.getHoraSalida().getText().toString());
        i.putExtra("horaLlegada", editarActividad.getHoraLlegada().getText().toString());
        i.putExtra("descripcion", editarActividad.getDescripcion().getText().toString());
        editarActividad.startActivity(i);
    }

    @Override
    public void recogerDatosActividad() {

        editarActividad = (EditarActividad) vista;

        Intent i = editarActividad.getIntent();
        Bundle b = i.getExtras();

        if (b != null){
            idActividad = b.getInt("idActividad");
            // ESTA PARTE IRIA ANTES DE HACER CUALQUIER PETICION, YA QUE TIENE EL ID
            typeResponseService = PresentadorEditarActividad.PREPARE_VIEW;
            APIConnection.getConnection("https://apirest-mjuceda.c9users.io/db", WebRequest.GETRequest, this);
        }
    }

    @Override
    public void onResponseService(String response) {

        switch (typeResponseService) {
            case PREPARE_VIEW:
                onPrepareViewResponseService(response);
                break;
            case CREATE_ACTIVITY:
                onCreateViewResponseService(response);
                break;
        }
    }

    private void onCreateViewResponseService(String response) {
        Toast.makeText(editarActividad, "¡EDITADO CON EXITO!", Toast.LENGTH_LONG).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(editarActividad, MainActivity.class);
                editarActividad.startActivity(i);
            }
        }, 2000);
    }

    private void onPrepareViewResponseService(String response) {

        //We get the whole DB in order to get all elements and avoid several call to API Rest
        editarActividad.setObjectJson(new Gson().fromJson(response, ObjectJson.class));

        //Get the actividad selected to be edited
        Actividad actividades = editarActividad.getActividades();

        for(Actividad a : editarActividad.getObjectJson().getActividad()) {
            if(a.getId() == idActividad) {
                actividades = a;
                break;
            }
        }

        editarActividad.getTituloAct().setText(actividades.getTitulo());
        editarActividad.getDescripcion().setText(actividades.getDescripcion());
        editarActividad.getIdprofesor().addAll(actividades.getProfesores());
        editarActividad.getIdGrupos().addAll(actividades.getGrupos());
        editarActividad.getLugarActividad().setText(actividades.getLugar());
        editarActividad.getDireccion().setText(actividades.getDireccion());
        editarActividad.getFechaSalida().setText("Fecha de Salida -> " + actividades.getFechasalida());
        editarActividad.getHoraSalida().setText(actividades.getHorasalida().toString());
        editarActividad.getHoraLlegada().setText(actividades.getHorallegada().toString());
        String nomFoto = actividades.getImg();
        nomFoto = Tools.removeExtension(nomFoto);

        Context context = editarActividad.getImageView().getContext();
        int id = context.getResources().getIdentifier(nomFoto, "drawable", context.getPackageName());
        editarActividad.getImageView().setImageResource(id);
        
        setProfesoresChecked();
        setGruposChecked();

        // DEVUELVE EL ARRAY DE PROFESORES
        editarActividad.setProfesoresArray(getArrayNombreProfesores());
        // DEVUELVE EL ARRAY DE GRUPOS
        editarActividad.setGrupo(getArrayNombreGrupos());
    }

    private void setGruposChecked() {

        List<Grupo> NOMBREGRUPO = editarActividad.getObjectJson().getGrupo();
        for (Grupo grupo : NOMBREGRUPO) {
            for (int i = 0; i < editarActividad.getIdGrupos().size(); i++) {
                if (grupo.getId() == editarActividad.getIdGrupos().get(i)){
                    editarActividad.getGrupos().setText(editarActividad.getGrupos().getText() + " , " + grupo.getNombre());
                }
            }
        }
    }

    private void setProfesoresChecked() {

        List<Profesor> NOMBREPRFESOR = editarActividad.getObjectJson().getProfesor();
        for (Profesor profesor: NOMBREPRFESOR) {
            for (int i = 0; i < editarActividad.getIdprofesor().size(); i++) {
                if (profesor.getId()== editarActividad.getIdprofesor().get(i)){
                    editarActividad.getProfesores().setText(editarActividad.getProfesores().getText() + " " + profesor.getNombre());
                }
            }
        }
    }

    private String[] getArrayNombreProfesores() {

        ObjectJson objectJson = editarActividad.getObjectJson();
        List<String> nombreProfesores = new ArrayList<>();

        for (Profesor profesor : objectJson.getProfesor()) {
            nombreProfesores.add(profesor.getNombre());
        }

        Collections.sort(nombreProfesores);
        return nombreProfesores.toArray(new String[nombreProfesores.size()]);
    }

    private String[] getArrayNombreGrupos() {

        ObjectJson objectJson = editarActividad.getObjectJson();
        List<String> nombreGrupos = new ArrayList<>();

        for (Grupo grupo : objectJson.getGrupo()) {
            nombreGrupos.add(grupo.getNombre());
        }

        Collections.sort(nombreGrupos);
        return nombreGrupos.toArray(new String[nombreGrupos.size()]);
    }

    private List<Integer> devIdProfesor() {

        List<Profesor> NOMBREPRFESOR = editarActividad.getObjectJson().getProfesor();
        List<Integer> idProfesores = new ArrayList<>();

        for (int i = 0; i < NOMBREPRFESOR.size(); i++) {
            for (int j = 0; j < editarActividad.getNombresProfesores().size(); j++) {
                if ((NOMBREPRFESOR.get(i).getNombre()).equalsIgnoreCase(editarActividad.getNombresProfesores().get(j))) {
                    idProfesores.add(NOMBREPRFESOR.get(i).getId());
                }
            }
        }

        return idProfesores;
    }

    private List<Integer> devIdGrupo() {

        List<Grupo> NOMBREGRUPO = editarActividad.getObjectJson().getGrupo();
        List<Integer> idGrupos = new ArrayList<>();

        for (int i = 0; i < NOMBREGRUPO.size(); i++) {
            for (int j = 0; j < editarActividad.getNombresGrupos().size(); j++) {
                if ((NOMBREGRUPO.get(i).getNombre()).equalsIgnoreCase(editarActividad.getNombresGrupos().get(j))) {
                    idGrupos.add(NOMBREGRUPO.get(i).getId());
                }
            }
        }

        return idGrupos;
    }
}
