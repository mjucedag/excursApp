package com.example.mj_uc.excursapp.contrato;


/**
 * The interface Contrato vista imagenes.
 */
public interface ContratoVistaImagenes {

    /**
     * The interface Vista.
     */
    interface Vista {}

    /**
     * The interface Presentador.
     */
    interface Presentador {


        /**
         * Sets vista.
         *
         * @param vista the vista
         */
        void setVista(Vista vista);


        /**
         * Go to confirmacion activity.
         *
         * @param i the index selected.
         */
        void goToConfirmacionActivity(int i);
    }
}
