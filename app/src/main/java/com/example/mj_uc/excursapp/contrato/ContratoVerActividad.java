package com.example.mj_uc.excursapp.contrato;


public interface ContratoVerActividad {

    interface Vista {

    }

    interface Presentador {
        void setVista(Vista vista);
        void populateFields();
    }
}
