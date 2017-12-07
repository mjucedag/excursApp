package com.example.mj_uc.excursapp.contrato;


import com.example.mj_uc.excursapp.MainActivity;

import java.util.List;

public interface ContratoDrawerMenu {

    interface Presentador {
        void onGroupQueryMenuSelected();
        void onDateQueryMenuSelected();
        void onHelpMenuSelected();
        void setMainActivity(MainActivity mainActivity);
        void createAlertDialog(List<CharSequence> list, String title);
    }
}
