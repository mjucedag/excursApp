package com.example.mj_uc.excursapp.contrato;


public interface ContratoVerActividad {

    interface Vista {
        void initVerActividad();
    }

    interface Presentador {
        void setVista(Vista vista);
        void prepareView();
        void deleteActividad(final Integer id);
        void doPhotoPrint();
    }
}
