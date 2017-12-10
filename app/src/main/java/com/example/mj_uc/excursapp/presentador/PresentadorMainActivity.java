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
import com.example.mj_uc.excursapp.tools.Tools;
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

        List<Album> albumOrdenadoFecha = Tools.getAlbumsOfActividades(objectJsons.getActividad(), objectJsons);

        mainActivity.getAlbumList().addAll(albumOrdenadoFecha);

        mainActivity.getAdapter().notifyDataSetChanged();
    }
}
