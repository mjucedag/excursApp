package com.example.mj_uc.excursapp.presentador;


import android.content.Intent;
import android.os.Bundle;

import com.example.mj_uc.excursapp.R;
import com.example.mj_uc.excursapp.contrato.ContratoVistaImagenes;
import com.example.mj_uc.excursapp.tools.Constants;
import com.example.mj_uc.excursapp.vista.ConfirmacionActivity;
import com.example.mj_uc.excursapp.vista.VistaImagenes;


/**
 * The type Presentador vista imagenes.
 */
public class PresentadorVistaImagenes implements ContratoVistaImagenes.Presentador {

    private ContratoVistaImagenes.Vista vista;

    @Override
    public void setVista(ContratoVistaImagenes.Vista vista) {
        this.vista = vista;
    }

    @Override
    public void goToConfirmacionActivity(int i) {

        VistaImagenes vistaImagenesActivity = (VistaImagenes) vista;

        Intent intent = new Intent(vistaImagenesActivity, ConfirmacionActivity.class);
        intent.putExtra("nombresImg", Constants.nombresImg[i]);
        intent.putExtra("idImg", Constants.idImg[i]);
        intent.putExtra("tituloAct", vistaImagenesActivity.getTitulo());
        intent.putExtra("lugar", vistaImagenesActivity.getLugar());
        intent.putExtra("direccion", vistaImagenesActivity.getDir());
        intent.putExtra("profesores", vistaImagenesActivity.getNomGuardados());
        intent.putExtra("grupos", vistaImagenesActivity.getNomGrupos());
        intent.putExtra("fecha", vistaImagenesActivity.getFechaGuardada());
        intent.putExtra("horaSalida", vistaImagenesActivity.getHoraSalida());
        intent.putExtra("horaLlegada", vistaImagenesActivity.getHoraLlegada());
        intent.putExtra("descripcion", vistaImagenesActivity.getDescrip());
        intent.putExtra("className", vistaImagenesActivity.getClassName());
        intent.putExtra("idActividad", vistaImagenesActivity.getIdActividad());

        vistaImagenesActivity.startActivity(intent);
    }
}
