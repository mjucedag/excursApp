package com.example.mj_uc.excursapp.presentador;


import android.content.Intent;
import android.widget.Toast;

import com.example.mj_uc.excursapp.contrato.ContratoConfirmacionActivity;
import com.example.mj_uc.excursapp.contrato.ContratoHelpActivity;
import com.example.mj_uc.excursapp.vista.ConfirmacionActivity;
import com.example.mj_uc.excursapp.vista.CreateActivity;
import com.example.mj_uc.excursapp.vista.Help.Help;
import com.example.mj_uc.excursapp.vista.Help.HelpCreate;
import com.example.mj_uc.excursapp.vista.Help.HelpDelete;
import com.example.mj_uc.excursapp.vista.Help.HelpEdit;
import com.example.mj_uc.excursapp.vista.Help.HelpPrint;
import com.example.mj_uc.excursapp.vista.Help.HelpSearch;
import com.example.mj_uc.excursapp.vista.VistaImagenes;


/**
 * The type Presentador confirmacion activity.
 */
public class PresentadorHelpActivity implements ContratoHelpActivity.Presentador {

    private ContratoHelpActivity.Vista vista;
    private Help helpActivity;

    @Override
    public void setVista(ContratoHelpActivity.Vista vista) {
        this.vista = vista;
        helpActivity = (Help) this.vista;
    }

    @Override
    public void goToHelpCreate() {
        Intent i = new Intent(helpActivity, HelpCreate.class);
        helpActivity.startActivity(i);
    }

    @Override
    public void goToHelpSearch() {
        Intent i = new Intent(helpActivity, HelpSearch.class);
        helpActivity.startActivity(i);
    }

    @Override
    public void goToHelpPrint() {
        Intent i = new Intent(helpActivity, HelpPrint.class);
        helpActivity.startActivity(i);
    }

    @Override
    public void goToHelpEdit() {
        Intent i = new Intent(helpActivity, HelpEdit.class);
        helpActivity.startActivity(i);
    }

    @Override
    public void goToHelpDelete() {
        Intent i = new Intent(helpActivity, HelpDelete.class);
        helpActivity.startActivity(i);
    }
}
