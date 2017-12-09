package com.example.mj_uc.excursapp.presentador;

import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.example.mj_uc.excursapp.MainActivity;
import com.example.mj_uc.excursapp.apirest.APIConnection;
import com.example.mj_uc.excursapp.apirest.WebRequest;
import com.example.mj_uc.excursapp.apirest.WebResponse;
import com.example.mj_uc.excursapp.contrato.ContratoCreateActivity;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.Grupo;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.example.mj_uc.excursapp.modelo.Pojo.Profesor;
import com.example.mj_uc.excursapp.vista.CreateActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Presentador create activity.
 */
public class PresentadorCreateActivity implements ContratoCreateActivity.Presentador, WebResponse {

    /**
     * The constant PREPARE_VIEW.
     */
    public static final int PREPARE_VIEW = 1;
    /**
     * The constant CREATE_ACTIVITY.
     */
    public static final int CREATE_ACTIVITY = 2;

    private ContratoCreateActivity.Vista vista;
    private CreateActivity createActivity;

    private Integer typeResponseService;

    @Override
    public void setVista(ContratoCreateActivity.Vista vista) {
        this.vista = vista;
    }

    @Override
    public void prepareView() {
        createActivity = (CreateActivity) vista;
        typeResponseService = PresentadorCreateActivity.PREPARE_VIEW;
        APIConnection.getConnection("https://apirest-mjuceda.c9users.io/db", WebRequest.GETRequest, this);
    }

    @Override
    public void saveActivity() {

        Actividad actividad = createActivity.getActividad();

        actividad.setTitulo(createActivity.getTituloAct().getText().toString());
        actividad.setDescripcion(createActivity.getDescripcion().getText().toString());
        actividad.setDireccion(createActivity.getDireccion().getText().toString());
        actividad.setLugar(createActivity.getLugarActividad().getText().toString());
        actividad.setFechasalida(createActivity.getFechaSalida().getText().toString().substring(19));
        actividad.setHorasalida(createActivity.getHoraSalida().getText().toString());
        actividad.setHorallegada(createActivity.getHoraLlegada().getText().toString());
        actividad.setGrupos(devIdGrupo());
        actividad.setProfesores(devIdProfesor());
        // Generamos el JSON
        String jsonString = new Gson().toJson(actividad);
        Toast.makeText(createActivity, "...", Toast.LENGTH_SHORT).show();

        typeResponseService = PresentadorCreateActivity.CREATE_ACTIVITY;
        APIConnection.getConnection("https://apirest-mjuceda.c9users.io/actividad", WebRequest.POSTRequest, this, jsonString);
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
        Toast.makeText(createActivity, "¡GUARDADO CON EXITO!", Toast.LENGTH_LONG).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(createActivity, MainActivity.class);
                createActivity.startActivity(i);
            }
        }, 2000);
    }

    private void onPrepareViewResponseService(String response) {

        //We get the whole DB in order to get all elements and avoid several call to API Rest
        createActivity.setObjectJson(new Gson().fromJson(response, ObjectJson.class));
        // DEVUELVE EL ARRAY DE PROFESORES
        createActivity.setProfesoresArray(getArrayNombreProfesores());
        // DEVUELVE EL ARRAY DE GRUPOS
        createActivity.setGruposArray(getArrayNombreGrupos());
    }

    private String[] getArrayNombreProfesores() {

        ObjectJson objectJson = createActivity.getObjectJson();
        List<String> nombreProfesores = new ArrayList<>();

        for (Profesor profesor : objectJson.getProfesor()) {
            nombreProfesores.add(profesor.getNombre());
        }

        Collections.sort(nombreProfesores);
        return nombreProfesores.toArray(new String[nombreProfesores.size()]);
    }

    private String[] getArrayNombreGrupos() {

        ObjectJson objectJson = createActivity.getObjectJson();
        List<String> nombreGrupos = new ArrayList<>();

        for (Grupo grupo : objectJson.getGrupo()) {
            nombreGrupos.add(grupo.getNombre());
        }

        Collections.sort(nombreGrupos);
        return nombreGrupos.toArray(new String[nombreGrupos.size()]);
    }

    private List<Integer> devIdProfesor() {

        List<Profesor> NOMBREPRFESOR = createActivity.getObjectJson().getProfesor();
        List<Integer> idProfesores = new ArrayList<>();

        for (int i = 0; i < NOMBREPRFESOR.size(); i++) {
            for (int j = 0; j < createActivity.getNomProfesores().size(); j++) {
                if ((NOMBREPRFESOR.get(i).getNombre()).equalsIgnoreCase(createActivity.getNomProfesores().get(j))) {
                    idProfesores.add(NOMBREPRFESOR.get(i).getId());
                }
            }
        }

        return idProfesores;
    }

    private List<Integer> devIdGrupo() {

        List<Grupo> NOMBREGRUPO = createActivity.getObjectJson().getGrupo();
        List<Integer> idGrupos = new ArrayList<>();

        for (int i = 0; i < NOMBREGRUPO.size(); i++) {
            for (int j = 0; j < createActivity.getNomGrupos().size(); j++) {
                if ((NOMBREGRUPO.get(i).getNombre()).equalsIgnoreCase(createActivity.getNomGrupos().get(j))) {
                    idGrupos.add(NOMBREGRUPO.get(i).getId());
                }
            }
        }

        return idGrupos;
    }
}
