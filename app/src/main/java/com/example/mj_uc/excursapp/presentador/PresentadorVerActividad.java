package com.example.mj_uc.excursapp.presentador;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mj_uc.excursapp.MainActivity;
import com.example.mj_uc.excursapp.R;
import com.example.mj_uc.excursapp.tools.Tools;
import com.example.mj_uc.excursapp.apirest.APIConnection;
import com.example.mj_uc.excursapp.apirest.WebRequest;
import com.example.mj_uc.excursapp.apirest.WebResponse;
import com.example.mj_uc.excursapp.contrato.ContratoVerActividad;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.Grupo;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.example.mj_uc.excursapp.modelo.Pojo.Profesor;
import com.example.mj_uc.excursapp.vista.EditarActividad;
import com.example.mj_uc.excursapp.vista.VerActividad;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Presentador ver actividad.
 */
public class PresentadorVerActividad implements ContratoVerActividad.Presentador, WebResponse{

    /**
     * The constant PREPARE_VIEW.
     */
    public static final int PREPARE_VIEW = 1;
    /**
     * The constant DELETE_ACTIVIDAD.
     */
    public static final int DELETE_ACTIVIDAD = 2;

    private ContratoVerActividad.Vista vista;
    private VerActividad verActividad;
    private Integer idActividad;

    private Integer typeResponseService;

    @Override
    public void setVista(ContratoVerActividad.Vista vista) {
        this.vista = vista;
    }

    @Override
    public void prepareView() {

        verActividad = (VerActividad) vista;

        Intent intent = verActividad.getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            idActividad = b.getInt("ID_ACTIVIDAD");

            typeResponseService = PresentadorVerActividad.PREPARE_VIEW;
            APIConnection.getConnection("https://apirest-mjuceda.c9users.io/db", WebRequest.GETRequest, this);
        }
    }

    @Override
    public void deleteActividad(final Integer id) {

        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(verActividad, android.app.AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("Borrar actividad").setMessage("¿Desea borrar esta actividad?").setIcon(R.drawable.papelera_celeste).setPositiveButton("Borrar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                typeResponseService = PresentadorVerActividad.DELETE_ACTIVIDAD;
                APIConnection.getConnection("https://apirest-mjuceda.c9users.io/actividad/" + id, WebRequest.DELETERequest, PresentadorVerActividad.this);
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        android.app.AlertDialog alert = builder.create();
        alert.show();
        Button b = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        b.setTextColor(verActividad.getResources().getColor(R.color.azulAlertDialog));
        b.setTextSize(20);
        Button b1 = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        b1.setTextColor(verActividad.getResources().getColor(R.color.azulAlertDialog));
        b1.setTextSize(20);
    }

    @Override
    public void onResponseService(String response) {

        switch (typeResponseService){
            case PREPARE_VIEW:
                onPrepareViewResponseService(response);
                break;
            case DELETE_ACTIVIDAD:
                onDeleteActividadResponseService(response);
                break;
        }
    }

    private void onDeleteActividadResponseService(String response) {

        Snackbar snackbar = Snackbar.make(verActividad.findViewById(R.id.verActividad), "La actividad se ha borrado correctamente", Snackbar.LENGTH_SHORT);
        snackbar.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(verActividad, MainActivity.class);
                verActividad.startActivity(i);
            }
        }, 2000);
    }

    private void onPrepareViewResponseService(String response) {

        ObjectJson objectJsons =  new Gson().fromJson(response, ObjectJson.class);

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


    private Bitmap takeScreenshot() {
        try {
            // crear un bitmap con la captura de pantalla
            View v1 = verActividad.getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            return bitmap;
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
        return null;
    }

    public void doPhotoPrint() {
        PrintHelper photoPrinter = new PrintHelper(verActividad);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT); // CAPTURA DE TODA LA PANTALLA
        Bitmap bitmap = takeScreenshot();
        photoPrinter.printBitmap("actividadPDP.jpg - test print", bitmap);
    }

    @Override
    public void editActividad(final Integer idActividad) {
        Intent i = new Intent(verActividad, EditarActividad.class);
        i.putExtra("idActividad", idActividad);
        verActividad.startActivity(i);
    }
}
