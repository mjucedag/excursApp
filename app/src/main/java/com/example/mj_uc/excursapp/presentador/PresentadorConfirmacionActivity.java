package com.example.mj_uc.excursapp.presentador;


import android.content.Intent;
import android.widget.Toast;

import com.example.mj_uc.excursapp.contrato.ContratoConfirmacionActivity;
import com.example.mj_uc.excursapp.contrato.ContratoVistaImagenes;
import com.example.mj_uc.excursapp.tools.Tools;
import com.example.mj_uc.excursapp.vista.ConfirmacionActivity;
import com.example.mj_uc.excursapp.vista.CreateActivity;
import com.example.mj_uc.excursapp.vista.VistaImagenes;


/**
 * The type Presentador confirmacion activity.
 */
public class PresentadorConfirmacionActivity implements ContratoConfirmacionActivity.Presentador {

    private ContratoConfirmacionActivity.Vista vista;

    @Override
    public void setVista(ContratoConfirmacionActivity.Vista vista) {
        this.vista = vista;
    }

    @Override
    public void confirmImage() {

        ConfirmacionActivity confirmacionActivity = (ConfirmacionActivity) vista;

        Toast.makeText(confirmacionActivity, "Imagen seleccionada", Toast.LENGTH_LONG).show();

        Class intentClass = CreateActivity.class;
        if(!Tools.isEmpty(confirmacionActivity.getClassName())){
            try {
                intentClass = Class.forName(confirmacionActivity.getClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        Intent i = new Intent(confirmacionActivity, intentClass);
        i.putExtra("idImg", confirmacionActivity.getId());
        i.putExtra("nombre", confirmacionActivity.getNom());
        i.putExtra("tituloAct", confirmacionActivity.getTitulo());
        i.putExtra("lugar", confirmacionActivity.getLugar());
        i.putExtra("direccion", confirmacionActivity.getDir());
        i.putExtra("profesores", confirmacionActivity.getNomGuardados());
        i.putExtra("grupos", confirmacionActivity.getNomGrupos());
        i.putExtra("fecha", confirmacionActivity.getFechaGuardada());
        i.putExtra("horaSalida", confirmacionActivity.getHoraSalida());
        i.putExtra("horaLlegada", confirmacionActivity.getHoraLlegada());
        i.putExtra("descripcion", confirmacionActivity.getDescrip());
        i.putExtra("idActividad", confirmacionActivity.getIdActividad());

        confirmacionActivity.startActivity(i);
    }

    @Override
    public void cancelImage() {

        ConfirmacionActivity confirmacionActivity = (ConfirmacionActivity) vista;

        Intent volver = new Intent(confirmacionActivity, VistaImagenes.class);
        volver.putExtra("tituloAct", confirmacionActivity.getTitulo());
        volver.putExtra("lugar", confirmacionActivity.getLugar());
        volver.putExtra("direccion", confirmacionActivity.getDir());
        volver.putExtra("profesores", confirmacionActivity.getNomGuardados());
        volver.putExtra("grupos", confirmacionActivity.getNomGrupos());
        volver.putExtra("fecha", confirmacionActivity.getFechaGuardada());
        volver.putExtra("horaSalida", confirmacionActivity.getHoraSalida());
        volver.putExtra("horaLlegada", confirmacionActivity.getHoraLlegada());
        volver.putExtra("descripcion", confirmacionActivity.getDescrip());
        volver.putExtra("idActividad", confirmacionActivity.getIdActividad());
        volver.putExtra("className", confirmacionActivity.getClassName());
        confirmacionActivity.startActivity(volver);
    }
}
